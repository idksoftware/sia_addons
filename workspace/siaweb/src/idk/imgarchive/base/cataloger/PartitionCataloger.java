package idk.imgarchive.base.cataloger;

import idk.archiveutils.FileInfo;
import idk.archiveutils.FileInfoList;
import idk.archiveutils.FileTypePicInclude;
import idk.archiveutils.FileUtils;
import idk.archiveutils.ImageFilename;
import idk.config.ConfigInfo;
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
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlutils.DateUtils;
import xmlutils.XMLWriteHelper;

/**
 * 
 * This class provides two files, the partition catalog file and the partition
 * link files
 * 
 * @author Iain Ferguson
 * 
 */
public class PartitionCataloger {

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

	static void makeCatalogFile(final CatalogInstanceEnviroment catalogEnviroment, final PartitionAddress item) {
		// make Catalog .html
		final String xmlFile = catalogEnviroment.getXmlInstancePath() + File.separator + "MCF"
				+ Workspace.makePartition(item.getCurrent()) + ".xml";

		final String htmlFilePath = catalogEnviroment.getHtmlInstancePath() + File.separator + "CAT"
				+ Workspace.makePartition(item.getCurrent()) + ".html";
		Log.info("Creating \"" + htmlFilePath + "\"");

		try {
			XmlHtml.makeHtml("partition_catalog.xsl", xmlFile, htmlFilePath);
		} catch (final FileNotFoundException e) {
			// Log.IOException("filename", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// FileUtils.copyIfNotExist(styleSheetDestPath,
			// styleSheetConfigPath);
			Css.Copy("partition_catalog.css", catalogEnviroment.getCssInstancePath());
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This makes the Contents file in the cluster. (MCF Master Contacts sheet)
	 * 
	 * @param catalogEnviroment
	 * @param item
	 */
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
		boolean res = true;
		
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
		if ((new File(partitionEnviroment.getCssPath(), "html.css")).exists() == false) {
			try {
				FileUtils.copyFile(new File(ConfigInfo.getCssPath(), "html.css"),
					new File(partitionEnviroment.getCssPath(), "html.css"));
			} catch (final IOException e1) {
				Log.IOException("html.css", e1);
			}
		}
		final XMLLinkFile xmlLinkFile = new XMLLinkFile(catalogEnviroment, fileCount, fileSizeTotal);
		while (count <= imageLast) {
			final ImageAddress ia = cursor.getCurrent();

			if (processFile(pCDocument, ia) < 0) {
				cursor.next();
				count++;
				continue;
			}
			fileCount = xmlLinkFile.getFileCount();
			fileSizeTotal = xmlLinkFile.getFileSizeTotal();

			String xmlPagePath = partitionEnviroment.getXmlPath() + File.separator
					+ ImageAddress.makeAddressString(ia.getCurrent()) + ".xml";
			String htmlPagePath = partitionEnviroment.getImagePath() + File.separator
					+ ImageAddress.makeAddressString(ia.getCurrent()) + ".html";

			try {
				XmlHtml.makeHtml("partition_image.xsl", xmlPagePath, htmlPagePath);
			} catch (final FileNotFoundException e) {
				Log.FileNotFound(e.getMessage());
				res = false;
			}

			// End image

			xmlPagePath = partitionEnviroment.getXmlPath() + File.separator + ImageAddress.makeAddressString(ia.getCurrent())
					+ ".xml";
			htmlPagePath = partitionEnviroment.getHtmlPath() + File.separator + ImageAddress.makeAddressString(ia.getCurrent())
					+ ".html";

			try {
				XmlHtml.makeHtml("html.xsl", xmlPagePath, htmlPagePath);
			} catch (final FileNotFoundException e) {
				Log.FileNotFound(e.getMessage());
				res = false;
			}

			// End detail

			cursor.next();
			count++;
		}

		String xmlFile = catalogEnviroment.getXmlInstancePath() + File.separator + "MCF"
				+ Workspace.makePartition(item.getCurrent()) + ".xml";
		writeXmlFile(xmlFile);
		makeCatalogFile(catalogEnviroment, item);

		xmlFile = catalogEnviroment.getXmlPath() + File.separator + "MCF" + Workspace.makePartition(item.getCurrent()) + ".xml";
		writeXmlFile(xmlFile);
		makeContentsFile(catalogEnviroment, item);

		return res;
	}

	protected int processFile(final Document pCDocument, final ImageAddress ia) throws ParseException, IOException {

		// Reads the manifast. This will give the sizes of the real images. This
		// is required
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

		// This goes round the real images
		for (final FileInfo item : fileInfoList) {

			fileSizeTotal += item.getSize();
		}

		final String relPath = PartitionAddress.makeAddressString(ia.getCurrentPartition()) + File.separator
				+ ImageAddress.makeAddressString(ia.getCurrent());

		final String previewPath = catalogEnviroment.getBaseInstanceWwwPath() + File.separator + relPath;

		final File previewFolder = new File(previewPath);
		final File[] files = previewFolder.listFiles();
		// This goes round the preview images
		for (final File item : files) {

			if (fileTypePicInclude.accept(item.getName()) == true) {
				Element itemPath = null;
				final ImageFilename imgFilename = new ImageFilename(item.getName());
				strImagePath = relPath + File.separator + item.getName();
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

		final XMLLinkFile xmlLinkFile = new XMLLinkFile(catalogEnviroment, fileCount, fileSizeTotal);
		xmlLinkFile.makeLinkFile(ia);

		final Element labelElem = doc.createElement("Label");
		labelElem.setTextContent(xmlLinkFile.getCurrentLabel());
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

	public void writeXmlFile(final String filename) throws FileNotFoundException, IOException {

		final Long fileCountObj = fileCount;
		fileCountElement.setTextContent(fileCountObj.toString());

		final Long fileSizeTotalObj = fileSizeTotal;
		fileSizeTotalElement.setTextContent(fileSizeTotalObj.toString());
		XMLWriteHelper.writeXmlFile(pCDocument, filename);

	}

	public void writeXmlLinkFile(final Document doc, final String filename) throws FileNotFoundException, IOException {

		final Long fileCountObj = fileCount;
		fileCountElement.setTextContent(fileCountObj.toString());

		final Long fileSizeTotalObj = fileSizeTotal;
		fileSizeTotalElement.setTextContent(fileSizeTotalObj.toString());
		final String fullPath = catalogEnviroment.getPartitionEnviroment().getXmlPath() + File.separator + filename;

		XMLWriteHelper.writeXmlFile(doc, fullPath);

	}
}
