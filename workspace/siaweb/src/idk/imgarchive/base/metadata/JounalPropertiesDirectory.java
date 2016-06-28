package idk.imgarchive.base.metadata;

import org.w3c.dom.Element;

import idk.Variant.InvalidFormatException;
import idk.Variant.VariantAttrMap;
import idk.Variant.VariantDirectory;
import idk.Variant.VariantListValue;
import idk.Variant.VariantStringValue;


public class JounalPropertiesDirectory extends VariantDirectory {

	   private final VariantAttrMap _variantAttrMap = new VariantAttrMap();
	    
	   public JounalPropertiesDirectory() {
		   loadMap();
	    }
	   
	   public JounalPropertiesDirectory(Element root) throws InvalidFormatException {
		   read(root);
	    }
	   
		@Override
		public VariantAttrMap getAttrMap() {
			return _variantAttrMap;
		}
		
		public void merge(final JounalPropertiesDirectory other) throws InvalidFormatException {
			super.merge(other);
		}

		@Override
		public String getXMLName() {
			// TODO Auto-generated method stub
			return "Jounal";
		}

		@Override
		protected Object clone() {
			JounalPropertiesDirectory vd = new JounalPropertiesDirectory();
			try {
				vd.merge(this);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
			return vd;
		}

		@Override
		protected void loadMap() {
			_variantAttrMap.put(new VariantStringValue(JounalAttribute.IMG_IN_PRIMARY_STORAGE, false));
			_variantAttrMap.put(new VariantStringValue(JounalAttribute.IMG_CHECKED_STATUS, false));
			_variantAttrMap.put(new VariantStringValue(JounalAttribute.IMG_PUBLISHABLE, false));
			_variantAttrMap.put(new VariantStringValue(JounalAttribute.IMG_TIMES_BACKED_UP, false));
		}

		@Override
		public boolean getObligatory() {
			return true;
		}

		@Override
		public String dtdComment() {
			return "This section details the jounal properies of the asset.";
		}
}

		