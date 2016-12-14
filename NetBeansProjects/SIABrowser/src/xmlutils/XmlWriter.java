package xmlutils;

/*! \mainpage Source code documentation

This Documenment details the operation of the software 
*/
/**
 *
 * @author Iain Ferguson
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class XmlWriter {
	AttributesImpl attributes = new AttributesImpl();
	String filepath = null;
	FileWriter writer = null;
	XMLSerializer xmlWriter = null;
	ContentHandler hd = null;

	public XmlWriter(String filepath, String name) {
		this.filepath = filepath;
		final File file = new File(filepath, name);
		Init(file);
	}
	
	public XmlWriter(File file) {
		Init(file);
	}
	
	private void  Init(File file) {
		

		try {
			if (file.exists()) {
				file.delete();
			}
		} catch (final Exception e) {
			throw new RuntimeException("Can't delete file: " + filepath);
		}
		try {
			writer = new FileWriter(file, true);
		} catch (final IOException e) {
			throw new RuntimeException("Can't create writer for file: " + filepath, e);
		}
		if (writer != null) {
			final OutputFormat of = new OutputFormat("XML", "ISO-8859-1", true);
			of.setIndent(1);
			of.setIndenting(true);

			xmlWriter = new XMLSerializer(writer, of);

			try {
				hd = xmlWriter.asContentHandler();
			} catch (final IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*
			 * try {
			 * 
			 * hd.processingInstruction("IDK-META:Data",
			 * "xmlns:IDK-META=\"http://schemas.xmlsoap.org/soap/envelope/\"");
			 * 
			 * } catch (SAXException e1) { // TODO Auto-generated catch block
			 * e1.printStackTrace(); }
			 */
			try {
				if (xmlWriter != null) {
					xmlWriter.startDocument();

				} else {
					throw new RuntimeException("No xml writer available.");
				}
			} catch (final SAXException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void addAttribute(String name, String value) {
		attributes.addAttribute((String) null, (String) null, name, "", value);
	}

	public void close() {

		if (xmlWriter != null) {

			try {

				xmlWriter.endDocument();
				writer.close();
			} catch (final Exception e) {
				throw new RuntimeException("Can't delete file: " + filepath, e);
			} finally {
				xmlWriter = null;
			}
		}

	}

	public void element(String content) {
		if (content == null) {
			return; // empty
		}
		if (xmlWriter != null) {
			xmlWriter.characters(content.toCharArray(), 0, content.length());
		} else {
			throw new RuntimeException("No xml writer available.");
		}

	}

	public void elementCDATA(String content) {
		if (content == null) {
			return; // empty
		}
		if (xmlWriter != null) {
			xmlWriter.startCDATA();
			xmlWriter.startNonEscaping();
			xmlWriter.characters(content.toCharArray(), 0, content.length());
			xmlWriter.endNonEscaping();
			xmlWriter.endCDATA();

		} else {
			throw new RuntimeException("No xml writer available.");
		}

	}

	public void endElement(String qname) {

		if (xmlWriter != null) {
			xmlWriter.endElement((String) null, (String) null, qname);
			resetAttributes();
		} else {
			throw new RuntimeException("No xml writer available.");
		}

	}

	public void endElement(String NameSpaceURL, String qname) {

		if (xmlWriter != null) {
			xmlWriter.endElement(NameSpaceURL, (String) null, qname);
			resetAttributes();
		} else {
			throw new RuntimeException("No xml writer available.");
		}

	}

	public void endElement(String NameSpaceURL, String localName, String qname) {

		if (xmlWriter != null) {
			xmlWriter.endElement(NameSpaceURL, localName, qname);
			resetAttributes();
		} else {
			throw new RuntimeException("No xml writer available.");
		}

	}

	public void comment(String comment) {

		if (xmlWriter != null) {
			xmlWriter.comment(comment);
			resetAttributes();
		} else {
			throw new RuntimeException("No xml writer available.");
		}

	}

	public FileWriter getWriter() {
		return writer;
	}

	/**
	 * @return the xmlWriter
	 */
	public final XMLSerializer getXmlWriter() {
		return xmlWriter;
	}

	private void resetAttributes() {
		attributes = new AttributesImpl();
	}

	/**
	 * before startElement: accumulate Attribute into the internal buffer by
	 * calling addAttribute(..); attributes are written when you call
	 * startElement. After writing the element the attributes buffer ist reset.
	 * 
	 * @param qname
	 */
	public void startElement(String qname) {

		if (xmlWriter != null) {
			final org.xml.sax.Attributes attr = attributes;
			xmlWriter.startElement((String) null, (String) null, qname, attr);

			resetAttributes();
		} else {
			throw new RuntimeException("No xml writer available.");
		}

	}

	/**
	 * before startElement: accumulate Attribute into the internal buffer by
	 * calling addAttribute(..); attributes are written when you call
	 * startElement. After writing the element the attributes buffer ist reset.
	 * 
	 * @param qname
	 */
	public void startElement(String NameSpaceURL, String qname) {

		if (xmlWriter != null) {
			final org.xml.sax.Attributes attr = attributes;

			xmlWriter.startElement(NameSpaceURL, (String) null, qname, attr);

			resetAttributes();
		} else {
			throw new RuntimeException("No xml writer available.");
		}

	}

	public void startElement(String NameSpaceURL, String localName, String qname) {

		if (xmlWriter != null) {
			final org.xml.sax.Attributes attr = attributes;

			xmlWriter.startElement(NameSpaceURL, localName, qname, attr);

			resetAttributes();
		} else {
			throw new RuntimeException("No xml writer available.");
		}

	}
}
