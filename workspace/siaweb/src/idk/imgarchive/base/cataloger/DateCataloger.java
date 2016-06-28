package idk.imgarchive.base.cataloger;

import idk.archiveutils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlutils.XMLUtil;
import xmlutils.XMLWriteHelper;

public class DateCataloger extends IndexCataloger {

	public DateCataloger(final String mainTag, final String xmlRootPath, final String file, final String htmlPath,
			final int itemsPerPage) throws IOException, ParseException {
		super(mainTag, xmlRootPath, file, htmlPath, itemsPerPage);

	}

	private String makeFilename(final String y, final String m, final String d) {
		if (m == null) {
			return y + "@nav.xml";
		} else if (d == null) {
			return y + '-' + m + "@nav.xml";
		}
		return y + '-' + m + '-' + d + "@nav.xml";
	}

	private void makeLinkFile(final String dateString, final String filename, final String id) throws FileNotFoundException,
			IOException, ParserConfigurationException {

		Document doc = null;
		doc = XMLWriteHelper.creatXMLFile();

		final Element documentElem = doc.createElement("Document");
		doc.appendChild(documentElem);
		final Element contents = doc.createElement("Contents");
		documentElem.appendChild(contents);

		XMLWriteHelper.writeXmlFile(doc, xmlRootPath + File.separator + filename);
	}

	@Override
	protected void makeLinkFiles() throws IOException, ParseException, ParserConfigurationException {
		for (int i = 0; i < titles.size(); i++) {
			readDate(titles.get(i));
			// makeLinkFile(titles.get(i), "2010", itemPaths.get(i));
		}
	}

	Document openDoc(final String filename) {
		final File itemFile = new File(xmlRootPath, filename);
		try {
			return XMLUtil.parse(itemFile.getAbsolutePath());
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void readDate(final String dateString) throws IOException, ParseException, ParserConfigurationException {
		final int endDay = dateString.indexOf('/');
		// String day = dateString.substring(0, endDay);
		final int endMonth = dateString.indexOf('/', endDay) + endDay + 1;
		final String month = dateString.substring(endDay + 1, endMonth);
		final int endYear = dateString.length();
		final String year = dateString.substring(endMonth + 1, endYear);

		// Year
		String filename = makeFilename(year, null, null);
		if (FileUtils.fileExists(xmlRootPath, filename) == null) {
			makeLinkFile(dateString, filename, null);
		} else {

			final Document yearDocument = openDoc(filename);
			filename = makeFilename(year, month, null);
			if (update(yearDocument, filename, month) == true) {
				// updated so add day
				makeLinkFile(dateString, filename, null);
			} else {
				filename = makeFilename(year, month, null);
				if (FileUtils.fileExists(xmlRootPath, filename) == null) {
					makeLinkFile(dateString, filename, null);
				}
				final Document monthDocument = openDoc(filename);
				// found so open month and update day
				update(monthDocument, filename, month);
			}
		}
	}

	private boolean update(final Document doc, final String filename, final String itemString) throws FileNotFoundException,
			IOException {
		NodeList nodes = doc.getElementsByTagName("Contents");
		final Node contents = nodes.item(0);
		if (contents.hasChildNodes() == false) {
			// Add month
		}
		nodes = contents.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Node item = nodes.item(i);
				if (item.getNodeName().matches("Item") == false) {
					return false;
				}
				final String name = item.getTextContent();
				if (name.matches(itemString) == true) {
					// Already entered
					return false;
				}
			}
		}
		// Add Month
		final Element itemElem = doc.createElement("Item");
		final Node textNode = doc.createTextNode(itemString);
		contents.appendChild(itemElem);
		itemElem.appendChild(textNode);
		XMLWriteHelper.writeXmlFile(doc, xmlRootPath + File.separator + filename);
		return true;
	}

}
