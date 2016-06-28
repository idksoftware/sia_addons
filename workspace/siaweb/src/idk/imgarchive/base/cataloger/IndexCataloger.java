package idk.imgarchive.base.cataloger;

import idk.archiveutils.FileUtils;
import idk.config.ConfigInfo;
import idk.imgarchive.base.hooks.Css;
import idk.imgarchive.base.hooks.XmlHtml;
import idk.imgarchive.base.log4j.Log;
import idk.imgarchive.base.workspacemanager.ImageAddress;
import idk.imgarchive.base.workspacemanager.ImageCursor;
import idk.imgarchive.base.workspacemanager.PartitionAddress;
import idk.imgarchive.base.workspacemanager.Workspace;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import xmlutils.XMLUtil;
import xmlutils.XMLWriteHelper;

public class IndexCataloger extends CatalogerBase {

	// private Document catalogDocument;
	/*
	 * private String htmlPath = null; private File inputFile = null; protected
	 * ArrayList<String> itemPaths = null; private int itemsPerPage = 100;
	 * private String mainTag = null; private String rootFile = null; String
	 * tagName = null; protected ArrayList<String> titles = null;
	 * 
	 * protected String xmlRootPath = null;
	 */
	/**
	 * 
	 * @param xmlRootPath
	 *            - Path to target xml header file for the tag. i.e Author.xml
	 *            is the main header file containing all the author's with
	 *            images in the archive.
	 * @param file
	 *            - The xml header file for the tag. i.e Author.xml
	 * @param htmlPath
	 *            - html path where the output is to be sent.
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	// Note this needs the main xml files to be build, i.e Auther.xml
	public IndexCataloger(final String mainTag, final String xmlRootPath, final String file, final String htmlPath,
			final int itemsPerPage) throws IOException, ParseException {
		this.itemsPerPage = itemsPerPage;
		this.xmlRootPath = xmlRootPath;
		this.htmlPath = htmlPath;
		inputFile = new File(xmlRootPath, file);
		rootFile = file;
		if (inputFile.exists() == false) {
			throw new IOException("File " + xmlRootPath + " do'es not exist");
		}
		try {
			catalogDocument = XMLUtil.parse(inputFile.getAbsolutePath());
		} catch (final IOException e2) {
			Log.IOException(inputFile.getName(), e2);
		} catch (final ParseException e2) {
			Log.ParseException(inputFile.getName(), e2);
		}
		this.mainTag = mainTag;

	}

	/*
	 * public boolean createIndex() throws IOException, ParseException { // make
	 * Catalog .html
	 * 
	 * final String htmlFilePath = htmlPath + File.separator + CATALOGS +
	 * File.separator + tagName + ".html";
	 * 
	 * final String styleSheetConfigPath = ConfigInfo.getConfigPath() +
	 * File.separator + "AuthorIndex.css";
	 * 
	 * XmlHtml.makeHtml("AuthorIndex.xsl", xmlRootPath + File.separator +
	 * rootFile, htmlFilePath);
	 * 
	 * return Css.Copy("AuthorIndex.css", htmlPath); }
	 */
	/**
	 * 
	 * @param title
	 *            - i.e if Author's are being process will be the author name
	 *            "Iain Ferguson"
	 * @param path
	 *            - Relative path to items i.e author "Iain Ferguson" list of
	 *            images.
	 * @param imagesPerPage
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	@Override
	public boolean createItemPages(final String mainTag, final String secondaryTag, final String path, final int idx,
			final int imagesPerPage) throws IOException, ParseException {

		final ArrayList<String> images = new ArrayList<String>();
		final File itemFile = new File(xmlRootPath + File.separator + path + ".xml");
		Document xmlItemDocument = null;
		try {
			xmlItemDocument = XMLUtil.parse(itemFile.getAbsolutePath());
		} catch (final IOException e2) {
			Log.IOException(itemFile.getName(), e2);
		} catch (final ParseException e2) {
			Log.ParseException(itemFile.getName(), e2);
		}

		NodeList nodes = xmlItemDocument.getElementsByTagName("Index");
		if (nodes == null) {
			return false;
		}

		tagName = XMLUtil.getStringValue((Element) nodes.item(0), "Tag");
		final String tagItem = XMLUtil.getStringValue((Element) nodes.item(0), "Item");
		if (tagItem.matches(secondaryTag) == false) {
			return false;
		}
		nodes = xmlItemDocument.getElementsByTagName("Contents");
		final Node index = nodes.item(0);
		if (index.hasChildNodes() == false) {
			return true;
		}
		nodes = index.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Node item = nodes.item(i);
				if (item.getNodeName().matches("Image") == false) {
					return false;
				}
				final String name = item.getTextContent();
				images.add(name);
			}
		}
		try {
			new CalalogHeader(xmlRootPath, xmlRootPath + File.separator + path + "@header.xml", mainTag, secondaryTag);
		} catch (final ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		final Document indexDoc = creatIndexFile();
		int noOfPages = images.size() / imagesPerPage;
		noOfPages = images.size() % imagesPerPage != 0 ? noOfPages + 1 : noOfPages;
		final ImageCursor imageCursor = Workspace.createImageCursor();

		int imageCount = 0;
		int pageCount = 0;

		final String catalogFilePath = xmlRootPath + File.separator + path + "@";
		Integer pageIdx = pageCount + 1;
		String fileIdx = pageIdx.toString() + ".xml";
		Document catDoc = creatCatalogFile();
		Document imageDocument = null;

		// Process all the XML files. Note This needs to be COMPLETE before XSL
		// processing as
		// the Index XML files will not be completed untill the last image file
		// has been generated.
		for (final String item : images) {
			// ((images.size() % imagesPerPage) == 0)
			imageCursor.absolute(ImageAddress.makeLong(item));
			final ImageAddress imageAddress = imageCursor.getCurrent();
			final File partitionPath = new File(PartitionAddress.makeAddressString(imageAddress.getCurrentPartition()));

			final String xmlFilePath = htmlPath + File.separator + partitionPath + File.separator + "www" + File.separator + "xml"
					+ File.separator + ImageAddress.makeAddressString(imageAddress.getCurrent()) + ".xml";

			try {
				imageDocument = XMLUtil.parse(xmlFilePath);
			} catch (final IOException e) {
				System.out.println("IO Exception " + e.getMessage());
			}
			appendCatalogFile(catDoc, imageDocument);

			if (imageCount == imagesPerPage) {
				indexElem(catDoc, pageCount, noOfPages, xmlRootPath, Integer.valueOf(idx).toString());
				indexTag(indexDoc, pageCount, null);
				XMLWriteHelper.writeXmlFile(catDoc, catalogFilePath + fileIdx);

				// start new page
				pageCount++;
				pageIdx = pageCount + 1;
				fileIdx = pageIdx.toString() + ".xml";
				catDoc = creatCatalogFile();
				imageCount = 0;
			}
			imageCount++;
		}
		indexElem(catDoc, pageCount, noOfPages, xmlRootPath, Integer.valueOf(idx).toString());
		indexTag(indexDoc, pageCount, null);

		XMLWriteHelper.writeXmlFile(catDoc, catalogFilePath + fileIdx);
		XMLWriteHelper.writeXmlFile(indexDoc, catalogFilePath + "index.xml");
		settupCSS();
		for (int i = 0; i <= pageCount; i++) {
			pageIdx = i + 1;
			fileIdx = pageIdx.toString() + ".xml";
			readCatalogPage(mainTag, path, pageIdx, catalogFilePath + fileIdx);
		}
		return true;
	}

	/**
	 * @return the itemsPerPage
	 */
	public final int getItemsPerPage() {
		return itemsPerPage;
	}

	public boolean readCatalogPage(final String mainTag, final String curPath, final int pageIdx, final String xmlPath)
			throws IOException, ParseException {

		final String htmlFilePath = htmlPath + File.separator + CATALOGS + File.separator + mainTag + File.separator + curPath
				+ File.separator + "html";
		FileUtils.makePath(htmlFilePath);
		final String htmlFile = File.separator + ((Integer) pageIdx).toString() + ".html";
		XmlHtml.makeHtml("tagcatalog.xsl", xmlPath, htmlFilePath + htmlFile);

		return true;
	}

	/**
	 * This function reads the Tag Header file. For example Author.xml
	 * 
	 * @return
	 * @throws IOException
	 * @throws TransformerException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws ParseException
	 * @throws ParserConfigurationException
	 */
	/*
	 * public boolean readHeader() throws IOException, ParseException,
	 * ParserConfigurationException { titles = new ArrayList<String>();
	 * itemPaths = new ArrayList<String>();
	 * 
	 * NodeList nodes = catalogDocument.getElementsByTagName("Build"); nodes =
	 * catalogDocument.getElementsByTagName("Envelope"); if (nodes == null) {
	 * return false; } nodes = catalogDocument.getElementsByTagName("Head"); if
	 * (nodes == null) { return false; } tagName =
	 * XMLUtil.getStringValue((Element) nodes.item(0), "Tag"); nodes =
	 * catalogDocument.getElementsByTagName("Index"); final Node index =
	 * nodes.item(0); if (index.hasChildNodes() == false) { return true; } nodes
	 * = index.getChildNodes(); for (int i = 0; i < nodes.getLength(); i++) { if
	 * (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) { final Node item =
	 * nodes.item(i); if (item.getNodeName().matches("Item") == false) { return
	 * false; } final String name = item.getTextContent(); titles.add(name); } }
	 * nodes = catalogDocument.getElementsByTagName("Body"); final Node body =
	 * nodes.item(0); if (body.hasChildNodes() == false) { return true; } nodes
	 * = body.getChildNodes(); for (int i = 0; i < nodes.getLength(); i++) { if
	 * (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
	 * 
	 * final Node path = nodes.item(i); if (path.getNodeName().matches("Path")
	 * == false) { return false; } final String name = path.getTextContent();
	 * itemPaths.add(name); } } makeLinkFiles(); createIndex();
	 * 
	 * for (int i = 0; i < titles.size(); i++) { // readIndex(titles.get(i),
	 * itemPaths.get(i)); createItemPages(mainTag, titles.get(i),
	 * itemPaths.get(i), i + 1, itemsPerPage); } return true; }
	 */
	public boolean readIndex(final String title, final String path) throws IOException, ParseException {

		final String htmlFilePath = htmlPath + File.separator + path + ".html";
		final String xslPath = ConfigInfo.getConfigPath() + File.separator + "AuthorItem.xsl";
		XmlHtml.makeHtml(xslPath, xmlRootPath + File.separator + path + ".xml", htmlFilePath);

		return Css.Copy("AuthorItem.css", htmlPath);
	}

	public boolean settupCSS() throws IOException {
		final String styleSheetConfigPath = ConfigInfo.getConfigPath() + File.separator + "AuthorItem.css";
		final String styleSheetDestPath = htmlPath + File.separator + CATALOGS +
		// File.separator + mainTag +
		// File.separator + curPath +
				File.separator + "css";
		return Css.Copy("AuthorItem.css", styleSheetDestPath, "index.css");

	}
}
