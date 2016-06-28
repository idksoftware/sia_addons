package idk.imgarchive.base.metadata;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xmlutils.DateUtils;
import xmlutils.XMLReadHelper;
import xmlutils.XMLUtil;
import xmlutils.XmlWriter;

/**
 * This is used as a Preset set of IPTC information as most of the IPTC is related to the
 * Author.
 *  
 * @author 501726576
 *
 */

public class IPTCInfo implements MetadataSection {

	// IPTC Core 1.1 Contact section
	public String creator = null;// (LightRoom)// new
	public String creatorJobtitle = null;// (LightRoom)// new
	public String creatorAddress = null; // new
	public String creatorCity = null; // new
	public String creatorState = null; // new
	public String creatorPostCode = null; // new
	public String creatorCountry = null; // new
	public String creatorPhone = null;// new
	public String creatorEmail = null; // new
	public String creatorWebsite = null; // new

	// IPTC Core 1.1 Image Section
	public Date dateCreated = null;// new
	public String intellectualGenre = null;// new
	// Definition: A classification that describes the nature of content,
	// e.g. sermon, interview, etc., rather than the content itself.
	public String iptcSceneCode = null; // new
	public String geographicFields = null;
	public String location = null;
	public String city = null; // new
	public String state = null; // new
	public String country = null; // new
	public String isoCountryCode = null;// new
	// IPTC Core 1.1 Content section
	public String headline = null;// new
	public String description = null;// new
	public ArrayList<String> keywordList = new ArrayList<String>();
	public String subjectCodes = null;// new
	public String descriptionWriter = null;// new
	// IPTC Core 1.1 Status Section
	public String title = null;
	public String jobIdentNumber = null;// new
	public String instructions = null;
	public String creditLine = null;
	public String source = null;// new
	public String copyRight = null;
	public String usageRights = null;
	public String copyRightURL;// new
	// IPTC Extension 1.1
	public String digitalImageGUID = null;

	public IPTCInfo() {
	}

	public IPTCInfo(final Element rootElement) throws NumberFormatException, ParseException {
		read(rootElement);
	}

	public void write(final String filepath, final String name) {
		final XmlWriter xmlWriter = new XmlWriter(filepath, name);
		xmlWriter.startElement("Document");
		write(xmlWriter);
		xmlWriter.endElement("Document");
		xmlWriter.close();
	}

	public void merge(final IPTCInfo obj) {

		//
		// IPTC Core 1.1 Contact section
		//
		if (obj.creator != null) {
			creator = obj.creator;
		}

		if (obj.creatorJobtitle != null) {
			creatorJobtitle = obj.creatorJobtitle;
		}

		if (obj.creatorAddress != null) {
			creatorAddress = obj.creatorAddress;
		}

		if (obj.creatorCity != null) {
			creatorCity = obj.creatorCity;
		}

		if (obj.creatorState != null) {
			creatorState = obj.creatorState;
		}

		if (obj.creatorPostCode != null) {
			creatorPostCode = obj.creatorPostCode;
		}

		if (obj.creatorCountry != null) {
			creatorCountry = obj.creatorCountry;
		}

		if (obj.creatorPhone != null) {
			creatorPhone = obj.creatorPhone;
		}

		if (obj.creatorEmail != null) {
			creatorEmail = obj.creatorEmail;
		}

		if (obj.creatorWebsite != null) {
			creatorWebsite = obj.creatorWebsite;
		}
		//
		// IPTC Core 1.1 Image Section
		//
		if (obj.dateCreated != null) {
			dateCreated = obj.dateCreated;
		}
		// Definition: A classification that describes the nature of content,
		// e.g. sermon, interview, etc., rather than the content itself.
		if (obj.intellectualGenre != null) {
			intellectualGenre = obj.intellectualGenre;
		}

		if (obj.iptcSceneCode != null) {
			iptcSceneCode = obj.iptcSceneCode;
		}

		if (obj.geographicFields != null) {
			geographicFields = obj.geographicFields;
		}

		if (obj.location != null) {
			location = obj.location;
		}

		if (obj.city != null) {
			city = obj.city;
		}

		if (obj.state != null) {
			state = obj.state;
		}

		if (obj.country != null) {
			country = obj.country;
		}

		if (obj.isoCountryCode != null) {
			isoCountryCode = obj.isoCountryCode;
		}
		//
		// IPTC Core 1.1 Content section
		//
		if (obj.headline != null) {
			headline = obj.headline;
		}

		if (obj.description != null) {
			description = obj.description;
		}

		for (final String item : obj.keywordList) {
			boolean found = false;
			for (final String thisItem : keywordList) {
				if (thisItem.matches(item) == true) {
					found = true;
					break;
				}
				if (found == false) {
					keywordList.add(item);
				}
			}
		}

		if (obj.subjectCodes != null) {
			subjectCodes = obj.subjectCodes;
		}

		if (obj.descriptionWriter != null) {
			descriptionWriter = obj.descriptionWriter;
		}
		//
		// IPTC Core 1.1 Status Section
		//
		if (obj.title != null) {
			title = obj.title;
		}

		if (obj.jobIdentNumber != null) {
			jobIdentNumber = obj.jobIdentNumber;
		}

		if (obj.instructions != null) {
			instructions = obj.instructions;
		}

		if (obj.creditLine != null) {
			creditLine = obj.creditLine;
		}

		if (obj.source != null) {
			source = obj.source;
		}

		if (obj.copyRight != null) {
			copyRight = obj.copyRight;
		}

		if (obj.usageRights != null) {
			usageRights = obj.usageRights;
		}

		if (obj.copyRightURL != null) {
			copyRightURL = obj.copyRightURL;
		}
		//
		// IPTC Extension 1.1
		//
		if (obj.digitalImageGUID != null) {
			digitalImageGUID = obj.digitalImageGUID;
		}

	}

	@Override
	public XmlWriter write(final XmlWriter xmlWriter) {

		xmlWriter.comment("IPTC Core 1.1");
		xmlWriter.startElement("Iptc");
		xmlWriter.comment("IPTC Core 1.1 Contact section");
		xmlWriter.startElement("Contact");
		// IPTC Core 1.1 Contact section
		xmlWriter.startElement("creator");
		xmlWriter.element(creator);
		xmlWriter.endElement("creator");

		xmlWriter.startElement("Jobtitle");
		xmlWriter.element(creatorJobtitle);
		xmlWriter.endElement("Jobtitle");

		xmlWriter.startElement("Address");
		xmlWriter.element(creatorAddress);
		xmlWriter.endElement("Address");

		xmlWriter.startElement("City");
		xmlWriter.element(creatorCity);
		xmlWriter.endElement("City");

		xmlWriter.startElement("State");
		xmlWriter.element(creatorState);
		xmlWriter.endElement("State");

		xmlWriter.startElement("PostCode");
		xmlWriter.element(creatorPostCode);
		xmlWriter.endElement("PostCode");

		xmlWriter.startElement("Country");
		xmlWriter.element(creatorCountry);
		xmlWriter.endElement("Country");

		xmlWriter.startElement("Phone");
		xmlWriter.element(creatorPhone);
		xmlWriter.endElement("Phone");

		xmlWriter.startElement("Email");
		xmlWriter.element(creatorEmail);
		xmlWriter.endElement("Email");

		xmlWriter.startElement("Website");
		xmlWriter.element(creatorWebsite);
		xmlWriter.endElement("Website");

		xmlWriter.endElement("Contact");
		xmlWriter.comment("IPTC Core 1.1 Image Section");
		xmlWriter.startElement("Image");
		// IPTC Core 1.1 Image Section
		xmlWriter.startElement("DateCreated");
		if (dateCreated != null) {
			xmlWriter.element(DateUtils.formatDDMMYYYY(dateCreated));
		}
		xmlWriter.endElement("DateCreated");

		xmlWriter.startElement("IntellectualGenre");
		xmlWriter.element(intellectualGenre);
		xmlWriter.endElement("IntellectualGenre");

		xmlWriter.startElement("IptcSceneCode");
		xmlWriter.element(iptcSceneCode);
		xmlWriter.endElement("IptcSceneCode");

		xmlWriter.startElement("GeographicFields");
		xmlWriter.element(geographicFields);
		xmlWriter.endElement("GeographicFields");

		xmlWriter.startElement("Location");
		xmlWriter.element(location);
		xmlWriter.endElement("Location");

		xmlWriter.startElement("City");
		xmlWriter.element(city);
		xmlWriter.endElement("City");

		xmlWriter.startElement("State");
		xmlWriter.element(state);
		xmlWriter.endElement("State");

		xmlWriter.startElement("Country");
		xmlWriter.element(country);
		xmlWriter.endElement("Country");

		xmlWriter.startElement("IsoCountryCode");
		xmlWriter.element(isoCountryCode);
		xmlWriter.endElement("IsoCountryCode");
		xmlWriter.endElement("Image");

		xmlWriter.comment("IPTC Core 1.1 Content section");
		xmlWriter.startElement("Content");

		xmlWriter.startElement("Headline");
		xmlWriter.element(headline);
		xmlWriter.endElement("Headline");

		xmlWriter.startElement("Description");
		xmlWriter.element(description);
		xmlWriter.endElement("Description");

		xmlWriter.startElement("Keywords");
		for (final String item : keywordList) {
			xmlWriter.startElement("Keyword");
			xmlWriter.element(item);
			xmlWriter.endElement("Keyword");
		}
		xmlWriter.endElement("Keywords");

		xmlWriter.startElement("SubjectCodes");
		xmlWriter.element(subjectCodes);
		xmlWriter.endElement("SubjectCodes");

		xmlWriter.startElement("SubjectCodes");
		xmlWriter.element(subjectCodes);
		xmlWriter.endElement("SubjectCodes");

		xmlWriter.startElement("DescriptionWriter");
		xmlWriter.element(descriptionWriter);
		xmlWriter.endElement("DescriptionWriter");
		xmlWriter.endElement("Content");

		xmlWriter.comment("IPTC Core 1.1 Status Section");
		xmlWriter.startElement("Status");

		xmlWriter.startElement("Title");
		xmlWriter.element(title);
		xmlWriter.endElement("Title");

		xmlWriter.startElement("JobIdentNumber");
		xmlWriter.element(jobIdentNumber);
		xmlWriter.endElement("JobIdentNumber");

		xmlWriter.startElement("Instructions");
		xmlWriter.element(instructions);
		xmlWriter.endElement("Instructions");

		xmlWriter.startElement("CreditLine");
		xmlWriter.element(creditLine);
		xmlWriter.endElement("CreditLine");

		xmlWriter.startElement("Source");
		xmlWriter.element(source);
		xmlWriter.endElement("Source");

		xmlWriter.startElement("CopyRight");
		xmlWriter.element(copyRight);
		xmlWriter.endElement("CopyRight");

		xmlWriter.startElement("UsageRights");
		xmlWriter.element(usageRights);
		xmlWriter.endElement("UsageRights");

		xmlWriter.startElement("CopyRightURL");
		xmlWriter.element(copyRightURL);
		xmlWriter.endElement("CopyRightURL");

		xmlWriter.endElement("Status");
		xmlWriter.comment("IPTC Extension 1.1");
		xmlWriter.startElement("Extension");

		xmlWriter.startElement("DigitalImageGUID");
		xmlWriter.element(digitalImageGUID);
		xmlWriter.endElement("DigitalImageGUID");

		xmlWriter.endElement("Extension");

		xmlWriter.endElement("IPTC");
		return xmlWriter;
	}

	public static void readIptcContact(final Element body, final IPTCInfo metadataInfo) throws ParseException {
		final Element iptcElem = XMLUtil.getElement(body, "Contact");
		if (iptcElem == null) {
			return;
		}
		// IPTC Core 1.1 Contact section
		metadataInfo.creator = XMLReadHelper.stringValue(iptcElem, "Creator", false);
		metadataInfo.creatorJobtitle = XMLReadHelper.stringValue(iptcElem, "CreatorJobtitle", false);
		metadataInfo.creatorAddress = XMLReadHelper.stringValue(iptcElem, "CreatorAddress", false);
		metadataInfo.creatorCity = XMLReadHelper.stringValue(iptcElem, "CreatorCity", false);
		metadataInfo.creatorState = XMLReadHelper.stringValue(iptcElem, "CreatorState", false);
		metadataInfo.creatorPostCode = XMLReadHelper.stringValue(iptcElem, "CreatorPostCode", false);
		metadataInfo.creatorCountry = XMLReadHelper.stringValue(iptcElem, "CreatorCountry", false);
		metadataInfo.creatorPhone = XMLReadHelper.stringValue(iptcElem, "CreatorPhone", false);
		metadataInfo.creatorEmail = XMLReadHelper.stringValue(iptcElem, "CreatorEmail", false);
		metadataInfo.creatorWebsite = XMLReadHelper.stringValue(iptcElem, "CreatorWebsite", false);
	}

	public static void readIptcImage(final Element body, final IPTCInfo metadataInfo) throws ParseException {
		final Element iptcElem = XMLUtil.getElement(body, "Image");

		// IPTC Core 1.1 Contact section
		metadataInfo.dateCreated = XMLReadHelper.dateValue(iptcElem, "DateCreated", false);
		metadataInfo.intellectualGenre = XMLReadHelper.stringValue(iptcElem, "IntellectualGenre", false);
		metadataInfo.iptcSceneCode = XMLReadHelper.stringValue(iptcElem, "IptcSceneCode", false);
		metadataInfo.geographicFields = XMLReadHelper.stringValue(iptcElem, "GeographicFields", false);
		metadataInfo.location = XMLReadHelper.stringValue(iptcElem, "Location", false);
		metadataInfo.city = XMLReadHelper.stringValue(iptcElem, "City", false);
		metadataInfo.state = XMLReadHelper.stringValue(iptcElem, "State", false);
		metadataInfo.country = XMLReadHelper.stringValue(iptcElem, "Country", false);
		metadataInfo.isoCountryCode = XMLReadHelper.stringValue(iptcElem, "IsoCountryCode", false);
	}

	public static void readIptcContent(final Element body, final IPTCInfo metadataInfo) throws ParseException {
		final Element iptcElem = XMLUtil.getElement(body, "Content");

		// IPTC Core 1.1 Content section
		metadataInfo.headline = XMLReadHelper.stringValue(iptcElem, "Headline", false);
		metadataInfo.description = XMLReadHelper.stringValue(iptcElem, "Description", false);
		final Element keywordsElem = XMLUtil.getElement(iptcElem, "Keywords");
		final NodeList itemList = keywordsElem.getChildNodes();
		final int n = itemList.getLength();
		Element itemElem = null;
		for (int i = 0; i < n; i++) {
			if ((itemElem = XMLReadHelper.isElement(itemList, i)) != null) {
				if (itemElem.getNodeName().matches("Keyword")) {
					String item = null;
					if ((item = itemElem.getTextContent()) == null) {
						return;
					}
					metadataInfo.keywordList.add(item);

				}
			}
		}
		metadataInfo.subjectCodes = XMLReadHelper.stringValue(iptcElem, "SubjectCodes", false);
		metadataInfo.descriptionWriter = XMLReadHelper.stringValue(iptcElem, "DescriptionWriter", false);
	}

	public static void readIptcStatus(final Element body, final IPTCInfo metadataInfo) throws ParseException {
		final Element iptcElem = XMLUtil.getElement(body, "Status");

		// IPTC Core 1.1 Status Section
		metadataInfo.title = XMLReadHelper.stringValue(iptcElem, "Title", false);
		metadataInfo.jobIdentNumber = XMLReadHelper.stringValue(iptcElem, "JobIdentNumber", false);
		metadataInfo.instructions = XMLReadHelper.stringValue(iptcElem, "Instructions", false);
		metadataInfo.creditLine = XMLReadHelper.stringValue(iptcElem, "CreditLine", false);
		metadataInfo.source = XMLReadHelper.stringValue(iptcElem, "Source", false);
		metadataInfo.copyRight = XMLReadHelper.stringValue(iptcElem, "CopyRight", false);
		metadataInfo.usageRights = XMLReadHelper.stringValue(iptcElem, "UsageRights", false);
		metadataInfo.copyRightURL = XMLReadHelper.stringValue(iptcElem, "CopyRightURL", false);
	}

	public static void readIptcExtension(final Element body, final IPTCInfo metadataInfo) throws ParseException {
		final Element iptcElem = XMLUtil.getElement(body, "Extension");

		// IPTC Core 1.1 Status Section
		metadataInfo.digitalImageGUID = XMLReadHelper.stringValue(iptcElem, "DigitalImageGUID", false);
	}

	public static void readIptc(final Element body, final IPTCInfo iptcInfo) throws ParseException {
		final Element iptcElem = XMLUtil.getElement(body, "Iptc");
		readIptcContact(iptcElem, iptcInfo);
		readIptcImage(iptcElem, iptcInfo);
		readIptcContent(iptcElem, iptcInfo);
		readIptcStatus(iptcElem, iptcInfo);
		readIptcExtension(iptcElem, iptcInfo);
	}

	@Override
	public void read(final Element rootElement) throws NumberFormatException, ParseException {
		readIptc(rootElement, this);
	}

	public static IPTCInfo create(final Element body) throws ParseException {
		final IPTCInfo iptcInfo = new IPTCInfo(body);
		IPTCInfo.readIptc(body, iptcInfo);
		return iptcInfo;
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
