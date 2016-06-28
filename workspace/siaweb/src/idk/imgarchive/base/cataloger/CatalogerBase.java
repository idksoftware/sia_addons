package idk.imgarchive.base.cataloger;

import idk.imgarchive.base.hooks.Css;
import idk.imgarchive.base.hooks.XmlHtml;

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

public abstract class CatalogerBase {
	public static final String CATALOGS = "catalogs";
	protected Document catalogDocument;
	protected String htmlPath = null;
	protected File inputFile = null;
	protected ArrayList<String> itemPaths = null;
	protected int itemsPerPage = 100;
	protected String mainTag = null;
	protected String rootFile = null;
	String tagName = null;
	protected ArrayList<String> titles = null;

	protected String xmlRootPath = null;

	protected void appendCatalogFile(final Document catDoc, final Document imageDoc) {
		final Element documentElem = catDoc.getDocumentElement();
		final Element contents = XMLUtil.getElement(documentElem, "Contents");
		final NodeList list = imageDoc.getElementsByTagName("LinkFile");
		final Element element = (Element) list.item(0);
		final Node dup = catDoc.importNode(element, true);
		contents.appendChild(dup);
	}

	protected Document creatCatalogFile() {
		Document doc = null;

		try {
			doc = XMLWriteHelper.creatXMLFile();
		} catch (final ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final Element documentElem = doc.createElement("Document");
		doc.appendChild(documentElem);
		final Element contents = doc.createElement("Contents");
		documentElem.appendChild(contents);
		return doc;
	}

	protected Document creatIndexFile() {
		Document doc = null;
		try {
			doc = XMLWriteHelper.creatXMLFile();
		} catch (final ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final Element contentsElem = doc.createElement("Contents");
		doc.appendChild(contentsElem);

		return doc;
	}

	protected void indexElem(final Document doc, final int pageCount, final int noOfPages, final String documentPath,
			final String filename) {
		final Element contents = doc.getDocumentElement();
		final Element indexingElem = doc.createElement("Indexing");
		contents.appendChild(indexingElem);

		final Element thisPageNoElem = doc.createElement("ThisPageNo");
		Node textNode = doc.createTextNode(Integer.valueOf(pageCount + 1).toString());
		thisPageNoElem.appendChild(textNode);
		indexingElem.appendChild(thisPageNoElem);

		if (pageCount < noOfPages - 1) {
			final Element nextPageNoElem = doc.createElement("NextPageNo");
			textNode = doc.createTextNode(filename + "@" + Integer.valueOf(pageCount + 2).toString() + ".html");
			nextPageNoElem.appendChild(textNode);
			indexingElem.appendChild(nextPageNoElem);
		}
		if (pageCount >= 1) {
			final Element prevPageNoElem = doc.createElement("PrevPageNo");
			textNode = doc.createTextNode(filename + "@" + Integer.valueOf(pageCount) + ".html");
			prevPageNoElem.appendChild(textNode);
			indexingElem.appendChild(prevPageNoElem);
		}

		final Element headerDocumentElem = doc.createElement("HeaderDocument");
		String path = ("File:///" + documentPath + File.separator + filename + "@header.xml").replace('\\', '/');
		textNode = doc.createTextNode(path);
		headerDocumentElem.appendChild(textNode);
		indexingElem.appendChild(headerDocumentElem);

		final Element indexDocumentElem = doc.createElement("IndexDocument");
		path = ("File:///" + documentPath + File.separator + filename + "@index.xml").replace('\\', '/');
		textNode = doc.createTextNode(path);
		indexDocumentElem.appendChild(textNode);
		indexingElem.appendChild(indexDocumentElem);

	}

	protected void indexTag(final Document doc, final int pageCount, final String filename) {
		final Element contents = doc.getDocumentElement();
		final Element pageNoElem = doc.createElement("PageNo");

		final Element labelElem = doc.createElement("Num");
		Node textNode = doc.createTextNode(Integer.valueOf(pageCount + 1).toString());
		labelElem.appendChild(textNode);
		pageNoElem.appendChild(labelElem);

		final Element htmlPage = doc.createElement("HtmlPage");
		if (filename == null) {
			textNode = doc.createTextNode(Integer.valueOf(pageCount) + ".html");
		} else {
			textNode = doc.createTextNode(filename + "@" + Integer.valueOf(pageCount) + ".html");
		}
		htmlPage.appendChild(textNode);
		pageNoElem.appendChild(htmlPage);

		contents.appendChild(pageNoElem);
	}

	protected void makeLinkFiles() throws IOException, ParseException, ParserConfigurationException {
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
	public boolean readHeader() throws IOException, ParseException, ParserConfigurationException {
		titles = new ArrayList<String>();
		itemPaths = new ArrayList<String>();

		NodeList nodes = catalogDocument.getElementsByTagName("Build");
		nodes = catalogDocument.getElementsByTagName("Envelope");
		if (nodes == null) {
			return false;
		}
		nodes = catalogDocument.getElementsByTagName("Head");
		if (nodes == null) {
			return false;
		}
		tagName = XMLUtil.getStringValue((Element) nodes.item(0), "Tag");
		nodes = catalogDocument.getElementsByTagName("Index");
		final Node index = nodes.item(0);
		if (index.hasChildNodes() == false) {
			return true;
		}
		nodes = index.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Node item = nodes.item(i);
				if (item.getNodeName().matches("Item") == false) {
					return false;
				}
				final String name = item.getTextContent();
				titles.add(name);
			}
		}
		nodes = catalogDocument.getElementsByTagName("Body");
		final Node body = nodes.item(0);
		if (body.hasChildNodes() == false) {
			return true;
		}
		nodes = body.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {

				final Node path = nodes.item(i);
				if (path.getNodeName().matches("Path") == false) {
					return false;
				}
				final String name = path.getTextContent();
				itemPaths.add(name);
			}
		}
		makeLinkFiles();
		createIndex();

		for (int i = 0; i < titles.size(); i++) {
			// readIndex(titles.get(i), itemPaths.get(i));
			createItemPages(mainTag, titles.get(i), itemPaths.get(i), i + 1, itemsPerPage);
		}
		return true;
	}

	public boolean createIndex() throws IOException, ParseException {
		// make Catalog .html
		final String htmlFilePath = htmlPath + File.separator + CATALOGS + File.separator + tagName + ".html";
		XmlHtml.makeHtml("AuthorIndex.xsl", xmlRootPath + File.separator + rootFile, htmlFilePath);
		return Css.Copy("AuthorIndex.css", htmlPath);
	}

	public abstract boolean createItemPages(String mainTag, String secondaryTag, String path, int idx, int imagesPerPage)
			throws IOException, ParseException;
}
