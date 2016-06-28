package idk.imgarchive.base.metadata;

import idk.Variant.VariantAttribute;



public enum CopyRightAttribute implements VariantAttribute {
	
	IMG_COPYRIGHT("CopyRight", "CopyRight"),
	IMG_USAGERIGHTS("UsageRights", "UsageRights"),
	IMG_COPYRIGHTURL("CopyRightURL", "CopyRightURL")
		;
	
	public final static String CopyRight = "CopyRight";
	
	protected String friendlyLable = null;
	protected String xmlLable = null;
	protected VariantAttribute.AttribType attribType = null;
	
	CopyRightAttribute(String friendlyLable, String xmlLable) {
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
