package idk.imgarchive.base.manifest;

import static org.w3c.dom.Node.ELEMENT_NODE;
import idk.archiveutils.FileInfo;
import idk.archiveutils.FileInfoList;
import idk.imgarchive.base.log4j.Log;
import idk.imgarchive.base.mirror.MirrorCopy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlutils.DateUtils;
import xmlutils.XMLUtil;
import xmlutils.XMLWriteHelper;

public abstract class Manifest extends MirrorCopy {

	public static class LogItem {
		String id = null;

		String msg = null;

		public String getId() {
			return id;
		}

		public String getMsg() {
			return msg;
		}
	}

	static int lastIdxFound = 0;
	// private ManagedInfo managedInfo = new ManagedInfo();
	protected static ArrayList<LogItem> logList = new ArrayList<LogItem>();
	protected static ArrayList<LogItem> remedyList = new ArrayList<LogItem>();
	protected static String schemaPath = null;

	public static ArrayList<LogItem> getLogList() {
		return logList;
	}

	public static ArrayList<LogItem> getRemedyList() {
		return remedyList;
	}

	public static final String makeImageManifestFilename(final long id) {
		return String.format("IMF%08X.xml", id);
	}

	public static final String makeImageManifestFilename(final String id) {
		return "IMF" + id + ".xml";
	}

	public static final String makeMasterManifestFilename() {
		return String.format("MMF.xml");
	}

	public static final String makePartitionManifestFilename(final long id) {
		return String.format("PMF%08X.xml", id);
	}

	public static void setSchemaPath(final String schemaPath) {
		Manifest.schemaPath = schemaPath;
	}

	protected ArrayList<Boolean> checkList = new ArrayList<Boolean>();
	protected long curId = -1;
	protected File dataFolder = null; // where the manifast file is stored.
	protected File[] files = null;

	protected File folder = null; // where the image files are stored.

	protected String id = "";

	protected boolean isError = false;

	protected FileInfoList list = new FileInfoList();
	protected String rootXMLTagName = null;
	protected Schema schema = null;

	protected boolean validManifastFound = false;

	protected Document xmpDocument;

	public Manifest() {
		lastIdxFound = 0;
	}

	protected void AddLogItem(final String id, final String msg) {
		final LogItem li = new LogItem();
		li.id = id;
		li.msg = msg;
		logList.add(li);
	}

	protected void AddRemedyItem(final String id, final String msg) {
		final LogItem li = new LogItem();
		li.id = id;
		li.msg = msg;
		remedyList.add(li);
	}

	protected abstract void createFile();

	public void createFile(final String name, final String filename) {
		xmpDocument = new DocumentImpl();
		// Root element.
		final Element root = xmpDocument.createElement(name);
		xmpDocument.appendChild(root);
		final Element manifest = xmpDocument.createElement("Manifest");
		root.appendChild(manifest);

		try {
			XMLWriteHelper.writeXmlFile(xmpDocument, filename);
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected boolean decode(final File inputFile) throws ParseException {
		try {
			xmpDocument = XMLUtil.parse(inputFile.getAbsolutePath());
		} catch (final IOException e) {
			System.out.println("IO Exception " + e.getMessage());
			return false;
		} catch (final ParseException e) {
			Log.ParseException(inputFile.getName(), e);
		}

		return true;
	}

	protected FileInfo find(final String name) {
		for (int i = lastIdxFound; i < list.size(); i++) {
			if (list.get(i).fileName.matches(name)) {
				lastIdxFound = i;
				checkList.set(i, Boolean.valueOf(true));
				return list.get(i);
			}
		}
		return null;
	}

	protected int findIdx(final String name) {
		for (int i = lastIdxFound; i < list.size(); i++) {
			if (list.get(i).fileName.matches(name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @return the xmpDocument
	 */
	public final Document getDocument() {
		return xmpDocument;
	}

	/**
	 * @return the list
	 */
	public final FileInfoList getList() {
		return list;
	}

	protected boolean imageItemExists() {
		return folder.exists();
	}

	protected boolean imageItemValid() {
		return validManifastFound;
	}

	protected void Init(final String pathToImageFolder, final String pathToSystemFolder, final String id) {
		folder = new File(pathToImageFolder + File.separator + id);
		if (folder.isDirectory() == false) {
			AddLogItem(id, "Error: " + pathToImageFolder + " not a directory");
			return;
		}
		dataFolder = new File(pathToSystemFolder + File.separator + id);

		this.id = id;
	}

	public void LoadSchema(final String name) {

		try {
			final String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
			final SchemaFactory factory = SchemaFactory.newInstance(language);
			schema = factory.newSchema(new File(name));
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	protected boolean logMissing(final String aId) {
		for (int i = 0; i < checkList.size(); i++) {
			if (checkList.get(i).booleanValue() == false) {
				final String filename = list.get(i).fileName;
				AddLogItem(aId, "Error: Missing \"" + filename + "\" file");
				return false;
			}
		}
		return true;
	}

	protected void readFolder() {

		final String[] fileList = folder.list();
		for (final String element : fileList) {

			if (element.startsWith(".") && element.startsWith(".lck")) {
				// ignore lock files
				continue;
			}
			FileInfo fileInfo = null;
			try {
				fileInfo = new FileInfo(folder.getAbsolutePath(), element);
			} catch (final NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (final IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			list.add(fileInfo);
		}
	}

	boolean readManifest() {
		final Element rootElement = xmpDocument.getDocumentElement();
		rootElement.normalize();
		final Element manifestElement = XMLUtil.getElement(rootElement, "Manifest");
		if (manifestElement.hasChildNodes()) {
			final NodeList pmList = manifestElement.getChildNodes();
			final int n = pmList.getLength();
			for (int i = 0; i < n; i++) {
				final Node fileNode = pmList.item(i);

				final short type = fileNode.getNodeType();

				String name = null;
				long crc = 0;
				Date lastModified = null;
				long size = 0;
				String md5String = null;

				if (type == ELEMENT_NODE) {

					final Element Name = XMLUtil.getElement((Element) fileNode, "Name");
					if (Name != null) {
						name = Name.getTextContent();
					}
					final Element LastModified = XMLUtil.getElement((Element) fileNode, "LastModified");
					if (LastModified != null) {
						final String lastModifiedString = LastModified.getTextContent();
						try {
							lastModified = DateUtils.parseDDMMYYYY(lastModifiedString);
						} catch (final ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					final Element Size = XMLUtil.getElement((Element) fileNode, "Size");
					if (Size != null) {
						final String sizeString = Size.getTextContent();
						size = Long.parseLong(sizeString, 10);
					}

					final Element Crc = XMLUtil.getElement((Element) fileNode, "Crc");
					if (Crc != null) {
						final String crcString = Crc.getTextContent();
						crc = Long.parseLong(crcString, 10);
					}
					final Element Md5 = XMLUtil.getElement((Element) fileNode, "Md5");
					if (Md5 != null) {
						md5String = Md5.getTextContent();
					}

					final FileInfo fileInfo = new FileInfo(name, lastModified, size, crc, md5String);

					list.add(fileInfo);
					checkList.add(Boolean.valueOf(false));
				}
			}
		}
		return true;

	}

	public boolean readManifest(final File inputFile) throws ParseException {

		try {
			if (decode(inputFile) == false) {
				return false;
			}
		} catch (final ParseException e) {
			// TODO Auto-generated catch block
			Log.ParseException(inputFile.getName(), e);
		}
		return readManifest();
	}

	public boolean readManifest(final String path, final String file) throws ParseException {
		final File inputFile = new File(path, file);
		if (inputFile.exists() == false) {
			AddLogItem(id, "Error: \"" + inputFile.getName() + "\" Missing Manifest file");
			System.out.println("Error: \"" + inputFile.getName() + "\" Missing Manifest file");
			return false;
		}
		try {
			if (decode(inputFile) == false) {
				AddLogItem(id, "Error: Unable to parse Manifest");
				return false;
			}
		} catch (final ParseException e) {
			// TODO Auto-generated catch block
			Log.ParseException(inputFile.getName(), e);
		}
		return readManifest();
	}

	public void setError(final boolean isError) {
		this.isError = isError;
	}

	public void writeFile(final String name, final String filename) {
		xmpDocument = new DocumentImpl();
		// Root element.
		final Element root = xmpDocument.createElement(name);
		xmpDocument.appendChild(root);
		final Element manifest = xmpDocument.createElement("Manifest");
		root.appendChild(manifest);

		for (int i = 0; i < list.size(); i++) {
			final FileInfo fileInfo = list.get(i);
			final Element file = xmpDocument.createElement("File");
			manifest.appendChild(file);

			/* Name */
			Element e = xmpDocument.createElement("Name");
			e.appendChild(xmpDocument.createTextNode(fileInfo.fileName));
			file.appendChild(e);

			/* LastModified */
			e = xmpDocument.createElement("LastModified");

			final String dateString = DateUtils.formatDDMMYYYY(fileInfo.getLastModified());
			e.appendChild(xmpDocument.createTextNode(dateString));
			file.appendChild(e);

			e = xmpDocument.createElement("Size");
			final Long size = Long.valueOf(fileInfo.getSize());
			e.appendChild(xmpDocument.createTextNode(size.toString()));
			file.appendChild(e);

			e = xmpDocument.createElement("Crc");
			final Long crc = Long.valueOf(fileInfo.getCrc());
			e.appendChild(xmpDocument.createTextNode(crc.toString()));
			file.appendChild(e);

			e = xmpDocument.createElement("Md5");
			e.appendChild(xmpDocument.createTextNode(fileInfo.getMd5()));
			file.appendChild(e);

		}
		// String filename = masterFolder.getAbsolutePath() + File.separator +
		// "MMF.xml";
		try {
			XMLWriteHelper.writeXmlFile(xmpDocument, filename);
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
