package idk.imgarchive.base.hooks;

import static org.w3c.dom.Node.ELEMENT_NODE;
import idk.imgarchive.base.log4j.Log;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlutils.DateUtils;
import xmlutils.XMLUtil;

public class HookFile {

	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		// String path = "c:/";
		// String filename = "hookfile.xml";
		// HookFile hookFile = new HookFile(path, filename);
	}

	public String filename = null;
	public String path = null;

	public Document xmlDocument = null;

	public HookFile(final String path, final String filename) {
		this.path = path;
		this.filename = filename;
	}

	protected boolean decode(final File inputFile) throws IOException, ParseException {

		try {
			xmlDocument = XMLUtil.parse(inputFile.getAbsolutePath());
		} catch (final IOException e2) {
			Log.IOException(inputFile.getName(), e2);
		} catch (final ParseException e2) {
			Log.ParseException(inputFile.getName(), e2);
		}

		return true;
	}

	boolean readHookFile() {
		final Element rootElement = xmlDocument.getDocumentElement();
		if (rootElement.hasChildNodes()) {
			final NodeList pmList = rootElement.getChildNodes();
			final int n = pmList.getLength();
			for (int i = 0; i < n; i++) {
				final Node fileNode = pmList.item(i);

				final short type = fileNode.getNodeType();

				String name = null;
				long crc = 0;
				Date lastModified = null;
				long size = 0;
				String md5String = null;

				if (type == ELEMENT_NODE) {

					final Element Name = XMLUtil.getElement((Element) fileNode, "Name");
					if (Name != null) {
						name = Name.getTextContent();
					}
					final Element LastModified = XMLUtil.getElement((Element) fileNode, "LastModified");
					if (LastModified != null) {
						final String lastModifiedString = LastModified.getTextContent();
						try {
							lastModified = DateUtils.parseDDMMYYYY(lastModifiedString);
						} catch (final ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					final Element Size = XMLUtil.getElement((Element) fileNode, "Size");
					if (Size != null) {
						final String sizeString = Size.getTextContent();
						size = Long.parseLong(sizeString, 10);
					}

					final Element Crc = XMLUtil.getElement((Element) fileNode, "Crc");
					if (Crc != null) {
						final String crcString = Crc.getTextContent();
						crc = Long.parseLong(crcString, 10);
					}
					final Element Md5 = XMLUtil.getElement((Element) fileNode, "Md5");
					if (Md5 != null) {
						md5String = Md5.getTextContent();
					}

				}

			}
		}

		return true;

	}

	public boolean readHookFile(final File inputFile) throws IOException, ParseException {

		if (decode(inputFile) == false) {
			return false;
		}
		return readHookFile();
	}

	public boolean readHookFile(final String path, final String file) throws IOException, ParseException {
		final File inputFile = new File(path, file);
		if (inputFile.exists() == false) {

			return false;
		}
		if (decode(inputFile) == false) {

			return false;
		}
		return readHookFile();
	}

}

/*
 * <?xml version="1.0" encoding="UTF-8" ?> <PartitionCatalog> <ForEachImage>
 * <Execute> <Cmd>xslt</Cmd> <Arguments> <XslFile> <Path>MainConfig</Path>
 * <FileName>Html</FileName> </XslFile> <XmlFile>
 * <Path>CurrentXMLImagePath</Path> <FileName>CurrentImage</FileName> </XmlFile>
 * <HtmlFile> <Path>CurrentHtmlImagePath</Path>
 * <FileName>CurrentImage</FileName> </HtmlFile> </Arguments> </Execute>
 * <Execute> <Cmd>copy</Cmd> <Arguments> <Source> <Path>MainConfig</Path>
 * <FileName>Html</FileName> </Source> <XmlFile>
 * <Path>CurrentXMLImagePath</Path> <FileName>CurrentImage</FileName> </XmlFile>
 * <HtmlFile> <Path>CurrentHtmlImagePath</Path>
 * <FileName>CurrentImage</FileName> </HtmlFile> </Arguments> </Execute>
 * </ForEachImage> <ForEachPartition>
 * 
 * </ForEachPartition> <ForEachCluster>
 * 
 * </ForEachCluster> <ForEachInstance>
 * 
 * </ForEachInstance> <Finally>
 * 
 * </Finally> </PartitionCatalog>
 */