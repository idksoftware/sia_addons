package idk.imgarchive.base.metadata;

import java.text.ParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import xmlutils.XMLReadHelper;
import xmlutils.XMLUtil;
import xmlutils.XmlWriter;

public class JounalInfo implements MetadataSection {

	private String checkedStatus = "No";
	private String inPrimaryStorage = "Yes";
	private String publishable = "Yes";
	private int timesBackedUp = 0;

	public JounalInfo() {
	}

	public JounalInfo(final Element rootElement) throws NumberFormatException, ParseException {
		read(rootElement);
	}

	/**
	 * @return the checkedStatus
	 */
	public final String getCheckedStatus() {
		return checkedStatus;
	}

	/**
	 * @return the inPrimaryStorage
	 */
	public final String getInPrimaryStorage() {
		return inPrimaryStorage;
	}

	/**
	 * @return the publishable
	 */
	public final String getPublishable() {
		return publishable;
	}

	/**
	 * @return the timesBackedUp
	 */
	public final int getTimesBackedUp() {
		return timesBackedUp;
	}

	@Override
	public void read(final Element rootElement) throws NumberFormatException, ParseException {
		final Element jounalElem = XMLUtil.getElement(rootElement, "Jounal");
		if (jounalElem == null) {
			return;
		}
		timesBackedUp = XMLReadHelper.integerValue(jounalElem, "TimesBackedUp", true, true);
		inPrimaryStorage = XMLReadHelper.stringValue(jounalElem, "InPrimaryStorage", true);
		checkedStatus = XMLReadHelper.stringValue(jounalElem, "CheckedStatus", true);
		publishable = XMLReadHelper.stringValue(jounalElem, "Publishable", true);
	}

	@Override
	public XmlWriter write(final XmlWriter xmlWriter) {
		xmlWriter.startElement("Jounal");

		xmlWriter.startElement("TimesBackedUp");
		xmlWriter.element(Integer.toString(timesBackedUp));
		xmlWriter.endElement("TimesBackedUp");
		xmlWriter.startElement("InPrimaryStorage");
		xmlWriter.element(inPrimaryStorage);
		xmlWriter.endElement("InPrimaryStorage");
		xmlWriter.startElement("CheckedStatus");
		xmlWriter.element(checkedStatus);
		xmlWriter.endElement("CheckedStatus");
		xmlWriter.startElement("Publishable");
		xmlWriter.element(publishable);
		xmlWriter.endElement("Publishable");

		xmlWriter.endElement("Jounal");
		return xmlWriter;
	}

	@Override
	public void merge(final MetadataSection obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Element createNode(final Element root, final Document doc) {
		// TODO Auto-generated method stub
		return null;
	}
}
