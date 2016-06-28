package idk.imgarchive.base.manifest;

import static org.w3c.dom.Node.ELEMENT_NODE;
import idk.archiveutils.FileInfo;
import idk.imgarchive.base.log4j.Log;
import idk.imgarchive.base.workspacemanager.ImageAddress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlutils.DateUtils;
import xmlutils.XMLUtil;

/*
 * Note Adding a new Image will mean creating a new image manifest in the image directory, adding an entry in
 * the Partition manifest or creating a new one, and finally modifying the master manifest.   
 */

public class MasterManifest extends Manifest {

	static final String DOCROOT = "MasterInfo";

	private File masterFolder = null;
	File mmFile = null;
	private File partitionFolder = null;
	private final PartitionInfoList partitionList = new PartitionInfoList();

	public MasterManifest() {
	}

	public MasterManifest(final String systemPath, final long partition) throws IOException {
		partitionFolder = new File(systemPath);
		masterFolder = new File(systemPath);
		if (masterFolder.isDirectory() == false) {
			throw new IOException("Invalid Path: \"" + systemPath + "\"");
		}
		curId = partition;

		mmFile = new File(masterFolder, "MMF.xml");
		if (mmFile.exists() == false) {
			if (partitionFolder.isDirectory() == false) {
				return;
			}

			final String[] files = partitionFolder.list();
			if (files.length != 1 || files[0].equals(ImageAddress.makeAddressString(partition)) == false) // Only
																											// the
			// current
			// image
			// will
			// be in
			// the
			// partition
			{

				// Found partition manifests with no master manifest. so error,
				// but fix the problem.
				// return;
			}

			// New partition
			createFile();
		}
		// New partition
		// this.id = id;

	}

	void add(String path, final long curId) {
		path = path + File.separator + ImageAddress.makeAddressString(curId);
		try {
			final FileInfo fileInfo = new FileInfo(path, makePartitionManifestFilename(curId));
			fileInfo.readFile();
			list.add(fileInfo);
		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkManifest(final String systemPath) throws ParseException, NoSuchAlgorithmException, IOException {
		final File sysFolder = new File(systemPath);

		final String manifestName = "MMF.xml";
		final File mFile = new File(sysFolder.getAbsolutePath(), manifestName);
		if (mFile.exists() == false) {
			System.out.println(id + ": Missing Manifest file");
			return false;
		}
		readManifest(sysFolder.getAbsolutePath(), manifestName);

		final String[] fileList = sysFolder.list();
		for (final String element : fileList) {
			if (element.matches(manifestName)) {
				continue;
			}
			final String pfile = sysFolder.getAbsolutePath() + File.separator + element + File.separator + "PMF" + element + ".xml";
			final FileInfo fileInfo = new FileInfo(pfile);
			FileInfo manifestInfo = null;

			if ((manifestInfo = find("PMF" + element + ".xml")) == null) {
				System.out.println(element + ": Not in Manifest");
				continue;
			}
			if (manifestInfo.isEqual(fileInfo) != true) {
				System.out.println(fileInfo.fileName + ": Corrupt");
				continue;
			}
		}
		return true;
	}

	@Override
	public void createFile() {
		createFile(DOCROOT, masterFolder.getAbsolutePath() + File.separator + makeMasterManifestFilename());
	}

	public PartitionInfoList getPartitionList() {
		return partitionList;
	}

	void modify(String path, final long curId, final int idx) {
		path = path + File.separator + ImageAddress.makeAddressString(curId);

		FileInfo fileInfo = null;
		try {
			fileInfo = new FileInfo(path, makePartitionManifestFilename(curId));
		} catch (final NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		list.set(idx, fileInfo);
	}

	boolean readMasterManifest(final String path, final InfoFilter infoFilter) throws IOException, ParseException {
		boolean matched = false;
		Document xmpDocument = null;
		final File inputFile = new File(path);
		if (inputFile.exists() == false) {
			System.out.println(": Missing Master Manifest file");
			return false;
		}

		try {
			xmpDocument = XMLUtil.parse(inputFile.getAbsolutePath());
		} catch (final IOException e2) {
			Log.IOException(inputFile.getName(), e2);
		} catch (final ParseException e2) {
			Log.ParseException(inputFile.getName(), e2);
		}

		final Element rootElement = xmpDocument.getDocumentElement();
		if (rootElement.hasChildNodes()) {
			final NodeList pmList = rootElement.getChildNodes();
			final int n = pmList.getLength();
			for (int i = 0; i < n; i++) {
				final Node fileNode = pmList.item(i);
				final short type = fileNode.getNodeType();
				String name = null;
				Date lastModified = null;

				if (type == ELEMENT_NODE) {

					final Element Name = XMLUtil.getElement((Element) fileNode, "Name");
					if (Name != null) {
						final String tmpName = Name.getTextContent();
						name = tmpName.substring(3, tmpName.indexOf("."));
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
					if (infoFilter != null) {
						matched = false;
						if (infoFilter.accept(lastModified) == true) {
							matched = true;
						}
					} else {
						matched = true;
					}

					if (matched == true) {
						// String partitionPath = rootFolder.getAbsolutePath() +
						// File.separator + name;
						// size = getDirSize(new File(partitionPath));
						final PartitionInfo partitionInfo = new PartitionInfo();
						partitionInfo.setId(name);
						partitionInfo.setLastModified(lastModified);
						// partitionInfo.setSize(size);
						partitionList.add(partitionInfo);
					}
				}
			}
		}
		return true;

	}

	public boolean updateLastPartition() throws ParseException {
		final String fileName = makePartitionManifestFilename(curId);
		if (readManifest(mmFile.getParent(), mmFile.getName()) == false) {
			return false;
		}
		final int idx = findIdx(fileName);

		if (idx < 0) {
			add(partitionFolder.getAbsolutePath(), curId);
		} else {
			modify(partitionFolder.getAbsolutePath(), curId, idx);
		}
		writeFile();
		return true;
	}

	public void writeFile() {
		writeFile(DOCROOT, masterFolder.getAbsolutePath() + File.separator + "MMF.xml");
	}
}
