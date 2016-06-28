package idk.imgarchive.base.metadata;

import xmlutils.XMLReadHelper;
import idk.Variant.VariantAttribute;




public enum IdentityAttribute implements VariantAttribute {
	
	IMG_NUMBER("Number", "Number"),
	IMG_REVISION("Revision", "Revision"),
	IMG_LABEL("Label", "Label"),
	IMG_ORGINAL_RAW("OrginalRaw", "OrginalRaw"),
	IMG_ORGINAL_PIC("OrginalPic", "OrginalPic"),
		;
	
	public final static String CopyRight = "CopyRight";
	
	protected String friendlyLable = null;
	protected String xmlLable = null;
	protected VariantAttribute.AttribType attribType = null;
	
	IdentityAttribute(String friendlyLable, String xmlLable) {
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
		return CopyRight;
	}

		
}
