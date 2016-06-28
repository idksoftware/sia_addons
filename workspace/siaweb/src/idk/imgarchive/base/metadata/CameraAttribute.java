package idk.imgarchive.base.metadata;

import idk.Variant.VariantAttribute;

public enum CameraAttribute implements VariantAttribute {
	
	
	IMG_F_NUMBER("fNumber", "FNumber"),

	IMG_DATE_TIME_DIGITIZED(" dateTimeDigitized", "DateTimeDigitized"),
	IMG_COMPONENTSCONFIGURATION(" componentsConfiguration", "ComponentsConfiguration"),

	IMG_FLASH_PIX_VERSION(" flashPixVersion", "FlashPixVersion"),
	IMG_FILE_SOURCE(" fileSource", "FileSource"),
	IMG_SCENE_TYPE(" sceneType", "SceneType"),

	IMG_EMBEDDED_METADATA(" embeddedMetadata", "EmbeddedMetadata"),
	IMG_EXIF_VERSION(" exifVersion", "ExifVersion"),
	IMG_EXPOSURE_BIAS(" exposureBias", "ExposureBias"),
	IMG_EXPOSURE_PROGRAM(" exposureProgram", "ExposureProgram"),
	IMG_EXPOSURE_TIME(" exposureTime", "ExposureTime"),
	IMG_FLASH(" flash", "Flash"),
	IMG_VIEW_ROTATION(" viewRotation", "ViewRotation"),
	IMG_WHITE_BALANCE(" whiteBalance", "WhiteBalance"),
	IMG_FOCAL_LENGTH(" focalLength", "FocalLength"),

	IMG_IMAGE_DETAILS(" imageDetails", "ImageDetails"),
	IMG_ISO_SPEED_RATING(" ISOSpeedRating", "ISOSpeedRating"),
	IMG_MEDIA_TYPE(" mediaType", "MediaType"),
	IMG_METERING_MODE(" meteringMode", "MeteringMode"),
	IMG_LIGHT_SOURCE(" lightSource", "LightSource"),
	IMG_MAKER(" maker", "Maker"),
	IMG_MODEL(" model", "Model"),
	IMG_ORIENTATION("Orientation", "Orientation"),
	IMG_PAGES(" pages", "Pages"),
		;
	
	public final static String CopyRight = "CopyRight";
	
	protected String friendlyLable = null;
	protected String xmlLable = null;
	protected VariantAttribute.AttribType attribType = null;
	
	CameraAttribute(String friendlyLable, String xmlLable) {
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
