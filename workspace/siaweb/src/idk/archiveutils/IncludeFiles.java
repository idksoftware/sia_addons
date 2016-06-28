package idk.archiveutils;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xmlutils.XMLReadHelper;
import xmlutils.XmlWriter;

@SuppressWarnings("serial")
public class IncludeFiles extends ArrayList<String> {
	public boolean read(final Element includeNode) {
		final NodeList itemList = includeNode.getChildNodes();
		final int n = itemList.getLength();
		Element itemElem = null;
		for (int i = 0; i < n; i++) {
			if ((itemElem = XMLReadHelper.isElement(itemList, i)) != null) {
				if (itemElem.getNodeName().matches("File")) {
					String item = null;
					if ((item = itemElem.getTextContent()) == null) {
						return false;
					}
					add(item);

				}
			}
		}
		return true;
	}

	public XmlWriter write(final XmlWriter xmlWriter) {

		xmlWriter.startElement("Include");
		for (final String file : this) {
			xmlWriter.startElement("File");
			xmlWriter.element(file);
			xmlWriter.endElement("File");
		}
		xmlWriter.endElement("Include");

		return xmlWriter;
	}

	public static IncludeFiles readIncludes(final Element includeElem) {
		final IncludeFiles includeFiles = new IncludeFiles();
		if (includeFiles.read(includeElem) == false) {
			return null;
		}
		return includeFiles;
	}
}
