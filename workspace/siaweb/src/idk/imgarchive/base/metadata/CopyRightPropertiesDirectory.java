package idk.imgarchive.base.metadata;

import org.w3c.dom.Element;

import idk.Variant.InvalidFormatException;
import idk.Variant.VariantAttrMap;
import idk.Variant.VariantDirectory;
import idk.Variant.VariantStringValue;


public class CopyRightPropertiesDirectory extends VariantDirectory {

	   private final VariantAttrMap _variantAttrMap = new VariantAttrMap();
	    
	   public CopyRightPropertiesDirectory() {
		   loadMap();
	    }

		public CopyRightPropertiesDirectory(Element root) throws InvalidFormatException {
			
			read(root);
		}

		protected void loadMap() {
			_variantAttrMap.put(new VariantStringValue(CopyRightAttribute.IMG_COPYRIGHT, false));
			_variantAttrMap.put(new VariantStringValue(CopyRightAttribute.IMG_USAGERIGHTS, false));
			_variantAttrMap.put(new VariantStringValue(CopyRightAttribute.IMG_COPYRIGHTURL, false));		
		}
		
		@Override
		public VariantAttrMap getAttrMap() {
			return _variantAttrMap;
		}
		
		public void merge(final CopyRightPropertiesDirectory other) throws InvalidFormatException {
			super.merge(other);
		}

		@Override
		public String getXMLName() {
			return "CopyrightProperties";
		}

		@Override
		protected Object clone() {
			CopyRightPropertiesDirectory vd = new CopyRightPropertiesDirectory();
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
			return "Details the ownership asset and how it may be used.";
		}
}

		