package idk.archiveutils;

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

public class FileInfo extends FileInfoBase {

	public FileInfo(final Element rootElement) throws ParseException {
		super(rootElement);
	}

	public FileInfo(final File f) throws IOException, NoSuchAlgorithmException {
		super(f);
	}

	public FileInfo(final String path) throws IOException, NoSuchAlgorithmException {
		super(path);
	}

	public FileInfo(final String name, final Date lastModified, final long size, final long crc, final String md5) {
		super(name, lastModified, size, crc, md5);
	}

	public FileInfo(final String path, final String name) throws IOException, NoSuchAlgorithmException {
		super(path, name);
	}

	@Override
	protected void readInfo(final Element rootElement) throws ParseException {
		final Element fileElem = XMLUtil.getElement(rootElement, "File");
		fileName = XMLReadHelper.stringValue(fileElem, "FileName", true);
		lastModified = XMLReadHelper.dateValue(fileElem, "LastModified", true);
		size = XMLReadHelper.integerValue(fileElem, "Size", true, false);
		crc = XMLReadHelper.longValue(fileElem, "Crc", true, false);
		md5 = XMLReadHelper.stringValue(fileElem, "Md5", true);
		final String uuidStr = XMLReadHelper.stringValue(fileElem, "UUID", true);
		uuid = UUID.fromString(uuidStr);
	}

	@Override
	public XmlWriter write(final XmlWriter xmlWriter) {
		xmlWriter.startElement("File");
		xmlWriter.startElement("FileName");
		xmlWriter.element(fileName);
		xmlWriter.endElement("FileName");
		xmlWriter.startElement("LastModified");
		xmlWriter.element(DateUtils.formatDDMMYYYY(lastModified));
		xmlWriter.endElement("LastModified");
		xmlWriter.startElement("Size");
		xmlWriter.element(Long.toString(size));
		xmlWriter.endElement("Size");
		xmlWriter.startElement("Crc");
		xmlWriter.element(Long.toString(crc));
		xmlWriter.endElement("Crc");
		xmlWriter.startElement("Md5");
		xmlWriter.element(md5);
		xmlWriter.endElement("Md5");
		xmlWriter.startElement("UUID");
		xmlWriter.element(uuid.toString());
		xmlWriter.endElement("UUID");
		xmlWriter.endElement("File");
		return xmlWriter;

	}

}
