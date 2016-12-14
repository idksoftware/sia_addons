package SIABrowser;

/*
 ImportJournal Completed="2016:06:14 08:53:36">
	<Event>
		<SourceImage>C:\Users\cn012540\Pictures\pics/Chrysanthemum.jpg</SourceImage>
		<Result>Imported</Result>
		<Location>2008-03-14/Chrysanthemum.jpg</Location>
	</Event>
	<Event>
		<SourceImage>C:\Users\cn012540\Pictures\pics/Desert.jpg</SourceImage>
		<Result>Imported</Result>
		<Location>2008-03-14/Desert.jpg</Location>
	</Event>
	<Event>
		<SourceImage>C:\Users\cn012540\Pictures\pics/Hydrangeas.jpg</SourceImage>
		<Result>Imported</Result>
		<Location>2008-03-24/Hydrangeas.jpg</Location>
	</Event>
</Event>
</Catalog>
 */

import static org.w3c.dom.Node.ELEMENT_NODE;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import idk.config.ConfigInfo;
import xmlutils.XMLReadHelper;
import xmlutils.XMLUtil;

public class MetadataReader {
	static String errorMessage = null;

	public static final String getErrorMessage() {
		return errorMessage;
	}

	static public Metadata read(final String xmlPath) {

		final File xmlFile = new File(xmlPath);
		if (xmlFile.exists() == false) {
			// if this fails then the logger cannot log to a file as its
			// destination is not known.
			errorMessage = "SEVERE Error: Can not find the configuration file \"" + ConfigInfo.IMAGE_FILE_NAME
					+ "\", check configuration?\rIf a new installation run Workspace Initialise command";
			return null;
		}
		Document document = null;
		try {
			document = XMLReadHelper.decode(xmlFile.getAbsolutePath());
		} catch (final ParseException e) {
			errorMessage = "SEVERE Error: Can not read the configuration file \"" + ConfigInfo.IMAGE_FILE_NAME
					+ "\", check configuration?\rIf a new installation run Workspace Initialise command";
		} catch (final IOException e) {
			errorMessage = "SEVERE Error: Can not open the configuration file \"" + ConfigInfo.IMAGE_FILE_NAME
					+ "\", check configuration?\rIf a new installation run Workspace Initialise command";
		}

		Metadata metadata = new Metadata();

		final Element root = document.getDocumentElement();
		root.normalize();

		try {
			parseFile(metadata, root);
		} catch (final ParseException e) {
			errorMessage = "SEVERE Error: Cant read configuration file \"" + ConfigInfo.IMAGE_FILE_NAME
					+ "\", check configuration?\rIf a new installation run Workspace Initialise command";
			return null;
		}

		return metadata;
	}

	
	
	static void parseFile(Metadata metadata, final Element root) throws ParseException {
		Element element = XMLUtil.getElement(root, "AssetProperties");
		assetProperties(metadata, element);
		element = XMLUtil.getElement(root, "MediaProerties");
		mediaProerties(metadata, element);
		element = XMLUtil.getElement(root, "CameraInformation");
		CameraInformation(metadata, element);
		element = XMLUtil.getElement(root, "GPS");
		GPS(metadata, element);
		element = XMLUtil.getElement(root, "CopyrightProperties");
		CopyrightProperties(metadata, element);
		
	}
	static void assetProperties(Metadata metadata, Element root) {
		
		Element element = XMLUtil.getElement(root, "SequenceId");
		if (element == null) return;
		metadata.getAssetProperties().setSequenceId(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "Filename");
		if (element == null) return;
		metadata.getAssetProperties().setFilename(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "Filepath");
		if (element == null) return;
		metadata.getAssetProperties().setFilepath(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "OrginalName");
		if (element == null) return;
		metadata.getAssetProperties().setOrginalName(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "UniqueId");
		if (element == null) return;
		metadata.getAssetProperties().setUniqueId(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "Label");
		if (element == null) return;
		metadata.getAssetProperties().setLabel(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "Rating");
		if (element == null) return;
		metadata.getAssetProperties().setRating(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "MediaType");
		if (element == null) return;
		metadata.getAssetProperties().setMediaType(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "Md5");
		if (element == null) return;
		metadata.getAssetProperties().setMd5(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "Crc");
		if (element == null) return;
		metadata.getAssetProperties().setCrc(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "FileSize");
		if (element == null) return;
		metadata.getAssetProperties().setFileSize(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "DateCreate");
		if (element == null) return;
		metadata.getAssetProperties().setDateCreate(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "DateModified");
		if (element == null) return;
		metadata.getAssetProperties().setDateModified(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "DateAdded");
		if (element == null) return;
		metadata.getAssetProperties().setDateAdded(XMLUtil.getStringValue((Element) element));
		
	}
	
	static void mediaProerties(Metadata metadata, Element root) {
		
		Element element = XMLUtil.getElement(root, "Width");
		if (element == null) return;
		metadata.getMediaProperties().setWidth(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "Height");
		if (element == null) return;
		metadata.getMediaProperties().setHeight(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "Resolution");
		if (element == null) return;
		metadata.getMediaProperties().setResolution(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "Depth");
		if (element == null) return;
		metadata.getMediaProperties().setDepth(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "ViewRotation");
		if (element == null) return;
		metadata.getMediaProperties().setViewRotation(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "SampleColor");
		if (element == null) return;
		metadata.getMediaProperties().setSampleColor(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "Resolution");
		if (element == null) return;
		metadata.getMediaProperties().setResolution(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "Depth");
		if (element == null) return;
		metadata.getMediaProperties().setDepth(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "ViewRotation");
		if (element == null) return;
		metadata.getMediaProperties().setViewRotation(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "SampleColor");
		if (element == null) return;
		metadata.getMediaProperties().setSampleColor(XMLUtil.getStringValue((Element) element));
	
		element = XMLUtil.getElement(root, "Page");
		if (element == null) return;
		metadata.getMediaProperties().setPage(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "ColorSpace");
		if (element == null) return;
		metadata.getMediaProperties().setColorSpace(XMLUtil.getStringValue((Element) element));
	
		element = XMLUtil.getElement(root, "Compression");
		if (element == null) return;
		metadata.getMediaProperties().setCompression(XMLUtil.getStringValue((Element) element));
	
		element = XMLUtil.getElement(root, "PrimaryEncoding");
		if (element == null) return;
		metadata.getMediaProperties().setPrimaryEncoding(XMLUtil.getStringValue((Element) element));
	
	}
	
	static void CameraInformation(Metadata metadata, Element root) {
		
		Element element = XMLUtil.getElement(root, "Maker");
		if (element == null) return;
		metadata.getCameraInformation().setMaker(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "Model");
		if (element == null) return;
		metadata.getCameraInformation().setModel(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "Software");
		if (element == null) return;
		metadata.getCameraInformation().setSoftware(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "SourceURL");
		if (element == null) return;
		metadata.getCameraInformation().setSourceURL(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "ExifVersion");
		if (element == null) return;
		metadata.getCameraInformation().setModel(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "CaptureDate");
		if (element == null) return;
		metadata.getCameraInformation().setModel(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "ExposureProgram");
		if (element == null) return;
		metadata.getCameraInformation().setModel(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "ISOSpeedRating");
		if (element == null) return;
		metadata.getCameraInformation().setiSOSpeedRating(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "ExposureBias");
		if (element == null) return;
		metadata.getCameraInformation().setExposureBias(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "ExposureTime");
		if (element == null) return;
		metadata.getCameraInformation().setExposureTime(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "Aperture");
		if (element == null) return;
		metadata.getCameraInformation().setAperture(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "MeteringMode");
		if (element == null) return;
		metadata.getCameraInformation().setMeteringMode(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "LightSource");
		if (element == null) return;
		metadata.getCameraInformation().setModel(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "Flash");
		if (element == null) return;
		metadata.getCameraInformation().setModel(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "FocalLength");
		if (element == null) return;
		metadata.getCameraInformation().setModel(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "SensingMethod");
		if (element == null) return;
		metadata.getCameraInformation().setSensingMethod(XMLUtil.getStringValue((Element) element));

		element = XMLUtil.getElement(root, "DigitalZoom");
		if (element == null) return;
		metadata.getCameraInformation().setModel(XMLUtil.getStringValue((Element) element));

	}
	
	
	static void GPS(Metadata metadata, Element root) {
		
		Element element = XMLUtil.getElement(root, "Latitude");
		if (element == null) return;
		metadata.getGpsProperties().setLatitude(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "Longitude");
		if (element == null) return;
		metadata.getGpsProperties().setLongitude(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "GPSTimeStamp");
		if (element == null) return;
		metadata.getGpsProperties().setGpsTimeStamp(XMLUtil.getStringValue((Element) element));
	}
	

	static void CopyrightProperties(Metadata metadata, Element root) {
		
		Element element = XMLUtil.getElement(root, "Copyright");
		if (element == null) return;
		metadata.getCopyrightProperties().setCopyright(XMLUtil.getStringValue((Element) element));
		
		element = XMLUtil.getElement(root, "UsageRights");
		if (element == null) return;
		metadata.getCopyrightProperties().setUsageRights(XMLUtil.getStringValue((Element) element));
	

		element = XMLUtil.getElement(root, "CopyrightURL");
		if (element == null) return;
		metadata.getCopyrightProperties().setCopyrightURL(XMLUtil.getStringValue((Element) element));
	}
}



