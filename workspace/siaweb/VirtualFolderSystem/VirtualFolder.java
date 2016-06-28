package idk.imgarchive.base.VirtualFolderSystem;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.codec.language.Soundex;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xmlutils.DateUtils;
import xmlutils.XMLReadHelper;
import xmlutils.XmlWriter;

public class VirtualFolder {
	public enum FolderList {
		All, FoldersOnly, ImagesOnly
	}

	protected static class VirtualFolderInfo {

		Date createdDate;
		String filename = null;
		Date lastModified;
		String name = null;

	}

	protected static final int MAX_FOLDER_DEPTH = 10;

	public static VirtualFolder createRoot() {
		return new VirtualFolder(VFSystem.getRootPath(), "root", "00000000.xml");
	}

	public static VirtualFolder readRoot() throws RuntimeException, IOException, ParseException {
		return new VirtualFolder(VFSystem.getRootPath(), "00000000.xml");
	}

	Date createdDate;
	String filename = null;
	ArrayList<VirtualFolderInfo> folderList = new ArrayList<VirtualFolderInfo>();
	ArrayList<String> imageList = new ArrayList<String>();
	Date lastModified;

	String name = null;

	private final String part1 = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" + "<Envelope>\n" + "<Body>\n"
			+ "	<ResponseStatus>\n" + " 		<StatusCode>Success</StatusCode>\n" + "	</ResponseStatus>\n";
	private final String part2 = "</Body>\n" + "</Envelope>\n";
	String systemFolder = null;
	// VirtualFolder parentFolder = null;
	VirtualFolder thisFolder = null;

	public VirtualFolder(final String systemFolder, final String filename) throws RuntimeException, IOException, ParseException {
		thisFolder = this;
		this.systemFolder = systemFolder;
		this.filename = filename;
		readFolder();
	}

	private VirtualFolder(final String systemFolder, final String name, final String filename) {
		thisFolder = this;
		createdDate = new Date();
		lastModified = new Date();
		this.systemFolder = systemFolder;
		this.name = name;
		this.filename = filename;
		writeFolder();
	}

	private void add(final VirtualFolder item) throws RuntimeException, IOException, ParseException {
		final VirtualFolderInfo virtualFolderInfo = readFolderInfo(item.filename);
		folderList.add(virtualFolderInfo);
		writeFolder();
	}

	public boolean addImage(final String imagePath) {
		for (final String item : imageList) {
			if (item.matches(imagePath) == true) {
				return false;
			}
		}
		imageList.add(imagePath);
		writeFolder();
		return true;
	}

	public VirtualFolder createFolder(final String name) throws RuntimeException, IOException, ParseException {
		final String sysFolder = VFSystem.getRootPath();
		VirtualFolder virtualFolder = null;

		if (findName(name) != null) {
			// name exists
			return null;
		}
		final String soundexName = new Soundex().soundex(name);
		int count = 0;
		File file = null;
		String tempName = null;
		do {
			tempName = soundexName + String.format("%04X", count) + ".xml";
			file = new File(sysFolder, tempName);
			count++;

		} while (file.exists() == true);
		virtualFolder = new VirtualFolder(sysFolder, name, tempName);

		add(virtualFolder);
		return virtualFolder;
	}

	protected boolean deleteFolderFile() {
		final File file = new File(VFSystem.getRootPath(), filename);
		return file.delete();
	}

	private VirtualFolderInfo findName(final String name) {
		for (final VirtualFolderInfo vfi : folderList) {
			if (vfi.name.matches(name) == true) {
				return vfi;
			}
		}
		return null;
	}

	public boolean folderExists(final String name) throws IOException {
		if (findName(name) != null) {
			return true;
		}
		return false;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getFilename() {
		return filename;
	}

	public VirtualFolder getFolder(final String newPath) throws RuntimeException, IOException, ParseException {
		final VirtualFolderInfo virtualFolderInfo = findName(newPath);
		if (virtualFolderInfo == null) {
			throw new RuntimeException("Folder \"" + newPath + "\" not found");
		}
		final VirtualFolder virtualFolder = new VirtualFolder(VFSystem.getRootPath(), virtualFolderInfo.filename);
		return virtualFolder;
	}

	/**
	 * @return the folderList
	 */
	public final ArrayList<VirtualFolderInfo> getFolderList() {
		return folderList;
	}

	/**
	 * @return the imageList
	 */

	public final ArrayList<String> getImageList() {
		return imageList;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public String getName() {
		return name;
	}

	String list(final String path, final FolderList type) {
		final StringBuilder builder = new StringBuilder();
		builder.append(part1);
		builder.append("		<FolderOf>" + path + "</FolderOf>\n");
		if (type == FolderList.ImagesOnly || type == FolderList.All) {
			builder.append("	<ImageListing>\n");
			for (final String item : imageList) {
				builder.append("		<Image>" + item + "</Image>\n");
			}
			builder.append("	</ImageListing>\n");

			builder.append("	<NumberOfImagess>");
			builder.append(imageList.size());
			builder.append("</NumberOfImagess>\n");
		}
		if (type == FolderList.FoldersOnly || type == FolderList.All) {
			builder.append("	<FolderListing>\n");
			for (final VirtualFolderInfo item : folderList) {
				builder.append("		<Folder><Name>" + item.name + "</Name><Mod>" + DateUtils.formatDDMMYYYY(item.lastModified)
						+ "</Mod></Folder>\n");
			}
			builder.append("	</FolderListing>\n");

			builder.append("	<NumberOfFolders>");
			builder.append(folderList.size());
			builder.append("</NumberOfFolders>\n");
		}

		builder.append(part2);
		return builder.toString();

	}

	private void readFolder() throws RuntimeException, IOException, ParseException {
		final File inputFile = new File(systemFolder, filename);
		if (inputFile.exists() == false) {
			throw new RuntimeException(filename + ": Missing  file");
		}

		final Document xmpDocument = XMLReadHelper.decode(inputFile);
		final Element rootElement = xmpDocument.getDocumentElement();
		name = XMLReadHelper.stringValue(rootElement, "Name", true);
		lastModified = XMLReadHelper.dateValue(rootElement, "LastModified", true);
		createdDate = XMLReadHelper.dateValue(rootElement, "Created", true);

		final Element folderElement = XMLReadHelper.elementValue(rootElement, "FolderList", true);
		final NodeList pmList = folderElement.getChildNodes();
		for (int i = 0; i < pmList.getLength(); i++) {
			Element fstNmElmnt = null;
			i = XMLReadHelper.getNextElement(pmList, i);
			if (i == -1) {
				break;
			}
			if ((fstNmElmnt = (Element) pmList.item(i)).getNodeName().matches("Folder") == true) {
				// String name = XMLReadHelper.stringValue(fstNmElmnt, "Name",
				// true);
				// String filename = XMLReadHelper.stringValue(fstNmElmnt,
				// "Filename", true);
				final String filename = fstNmElmnt.getTextContent();
				final VirtualFolderInfo virtualFolderInfo = readFolderInfo(filename);
				folderList.add(virtualFolderInfo);
			}
		}

		final Element imageListElement = XMLReadHelper.elementValue(rootElement, "ImageList", true);
		final NodeList imgList = imageListElement.getChildNodes();
		for (int i = 0; i < imgList.getLength(); i++) {
			Element fstNmElmnt = null;
			i = XMLReadHelper.getNextElement(imgList, i);
			if (i == -1) {
				break;
			}
			if ((fstNmElmnt = (Element) imgList.item(i)).getNodeName().matches("Image") == true) {
				final String imagePath = fstNmElmnt.getTextContent();
				imageList.add(imagePath);
			}
		}

		return;
	}

	private VirtualFolderInfo readFolderInfo(final String childFilename) throws RuntimeException, IOException, ParseException {
		final VirtualFolderInfo virtualFolderInfo = new VirtualFolderInfo();
		final File inputFile = new File(systemFolder, childFilename);

		if (inputFile.exists() == false) {
			throw new RuntimeException(filename + ": Missing  file");
		}
		virtualFolderInfo.filename = childFilename;

		final Document xmpDocument = XMLReadHelper.decode(inputFile);
		final Element rootElement = xmpDocument.getDocumentElement();
		virtualFolderInfo.name = XMLReadHelper.stringValue(rootElement, "Name", true);

		virtualFolderInfo.lastModified = XMLReadHelper.dateValue(rootElement, "LastModified", true);
		virtualFolderInfo.createdDate = XMLReadHelper.dateValue(rootElement, "Created", true);

		return virtualFolderInfo;
	}

	protected boolean removeFolder(final String name) {
		final VirtualFolderInfo vfi = findName(name);
		if (vfi == null) {
			return false;
		}
		if (folderList.remove(vfi) == false) {
			throw new RuntimeException("Cannot remove \"" + name + "\"");
		}
		writeFolder();
		return true;
	}

	private void writeFolder() throws RuntimeException {

		final XmlWriter xmlWriter = new XmlWriter(systemFolder, filename);
		xmlWriter.startElement("VirtualFolder");
		xmlWriter.startElement("Name");
		xmlWriter.element(name);
		xmlWriter.endElement("Name");
		xmlWriter.startElement("LastModified");
		String dateString = DateUtils.formatDDMMYYYY(lastModified);
		xmlWriter.element(dateString);
		xmlWriter.endElement("LastModified");
		xmlWriter.startElement("Created");
		dateString = DateUtils.formatDDMMYYYY(createdDate);
		xmlWriter.element(dateString);
		xmlWriter.endElement("Created");
		xmlWriter.startElement("FolderList");
		for (final VirtualFolderInfo item : folderList) {
			xmlWriter.addAttribute("Name", item.name);
			xmlWriter.startElement("Folder");
			xmlWriter.element(item.filename);
			xmlWriter.endElement("Folder");
		}
		xmlWriter.endElement("FolderList");
		xmlWriter.startElement("ImageList");
		for (final String item : imageList) {
			xmlWriter.addAttribute("Id", item);
			xmlWriter.startElement("Image");
			xmlWriter.element(item);
			xmlWriter.endElement("Image");
		}
		xmlWriter.endElement("ImageList");
		xmlWriter.endElement("VirtualFolder");
		xmlWriter.close();
	}

}
