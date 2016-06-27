package xmlutils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.ParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This class with handle some simple XML operations.
 * 
 * @author <a href="mailto:ben@benlitchfield.com">Ben Litchfield</a>
 * @author <a href="mailto:chris@oezbek.net">Christopher Oezbek</a>
 * 
 * @version $Revision: 1.4 $
 */
public class XMLUtil {
	/**
	 * Convert the document to an array of bytes.
	 * 
	 * @param doc
	 *            The XML document.
	 * @param encoding
	 *            The encoding of the output data.
	 * 
	 * @return The XML document as an array of bytes.
	 * 
	 * @throws TransformerException
	 *             If there is an error transforming to text.
	 */
	public static byte[] asByteArray(Document doc, String encoding) throws TransformerException {
		final Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

		final StringWriter writer = new StringWriter();
		final Result result = new StreamResult(writer);
		final DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
		return writer.getBuffer().toString().getBytes();
	}

	/**
	 * Get the first instance of an element by name.
	 * 
	 * @param parent
	 *            The parent to get the element from.
	 * @param elementName
	 *            The name of the element to look for.
	 * @return The element or null if it is not found.
	 */
	public static Element getElement(Element parent, String elementName) {
		Element retval = null;
		final NodeList children = parent.getElementsByTagName(elementName);
		if (children.getLength() > 0) {
			retval = (Element) children.item(0);
		}
		return retval;
	}
	
	public static String getAttibute(Element parent, String AttibuteName) {
		final String value = parent.getAttribute(AttibuteName);
		return value;
	}
	/**
	 * Get the integer value of a subnode.
	 * 
	 * @param parent
	 *            The parent element that holds the values.
	 * @param nodeName
	 *            The name of the node that holds the integer value.
	 * 
	 * @return The integer value of the node.
	 */
	public static Integer getIntValue(Element parent, String nodeName) {
		final String intVal = XMLUtil.getStringValue(XMLUtil.getElement(parent, nodeName));
		Integer retval = null;
		if (intVal != null) {
			retval = new Integer(intVal);
		}
		return retval;
	}

	/**
	 * This will get the text value of an element.
	 * 
	 * @param node
	 *            The node to get the text value for.
	 * @return The text of the node.
	 */
	public static String getStringValue(Element node) {
		String retval = "";
		final NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			final Node next = children.item(i);
			if (next instanceof Text) {
				retval = next.getNodeValue();
				break;
			}
		}
		return retval;
	}

	/**
	 * Get the value of a subnode.
	 * 
	 * @param parent
	 *            The parent element that holds the values.
	 * @param nodeName
	 *            The name of the node that holds the value.
	 * 
	 * @return The value of the sub node.
	 */
	public static String getStringValue(Element parent, String nodeName) {
		final Element element = XMLUtil.getElement(parent, nodeName);
		if (element == null) {
			return null;
		}
		return XMLUtil.getStringValue(element);
	}

	/*
	 * catch( Exception e ) { IOException thrown = new IOException(
	 * e.getMessage() ); throw thrown; }
	 */
	/**
	 * Create a new blank XML document.
	 * 
	 * @return The new blank XML document.
	 * 
	 * @throws IOException
	 *             If there is an error creating the XML document.
	 * @throws ParseException
	 */
	public static Document newDocument() throws ParseException {

		final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (final ParserConfigurationException e) {
			final ParseException thrown = new ParseException(e.getMessage(), 0);
			throw thrown;
		}
		return builder.newDocument();
	}

	/*
	 * IOException thrown = new IOException( e.getMessage() ); throw thrown;
	 */
	/**
	 * This will parse an InputSource and create a DOM document.
	 * 
	 * @param is
	 *            The stream to get the XML from.
	 * @return The DOM document.
	 * @throws IOException
	 *             It there is an error creating the dom.
	 * @throws ParseException
	 */
	public static Document parse(InputSource is) throws IOException, ParseException {

		final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (final ParserConfigurationException e) {
			final ParseException thrown = new ParseException(e.getMessage(), 0);
			throw thrown;
		}
		try {
			return builder.parse(is);
		} catch (final IOException e) {
			final IOException thrown = new IOException(e.getMessage());
			throw thrown;

		} catch (final SAXException e) {
			final ParseException thrown = new ParseException(e.getMessage(), 0);
			throw thrown;
		}

	}

	/**
	 * This will parse an XML stream and create a DOM document.
	 * 
	 * @param is
	 *            The stream to get the XML from.
	 * @return The DOM document.
	 * @throws IOException
	 * @throws IOException
	 *             It there is an error creating the dom.
	 * @throws ParseException
	 */
	public static Document parse(InputStream is) throws IOException, ParseException {

		final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (final ParserConfigurationException e) {
			final ParseException thrown = new ParseException(e.getMessage(), 0);
			throw thrown;
		}
		try {
			return builder.parse(is);
		} catch (final IOException e) {
			final IOException thrown = new IOException(e.getMessage());
			throw thrown;

		} catch (final SAXException e) {
			final ParseException thrown = new ParseException(e.getMessage(), 0);
			throw thrown;
		}

	}

	/**
	 * This will parse an XML stream and create a DOM document.
	 * 
	 * @param fileName
	 *            The file to get the XML from.
	 * @return The DOM document.
	 * @throws ParserConfigurationException
	 * @throws IOException
	 *             It there is an error creating the dom.
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 * @throws SAXException
	 * @throws ParseException
	 */
	public static Document parse(String fileName) throws IOException, ParseException {
		final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (final ParserConfigurationException e) {
			final ParseException thrown = new ParseException(e.getMessage(), 0);
			throw thrown;
		}
		try {
			return builder.parse(fileName);
		} catch (final IOException e) {
			final IOException thrown = new IOException(e.getMessage());
			throw thrown;

		} catch (final SAXException e) {
			final ParseException thrown = new ParseException(e.getMessage(), 0);
			throw thrown;
		}
	}

	/**
	 * Save the XML document to a file.
	 * 
	 * @param doc
	 *            The XML document to save.
	 * @param file
	 *            The file to save the document to.
	 * @param encoding
	 *            The encoding to save the file as.
	 * 
	 * @throws TransformerException
	 *             If there is an error while saving the XML.
	 */
	public static void save(Document doc, String file, String encoding) throws TransformerException {
		try {
			final Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			// initialize StreamResult with File object to save to file

			final Result result = new StreamResult(new File(file));
			final DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
		} finally {

		}
	}

	/**
	 * Save the XML document to an output stream.
	 * 
	 * @param doc
	 *            The XML document to save.
	 * @param outStream
	 *            The stream to save the document to.
	 * @param encoding
	 *            The encoding to save the file as.
	 * 
	 * @throws TransformerException
	 *             If there is an error while saving the XML.
	 */
	public static void save(Node doc, OutputStream outStream, String encoding) throws TransformerException {
		try {
			final Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

			// initialize StreamResult with File object to save to file
			final Result result = new StreamResult(outStream);
			final DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
		} finally {

		}
	}

	/**
	 * Set an XML element document.
	 * 
	 * @param parent
	 *            The parent document to set the value in.
	 * @param name
	 *            The name of the XML element to set.
	 * @param node
	 *            The node to set or clear.
	 */
	public static void setElementableValue(Element parent, String name, Elementable node) {
		final NodeList nodes = parent.getElementsByTagName(name);
		if (node == null) {
			for (int i = 0; i < nodes.getLength(); i++) {
				parent.removeChild(nodes.item(i));
			}
		} else {
			if (nodes.getLength() == 0) {
				if (parent.hasChildNodes()) {
					final Node firstChild = parent.getChildNodes().item(0);
					parent.insertBefore(node.getElement(), firstChild);
				} else {
					parent.appendChild(node.getElement());
				}
			} else {
				final Node oldNode = nodes.item(0);
				parent.replaceChild(node.getElement(), oldNode);
			}
		}
	}

	/**
	 * Set the integer value of an element.
	 * 
	 * @param parent
	 *            The parent element that will hold this subelement.
	 * @param nodeName
	 *            The name of the subelement.
	 * @param intValue
	 *            The value to set.
	 */
	public static void setIntValue(Element parent, String nodeName, Integer intValue) {
		Element currentValue = getElement(parent, nodeName);
		if (intValue == null) {
			if (currentValue != null) {
				parent.removeChild(currentValue);
			} else {
				// it doesn't exist so we don't need to remove it.
			}
		} else {
			if (currentValue == null) {
				currentValue = parent.getOwnerDocument().createElement(nodeName);
				parent.appendChild(currentValue);
			}
			XMLUtil.setStringValue(currentValue, intValue.toString());
		}
	}

	/**
	 * This will set the text value of an element.
	 * 
	 * @param node
	 *            The node to get the text value for.
	 * @param value
	 *            The new value to set the node to.
	 */
	public static void setStringValue(Element node, String value) {
		final NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			final Node next = children.item(i);
			if (next instanceof Text) {
				node.removeChild(next);
			}
		}
		node.appendChild(node.getOwnerDocument().createTextNode(value));
	}

	/**
	 * Set the value of an element.
	 * 
	 * @param parent
	 *            The parent element that will hold this subelement.
	 * @param nodeName
	 *            The name of the subelement.
	 * @param nodeValue
	 *            The value to set.
	 */
	public static void setStringValue(Element parent, String nodeName, String nodeValue) {
		Element currentValue = getElement(parent, nodeName);
		if (nodeValue == null) {
			if (currentValue != null) {
				parent.removeChild(currentValue);
			} else {
				// it doesn't exist so we don't need to remove it.
			}
		} else {
			if (currentValue == null) {
				currentValue = parent.getOwnerDocument().createElement(nodeName);
				parent.appendChild(currentValue);
			}
			XMLUtil.setStringValue(currentValue, nodeValue);
		}
	}

	/**
	 * Utility class, should not be instantiated.
	 * 
	 */
	private XMLUtil() {
	}
}
