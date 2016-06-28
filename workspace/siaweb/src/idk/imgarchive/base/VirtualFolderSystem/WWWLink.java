package idk.imgarchive.base.VirtualFolderSystem;

import xmlutils.XmlWriter;

public class WWWLink {
	private String name = null;
	private String path = null;

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the link
	 */
	public final String getPath() {
		return path;
	}

	public WWWLink(final String name, final String path) {
		this.name = name;
		this.path = path;
	}

	XmlWriter write(final XmlWriter xmlWriter) {
		return write(xmlWriter, this);
	}

	static XmlWriter write(final XmlWriter xmlWriter, final WWWLink wwwLink) {
		xmlWriter.startElement("Link");
		xmlWriter.addAttribute("Name", wwwLink.getName());
		xmlWriter.startElement("Path");
		xmlWriter.element(wwwLink.getPath());
		xmlWriter.endElement("Path");
		xmlWriter.endElement("Link");
		return xmlWriter;
	}
}
