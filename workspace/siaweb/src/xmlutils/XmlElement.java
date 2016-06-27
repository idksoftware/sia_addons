package xmlutils;

import org.w3c.dom.Element;

public class XmlElement {
	static final String string(Element parent, String tag) {
		final Element Name = XMLUtil.getElement(parent, tag);
		if (Name == null) {
			return null;
		}
		return Name.getTextContent();
	}
}
