package idk.imgarchive.base.metadata;

import org.w3c.dom.Element;

import idk.Variant.InvalidFormatException;
import idk.Variant.VariantAttrMap;
import idk.Variant.VariantDirectory;
import idk.Variant.VariantListValue;
import idk.Variant.VariantStringValue;


public class IPTCPropertiesDirectory extends VariantDirectory {

	   private final VariantAttrMap _variantAttrMap = new VariantAttrMap();
	    
	   public IPTCPropertiesDirectory() {
		   loadMap();
		
	    }

		public IPTCPropertiesDirectory(Element root) throws InvalidFormatException {
			read(root);
		}

		@Override
		public VariantAttrMap getAttrMap() {
			return _variantAttrMap;
		}
		
		public void merge(final IPTCPropertiesDirectory other) throws InvalidFormatException {
			super.merge(other);
		}

		@Override
		public String getXMLName() {
			return "IPTCProperties";
		}

		@Override
		protected Object clone() {
			IPTCPropertiesDirectory vd = new IPTCPropertiesDirectory();
			try {
				vd.merge(this);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
			return vd;
		}

		@Override
		protected void loadMap() {
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_CREATOR, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_CREATORJOBTITLE, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_CREATORADDRESS, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_CREATORCITY, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_CREATORSTATE, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_CREATORPOSTCODE, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_CREATORCOUNTRY, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_CREATORPHONE, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_CREATOREMAIL, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_CREATORWEBSITE, false));

				// IPTC Core 1.1 Image Section
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_DATECREATED, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_INTELLECTUALGENRE, false));
				// Definition: A classification that describes the nature of content,
				// e.g. sermon, interview, etc., rather than the content itself.
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_IPTCsCENECODE, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_GEOGRAPHICFIELDS, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_LOCATION, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_CITY, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_STATE, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_COUNTRY, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_ISOCOUNTRYCODE, false));
				// IPTC Core 1.1 Content section
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_HEADLINE, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_DESCRIPTION, false));
			   _variantAttrMap.put(new VariantListValue(IPTCAttribute.IMG_KEYWORDLIST,
		    			new VariantStringValue(IPTCAttribute.IMG_KEYWORD, false),  false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_SUBJECTCODES, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_DESCRIPTIONWRITER, false));
				// IPTC Core 1.1 Status Section
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_TITLE, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_JOBIDENTNUMBER, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_INSTRUCTIONS, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_CREDITLINE, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_SOURCE, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_COPYRIGHT, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_USAGERIGHTS, false));
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_COPYRIGHTURL, false));
				// IPTC Extension 1.1
			   _variantAttrMap.put(new VariantStringValue(IPTCAttribute.IMG_DIGITALIMAGEGUID, false));
			
		}

		@Override
		public boolean getObligatory() {
			return false;
		}

		@Override
		public String dtdComment() {
			return "International Press Telecommunications Council (IPTC) Photo Matadata standard was" + "\r\n" +
					"developed for professal use with a focus on news and stock photos. Image Archive" + "\r\n" +
					"supports the metadata standard in the section"; 
		}
}

		