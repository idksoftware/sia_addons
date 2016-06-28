/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idk.imgarchive.base.cataloger;

//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//import org.w3c.dom.Node;
import idk.archiveutils.FileTypeInfoInclude;
import idk.imgarchive.base.log4j.Log;
import idk.imgarchive.base.workspacemanager.ImageAddress;
import idk.imgarchive.base.workspacemanager.ImageCursor;
import idk.imgarchive.base.workspacemanager.Workspace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlutils.XMLWriteHelper;

/**
 * 
 * @author Iain Ferguson
 */
public class MetadataCatalog {
	private final File destFolder;
	private final FileTypeInfoInclude fileTypeInfoInclude = new FileTypeInfoInclude();
	private Document rootDocument;
	private Node rootNode;

	public MetadataCatalog(final String rf) {
		destFolder = new File(rf);
	}

	public void doProcess() throws ParseException, IOException {
		final ImageCursor cursor = Workspace.createImageCursor();
		for (final ImageAddress item : cursor) {
			final File subFolder = new File(item.getPath());
			final File[] infoFiles = subFolder.listFiles(fileTypeInfoInclude);
			processFile(infoFiles[0], item);

		}
	}

	protected void init() throws ParserConfigurationException {

		// Create instance of DocumentBuilderFactory
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// Get the DocumentBuilder
		final DocumentBuilder parser = factory.newDocumentBuilder();
		// Create blank DOM Document

		rootDocument = parser.newDocument();

		// Insert the root element node
		final Element element = rootDocument.createElement("Catalogue");
		rootDocument.appendChild(element);

	}

	public void process() throws ParseException, IOException, ParserConfigurationException {
		init();
		doProcess();
	}

	protected int processFile(final File file, final ImageAddress item) throws ParseException, IOException {
		final ReadFile readFile = new ReadFile(file);
		readFile.process();
		final Document doc = readFile.getDocument();
		final NodeList list = doc.getElementsByTagName("Document");
		final Element rootElement = (Element) list.item(0);

		final Element documentID = doc.createElement("DocumentID");
		final String fileName = file.getName();
		final String strDocID = fileName.substring(3, fileName.indexOf("."));
		documentID.setTextContent(strDocID);

		// Insert the root partition node
		final Element parentPath = doc.createElement("ParentPath");
		parentPath.setTextContent(item.getPath());
		// documentID.appendChild(parentPath);

		rootElement.insertBefore(documentID, rootElement.getFirstChild());

		documentID.appendChild(parentPath);
		if (rootElement != null) {
			final NodeList rootlist = rootDocument.getElementsByTagName("Catalogue");
			rootNode = rootlist.item(0);
			final Node importNode = rootDocument.importNode(rootElement, true);
			rootNode.appendChild(importNode);
		}

		return 0;
	}

	public void writeXmlFile() {
		writeXmlFile(destFolder.getAbsolutePath());
	}

	public void writeXmlFile(final String filename) {
		try {
			XMLWriteHelper.writeXmlFile(rootDocument, filename);
		} catch (final FileNotFoundException e) {
			Log.getLogger().error("File not found " + filename + "\"", e);
		} catch (final IOException e) {

			Log.getLogger().error("IO Error " + filename + "\"", e);

		}
		/*
		 * try { // Prepare the DOM document for writing
		 * 
		 * DOMSource source = new DOMSource(rootDocument);
		 * 
		 * // Prepare the output file File file = new File(filename);
		 * StreamResult result = new StreamResult(file);
		 * 
		 * // Write the DOM document to the file Transformer xformer =
		 * TransformerFactory.newInstance().newTransformer();
		 * xformer.transform(source, result); } catch
		 * (TransformerConfigurationException e) {
		 * Log.getLogger().error("Missing tag file " + itemFile.getName() +
		 * "\""); return false; } catch (TransformerException e) { }
		 */
	}
}
