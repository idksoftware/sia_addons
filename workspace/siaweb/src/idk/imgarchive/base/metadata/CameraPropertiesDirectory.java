package idk.imgarchive.base.metadata;

import org.w3c.dom.Element;

import xmlutils.XMLUtil;

import idk.Variant.InvalidFormatException;
import idk.Variant.VariantAttrMap;
import idk.Variant.VariantDirectory;
import idk.Variant.VariantStringValue;


public class CameraPropertiesDirectory extends VariantDirectory {

	   private final VariantAttrMap _variantAttrMap = new VariantAttrMap();
	   public CameraPropertiesDirectory() {
		   loadMap();
	   }
	   
	   public CameraPropertiesDirectory(Element parent) throws InvalidFormatException {	
			read(parent);
	   }
	   
	   public void loadMap() {
		  
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_F_NUMBER, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_DATE_TIME_DIGITIZED, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_COMPONENTSCONFIGURATION, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_FLASH_PIX_VERSION, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_FILE_SOURCE, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_SCENE_TYPE, false));
		  
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_EMBEDDED_METADATA, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_EXIF_VERSION, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_EXPOSURE_BIAS, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_EXPOSURE_PROGRAM, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_EXPOSURE_TIME, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_FLASH, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_VIEW_ROTATION, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_WHITE_BALANCE, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_FOCAL_LENGTH, false));
		  
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_IMAGE_DETAILS, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_ISO_SPEED_RATING, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_MEDIA_TYPE, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_METERING_MODE, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_LIGHT_SOURCE, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_MAKER, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_MODEL, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_ORIENTATION, false));
		  _variantAttrMap.put(new VariantStringValue(CameraAttribute.IMG_PAGES, false));
			
	    }

		

		@Override
		public VariantAttrMap getAttrMap() {
			return _variantAttrMap;
		}
		
		public void merge(final CameraPropertiesDirectory other) throws InvalidFormatException {
			super.merge(other);
		}

		@Override
		public String getXMLName() {
			return NameSpace.CAMERA_PROPERTIES;
		}

		@Override
		protected Object clone() {
			CameraPropertiesDirectory vd = new CameraPropertiesDirectory();
			try {
				vd.merge(this);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
			return vd;
		}

		@Override
		public boolean getObligatory() {
			return false;
		}

		@Override
		public String dtdComment() {
			
			return "This is used to describe the Camera used to take the photographic asset.";
		}
}

		
