package idk.imgarchive.base.metadata;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import org.w3c.dom.Document;
import idk.Variant.InvalidFormatException;

import xmlutils.XmlWriter;

public class AssetProperties {
	public static final String SUMMARY_MAP_FILE = "SummaryMap.xml";
	private XmlWriter xmlWriter = null;
	private SummaryMetadata summaryMetadata = null;
	
	public AssetProperties(final Document document) throws ParseException, InvalidFormatException {
		decode(document);
	}

	public AssetProperties() {
		summaryMetadata = new SummaryMetadata();
		// this.importConfig = importConfig;
	}

	public AssetProperties(final SummaryMetadata sm) {
		summaryMetadata = sm;
		// this.importConfig = importConfig;
	}
	
	public void decode(final Document document) throws ParseException, InvalidFormatException {
		summaryMetadata = MetadataFactory.decode(document);
	}

	
	public void writeFile(final String path, final String filename) throws InvalidFormatException {
		xmlWriter = new XmlWriter(path, filename);
		write(xmlWriter);
		xmlWriter.close();
	}

	/**
	 * @return the iaMetaData
	 */
	public final SummaryMetadata getSummaryMetadata() {
		return summaryMetadata;
	}
	
	public void write(XmlWriter xmlWriter) throws InvalidFormatException {
		summaryMetadata.write(xmlWriter);
	}
	
	
	/*
	static public void writeInfo(final SummaryMetadata summaryMetadata, final FilePropertiesInfo fileInfo,
											final IdentityInfo identityInfo, final String sourcePath, final String path, final File jpegFile,
											final ImportConfig importConfig) {

		importConfig.setLabelIsOrginalFilename(false);
		
		// Set the JounalInfo to defaults
		JounalInfo jounalInfo = new JounalInfo();
		
		final File summaryMap = new File("testfiles\\MetadataMapper\\summary.xml");
		//File summaryMap = new File(ConfigInfo.getConfigPath(), SUMMARY_MAP_FILE);
		
		
		
		SummaryMetadata exifMetadata = null;
		
		if (jpegFile != null) {	// A picture file has been passed to be used for Exif info so use this
								// for Exif infomation.
			try {
				exifMetadata = MetadataFactory.create(jounalInfo, identityInfo, summaryMap, jpegFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			File primaryFile = new File(sourcePath, identityInfo.getInfoFilename());
			try {
				exifMetadata = MetadataFactory.create(jounalInfo, identityInfo, summaryMap, primaryFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			if (summaryMetadata != null) {
				exifMetadata.merge(summaryMetadata);
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File xmlOutputFile = new File(path, identityInfo.getInfoFilename());
		
		XmlWriter xmlWriter = new XmlWriter(xmlOutputFile);
		try {
			exifMetadata.write(xmlWriter);
		} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xmlWriter.close();
			
	}
	*/
	static public void writeIPTCFile(File file) {
		XmlWriter xmlWriter = new XmlWriter(file);
		xmlWriter.startElement("Document");
//		IPTCInfo iptcInfo = new IPTCInfo();
//		iptcInfo.write(xmlWriter);
		xmlWriter.startElement("Document");
		xmlWriter.close();
	}

}
