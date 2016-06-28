package idk.imgarchive.base.metadata;

import org.w3c.dom.Element;

import xmlutils.XMLUtil;

import idk.Variant.InvalidFormatException;
import idk.Variant.VariantAttrMap;
import idk.Variant.VariantDirectory;
import idk.Variant.VariantListValue;
import idk.Variant.VariantStringValue;


public class AssetPropertiesDirectory extends VariantDirectory {

	   private final VariantAttrMap _imageAttrMap = new VariantAttrMap();
	    
	   public AssetPropertiesDirectory() {
		   loadMap();
	   }
	   
	   public AssetPropertiesDirectory(Element parent) throws InvalidFormatException {
			read(parent);
	   }
	   
	   protected void loadMap()
	   {
	    	_imageAttrMap.put(new VariantStringValue(AssetAttribute.IMG_LABEL, false));
	    	_imageAttrMap.put(new VariantStringValue(AssetAttribute.IMG_CAPTION, false));
	    	_imageAttrMap.put(new VariantStringValue(AssetAttribute.IMG_CATEGORY, false));
	    	_imageAttrMap.put(new VariantStringValue(AssetAttribute.IMG_HARD_COPY_LOCATION, false));
	    	_imageAttrMap.put(new VariantStringValue(AssetAttribute.IMG_LANGUAGE, false));
	    	_imageAttrMap.put(new VariantStringValue(AssetAttribute.IMG_AUTHOR, false));
	    	_imageAttrMap.put(new VariantStringValue(AssetAttribute.IMG_TYPE, false));
	    	_imageAttrMap.put(new VariantStringValue(AssetAttribute.IMG_USER_COMMENT, false));
	    	_imageAttrMap.put(new VariantStringValue(AssetAttribute.IMG_EDITOR, false));
	    	_imageAttrMap.put(new VariantListValue(AssetAttribute.IMG_TAGLIST,
	    			new VariantStringValue(AssetAttribute.IMG_TAG, false),  false));
	    	_imageAttrMap.put(new VariantStringValue(AssetAttribute.IMG_INTELLECTUAL_GENRE, false));
	    	_imageAttrMap.put(new VariantStringValue(AssetAttribute.IMG_RATING, false));
	    	_imageAttrMap.put(new VariantStringValue(AssetAttribute.IMG_DATE_CREATED, false));
	    	_imageAttrMap.put(new VariantStringValue(AssetAttribute.IMG_MEDIA_TYPE, false));
	    }

		

		



		@Override
		public VariantAttrMap getAttrMap() {
			return _imageAttrMap;
		}
		
		public void merge(final AssetPropertiesDirectory other) throws InvalidFormatException {
			super.merge(other);
		}

		@Override
		public String getXMLName() {
			return NameSpace.ASSET_PROPERTIES;
		}



		@Override
		protected Object clone() {
			AssetPropertiesDirectory vd = new AssetPropertiesDirectory();
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
			// TODO Auto-generated method stub
			return "These properties are used to describe the photograghic Asset.";
		}
}

		