package idk.imgarchive.base.metadata;

import java.text.ParseException;

import org.w3c.dom.Element;

import xmlutils.XMLReadHelper;
import xmlutils.XMLUtil;
import xmlutils.XmlWriter;

public class IdentityInfo {

	public long imageNumber = 1;
	public String label = "";
	public String orginalPic = "";
	public String orginalRaw = "";
	public int revisonNumber = 1;

	public IdentityInfo(final Element rootElement) throws ParseException {
		read(rootElement);
	}

	public IdentityInfo(final long imageNumber, final int revisonNumber, final String label) {
		init(imageNumber, revisonNumber, label);
	}

	public IdentityInfo(final long imageNumber, final String label, final String orginalRaw, final String orginalPic) {
		init(imageNumber, 1, label);
		this.orginalRaw = orginalRaw;
		this.orginalPic = orginalPic;
	}

	/**
	 * @return the imageNumber
	 */
	public final long getImageNumber() {
		return imageNumber;
	}

	/**
	 * @return the label
	 */
	public final String getLabel() {
		return label;
	}

	/**
	 * @return the orginalPic
	 */
	public final String getOrginalPic() {
		return orginalPic;
	}

	/**
	 * @return the orginalRaw
	 */
	public final String getOrginalRaw() {
		return orginalRaw;
	}

	/**
	 * @return the revisonNumber
	 */
	public final int getRevisonNumber() {
		return revisonNumber;
	}

	public final String getInfoFilename() {
		return makeInfoFilename(imageNumber);
	}

	public final String getExifFilename() {
		return makeExifFilename(imageNumber);
	}

	public final String getIPTCFilename() {
		return makeIPTCFilename(imageNumber);
	}

	public void init(final long imageNumber, final int revisonNumber, final String label) {
		this.imageNumber = imageNumber;
		this.revisonNumber = revisonNumber;
		this.label = label;
	}

	public void read(final Element rootElement) throws ParseException {
		final Element identityElem = XMLUtil.getElement(rootElement, "Identity");
		if (identityElem == null) {
			return;
		}
		imageNumber = XMLReadHelper.longValue(identityElem, "Number", true, false);
		revisonNumber = XMLReadHelper.integerValue(identityElem, "Revision", true, true);
		label = XMLReadHelper.stringValue(identityElem, "Label", true);
		// Note one or other needs to be present. However there are times when there is:
		// 1) a RAW file only.
		// 2) a PIC file only
		// 3) both
		orginalRaw = XMLReadHelper.stringValue(identityElem, "OrginalRaw", false);
		orginalPic = XMLReadHelper.stringValue(identityElem, "OrginalPic", false);
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public final void setLabel(final String label) {
		this.label = label;
	}

	public XmlWriter write(final XmlWriter xmlWriter) {
		xmlWriter.startElement("Identity");

		xmlWriter.startElement("Number");
		xmlWriter.element(Long.toString(imageNumber));
		xmlWriter.endElement("Number");
		xmlWriter.startElement("Revision");
		xmlWriter.element(Integer.toString(revisonNumber));
		xmlWriter.endElement("Revision");
		xmlWriter.startElement("Label");
		xmlWriter.element(label);
		xmlWriter.endElement("Label");
		xmlWriter.startElement("OrginalRaw");
		if (orginalRaw == null) {
			xmlWriter.element("");
		} else {
			xmlWriter.element(orginalRaw);
		}
		xmlWriter.endElement("OrginalRaw");
		xmlWriter.startElement("OrginalPic");
		if (orginalPic == null) {
			xmlWriter.element("");
		} else {
			xmlWriter.element(orginalPic);
		}
		xmlWriter.endElement("OrginalPic");
		xmlWriter.endElement("Identity");

		return xmlWriter;
	}

	public final static String makeInfoFilename(final long id) {
		return String.format("IFO%08X.xml", id);
	}

	public final static String makeExifFilename(final long id) {
		return String.format("EXF%08X.xml", id);
	}

	public final static String makeIPTCFilename(final long id) {
		return String.format("IPC%08X.xml", id);
	}
}
