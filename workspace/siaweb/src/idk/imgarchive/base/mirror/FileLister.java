package idk.imgarchive.base.mirror;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import idk.archiveutils.FileInfo;
import idk.archiveutils.FileInfoList;
import idk.imgarchive.base.cataloger.PartitionCataloger;
import idk.imgarchive.base.log4j.Log;
import idk.imgarchive.base.manifest.ManifestManager;
import idk.imgarchive.base.manifest.PartitionInfo;
import idk.imgarchive.base.manifest.PartitionInfoList;
import idk.imgarchive.base.workspacemanager.PartitionAddress;
import idk.imgarchive.base.workspacemanager.PartitionCursor;
import idk.imgarchive.base.workspacemanager.Workspace;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import xmlutils.DateUtils;
import xmlutils.XMLReadHelper;
import xmlutils.XMLUtil;

/**
 * 
 * @author Iain Ferguson
 */
public class FileLister {

	private Date backupFromDate = null;
	private final File configFolder;
	private int configFolderLength = 0;
	private final FileInfoList fileList = new FileInfoList();
	private PartitionInfoList partitionList = new PartitionInfoList();
	private final boolean systemDetail = false;
	private long totalPartitionSize = 0;
	private long totalSystemSize = 0;

	public FileLister(final String rf) {
		configFolder = new File(rf);
		configFolderLength = configFolder.getAbsolutePath().length();
	}

	public FileLister(final String rf, final Date date) {
		configFolder = new File(rf);
		backupFromDate = date;
		configFolderLength = configFolder.getAbsolutePath().length();
	}

	public FileLister(final String rf, final String dateStr) {
		configFolder = new File(rf);

		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.UK);
		try {
			backupFromDate = sdf.parse(dateStr);
		} catch (final ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		configFolderLength = configFolder.getAbsolutePath().length();
	}

	void addFile(final File file) {
		final String relPath = file.getAbsolutePath().substring(configFolderLength + "\\SYS".length());
		addFile(file, relPath);
	}

	void addFile(final File file, final String relPath) {
		// long d = file.lastModified();
		final long size = file.length();
		if (backupFromDate == null) {
			final Date date = new Date(file.lastModified());
			final FileInfo fileInfo = new FileInfo(relPath, date, size, -1, null);
			fileList.add(fileInfo);
		} else {
			if (backupFromDate.getTime() < file.lastModified()) {
				// final SimpleDateFormat sdf = new
				// SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.UK);
				final Date date = new Date(file.lastModified());
				// System.out.println("Path: " + file + sdf.format(date));
				final FileInfo fileInfo = new FileInfo(relPath, date, size, -1, null);
				fileList.add(fileInfo);
				totalSystemSize += size;
			}
		}
	}

	private PartitionInfo filePartition(final long idx) {
		final String idxStr = String.format("%08X", idx);
		for (final PartitionInfo item : partitionList) {
			if (idxStr.equalsIgnoreCase(item.getId())) {
				return item;
			}
		}
		return null;
	}

	public long getDirSize(final File dir) {
		long size = 0;
		if (dir.isFile()) {
			size = dir.length();
		} else {
			final File[] subFiles = dir.listFiles();

			for (final File file : subFiles) {
				if (file.isFile()) {
					size += file.length();
				} else {
					size += getDirSize(file);
				}

			}
		}
		return size;
	}

	public long getDirSizeInMegabytes(final File dir) {
		return getDirSize(dir) / 1024 / 1024;
	}

	public FileInfoList getFileList() {
		return fileList;
	}

	public PartitionInfoList getPartitionList() {
		return partitionList;
	}

	public boolean Process() throws NumberFormatException, IOException, ParseException, TransformerException,
			ParserConfigurationException {

		partitionList = ManifestManager.getPartitionInfoList(null);

		processPartitions();
		/*
		 * Only do this if system needs backing up String sysPath =
		 * ConfigInfo.getSystemPath(); ProcessSys(sysPath);
		 */
		return true;
	}

	private int ProcessDataFileFolder(final File dataFileFolder) {
		// boolean isError = false;

		final String[] dataFiles = dataFileFolder.list();
		for (final String dataFile2 : dataFiles) {
			// This is the pic folder
			final File dataFile = new File(dataFileFolder.getAbsolutePath(), dataFile2);
			addFile(dataFile);
		}
		// If isError is true put in the message to the user that this partition
		// must be restored

		return 0;
	}

	private int ProcessDataFolder(final File rootDataFolder) {
		// boolean isError = false;

		final String[] dataFolders = rootDataFolder.list();
		// for (int i = 0; i < dataFolders.length; i++)
		for (final String dataFolder : dataFolders) {

			// This is the pic folder

			final File dataFileFolder = new File(rootDataFolder.getAbsolutePath(), dataFolder);
			ProcessDataFileFolder(dataFileFolder);
			System.out.println("Path: " + dataFileFolder);

		}
		// If isError is true put in the message to the user that this partition
		// must be restored

		return 0;
	}

	private void processPartitions() throws IOException, ParseException, ParserConfigurationException {
		final PartitionCursor pcursor = Workspace.createPartitionCursor();
		PartitionInfo partitionInfo = null;
		for (final PartitionAddress item : pcursor) {
			if ((partitionInfo = filePartition(item.getCurrent())) != null) {
				final String xmlPath = item.getPath() + File.separator + "catalog.xml";

				// Make sure partition catalog is upto date.
				final PartitionCataloger partitionCataloger = new PartitionCataloger();
				System.out.println("partition " + item);
				partitionCataloger.process(item);
				partitionCataloger.writeXmlFile(xmlPath);

				Document doc = null;
				try {
					doc = XMLUtil.parse(xmlPath);
				} catch (final IOException e2) {
					Log.IOException(xmlPath, e2);
				} catch (final ParseException e2) {
					Log.ParseException(xmlPath, e2);
				}
				final Element root = doc.getDocumentElement();
				root.normalize();
				final long size = XMLReadHelper.longValue(root, "TotalSize", true, false);
				totalPartitionSize += size;
				partitionInfo.setSize(size);
				doc = null;
			}
		}

	}

	public boolean ProcessSys(final String path) {
		final File folder = new File(path);
		final String[] files = folder.list();

		for (final String file : files) {
			// Process data folder
			if (file.compareTo("data") == 0) {
				final File dataFolder = new File(folder, file);
				if (dataFolder.isDirectory() == false) {
					return false;
				}
				ProcessDataFolder(dataFolder);
			} else {
				final File dataFile = new File(folder, file);
				addFile(dataFile);
			}

		}
		return true;
	}

	public Document writeXmlDocument() {

		Element e = null;
		Node n = null;
		// Document (Xerces implementation only).
		final Document xmldoc = new DocumentImpl();
		// Root element.
		final Element root = xmldoc.createElement("BackupJounal");

		Element m = xmldoc.createElement("CreateDate");
		String dateString = DateUtils.formatDDMMYYYY(new Date());
		Node mt = xmldoc.createTextNode(dateString);
		m.appendChild(mt);
		root.appendChild(m);

		m = xmldoc.createElement("TotalPartitionSize");
		Long longStr = Long.valueOf(totalPartitionSize);
		mt = xmldoc.createTextNode(longStr.toString());
		m.appendChild(mt);
		root.appendChild(m);

		m = xmldoc.createElement("TotalSystemSize");
		longStr = Long.valueOf(totalSystemSize);
		mt = xmldoc.createTextNode(longStr.toString());
		m.appendChild(mt);
		root.appendChild(m);

		m = xmldoc.createElement("TotalSize");
		longStr = Long.valueOf(totalSystemSize + totalPartitionSize);
		mt = xmldoc.createTextNode(longStr.toString());
		m.appendChild(mt);
		root.appendChild(m);

		final Element partitionsEle = xmldoc.createElement("Partitions");
		root.appendChild(partitionsEle);

		for (final PartitionInfo value : partitionList) {
			e = xmldoc.createElement("Item");
			// e.setAttributeNS(null, "ID", "test");

			n = xmldoc.createTextNode(value.getId());
			e.appendChild(n);
			m = xmldoc.createElement("ModDate");
			dateString = DateUtils.formatDDMMYYYY(value.getLastModified());
			mt = xmldoc.createTextNode(dateString);
			m.appendChild(mt);
			e.appendChild(m);
			// partitionsEle.appendChild(e);
			m = xmldoc.createElement("Size");
			final Long longObj = value.getSize();
			mt = xmldoc.createTextNode(longObj.toString());
			m.appendChild(mt);
			e.appendChild(m);
			partitionsEle.appendChild(e);
		}
		if (systemDetail == true) {
			final Element fileListEle = xmldoc.createElement("System");
			root.appendChild(fileListEle);
			for (final FileInfo value : fileList) {
				e = xmldoc.createElement("Item");
				// e.setAttributeNS(null, "ID", "test");

				n = xmldoc.createTextNode(value.path);
				e.appendChild(n);
				m = xmldoc.createElement("ModDate");
				mt = xmldoc.createTextNode(DateUtils.formatDDMMYYYY(value.getLastModified()));
				m.appendChild(mt);
				e.appendChild(m);
				m = xmldoc.createElement("Size");
				final Long longObj = value.getSize();
				mt = xmldoc.createTextNode(longObj.toString());
				m.appendChild(mt);
				e.appendChild(m);
				fileListEle.appendChild(e);
			}
		}
		xmldoc.appendChild(root);
		return xmldoc;
	}

	public void writeXmlFile(final String filename) throws IOException {

		final Document xmldoc = writeXmlDocument();

		FileOutputStream fos = null;

		fos = new FileOutputStream(filename);

		// XERCES 1 or 2 additionnal classes.
		final OutputFormat of = new OutputFormat("XML", "ISO-8859-1", true);
		of.setIndent(1);
		of.setIndenting(true);
		// of.setDoctype(null,"users.dtd");
		final XMLSerializer serializer = new XMLSerializer(fos, of);
		// As a DOM Serializer

		serializer.asDOMSerializer();
		serializer.serialize(xmldoc.getDocumentElement());
		fos.close();

	}

}
