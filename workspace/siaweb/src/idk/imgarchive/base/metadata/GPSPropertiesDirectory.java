package idk.imgarchive.base.metadata;

import org.w3c.dom.Element;

import idk.Variant.InvalidFormatException;
import idk.Variant.VariantAttrMap;
import idk.Variant.VariantDirectory;
import idk.Variant.VariantListValue;
import idk.Variant.VariantStringValue;


public class GPSPropertiesDirectory extends VariantDirectory {

	   private final VariantAttrMap _variantAttrMap = new VariantAttrMap();
	    
	   public GPSPropertiesDirectory() {
		   loadMap();	   
	    }

	   	public GPSPropertiesDirectory(Element root) throws InvalidFormatException {
		   read(root);
	    }
		@Override
		public VariantAttrMap getAttrMap() {
			return _variantAttrMap;
		}
		
		public void merge(final GPSPropertiesDirectory other) throws InvalidFormatException {
			super.merge(other);
		}

		@Override
		public String getXMLName() {
			return NameSpace.GPS_PROPERTIES;
		}

		@Override
		protected Object clone() {
			GPSPropertiesDirectory vd = new GPSPropertiesDirectory();
			try {
				vd.merge(this);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
			return vd;
		}

		@Override
		protected void loadMap() {
			_variantAttrMap.put(new VariantStringValue(GPSAttribute.IMG_GPS_VERSION_ID, false));
			_variantAttrMap.put(new VariantStringValue(GPSAttribute.IMG_GPS_ALTITUDE, false));
			_variantAttrMap.put(new VariantStringValue(GPSAttribute.IMG_GPS_LATITUDE_REF, false));
			_variantAttrMap.put(new VariantStringValue(GPSAttribute.IMG_GPS_LATITUDE, false));
			_variantAttrMap.put(new VariantStringValue(GPSAttribute.IMG_GPS_LONGITUDE_REF, false));
			_variantAttrMap.put(new VariantStringValue(GPSAttribute.IMG_GPS_LONGITUDE, false));
			_variantAttrMap.put(new VariantStringValue(GPSAttribute.IMG_GPS_TIME_STAMP, false));
			_variantAttrMap.put(new VariantStringValue(GPSAttribute.IMG_GPS_MAP_DATUM, false));
			
		}

		@Override
		public boolean getObligatory() {
			return false;
		}

		@Override
		public String dtdComment() {
			return "If an asset contains locational information then this section is used" + "\r\n" +
				   "to record this information";
		}
}

		