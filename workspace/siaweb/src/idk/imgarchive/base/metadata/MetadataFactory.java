package idk.imgarchive.base.metadata;

import idk.Variant.InvalidFormatException;

import idk.Variant.VariantContainer;
import idk.Variant.VariantValue;


//import idk.imgarchive.base.exif.DrewExifReader;
//import idk.imgarchive.base.exif.ExifContainer;
//import idk.imgarchive.base.metadata.mapper.MetadataExtractorMapper;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

//import com.drew.imaging.ImageProcessingException;


public class MetadataFactory {
	
	/**
	 * Decodes the passed document into a SummaryMetadata object.
	 * 
	 * @param document
	 * @return
	 * @throws ParseException
	 * @throws InvalidFormatException
	 */
	static public SummaryMetadata decode(final Document document) throws ParseException, InvalidFormatException {
		final Element rootElement = document.getDocumentElement();
		rootElement.normalize();
		return new SummaryMetadata(rootElement);
	}

	/**
	 * File summaryMap	- This is the mapping file that maps the full metadata to the summary
	 * File image		- The image to be processed  
	 * @throws InvalidFormatException 
	 * @throws ImageProcessingException 
	 * @throws NoSuchAlgorithmException 
	 */
	/*
	static public SummaryMetadata create(JounalInfo jounalInfo, IdentityInfo identityInfo, File summaryMap, File image) throws IOException, ParseException {
		
		ExifContainer fullExifContainer = null;
		try {
			fullExifContainer = DrewExifReader.createExifContainer(image);
		} catch (ImageProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		MetadataExtractorMapper mem = new MetadataExtractorMapper();
		VariantContainer summaryExifContainer = mem.create(summaryMap, fullExifContainer);
		FilePropertiesInfo fileInfo = null;
		try {
			fileInfo = new FilePropertiesInfo(image);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SummaryMetadata summaryMetadata = new SummaryMetadata();
		FilePropertiesDirectory filePropertiesDirectory = summaryMetadata.getFilePropertiesDirectory();
		try {
		
			VariantValue vv = filePropertiesDirectory.getAttrValue(FileAttribute.IMG_ORIGINAL_FILE);
			vv.setString(fileInfo.getOriginalFile());
			vv = filePropertiesDirectory.getAttrValue(FileAttribute.IMG_LAST_MODIFIED);
			vv.setString(fileInfo.getLastModified().toString());
			vv = filePropertiesDirectory.getAttrValue(FileAttribute.IMG_SIZE);
			vv.setString(Long.toString(fileInfo.getSize()));
			vv = filePropertiesDirectory.getAttrValue(FileAttribute.IMG_CRC);
			vv.setString(Long.toString(fileInfo.getCrc()));
			vv = filePropertiesDirectory.getAttrValue(FileAttribute.IMG_MD5);
			vv.setString(fileInfo.getMd5());
			vv = filePropertiesDirectory.getAttrValue(FileAttribute.IMG_ARCHIVE_NAME);
			vv.setString(fileInfo.getFileName());
			vv = filePropertiesDirectory.getAttrValue(FileAttribute.IMG_UUID);
			vv.setString(fileInfo.getUuid().toString());
			
		} catch (InvalidFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		IdentityPropertiesDirectory identityPropertiesDirectory = summaryMetadata.getIdentityPropertiesDirectory();
		try {
			VariantValue vv = identityPropertiesDirectory.getAttrValue(IdentityAttribute.IMG_NUMBER);
			vv.setString(Long.toString(identityInfo.getImageNumber()));
			vv = identityPropertiesDirectory.getAttrValue(IdentityAttribute.IMG_REVISION);
			vv.setString(Long.toString(identityInfo.getRevisonNumber()));
			vv = identityPropertiesDirectory.getAttrValue(IdentityAttribute.IMG_LABEL);
			vv.setString(identityInfo.getLabel());
			vv = identityPropertiesDirectory.getAttrValue(IdentityAttribute.IMG_ORGINAL_RAW);
			vv.setString(identityInfo.getOrginalRaw());
			vv = identityPropertiesDirectory.getAttrValue(IdentityAttribute.IMG_ORGINAL_PIC);
			vv.setString(identityInfo.getOrginalPic());
			
		} catch (InvalidFormatException e1) {
			e1.printStackTrace();
		}
		
		JounalPropertiesDirectory jounalPropertiesDirectory = summaryMetadata.getJounalPropertiesDirectory();
		try {
			
			VariantValue vv = jounalPropertiesDirectory.getAttrValue(JounalAttribute.IMG_IN_PRIMARY_STORAGE);
			vv.setString(jounalInfo.getInPrimaryStorage());
			vv = jounalPropertiesDirectory.getAttrValue(JounalAttribute.IMG_CHECKED_STATUS);
			vv.setString(jounalInfo.getCheckedStatus());
			vv = jounalPropertiesDirectory.getAttrValue(JounalAttribute.IMG_PUBLISHABLE);
			vv.setString(jounalInfo.getPublishable());
			vv = jounalPropertiesDirectory.getAttrValue(JounalAttribute.IMG_TIMES_BACKED_UP);
			vv.setString(Integer.toString(jounalInfo.getTimesBackedUp()));
			
		} catch (InvalidFormatException e1) {
			e1.printStackTrace();
		}
		
		
		if (summaryExifContainer != null) {
			try {
				summaryMetadata.merge(summaryExifContainer);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return summaryMetadata;
	}
	*/
	/**
	 * Parse Info xml file and create a FilePropertiesInfo object from it.
	 * 
	 * @param document
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	static public final FilePropertiesInfo getFileInfo(final Document document) throws NumberFormatException, ParseException {
		final Element rootElement = document.getDocumentElement();
		rootElement.normalize();
		FilePropertiesInfo filePropertiesInfo = new FilePropertiesInfo(rootElement);
		return filePropertiesInfo;
	}
	/**
	 * Parse Info xml file and create a getIdentityInfo object from it.
	 * 
	 * @param document
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	static public final IdentityInfo getIdentityInfo(final Document document) throws NumberFormatException, ParseException {
		final Element rootElement = document.getDocumentElement();
		rootElement.normalize();
		IdentityInfo identityInfo = new IdentityInfo(rootElement);
		return identityInfo;
	}
	/**
	 * Parse Info xml file and create a getJounalInfo object from it.
	 * 
	 * @param document
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	static public final JounalInfo getJounalInfo(final Document document) throws NumberFormatException, ParseException {
		final Element rootElement = document.getDocumentElement();
		rootElement.normalize();
		JounalInfo jounalInfo = new JounalInfo(rootElement);
		return jounalInfo;
	}
}
