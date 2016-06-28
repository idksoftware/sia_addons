package idk.imgarchive.base.metadata;

import org.w3c.dom.Element;

import idk.Variant.InvalidFormatException;
import idk.Variant.VariantAttrMap;
import idk.Variant.VariantDirectory;
import idk.Variant.VariantListValue;
import idk.Variant.VariantStringValue;


public class FilePropertiesDirectory extends VariantDirectory {

	   private final VariantAttrMap _variantAttrMap = new VariantAttrMap();
	    
	   public FilePropertiesDirectory() {
		   loadMap();
	    }
	   
	   public FilePropertiesDirectory(Element root) throws InvalidFormatException {
		  
		   read(root);
	    }
	   
		@Override
		public VariantAttrMap getAttrMap() {
			return _variantAttrMap;
		}
		
		public void merge(final FilePropertiesDirectory other) throws InvalidFormatException {
			super.merge(other);
		}

		@Override
		public String getXMLName() {
			// TODO Auto-generated method stub
			return "File";
		}

		@Override
		protected Object clone() {
			FilePropertiesDirectory vd = new FilePropertiesDirectory();
			try {
				vd.merge(this);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
			return vd;
		}

		@Override
		protected void loadMap() {
			
			_variantAttrMap.put(new VariantStringValue(FileAttribute.IMG_ORIGINAL_FILE, false));
			_variantAttrMap.put(new VariantStringValue(FileAttribute.IMG_LAST_MODIFIED, false));
			_variantAttrMap.put(new VariantStringValue(FileAttribute.IMG_SIZE, false));
			_variantAttrMap.put(new VariantStringValue(FileAttribute.IMG_CRC, false));
			_variantAttrMap.put(new VariantStringValue(FileAttribute.IMG_MD5, false));
			_variantAttrMap.put(new VariantStringValue(FileAttribute.IMG_ARCHIVE_NAME, false));
			_variantAttrMap.put(new VariantStringValue(FileAttribute.IMG_UUID, false));
			
		}

		@Override
		public boolean getObligatory() {
			return false;
		}

		@Override
		public String dtdComment() {
			return "This details the primary image file. These details are completed" + "\r\n" +
				   "by Image Archive";
		}
}

		
