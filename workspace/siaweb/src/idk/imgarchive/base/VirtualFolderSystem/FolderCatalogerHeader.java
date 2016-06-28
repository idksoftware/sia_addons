package idk.imgarchive.base.VirtualFolderSystem;

import idk.archiveutils.FileUtils;
import idk.imgarchive.base.VirtualFolderSystem.VFolderCataloger.WWWPath;
import idk.imgarchive.base.VirtualFolderSystem.VirtualFolder.VirtualFolderInfo;

import java.util.ArrayList;

import xmlutils.DateUtils;
import xmlutils.XmlWriter;

public class FolderCatalogerHeader {
	public ArrayList<WWWLink> folderList = new ArrayList<WWWLink>();
	VirtualFolder vf = null;
	String xmlPath = null;
	WWWPath currentPath = null;
	ArrayList<WWWLink> pageList = null;

	/*
	 * This will hold the folder infomation and the image pages assocated with
	 * this folder.
	 */

	public FolderCatalogerHeader(final WWWPath currentPath, final String xmlPath, final VirtualFolder currentVF,
			final ArrayList<WWWLink> pageList) {
		vf = currentVF;
		this.pageList = pageList;
		this.xmlPath = xmlPath;
		this.currentPath = currentPath;
		for (final VirtualFolderInfo item : vf.getFolderList()) {
			folderList.add(new WWWLink(item.name, FileUtils.stripFileExtension(item.filename)));
		}
	}

	public void writeHeader() {

		final String name = vf.filename.substring(0, vf.filename.indexOf("."));
		final XmlWriter xmlWriter = new XmlWriter(xmlPath, name + "@header.xml");
		xmlWriter.startElement("VirtualFolder");
		xmlWriter.startElement("Name");
		xmlWriter.element(vf.name);
		xmlWriter.endElement("Name");
		xmlWriter.startElement("Filename");
		xmlWriter.element(vf.filename);
		xmlWriter.endElement("Filename");
		xmlWriter.startElement("LastModified");
		String dateString = DateUtils.formatDDMMYYYY(vf.getLastModified());
		xmlWriter.element(dateString);
		xmlWriter.endElement("LastModified");
		xmlWriter.startElement("Created");
		dateString = DateUtils.formatDDMMYYYY(vf.getCreatedDate());
		xmlWriter.element(dateString);
		xmlWriter.endElement("Created");

		// Parent folder List
		xmlWriter.startElement("PathList");
		for (final WWWLink item : currentPath) {
			item.write(xmlWriter);
		}
		xmlWriter.endElement("PathList");
		// Folder List
		xmlWriter.startElement("FolderList");

		for (final WWWLink item : folderList) {
			item.write(xmlWriter);
		}
		xmlWriter.endElement("FolderList");

		// Image List
		xmlWriter.startElement("PageList");
		for (final WWWLink page : pageList) {
			page.write(xmlWriter);
		}
		xmlWriter.endElement("PageList");
		xmlWriter.endElement("VirtualFolder");
		xmlWriter.close();
	}

}