package idk.imgarchive.base.metadata;

import java.text.ParseException;

import org.w3c.dom.Element;

import xmlutils.XMLReadHelper;
import xmlutils.XmlWriter;

/**
 * IPTC Core 1.1 Contact section The Contact Info fields provide a generic
 * structure for storing information which should make it easy to get in contact
 * with the person or organisation that created this image.
 */

public class IPTCContactInfo {

	/**
	 * Creator or Author, contains preferably the name of the person who created
	 * the content of this item, a photographer for photographs, a graphic
	 * artist for graphics, or a writer for textual news. If it is not
	 * appropriate to add the name of a person, the name of a company or
	 * organization could be applied as well. Provider Identifies the provider
	 * of the item, who is not necessarily the owner/creator.
	 */
	private String creator = null;

	/**
	 * This field should contain the job title of the person who created the
	 * photograph or work. Examples might include titles such as: Staff
	 * Photographer, Independent Commercial Photographer, or staff writer. Since
	 * this is a qualifier for the Creator field, the Creator field must also be
	 * filled out.
	 * 
	 * Note: This field is “shared” with the “Author Title” field in the
	 * Description Panel of the Adobe File Info field
	 */
	private String creatorJobTitle = null;

	/**
	 * The address field is a multi-line field. Enter the street name and number
	 * or postbox to which mail should be sent, and a company name or location
	 * (building name, floor number) if necessary.
	 */
	private String contactAddress = null;

	/**
	 * Enter the name of the city in which the primary contact’s business is
	 * located.
	 */
	private String contactCity = null;

	/**
	 * Enter the name of the State or Province in which the primary contact’s
	 * business is located. Since the abbreviation for a State or Province may
	 * be unknown to those viewing your metadata internationally, consider using
	 * the full spelling of the name.
	 */

	private String stateProvince = null;

	/**
	 * Enter the local postal code (such as ZIP code) in which the primary
	 * contact’s business is located.
	 */
	private String contactPostalCode = null;

	/**
	 * Enter the name of the country (or ISO Country Code) in which the primary
	 * contact’s business is located. For ease of the end user you might
	 * consider using a combination of country name and code - e.g. "Germany -
	 * DE or "Great Britain - UK"
	 */
	private String contactCountry = null;

	/**
	 * Enter the primary contact’s business or work telephone number here.
	 * Multiple numbers can be given, separated by a comma. Be sure to include
	 * the complete international format of a phone number which is:
	 * +{countrycode} ({regional code}) {phone number} - {extension if required}
	 * e.g. +1 (212) 1234578
	 */
	private String contactPhone = null;

	/**
	 * Enter the primary contact’s business or work email address, such as
	 * name@domain.com. Multiple email addresses can be given, separated by a
	 * comma.
	 */
	private String contactEmail = null;

	/**
	 * Enter the URL or web address for the primary contact’s business. Multiple
	 * addresses can be given, separated by a comma.
	 */
	private String contactWebsite = null;

	/**
	 * @return the creator
	 */
	public final String getCreator() {
		return creator;
	}

	/**
	 * @param creator
	 *            the creator to set
	 */
	public final void setCreator(final String creator) {
		this.creator = creator;
	}

	/**
	 * @return the creatorJobTitle
	 */
	public final String getCreatorJobTitle() {
		return creatorJobTitle;
	}

	/**
	 * @param creatorJobTitle
	 *            the creatorJobTitle to set
	 */
	public final void setCreatorJobTitle(final String creatorJobTitle) {
		this.creatorJobTitle = creatorJobTitle;
	}

	/**
	 * @return the contactAddress
	 */
	public final String getContactAddress() {
		return contactAddress;
	}

	/**
	 * @param contactAddress
	 *            the contactAddress to set
	 */
	public final void setContactAddress(final String contactAddress) {
		this.contactAddress = contactAddress;
	}

	/**
	 * @return the contactCity
	 */
	public final String getContactCity() {
		return contactCity;
	}

	/**
	 * @param contactCity
	 *            the contactCity to set
	 */
	public final void setContactCity(final String contactCity) {
		this.contactCity = contactCity;
	}

	/**
	 * @return the stateProvince
	 */
	public final String getStateProvince() {
		return stateProvince;
	}

	/**
	 * @param stateProvince
	 *            the stateProvince to set
	 */
	public final void setStateProvince(final String stateProvince) {
		this.stateProvince = stateProvince;
	}

	/**
	 * @return the contactPostalCode
	 */
	public final String getContactPostalCode() {
		return contactPostalCode;
	}

	/**
	 * @param contactPostalCode
	 *            the contactPostalCode to set
	 */
	public final void setContactPostalCode(final String contactPostalCode) {
		this.contactPostalCode = contactPostalCode;
	}

	/**
	 * @return the contactCountry
	 */
	public final String getContactCountry() {
		return contactCountry;
	}

	/**
	 * @param contactCountry
	 *            the contactCountry to set
	 */
	public final void setContactCountry(final String contactCountry) {
		this.contactCountry = contactCountry;
	}

	/**
	 * @return the contactPhone
	 */
	public final String getContactPhone() {
		return contactPhone;
	}

	/**
	 * @param contactPhone
	 *            the contactPhone to set
	 */
	public final void setContactPhone(final String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * @return the contactEmail
	 */
	public final String getContactEmail() {
		return contactEmail;
	}

	/**
	 * @param contactEmail
	 *            the contactEmail to set
	 */
	public final void setContactEmail(final String contactEmail) {
		this.contactEmail = contactEmail;
	}

	/**
	 * @return the contactWebsite
	 */
	public final String getContactWebsite() {
		return contactWebsite;
	}

	/**
	 * @param contactWebsite
	 *            the contactWebsite to set
	 */
	public final void setContactWebsite(final String contactWebsite) {
		this.contactWebsite = contactWebsite;
	}

	public static IPTCContactInfo read(final Element iptcElem) throws ParseException {
		// IPTC Core 1.1 Contact section
		final IPTCContactInfo iptcContactInfo = new IPTCContactInfo();
		read(iptcElem, iptcContactInfo);
		return iptcContactInfo;
	}

	public static void read(final Element iptcElem, final IPTCContactInfo iptcContactInfo) throws ParseException {
		iptcContactInfo.creator = XMLReadHelper.stringValue(iptcElem, "Creator", false);
		iptcContactInfo.creatorJobTitle = XMLReadHelper.stringValue(iptcElem, "CreatorJobtitle", false);
		iptcContactInfo.contactAddress = XMLReadHelper.stringValue(iptcElem, "CreatorAddress", false);
		iptcContactInfo.contactCity = XMLReadHelper.stringValue(iptcElem, "CreatorCity", false);
		iptcContactInfo.stateProvince = XMLReadHelper.stringValue(iptcElem, "CreatorState", false);
		iptcContactInfo.contactPostalCode = XMLReadHelper.stringValue(iptcElem, "CreatorPostCode", false);
		iptcContactInfo.contactCountry = XMLReadHelper.stringValue(iptcElem, "CreatorCountry", false);
		iptcContactInfo.contactPhone = XMLReadHelper.stringValue(iptcElem, "CreatorPhone", false);
		iptcContactInfo.contactEmail = XMLReadHelper.stringValue(iptcElem, "CreatorEmail", false);
		iptcContactInfo.contactWebsite = XMLReadHelper.stringValue(iptcElem, "CreatorWebsite", false);

	}

	public XmlWriter write(final XmlWriter xmlWriter) {
		xmlWriter.startElement("Contact");
		// IPTC Core 1.1 Contact section
		xmlWriter.startElement("creator");
		xmlWriter.element(creator);
		xmlWriter.endElement("creator");

		xmlWriter.startElement("Jobtitle");
		xmlWriter.element(creatorJobTitle);
		xmlWriter.endElement("Jobtitle");

		xmlWriter.startElement("Address");
		xmlWriter.element(contactAddress);
		xmlWriter.endElement("Address");

		xmlWriter.startElement("City");
		xmlWriter.element(contactCity);
		xmlWriter.endElement("City");

		xmlWriter.startElement("State");
		xmlWriter.element(stateProvince);
		xmlWriter.endElement("State");

		xmlWriter.startElement("PostCode");
		xmlWriter.element(contactPostalCode);
		xmlWriter.endElement("PostCode");

		xmlWriter.startElement("Country");
		xmlWriter.element(contactCountry);
		xmlWriter.endElement("Country");

		xmlWriter.startElement("Phone");
		xmlWriter.element(contactPhone);
		xmlWriter.endElement("Phone");

		xmlWriter.startElement("Email");
		xmlWriter.element(contactEmail);
		xmlWriter.endElement("Email");

		xmlWriter.startElement("Website");
		xmlWriter.element(contactWebsite);
		xmlWriter.endElement("Website");

		xmlWriter.endElement("Contact");
		return xmlWriter;
	}

}
