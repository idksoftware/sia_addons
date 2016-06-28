package idk.imgarchive.base.cataloger;

import idk.archiveutils.FileInfo;
import idk.archiveutils.FileInfoList;
import idk.archiveutils.FileTypePicInclude;
import idk.archiveutils.ImageFilename;
import idk.imgarchive.base.hooks.Css;
import idk.imgarchive.base.hooks.XmlHtml;
import idk.imgarchive.base.log4j.Log;
import idk.imgarchive.base.manifest.ImageManifest;
import idk.imgarchive.base.manifest.ManifestManager;
import idk.imgarchive.base.workspacemanager.ImageAddress;
import idk.imgarchive.base.workspacemanager.ImageCursor;
import idk.imgarchive.base.workspacemanager.PartitionAddress;
import idk.imgarchive.base.workspacemanager.Workspace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlutils.DateUtils;

/**
 * 
 * This class provides two files, the partition catalog file and the partition
 * link files
 * 
 * @author Iain Ferguson
 * 
 */
public class JournalPartitionCataloger {

	CatalogInstanceEnviroment catalogEnviroment = null;
	// private String xmlPath = null;
	private Element catalogueElement = null;
	private Element catalogueItemsElement = null;
	private PartitionAddress currentItem = null;
	private final Date dateCreated = new Date();
	private long fileCount = 0;
	private Element fileCountElement = null;
	private long fileSizeTotal = 0;
	private Element fileSizeTotalElement = null;

	private long imageCount = 0;
	private long imageFirst = 0;
	private long imageLast = 0;
	// Partition Contents document
	private Document pCDocument = null;

	/**
	 * @return the currentItem
	 */
	public final PartitionAddress getCurrentItem() {
		return currentItem;
	}

	/**
	 * @return the dateCreated
	 */
	public final Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @return the fileCount
	 */
	public final long getFileCount() {
		return fileCount;
	}

	/**
	 * @return the fileSizeTotal
	 */
	public final long getFileSizeTotal() {
		return fileSizeTotal;
	}

	/**
	 * @return the imageCount
	 */
	public final long getImageCount() {
		return imageCount;
	}

	/**
	 * @return the imageFirst
	 */
	public final long getImageFirst() {
		return imageFirst;
	}

	/**
	 * @return the imageLast
	 */
	public final long getImageLast() {
		return imageLast;
	}

	static void makeContentsFile(final CatalogInstanceEnviroment catalogEnviroment, final PartitionAddress item) {
		// make File Info manifest .html
		final String xmlFile = catalogEnviroment.getXmlPath() + File.separator + "MCF" + Workspace.makePartition(item.getCurrent())
				+ ".xml";
		// final String styleSheetDestPath = catalogEnviroment.getCssPath() +
		// File.separator + "partition_catalog.css";

		final String htmlFilePath = catalogEnviroment.getHtmlPath() + File.separator + "FI"
				+ Workspace.makePartition(item.getCurrent()) + ".html";
		Log.info("Creating \"" + htmlFilePath + "\"");

		// final String styleSheetConfigPath = ConfigInfo.getCssPath() +
		// File.separator + "partition_file.css";

		try {
			XmlHtml.makeHtml("partition_file.xsl", xmlFile, htmlFilePath);
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Css.Copy("partition_catalog.css", catalogEnviroment.getCssPath());
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean process(final PartitionAddress item) throws ParseException, IOException, ParserConfigurationException {

		final ImageCursor cursor = Workspace.createImageCursor();
		imageLast = item.getLastImage();
		imageFirst = item.getFirstImage();
		imageCount = imageLast - imageFirst;
		imageCount++; // count the current one being added
		currentItem = item;

		int count = (int) imageFirst;

		// Create instance of DocumentBuilderFactory
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// Get the DocumentBuilder
		DocumentBuilder parser = null;
		try {
			parser = factory.newDocumentBuilder();
		} catch (final ParserConfigurationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// Create blank DOM Document

		pCDocument = parser.newDocument();

		// Insert the root Catalogue element node
		catalogueElement = pCDocument.createElement("Catalogue");
		pCDocument.appendChild(catalogueElement);

		// Insert the root Catalogue element node

		final Element clusterPathElement = pCDocument.createElement("ClusterPath");
		String clusterpath = new File(item.getPath()).getParent() + File.separator;
		clusterpath = clusterpath.replace('\\', '/');
		clusterPathElement.setTextContent(clusterpath);
		catalogueElement.appendChild(clusterPathElement);

		// Insert the root partition node
		final Element partitionElement = pCDocument.createElement("PartitionID");
		partitionElement.setTextContent(Workspace.makePartition(item.getCurrent()));
		catalogueElement.appendChild(partitionElement);

		// Insert the root partition node
		final Element dateElement = pCDocument.createElement("DateCreated");

		final String dateStr = DateUtils.formatDDMMYYYY(dateCreated);
		dateElement.setTextContent(dateStr);
		catalogueElement.appendChild(dateElement);

		// Insert the root partition node
		final Element firstImageElement = pCDocument.createElement("FirstImage");
		firstImageElement.setTextContent(Long.toString(imageFirst));
		catalogueElement.appendChild(firstImageElement);

		final Element lastImageElement = pCDocument.createElement("LastImage");
		lastImageElement.setTextContent(Long.toString(imageLast));
		catalogueElement.appendChild(lastImageElement);

		final Element imageCountElement = pCDocument.createElement("CountImage");
		imageCountElement.setTextContent(Long.toString(imageCount));
		catalogueElement.appendChild(imageCountElement);

		// Insert the root partition node
		fileCountElement = pCDocument.createElement("NumberOfFiles");
		fileCountElement.setTextContent("Unknown");
		catalogueElement.appendChild(fileCountElement);

		// Insert the root partition node
		fileSizeTotalElement = pCDocument.createElement("TotalSize");
		fileSizeTotalElement.setTextContent("Unknown");
		catalogueElement.appendChild(fileSizeTotalElement);

		// Insert the root Catalogue element node
		catalogueItemsElement = pCDocument.createElement("CatalogueItems");
		catalogueElement.appendChild(catalogueItemsElement);

		if (cursor.absolute(imageFirst) == false) {
			return false;
		}

		catalogEnviroment = CatalogInstanceEnviroment.make(item);
		final CatalogInstanceEnviroment.PartitionEnviroment partitionEnviroment = catalogEnviroment.getPartitionEnviroment();
		// try {
		// FileUtils.copyFile(new File(ConfigInfo.getCssPath(), "html.css"), new
		// File(partitionEnviroment.getCssPath(),
		// "html.css"));
		// } catch (final IOException e1) {
		// Log.IOException("html.css", e1);
		// }

		while (count <= imageLast) {
			final ImageAddress ia = cursor.getCurrent();

			if (processFile(pCDocument, ia) < 0) {
				count++;
				continue;
			}
			fileCount = 0;
			fileSizeTotal = 0;

			/*
			 * String xmlPagePath = partitionEnviroment.getXmlPath() +
			 * File.separator + ImageAddress.makeAddressString(ia.getCurrent())
			 * + ".xml"; String htmlPagePath =
			 * partitionEnviroment.getImagePath() + File.separator +
			 * ImageAddress.makeAddressString(ia.getCurrent()) + ".html";
			 * 
			 * try { XmlHtml.makeHtml("partition_image.xsl", xmlPagePath,
			 * htmlPagePath); } catch (final FileNotFoundException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 * 
			 * 
			 * 
			 * 
			 * xmlPagePath = partitionEnviroment.getXmlPath() + File.separator +
			 * ImageAddress.makeAddressString(ia.getCurrent()) + ".xml";
			 * htmlPagePath = partitionEnviroment.getHtmlPath() + File.separator
			 * + ImageAddress.makeAddressString(ia.getCurrent()) + ".html";
			 * 
			 * try { XmlHtml.makeHtml("html.xsl", xmlPagePath, htmlPagePath); }
			 * catch (final FileNotFoundException e) { // TODO Auto-generated
			 * catch block Log.FileNotFoundException("html.xsl", e); }
			 */
			// End detail

			cursor.next();
			count++;
		}

		final String xmlFile = catalogEnviroment.getXmlPath() + File.separator + "MCF" + Workspace.makePartition(item.getCurrent())
				+ ".xml";
		writeXmlFile(xmlFile);

		makeContentsFile(catalogEnviroment, item);

		return true;
	}

	protected int processFile(final Document pCDocument, final ImageAddress ia) throws ParseException, IOException {

		ImageManifest imageManifest = ManifestManager.getImageManifest(ia);
		if (imageManifest == null) {
			return -1;
		}
		final FileInfoList fileInfoList = imageManifest.getList();
		fileCount += fileInfoList.size();

		final FileTypePicInclude fileTypePicInclude = new FileTypePicInclude();
		String strImagePath = null;
		final String strHtmlPath = "www/html/" + ImageAddress.makeAddressString(ia.getCurrent()) + ".html";

		Node rootNode = null;
		final Document doc = imageManifest.getDocument();
		final NodeList list = doc.getElementsByTagName("Manifest");
		final Element rootElement = (Element) list.item(0);

		final Element imagePath = doc.createElement("ImagePath");
		rootElement.insertBefore(imagePath, rootElement.getFirstChild());

		for (final FileInfo item : fileInfoList) {

			if (fileTypePicInclude.accept(item.getFileName()) == true) {
				Element itemPath = null;
				final ImageFilename imgFilename = new ImageFilename(item.getFileName());
				strImagePath = ia.makeRelativeAddressString() + File.separator + item.getFileName();
				switch (imgFilename.getPostfixType()) {
				case Thumbnail:
					itemPath = doc.createElement("Thumbnail");
					break;
				case Tiny:
					itemPath = doc.createElement("Tiny");
					break;
				case Small:
					itemPath = doc.createElement("Small");
					break;
				case Medium:
					itemPath = doc.createElement("Medium");
					break;
				case Large:
					itemPath = doc.createElement("Large");
					break;
				case Full:
					itemPath = doc.createElement("Full");
					break;
				}
				itemPath.setTextContent(strImagePath);
				imagePath.appendChild(itemPath);
			}

			fileSizeTotal += item.getSize();
		}

		if (strHtmlPath != null) {
			final Element wepPagePath = doc.createElement("WebPage");
			wepPagePath.setTextContent(strHtmlPath);
			rootElement.insertBefore(wepPagePath, rootElement.getFirstChild());
		}

		final Element documentID = doc.createElement("Id");
		final String strDocID = String.format("%08X", ia.getCurrent());
		documentID.setTextContent(strDocID);
		rootElement.insertBefore(documentID, rootElement.getFirstChild());

		final Element labelElem = doc.createElement("Label");
		// labelElem.setTextContent(xmlLinkFile.getCurrentLabel());
		rootElement.insertBefore(labelElem, rootElement.getFirstChild());

		if (rootElement != null) {
			final NodeList rootlist = pCDocument.getElementsByTagName("CatalogueItems");
			rootNode = rootlist.item(0);
			final Node importNode = pCDocument.importNode(rootElement, true);
			rootNode.appendChild(importNode);
		}

		imageManifest = null;
		return 0;
	}

	public void writeXmlFile(final String filename) {

		final Long fileCountObj = fileCount;
		fileCountElement.setTextContent(fileCountObj.toString());

		final Long fileSizeTotalObj = fileSizeTotal;
		fileSizeTotalElement.setTextContent(fileSizeTotalObj.toString());

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filename);
		} catch (final FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// XERCES 1 or 2 additionnal classes.
		final OutputFormat of = new OutputFormat("XML", "ISO-8859-1", true);
		of.setIndent(1);
		of.setIndenting(true);
		// of.setDoctype(null,"users.dtd");
		final XMLSerializer serializer = new XMLSerializer(fos, of);
		// As a DOM Serializer
		try {
			serializer.asDOMSerializer();
			serializer.serialize(pCDocument.getDocumentElement());
			fos.close();
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
