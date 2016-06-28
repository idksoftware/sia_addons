package idk.imgarchive.base.metadata;

import idk.Variant.VariantAttribute;



public enum FileAttribute implements VariantAttribute {
	
    IMG_ORIGINAL_FILE("Original File", "OriginalFile"),
    IMG_LAST_MODIFIED("Last Modified", "LastModified"),
    IMG_SIZE("Size", "Size"),
    IMG_CRC("Crc", "Crc"),
    IMG_MD5("Md5", "Md5"),
    IMG_ARCHIVE_NAME("Archive Name", "ArchiveName"),
    IMG_UUID("UUID", "UUID")
		;
	
	public final static String FILE = "File";
	
	protected String friendlyLable = null;
	protected String xmlLable = null;
	protected VariantAttribute.AttribType attribType = null;
	
	FileAttribute(String friendlyLable, String xmlLable) {
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
		return FILE;
	}

		
}
