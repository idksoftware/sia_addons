/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idk.archiveutils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xmlutils.XMLUtil;

/**
 * 
 * @author Iain Ferguson
 */
public class XMLFilter {

	public ArrayList<String> filterList = null;

	public XMLFilter() {

	}

	public void AddKeyword(final String k) {
		final ArrayList<String> filterList = new ArrayList<String>();
		filterList.add(k);
	}

	private ArrayList<String> GetKeywords(final String ks) {
		final ArrayList<String> list = new ArrayList<String>();
		int start = 0;
		int end = 0;
		do {
			end = ks.indexOf(',', start);
			if (end == -1) {
				break;
			}
			final String val = ks.substring(start, end);
			start = end + 1;
			list.add(val);
		} while (end != -1);
		if (start < ks.length()) {
			list.add(ks.substring(start, ks.length()));
		}

		return list;
	}

	public String GetTag(final String fileName, final String tagType) {
		Document doc = null;

		try {
			doc = XMLUtil.parse(fileName);
			final NodeList nodeList = doc.getElementsByTagName(tagType);

			final int u = nodeList.getLength();

			for (int j = 0; j < u; j++) {
				final Element element = (Element) nodeList.item(j);

				final String userString = element.getTextContent();
				return userString;

			}
		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	public boolean ProcessKeywords(final String fileName) {
		Document doc = null;

		try {
			doc = XMLUtil.parse(fileName);
			final Element element = doc.getDocumentElement();

			final Element keywords = XMLUtil.getElement(element, "Keywords");
			if (keywords != null) {
				final String keywordsString = keywords.getTextContent();
				System.out.println(keywordsString);
				final List<String> keyList = GetKeywords(keywordsString);

				final ListIterator<String> keyListIterator = keyList.listIterator();
				while (keyListIterator.hasNext()) {
					final String nextKeyListElement = keyListIterator.next();
					final ListIterator<String> filterListIterator = filterList.listIterator();
					while (filterListIterator.hasNext()) {
						final String nextFilterListElement = filterListIterator.next();
						if (nextKeyListElement.equalsIgnoreCase(nextFilterListElement)) {
							return true;
						}
					}
				}

			}

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		// return true;
		return false;
	}

	public boolean ProcessTag(final String fileName, final String tagType, final String filter) {
		return GetTag(fileName, tagType).equalsIgnoreCase(filter) == true;
	}

}
