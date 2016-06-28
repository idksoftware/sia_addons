/**
 * 
 */
package idk.imgarchive.base.metadata;

import java.text.ParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import xmlutils.XmlWriter;

/**
 * @author 501726576
 * 
 */
interface MetadataSection extends Cloneable {

	/*
	 * 
	 */
	public XmlWriter write(XmlWriter xmlWriter);

	public void read(Element rootElement) throws NumberFormatException, ParseException;

	public void merge(MetadataSection obj);

	public Element createNode(Element root, Document doc);
}
