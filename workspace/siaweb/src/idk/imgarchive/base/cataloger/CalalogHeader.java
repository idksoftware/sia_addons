package idk.imgarchive.base.cataloger;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import xmlutils.XMLWriteHelper;

public class CalalogHeader {

	String xmlRootPath = null;

	CalalogHeader(final String xmlRootPath, final String file, final String mainTag, final String secTag) throws IOException,
			ParserConfigurationException {
		this.xmlRootPath = xmlRootPath;
		final Document doc = XMLWriteHelper.creatXMLFile();
		final Element header = doc.createElement("Header");
		final Node root = doc.appendChild(header);

		final Element mainTagElem = doc.createElement("MainTag");
		Node textNode = doc.createTextNode(mainTag);
		mainTagElem.appendChild(textNode);
		root.appendChild(mainTagElem);

		final Element secTagElem = doc.createElement("SecondaryTag");
		textNode = doc.createTextNode(secTag);
		secTagElem.appendChild(textNode);
		root.appendChild(secTagElem);

		final Element contentsDocumentElem = doc.createElement("ContentsDocument");
		final String path = ("File:///" + xmlRootPath + File.separator + mainTag + ".xml").replace('\\', '/');
		textNode = doc.createTextNode(path);
		contentsDocumentElem.appendChild(textNode);
		root.appendChild(contentsDocumentElem);

		XMLWriteHelper.writeXmlFile(doc, file);
	}
}
