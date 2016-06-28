package idk.imgarchive.base.metadata;

import org.w3c.dom.Element;

import idk.Variant.InvalidFormatException;
import idk.Variant.VariantAttrMap;
import idk.Variant.VariantDirectory;
import idk.Variant.VariantListValue;
import idk.Variant.VariantStringValue;


public class IdentityPropertiesDirectory extends VariantDirectory {

	   private final VariantAttrMap _variantAttrMap = new VariantAttrMap();
	    
	   public IdentityPropertiesDirectory() {
		   loadMap();
	    }
	   
	   public IdentityPropertiesDirectory(Element root) throws InvalidFormatException {
		   read(root);
	    }
		@Override
		public VariantAttrMap getAttrMap() {
			return _variantAttrMap;
		}
		
		public void merge(final IdentityPropertiesDirectory other) throws InvalidFormatException {
			super.merge(other);
		}

		@Override
		public String getXMLName() {
			return "Identity";
		}

		@Override
		protected Object clone() {
			IdentityPropertiesDirectory vd = new IdentityPropertiesDirectory();
			try {
				vd.merge(this);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
			return vd;
		}

		@Override
		protected void loadMap() {
			_variantAttrMap.put(new VariantStringValue(IdentityAttribute.IMG_NUMBER, false));
			_variantAttrMap.put(new VariantStringValue(IdentityAttribute.IMG_REVISION, false));
			_variantAttrMap.put(new VariantStringValue(IdentityAttribute.IMG_LABEL, false));
			_variantAttrMap.put(new VariantStringValue(IdentityAttribute.IMG_ORGINAL_RAW, false));
			_variantAttrMap.put(new VariantStringValue(IdentityAttribute.IMG_ORGINAL_PIC, false));
		}

		@Override
		public boolean getObligatory() {
			return false;
		}

		@Override
		public String dtdComment() {
			return "This details the identiy of the asset. the Asset number and revision are controlled" + "\r\n" +
					"be Image Archive. The Label orginal file names can be change but if blank will be" + "\r\n" +
					"filled with defaults";
		}
}

		
