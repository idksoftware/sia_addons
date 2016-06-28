package idk.Variant;





import java.io.File;

import idk.Variant.VariantAttribute.AttribType;
import idk.Variant.InvalidFormatException;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xmlutils.XMLReadHelper;
import xmlutils.XMLUtil;
import xmlutils.XMLWriteHelper;
import xmlutils.XmlWriter;



public abstract class VariantDirectory implements Cloneable {
	
	public abstract VariantAttrMap getAttrMap();
	public abstract String getXMLName();
	public abstract boolean getObligatory();
	boolean writeEmptyTags = true;
	
	
	public abstract String dtdComment();
	 /**
     * Returns the name of a specified tag as a String.
     *
     * @param tagType the tag type identifier
     * @return the tag's name as a String
     */

    public VariantValue getAttrValue(VariantAttribute tagType)
    {
    	VariantAttrMap nameMap = getAttrMap();
        if (!nameMap.getMap().containsKey(tagType)) {
           
            return null;
        }
        return nameMap.getMap().get(tagType);
        
    }
    /**
     * Returns the name of a specified tag as a String.
     *
     * @param tagType the tag type identifier
     * @return the tag's name as a String
     */

    public String getTagName(VariantAttribute tagType)
    {
    	VariantValue value = getAttrValue(tagType);
    	return value.getValue();
    }
	
	/**
	 * @return the writeEmptyTags
	 */
	public final boolean isWriteEmptyTags() {
		return writeEmptyTags;
	}
	/**
	 * @param writeEmptyTags the writeEmptyTags to set
	 */
	public final void setWriteEmptyTags(boolean writeEmptyTags) {
		this.writeEmptyTags = writeEmptyTags;
	}
	
	public void readXMLItem(Element parent, VariantAttribute attrLable) throws InvalidFormatException {
		VariantValue attrValue = getAttrValue(attrLable);
		if (attrValue.getAttribType() == AttribType.ListValue) {
			readList(parent, (VariantListValue)attrValue);
		} else {
			readXMLItem(parent, attrValue);
		}
	}
	
	public void readXMLItem(Element parent, VariantValue attrValue) throws InvalidFormatException {
		VariantAttribute imageAttribute = attrValue.getAttribute();
		String stringValue = XMLUtil.getStringValue(parent, imageAttribute.getXmlLable());
		if (stringValue == null) {
			return; 
		}
		// Note this is to validate the string. This value will in all cases
		// be turned back to a string.
		
		switch(attrValue.getAttribType()) {
		case BooleanValue:
			attrValue.setValue(stringValue);
			break;
		case DateValue:
			attrValue.setValue(stringValue);
			break;
		case IntegerValue:
			attrValue.setValue(stringValue);
			break;
		case LongValue:
			attrValue.setValue(stringValue);
			break;
		case StringValue:
			attrValue.setValue(stringValue);
			break;
		case FloatValue:
			attrValue.setValue(stringValue);
			break;
		}
		
	}
	
	private void readList(Element parent, VariantListValue attrValue) throws InvalidFormatException
	{
		
		VariantAttribute imageAttribute = attrValue.getAttribute();
		Element element = XMLUtil.getElement(parent, imageAttribute.getXmlLable());
		if (element == null) {
			return;
		}
		NodeList nodeList = element.getChildNodes();
		if (nodeList == null) {
			return;
		}
		for (int i = 0; i < nodeList.getLength(); i++) {
			i = XMLReadHelper.getNextElement(nodeList, i);
			if (i < 0) {
				break;	// Got to end of list without finding an Element
						// so quit?
			}
			Element itemElem = XMLReadHelper.isElement(nodeList, i);
			String text = XMLUtil.getStringValue(itemElem);
			attrValue.add(text);
		}
		
	}
	
	public void read(Element parent) throws InvalidFormatException {
		loadMap();
		Element element = XMLUtil.getElement(parent, getXMLName());
		if (element == null) {
			return;
		}
		readXML(element);
	}
	
	public void readXML(Element parent) throws InvalidFormatException {
		VariantAttrMap map = getAttrMap();
		for (VariantValue otherValue : map) {
			VariantAttribute attrLable = otherValue.getAttribute();
			readXMLItem(parent, attrLable);
		}
	}
	
	public void print() {
		VariantAttrMap map = getAttrMap();
		
		for (VariantValue value : map) {
			if (value instanceof VariantListValue ) {
				System.out.println("XML Label: " + value.getAttribute().getXmlLable() + " Friendly Label: " +
						value.getAttribute().getFriendlyLable() + " Obligatory: " + value.isObligatory());
				AttribList attribList = ((VariantListValue)value).getList();
				for (VariantValue item : attribList) {
					System.out.println("XML Label: " + item.getAttribute().getXmlLable() + " Friendly Label: " +
							item.getAttribute().getFriendlyLable() + " Value: " + item.getValue());
					
				}
			} else {
				if (value.getValue() == null) {
					continue;
				}
				System.out.println("XML Label: " + value.getAttribute().getXmlLable() + " Friendly Label: " +
									value.getAttribute().getFriendlyLable() +
									" Value: " + ((value.getValue() != null)? value.getValue() : "Empty") +
									" Obligatory: " + value.isObligatory());
									
			}
			
		}
	}
	
	protected void merge(final VariantDirectory other) throws InvalidFormatException {
		VariantAttrMap otherMap = other.getAttrMap();
		
		for (VariantValue otherValue : otherMap) {
			VariantAttribute attrLable = otherValue.getAttribute();
			VariantValue thisValue = getAttrValue(attrLable);
			thisValue.setString(otherValue.getStringValue());
		}
	}
	
	protected abstract Object clone();
	
	protected abstract void loadMap();

	public void writeXML(Document doc, Element parent) throws InvalidFormatException {
		VariantAttrMap map = getAttrMap();
		boolean item2Write = false;
		Element elem = null;
		final Element directoryElem = doc.createElement(this.getXMLName());
		for (VariantValue item : map) {
			if (writeEmptyTags == false) {
				if (item.getValue() == null) {
					if (item.obligatory == true) {
						throw new InvalidFormatException();
					}
					continue;
				}
				elem = XMLWriteHelper.getElement(doc, item.getAttribute().getXmlLable(), item.getValue());
			} else {
				elem = XMLWriteHelper.getElement(doc, item.getAttribute().getXmlLable(), (String)null);
			}
			directoryElem.appendChild(elem);
			
		
		}
		if (writeEmptyTags == false) {
			if (item2Write != false) {
				parent.appendChild(directoryElem);
			}
		} else {
			parent.appendChild(directoryElem);
		}
	}
	public void writeXML(XmlWriter xmlWriter) throws InvalidFormatException {
		//xmlWriter.comment("EXIF GPS Section");
		xmlWriter.startElement(this.getXMLName());
		VariantAttrMap map = getAttrMap();
		for (VariantValue item : map) {
			if (item.getValue() == null) {
				if (item.obligatory == true) {
					throw new InvalidFormatException();
				}
				continue;
			}
			xmlWriter.startElement(item.getAttribute().getXmlLable());
			xmlWriter.element(item.getValue());
			xmlWriter.endElement(item.getAttribute().getXmlLable());
		}
		xmlWriter.endElement(this.getXMLName());
	}
	
	public String dtd() {	
		VariantAttrMap map = getAttrMap();
		StringBuilder sb = new StringBuilder("<!ELEMENT " + this.getXMLName() + " (");
		int size = map.size();
		int i = 0;
		int j = 1;
		for (VariantValue item : map) {
			VariantAttribute vb = item.getAttribute();
			sb.append(vb.getXmlLable());
			if (item.isObligatory() == false) {
				sb.append("?");
			}	
			if (i < size - 1) {
				sb.append(", ");
			}
			if (sb.length() > 80 * j) {
				j++;
				sb.append("\r\n\t\t");
			}
			i++;
		}
		sb.append(")>");
		sb.append("\r\n");
		for (VariantValue item : map) {
			// <!ELEMENT Filename (#PCDATA)>
			sb.append("\t<!ELEMENT " + item.getAttribute().getXmlLable() + " (#PCDATA)>");
			sb.append("\r\n");
		}
		return sb.toString();
	}
}

/*
 AttribType attType = item.getAttribType();
			switch(attType) {
			case BooleanValue:
				//if (!(item instanceof VariantBooleanValue)) {
				//	conting
				//}
				elem = XMLWriteHelper.getElement(doc, item.getAttribute().getXmlLable(), item.getValue());
				
			case DateValue:
				elem = XMLWriteHelper.getElement(doc, item.getAttribute().getXmlLable(), item..getValue());
			case IntegerValue:
			case LongValue:
			case StringValue:
			case FloatValue:
			case DoubleValue:
			case ListValue:
			}
 */
