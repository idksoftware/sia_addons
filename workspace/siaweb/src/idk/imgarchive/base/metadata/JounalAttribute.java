package idk.imgarchive.base.metadata;

import idk.Variant.VariantAttribute;


public enum JounalAttribute implements VariantAttribute {
    
    IMG_IN_PRIMARY_STORAGE("In Primary Storage", "InPrimaryStorage"),
    IMG_CHECKED_STATUS("CheckedStatus", "CheckedStatus"),
    IMG_PUBLISHABLE("Publishable", "Publishable"),
    IMG_TIMES_BACKED_UP("Times Backe dUp", "TimesBackedUp")
		;
	
	public final static String CopyRight = "CopyRight";
	
	protected String friendlyLable = null;
	protected String xmlLable = null;
	protected VariantAttribute.AttribType attribType = null;
	
	JounalAttribute(String friendlyLable, String xmlLable) {
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
