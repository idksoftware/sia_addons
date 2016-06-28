package idk.imgarchive.base.metadata;

import idk.Variant.VariantAttribute;

import java.util.ArrayList;
import java.util.Date;

//import com.drew.metadata.TagLabel;


public enum IPTCAttribute implements VariantAttribute {
	
	IMG_CREATOR("Creator", "Creator"),
	IMG_CREATORJOBTITLE("Creator Job Title", "CreatorJobtitle"),
	IMG_CREATORADDRESS("Creator Address", "CreatorAddress"),
	IMG_CREATORCITY("Creator City", "CreatorCity"),
	IMG_CREATORSTATE("Creator State", "CreatorState"),
	IMG_CREATORPOSTCODE("Creator PostCode", "CreatorPostCode"),
	IMG_CREATORCOUNTRY("Creator Country", "CreatorCountry"),
	IMG_CREATORPHONE("Creator Phone", "CreatorPhone"),
	IMG_CREATOREMAIL("Creator Email", "CreatorEmail"),
	IMG_CREATORWEBSITE("Creator Website", "CreatorWebsite"),

	// IPTC Core 1.1 Image Section
	IMG_DATECREATED("Date Created", "DateCreated"),
	IMG_INTELLECTUALGENRE("Intellectual Genre", "IntellectualGenre"),
	// Definition: A classification that describes the nature of content,
	// e.g. sermon, interview, etc., rather than the content itself.
	IMG_IPTCsCENECODE("IPTC Scene Code", "IPTCSceneCode"),
	IMG_GEOGRAPHICFIELDS("Geographic Fields", "GeographicFields"),
	IMG_LOCATION("Location", "Location"),
	IMG_CITY("City", "City"),
	IMG_STATE("State", "State"),
	IMG_COUNTRY("Country", "Country"),
	IMG_ISOCOUNTRYCODE("ISO Country Code", "ISOCountryCode"),
	// IPTC Core 1.1 Content section
	IMG_HEADLINE("Headline", "Headline"),
	IMG_DESCRIPTION("Description", "Description"),
	IMG_KEYWORDLIST("Keyword List", "KeywordList"),
	IMG_SUBJECTCODES("Subject Codes", "SubjectCodes"),
	IMG_DESCRIPTIONWRITER("Description Writer", "DescriptionWriter"),
	// IPTC Core 1.1 Status Section
	IMG_TITLE("Title", "title"),
	IMG_JOBIDENTNUMBER("Job Ident Number", "JobIdentNumber"),
	IMG_INSTRUCTIONS("Instructions", "Instructions"),
	IMG_CREDITLINE("Credit Line", "CreditLine"),
	IMG_SOURCE("Source", "Source"),
	IMG_COPYRIGHT("Copy Right", "CopyRight"),
	IMG_USAGERIGHTS("Usage Rights", "UsageRights"),
	IMG_COPYRIGHTURL("Copy Right URL", "CopyRightURL"),
	// IPTC Extension 1.1
	IMG_DIGITALIMAGEGUID("Digital Image GUID", "DigitalImageGUID"),
	IMG_KEYWORD("Keyword", "Keyword"),
		;
	
	public final static String IPTCProperties = "IPTCProperties";
	
	protected String friendlyLable = null;
	protected String xmlLable = null;
	protected VariantAttribute.AttribType attribType = null;
	
	IPTCAttribute(String friendlyLable, String xmlLable) {
		this.friendlyLable = friendlyLable;
		this.xmlLable = xmlLable;	
	}
	
	/**
	 * @return the friendlyLable
	 */
	public final String getFriendlyLable() {
		return friendlyLable;
	}
	/**
	 * @return the xmlLable
	 */
	public final String getXmlLable() {
		return xmlLable;
	}

	@Override
	public String getXmlNamespace() {
		return IPTCProperties;
	}

		
}
