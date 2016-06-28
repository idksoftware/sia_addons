package idk.imgarchive.base.cataloger;

import idk.archiveutils.FileTypeExifInclude;
import idk.archiveutils.FileTypeInfoInclude;
import idk.archiveutils.FileTypePicInclude;
import idk.archiveutils.ImageFilename;



import idk.imgarchive.base.cataloger.CatalogInstanceEnviroment.PartitionEnviroment;
import idk.imgarchive.base.metadata.IdentityInfo;
import idk.imgarchive.base.metadata.MetadataFactory;
import idk.imgarchive.base.workspacemanager.ImageAddress;
import idk.imgarchive.base.workspacemanager.ImageCursor;
import idk.imgarchive.base.workspacemanager.Workspace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import xmlutils.DateUtils;
import xmlutils.XMLUtil;
import xmlutils.XMLWriteHelper;

public class WwwXMLLinkFile {
	PartitionEnviroment partitionEnviroment = null;
	private final FileTypeExifInclude fileTypeExifInclude = new FileTypeExifInclude();
	private final FileTypeInfoInclude fileTypeInfoInclude = new FileTypeInfoInclude();
	private final FileTypePicInclude fileTypePicInclude = new FileTypePicInclude();
	private long fileSizeTotal = 0;
	private long fileCount = 0;
	private String currentLabel = null;
	private String basePath = null;

	public WwwXMLLinkFile(final CatalogInstanceEnviroment catalogEnviroment, final long fileCount, final long fileSizeTotal) {
		partitionEnviroment = catalogEnviroment.getPartitionEnviroment();
		basePath = catalogEnviroment.getBasePath();
		this.fileCount = fileCount;
		this.fileSizeTotal = fileSizeTotal;
	}

	/**
	 * @return the currentLabel
	 */
	public final String getCurrentLabel() {
		return currentLabel;
	}

	/**
	 * @return the fileSizeTotal
	 */
	public final long getFileSizeTotal() {
		return fileSizeTotal;
	}

	/**
	 * @return the fileCount
	 */
	public final long getFileCount() {
		return fileCount;
	}

	protected int makeLinkFile(final ImageAddress ia) throws IOException, ParseException {
		final Date dateCreated = new Date();
		Document doc = null;

		// Create instance of DocumentBuilderFactory
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// Get the DocumentBuilder
		DocumentBuilder parser;
		try {
			parser = factory.newDocumentBuilder();
		} catch (final ParserConfigurationException e) {
			throw new ParseException(e.getMessage(), 0);
		}
		// Create blank DOM Document

		doc = parser.newDocument();

		// Insert the root Catalogue element node
		final Element catalogueElement = doc.createElement("LinkFile");
		doc.appendChild(catalogueElement);

		// Insert the root partition node
		final Element itemElement = doc.createElement("DateCreated");

		final String dateStr = DateUtils.formatDDMMYYYY(dateCreated);
		itemElement.setTextContent(dateStr);
		catalogueElement.appendChild(itemElement);

		Element elem = doc.createElement("Id");
		catalogueElement.appendChild(elem);
		final String item = ImageAddress.makeAddressString(ia.getCurrent());
		Node n = doc.createTextNode(item);
		elem.appendChild(n);

		// Path
		elem = doc.createElement("Path");
		catalogueElement.appendChild(elem);
		final File image = new File(ia.getPath());
		final String path = image.getParent();
		n = doc.createTextNode(path);
		elem.appendChild(n);

		// Partition
		elem = doc.createElement("Partition");
		catalogueElement.appendChild(elem);
		final int idx = image.getParent().lastIndexOf(File.separator);
		final String partition = image.getParent().substring(idx + 1);
		n = doc.createTextNode(partition);
		elem.appendChild(n);

		int res = processFolder(doc, catalogueElement, ia.getPath());
		res = processInfo(doc, catalogueElement, ia.getPath());
		processLinks(doc, catalogueElement, ia);
		writeXmlLinkFile(doc, ImageAddress.makeAddressString(ia.getCurrent()) + ".xml");
		return res;
	}

	protected int processFolder(final Document doc, final Element rootElement, final String f) {

		final File picFolder = new File(f);

		// This is the pic folder
		final File[] infoFiles = picFolder.listFiles(fileTypeInfoInclude);
		final File[] exifFiles = picFolder.listFiles(fileTypeExifInclude);
		final File[] picFiles = picFolder.listFiles(fileTypePicInclude);
		ProcessPaths(doc, rootElement, picFolder, infoFiles[0], exifFiles, picFiles);

		return 0;
	}

	protected int processInfo(final Document doc, final Element rootElement, final String f) throws IOException, ParseException {
		Element e = null;
		Node n = null;
		final File picFolder = new File(f);

		final File[] infofiles = picFolder.listFiles(new FileTypeInfoInclude());
		final File infofile = infofiles[0];
		final Document document = XMLUtil.parse(infofile.getAbsolutePath());
		
		final IdentityInfo identityInfo = MetadataFactory.getIdentityInfo(document);
		currentLabel = identityInfo.getLabel();
		if (currentLabel == null) {
			currentLabel = MetadataFactory.getFileInfo(document).getFileName();
		}
		// This is the pic folder
		final Element imageInfoElem = doc.createElement("ImageInfo");
		e = doc.createElement("Label");
		rootElement.appendChild(imageInfoElem);
		n = doc.createTextNode(currentLabel);
		e.appendChild(n);
		imageInfoElem.appendChild(e);
		return 0;
	}

	protected int processLinks(final Document doc, final Element rootElement, final ImageAddress ia) {
		Element e = null;
		Node n = null;

		final ImageCursor cursor = Workspace.createImageCursor();
		cursor.absolute(ia.getCurrent());
		// This is the pic folder
		final Element linksElem = doc.createElement("Links");
		rootElement.appendChild(linksElem);
		ImageAddress curr = null;
		if ((curr = cursor.next()) != null) {
			e = doc.createElement("Next");
			linksElem.appendChild(e);

			final long id = curr.getCurrent();
			final String itemStr = ImageAddress.makeAddressString(id);
			n = doc.createTextNode(itemStr);
			e.appendChild(n);
		}

		cursor.absolute(ia.getCurrent());

		if ((curr = cursor.previous()) != null) {
			e = doc.createElement("Prev");
			linksElem.appendChild(e);
			final long id = curr.getCurrent();
			final String itemStr = ImageAddress.makeAddressString(id);
			n = doc.createTextNode(itemStr);
			e.appendChild(n);
		}
		return 0;
	}

	private int ProcessPaths(final Document doc, final Element rootElement, final File image, final File infoFile,
			final File[] exifFiles, final File[] picFiles) {

		Element e = null;
		Node n = null;

		// This is the pic folder
		final Element pathList = doc.createElement("PathList");
		rootElement.appendChild(pathList);

		// Info file
		e = doc.createElement("InfoFile");
		n = doc.createTextNode(infoFile.getAbsolutePath());
		e.appendChild(n);
		pathList.appendChild(e);

		// Info file
		e = doc.createElement("ExifFiles");
		for (final File file : exifFiles) {
			final Element pe = doc.createElement("ExifFile");
			final Node pn = doc.createTextNode(file.getAbsolutePath());
			pe.appendChild(pn);
			e.appendChild(pe);
		}
		pathList.appendChild(e);
		// Pic files
		e = doc.createElement("PicFiles");
		/*
		 * Absolute file names for (File file : picFiles) { Element pe = null;
		 * doc.createElement("PicFile"); ImageFilename item = new
		 * ImageFilename(file.getName()); pe =
		 * doc.createElement(item.getImageSize().toString());
		 * 
		 * 
		 * Node pn = doc.createTextNode(file.getAbsolutePath());
		 * pe.appendChild(pn); e.appendChild(pe); }
		 */
		// reletive paths from cluser/DCIM
		for (final File file : picFiles) {
			Element pe = null;
			doc.createElement("PicFile");
			final ImageFilename item = new ImageFilename(file.getName());
			pe = doc.createElement(item.getImageSize().toString());

			final String absPath = file.getAbsolutePath();
			final String relPath = absPath.substring(basePath.length() + 1);
			final Node pn = doc.createTextNode(relPath);
			pe.appendChild(pn);
			e.appendChild(pe);
		}
		pathList.appendChild(e);
		return 0;
	}

	public void writeXmlLinkFile(final Document doc, final String filename) {
		/*
		 * final Long fileCountObj = fileCount;
		 * fileCountElement.setTextContent(fileCountObj.toString());
		 * 
		 * final Long fileSizeTotalObj = fileSizeTotal;
		 * fileSizeTotalElement.setTextContent(fileSizeTotalObj.toString());
		 */
		final String fullPath = partitionEnviroment.getXmlPath() + File.separator + filename;

		try {
			XMLWriteHelper.writeXmlFile(doc, fullPath);
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
