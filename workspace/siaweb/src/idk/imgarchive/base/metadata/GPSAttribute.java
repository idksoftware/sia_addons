package idk.imgarchive.base.metadata;

import idk.Variant.VariantAttribute;




public enum GPSAttribute implements VariantAttribute {
	
	IMG_GPS_VERSION_ID("GPS Version ID", "GPSVersionID"),
	IMG_GPS_ALTITUDE("GPS Altitude", "GPSAltitude"),
	IMG_GPS_LATITUDE_REF("GPS Latitude Ref", "GPSLatitudeRef"),
	IMG_GPS_LATITUDE("GPS Latitude", "GPSLatitude"),
	IMG_GPS_LONGITUDE_REF("GPS Longitude Ref", "GPSLongitudeRef"),
	IMG_GPS_LONGITUDE("GPS Longitude", "GPSLongitude"),
	IMG_GPS_TIME_STAMP("GPS TimeStamp", "GPSTimeStamp"),
	IMG_GPS_MAP_DATUM("GPS Map Datum", "GPSMapDatum"),
		;
	
	public final static String CopyRight = "CopyRight";
	
	protected String friendlyLable = null;
	protected String xmlLable = null;
	protected VariantAttribute.AttribType attribType = null;
	
	GPSAttribute(String friendlyLable, String xmlLable) {
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
