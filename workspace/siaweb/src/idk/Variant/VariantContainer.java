package idk.Variant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import xmlutils.XmlWriter;

@SuppressWarnings("serial")
public abstract class VariantContainer extends ArrayList<VariantDirectory> implements Cloneable {
	
	public XmlWriter write(final XmlWriter xmlWriter) throws InvalidFormatException {
		xmlWriter.startElement("Document");
		for (VariantDirectory item : this) {
			item.writeXML(xmlWriter);
		}
		xmlWriter.endElement("Document");
		return xmlWriter;
	}
	
	public Element createNode(final Element root, final Document doc) throws InvalidFormatException {
		for (VariantDirectory item : this) {
			item.writeXML(doc, root);
		}
		return root;
	}
	
	public void readXML(final Element parent) throws InvalidFormatException {
		for (VariantDirectory item : this) {
			item.readXML(parent);
		}
	}
	
	public void readXML(final File file) throws InvalidFormatException {
		for (VariantDirectory item : this) {
			//item.readXML(parent);
		}
	}
	
	public String xmlHeader() {
		return "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>" + "\r\n"; 
	}
	
	public String xmlComment() {
		return "<!-- " + this.getDiscription() + " -->";
	}
	/**
	 * <!ELEMENT MediaItem (AssetProperties, MediaProperties?, AnnotationFields?,
	 * 					UserFields?, MetaDataFields?, MovieTracks?, MovieChapterTracks?)>
	 * @return
	 */
	public String dtdHeader() {
		return "<!-- DTD Version 3.1.0 -->" + "\r\n" +
				"<!DOCTYPE CatalogType [" + "\r\n";
	}
	
	public String dtdFooter() {
		return "]>" + "\r\n" +
				"<!-- DTD Version 3.1.0 -->" + "\r\n";
	}
	
	public String dtd() {	
		StringBuilder sb = new StringBuilder(4000);
		
		sb.append("<!ELEMENT " + this.getXMLName() + " (");
		int size = this.size();
		int i = 0;
		for (VariantDirectory item : this) {
			sb.append(item.getXMLName());
			if (item.getObligatory() == false) {
				sb.append("?");
			}	
			if (i < size - 1) {
				sb.append(", ");
			}
			i++;
		}
		sb.append(")>");
		sb.append("\r\n");
		for (VariantDirectory item : this) {
			if (item.dtdComment() != null) {
				sb.append("<!-- " + item.dtdComment() + "-->");
				sb.append("\r\n");
			}
			sb.append(item.dtd());
			sb.append("\r\n");
		}
		
		
		return sb.toString();
	}
	
	public void createDTDFile(File file) throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		out.write(xmlHeader());
		out.write(dtdHeader());
		out.write(dtd());
		out.write(dtdFooter());
		out.close();
	}
	
	public void merge(final VariantContainer other) throws InvalidFormatException {	
		for (VariantDirectory otherDir : other) {
			// This needs a lookup for dup directories
			VariantDirectory thisDir = lookUp(otherDir);
			if (thisDir != null) {
				thisDir.merge(otherDir);
			} else {
				add((VariantDirectory)otherDir.clone());
			}
		}
	}
	public abstract String getXMLName();
	public abstract String getDiscription();
	public abstract Object clone();
	
	VariantDirectory lookUp(VariantDirectory otherDir) {
		Class<?> otherDirType = otherDir.getClass();
		for (VariantDirectory item : this) {
			Class<?> itemType = item.getClass();
			
			if (itemType == otherDirType) {
				return item;
			}
		}
		return null;
	}
	
	public void print() {
		for (VariantDirectory item : this) {
			
			System.out.println("----- Directory Name: " + item.getXMLName());
			item.print();
		}
	}
}
