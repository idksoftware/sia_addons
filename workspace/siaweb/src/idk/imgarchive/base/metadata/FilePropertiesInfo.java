package idk.imgarchive.base.metadata;

import idk.archiveutils.FileInfo;
import idk.archiveutils.FileInfoBase;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import org.w3c.dom.Element;

import xmlutils.DateUtils;
import xmlutils.XMLReadHelper;
import xmlutils.XMLUtil;
import xmlutils.XmlWriter;

public class FilePropertiesInfo extends FileInfoBase {

	private String archiveName = "";

	public FilePropertiesInfo(final Element rootElement) throws ParseException {
		super(rootElement);
	}

	public FilePropertiesInfo(final File f) throws IOException, NoSuchAlgorithmException {
		super(f);

	}

	public FilePropertiesInfo(final FileInfo fileInfo) throws IOException, NoSuchAlgorithmException {
		super(fileInfo.getFileName(), fileInfo.getLastModified(), fileInfo.getSize(),
													fileInfo.getCrc(), fileInfo.getMd5());
	}
	
	public FilePropertiesInfo(final String path) throws IOException, NoSuchAlgorithmException {
		super(path);

	}
	public FilePropertiesInfo(final String name, final Date lastModified, final long size, final long crc, final String md5) {
		super(name, lastModified, size, crc, md5);
	}

	public FilePropertiesInfo(final String path, final String name) throws IOException, NoSuchAlgorithmException {
		super(path, name);

	}

	@Override
	protected void fileInit(final File file) throws IOException, NoSuchAlgorithmException {

		init();
		readFile();
	}

	public void archiveName(final String name) {
		archiveName = name;
	}

	/**
	 * @return the archiveName
	 */
	public final String getArchiveName() {
		return archiveName;
	}
	
	/**
	 * @return the uuid
	 */
	public final UUID getUuid() {
		return uuid;
	}
	
	@Override
	protected void readInfo(final Element rootElement) throws ParseException {
		final Element fileElem = XMLUtil.getElement(rootElement, "File");
		if (fileElem == null) {
			return;
		}
		fileName = XMLReadHelper.stringValue(fileElem, "OriginalFile", true);
		lastModified = XMLReadHelper.dateValue(fileElem, "LastModified", true);
		size = XMLReadHelper.integerValue(fileElem, "Size", true, false);
		crc = XMLReadHelper.longValue(fileElem, "Crc", true, false);
		md5 = XMLReadHelper.stringValue(fileElem, "Md5", true);
		archiveName = XMLReadHelper.stringValue(fileElem, "ArchiveName", false);
		final String uuidStr = XMLReadHelper.stringValue(fileElem, "UUID", true);
		uuid = UUID.fromString(uuidStr);
	}

	@Override
	public XmlWriter write(final XmlWriter xmlWriter) {
		xmlWriter.startElement("File");
		// xmlWriter.addAttribute("test", "name");
		xmlWriter.startElement("OriginalFile");
		xmlWriter.element(fileName);
		xmlWriter.endElement("OriginalFile");
		xmlWriter.startElement("LastModified");

		xmlWriter.element(DateUtils.formatDDMMYYYY(lastModified));
		xmlWriter.endElement("LastModified");
		// xmlWriter.startElement("Path");
		// xmlWriter.element(this.file.getName());
		// xmlWriter.endElement("Path");
		xmlWriter.startElement("Size");
		xmlWriter.element(Long.toString(size));
		xmlWriter.endElement("Size");
		xmlWriter.startElement("Crc");
		xmlWriter.element(Long.toString(crc));
		xmlWriter.endElement("Crc");
		xmlWriter.startElement("Md5");
		xmlWriter.element(md5);
		xmlWriter.endElement("Md5");
		xmlWriter.startElement("ArchiveName");
		xmlWriter.element(archiveName);
		xmlWriter.endElement("ArchiveName");
		xmlWriter.startElement("UUID");
		xmlWriter.element(uuid.toString());
		xmlWriter.endElement("UUID");
		xmlWriter.endElement("File");
		return xmlWriter;

	}

}
