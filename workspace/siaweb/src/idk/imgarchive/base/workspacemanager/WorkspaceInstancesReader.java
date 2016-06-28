package idk.imgarchive.base.workspacemanager;

import static org.w3c.dom.Node.ELEMENT_NODE;
import siawebapp.ApplicationError;
import siawebapp.ApplicationError.ErrorCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlutils.XMLUtil;

public class WorkspaceInstancesReader {

	static private String currentInstance = null;
	static private String defaultInstance = null;
	static public final String INSTANCES_FILE_NAME = "instances.xml";
	static protected ArrayList<String> list = null;

	/**
	 * Cluster XML Document
	 */

	/**
	 * 
	 */
	public static final void add(final String newName) {
		if (list == null) {
			list = new ArrayList<String>();
		}
		list.add(newName);
	}

	/**
	 * This decodes XML cluster information file.
	 * 
	 * @param inputFile
	 *            - Input file to be read.
	 * @return
	 */
	static protected Document decode(final File inputFile) {
		Document xmlDocument = null;
		try {
			xmlDocument = XMLUtil.parse(inputFile.getAbsolutePath());
		} catch (final IOException e) {
			System.out.println("IO Exception " + e.getMessage());
			return null;
		} catch (final ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return xmlDocument;
	}

	/**
	 * @return the currentInstance
	 */
	static public final String getCurrentInstance() {
		return currentInstance;
	}

	/**
	 * @return the default instance
	 */
	static public final String getDefaultInstance() {
		return defaultInstance;
	}

	/**
	 * @return the list
	 */
	public static final ArrayList<String> getList() {
		return list;
	}

	static public boolean IsPresent(final String item) {
		for (final String value : list) {
			if (value.matches(item) == true) {
				return true;
			}
		}
		return false;
	}

	static public boolean readFile(final String path) {
		list = new ArrayList<String>();
		Document xmlDocument = null;

		final File inputFile = new File(path, INSTANCES_FILE_NAME);
		if (inputFile.exists() == false) {

			// System.out.println(id + ": Missing Manifest file");
			return false;
		}
		if ((xmlDocument = decode(inputFile)) == null) {
			// AddLogItem(id, "Error: Unable to parse Manifest");
			return false;
		}

		final Element rootElement = xmlDocument.getDocumentElement();

		final Element current = XMLUtil.getElement(rootElement, "Default");

		defaultInstance = current.getTextContent();
		if (defaultInstance == null) {
			ApplicationError.setErrorCode(ErrorCode.DefaultNotSet);
			return false;
		}
		final Element instances = XMLUtil.getElement(rootElement, "Instances");
		boolean defaultFound = false;
		if (instances.hasChildNodes()) {
			final NodeList nodeList = instances.getChildNodes();
			final int n = nodeList.getLength();
			for (int i = 0; i < n; i++) {
				final Node fileNode = nodeList.item(i);

				if (fileNode.getNodeType() == ELEMENT_NODE) {
					if (fileNode.getNodeName().matches("Item") == false) {
						ApplicationError.setErrorCode(ErrorCode.ValidTag);
						return false;
					}

					final String instanceString = fileNode.getTextContent();
					if (defaultInstance.matches(instanceString) == true) {
						defaultFound = true;
					}
					list.add(instanceString);
				}
			}
		}
		if (defaultFound == false) {
			ApplicationError.setErrorCode(ErrorCode.DefaultNotFound);
			return false;
		}
		if (list.isEmpty() == true) {
			ApplicationError.setErrorCode(ErrorCode.NoInstancesFound);
			return false;
		}
		return true;

	}

	/**
	 * @param defaultInstance
	 *            the defaultInstance to set
	 */
	public static final void setDefaultInstance(final String defaultInstance) {
		WorkspaceInstancesReader.defaultInstance = defaultInstance;
	}

	/**
	 * @return the currentInstance
	 */
	static public final void SetInstance(final String instanceName) {
		currentInstance = instanceName;
		list.add(instanceName);
	}

	static public void writeXmlFile(final String path) throws NoSuchAlgorithmException, FileNotFoundException {

		Element e = null;
		Node n = null;
		// Document (Xerces implementation only).
		final Document xmldoc = new DocumentImpl();
		// Root element.
		final Element root = xmldoc.createElement("WorkSpaces");
		e = xmldoc.createElement("Default");
		n = xmldoc.createTextNode(WorkspaceInstancesReader.defaultInstance);
		e.appendChild(n);
		root.appendChild(e);

		e = xmldoc.createElement("Instances");
		root.appendChild(e);
		for (final String value : list) {
			final Element ie = xmldoc.createElement("Item");
			// e.setAttributeNS(null, "ID", "test");

			n = xmldoc.createTextNode(value);
			ie.appendChild(n);
			e.appendChild(ie);
		}

		xmldoc.appendChild(root);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path + File.separator + INSTANCES_FILE_NAME);
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
			serializer.serialize(xmldoc.getDocumentElement());
			fos.close();
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
