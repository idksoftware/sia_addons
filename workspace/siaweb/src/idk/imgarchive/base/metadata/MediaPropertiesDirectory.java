package idk.imgarchive.base.metadata;

import org.w3c.dom.Element;

import idk.Variant.InvalidFormatException;
import idk.Variant.VariantAttrMap;
import idk.Variant.VariantDirectory;
import idk.Variant.VariantStringValue;


public class MediaPropertiesDirectory extends VariantDirectory {

	   private final VariantAttrMap _variantAttrMap = new VariantAttrMap();
	    
	   public MediaPropertiesDirectory() {
		   loadMap();
	    }

		public MediaPropertiesDirectory(Element root) throws InvalidFormatException {
			read(root);
		}

		protected void loadMap() {
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_COPYRIGHT, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_HEIGHT, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_WIDTH, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_COLORPROFILE, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_COLORSPACE, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_COMMENTS, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_COMPRESSION, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_DATETIME, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_DATETIMEORIGINAL, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_DEPTH, false));

			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_ORIENTATION, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_SAMPLECOLOR, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_PRIMARYENCODING, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_RESOLUTION, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_MEDIATYPE, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_XRESOLUTION, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_YRESOLUTION, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_RESOLUTIONUNIT, false));
			_variantAttrMap.put(new VariantStringValue(MediaAttribute.IMG_IMAGEDETAILS, false));
		}
		
		@Override
		public VariantAttrMap getAttrMap() {
			return _variantAttrMap;
		}
		
		public void merge(final MediaPropertiesDirectory other) throws InvalidFormatException {
			super.merge(other);
		}

		@Override
		public String getXMLName() {
			return NameSpace.MEDIA_PROPERTIES;
		}

		@Override
		protected Object clone() {
			MediaPropertiesDirectory vd = new MediaPropertiesDirectory();
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
			return "This section details the assets media properties";
		}
}

		
