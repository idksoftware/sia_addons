package idk.imgarchive.base.metadata;

import java.util.Date;

import idk.Variant.VariantAttribute;



public enum MediaAttribute implements VariantAttribute {
	
	IMG_COPYRIGHT("Copy Right", "CopyRight"),
	IMG_HEIGHT("Height", "Height"),
	IMG_WIDTH("Width", "Width"),
	IMG_COLORPROFILE("ColorProfile", "ColorProfile"),
	IMG_COLORSPACE("ColorSpace", "ColorSpace"),
	IMG_COMMENTS("Comments", "Comments"),
	IMG_COMPRESSION("Compression", "Compression"),
	IMG_DATETIME("DateTime", "DateTime"),
	IMG_DATETIMEORIGINAL("DateTimeOriginal", "DateTimeOriginal"),
	IMG_DEPTH("Depth", "Depth"),

	IMG_ORIENTATION("Orientation", "Orientation"),
	IMG_SAMPLECOLOR("SampleColor", "SampleColor"),
	IMG_PRIMARYENCODING("PrimaryEncoding", "PrimaryEncoding"),
	IMG_RESOLUTION("Resolution", "Resolution"),
	IMG_MEDIATYPE("MediaType", "MediaType"),
	IMG_XRESOLUTION("XResolution", "XResolution"),
	IMG_YRESOLUTION("YResolution", "YResolution"),
	IMG_RESOLUTIONUNIT("ResolutionUnit", "ResolutionUnit"),
	IMG_IMAGEDETAILS("ImageDetails", "ImageDetails"),
		;
	
	public final static String CopyRight = "CopyRight";
	
	protected String friendlyLable = null;
	protected String xmlLable = null;
	protected VariantAttribute.AttribType attribType = null;
	
	MediaAttribute(String friendlyLable, String xmlLable) {
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
