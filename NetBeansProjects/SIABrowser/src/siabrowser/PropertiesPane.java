package SIABrowser;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class PropertiesPane extends TabPane {

	PropertiesPane(Metadata metadata) {
		
	    
	   
	    //Create Tabs
	    Tab tabA = new Tab();
	    tabA.setText("Assets");
	    TopicPane assets = new TopicPane();
        
	    assets.setImageName(metadata.getAssetProperties().getFilename());
        assets.setTitle("Assets");

        assets.add("Sequence Id", metadata.getAssetProperties().getSequenceId() , false);
        assets.add("Filename", metadata.getAssetProperties().getFilename(), true);
        assets.add("Filepath", metadata.getAssetProperties().getFilepath(), false);
        assets.add("Orginal Name", metadata.getAssetProperties().getOrginalName(), false);
        assets.add("Unique Id", metadata.getAssetProperties().getUniqueId(), false);
        assets.add("Label", metadata.getAssetProperties().getLabel(), true);
        assets.add("Rating", metadata.getAssetProperties().getRating(), true);
        assets.add("MediaType", metadata.getAssetProperties().getMediaType(), new InputDialog(
        											metadata.getAssetProperties().getFilename(),
        											"Filename", metadata.getAssetProperties().getMediaType()));
        assets.add("Md5", metadata.getAssetProperties().getMd5(), false);
        assets.add("Crc", metadata.getAssetProperties().getCrc(), false);
        assets.add("File Size", metadata.getAssetProperties().getFileSize(), false);
        assets.add("Date Create", metadata.getAssetProperties().getDateCreate(), false);
        assets.add("Date Modified", metadata.getAssetProperties().getDateModified(), false);
        assets.add("Date Added", metadata.getAssetProperties().getDateAdded(), false);
        
        assets.editProps();
	    
	    tabA.setContent(assets);
	    getTabs().add(tabA);
	   
	    Tab tabB = new Tab();
	    tabB.setText("Media");
	    //Add something in Tab
	    TopicPane media = new TopicPane();
        
	    media.setImageName("p1030517_24721881802_o.jpg.jpg");
	    media.setTitle("Media");

	    media.add("Width", metadata.getMediaProperties().getWidth(), false);
	    media.add("Height", metadata.getMediaProperties().getHeight(), true);
	    media.add("Resolution", metadata.getMediaProperties().getResolution(), false);
	    media.add("Depth", metadata.getMediaProperties().getDepth(), true);
	    media.add("ViewRotation", metadata.getMediaProperties().getViewRotation(), false);
	    media.add("SampleColor", metadata.getMediaProperties().getSampleColor(), true);
	    media.add("Page", metadata.getMediaProperties().getPage(), false);
	    media.add("ColorSpace", metadata.getMediaProperties().getColorSpace(), true);
	    media.add("Compression", metadata.getMediaProperties().getCompression(), false);
	    media.add("PrimaryEncoding", metadata.getMediaProperties().getPrimaryEncoding(), true);
	    
	    tabB.setContent(media);
	    getTabs().add(tabB);
	   
	    Tab tabC = new Tab();
	    tabC.setText("Camera");
	    //Add something in Tab
	    TopicPane camera = new TopicPane();
        
	    camera.setImageName("p1030517_24721881802_o.jpg.jpg");
	    camera.setTitle("Camera");

	    camera.add("Maker", metadata.getCameraInformation().getMaker(), false);
	    camera.add("Model", metadata.getCameraInformation().getModel(), true);
	    camera.add("Software", metadata.getCameraInformation().getSoftware(), true);
	    camera.add("SourceURL", metadata.getCameraInformation().getSourceURL(), true);
	    camera.add("ExifVersion", metadata.getCameraInformation().getExifVersion(), true);
	    camera.add("CaptureDate", metadata.getCameraInformation().getCaptureDate(), true);
	    camera.add("ExposureProgram", metadata.getCameraInformation().getExposureProgram(), true);
	    camera.add("ISOSpeedRating", metadata.getCameraInformation().getiSOSpeedRating(), true);
	    camera.add("ExposureBias", metadata.getCameraInformation().getExposureBias(), true);
	    camera.add("ExposureTime", metadata.getCameraInformation().getExposureTime(), true);
	    camera.add("Aperture", metadata.getCameraInformation().getAperture(), true);
	    camera.add("MeteringMode", metadata.getCameraInformation().getMeteringMode(), true);
	    camera.add("LightSource", metadata.getCameraInformation().getLightSource(), true);
	    camera.add("Flash", metadata.getCameraInformation().getFlash(), true);
	    camera.add("FocalLength", metadata.getCameraInformation().getFocalLength(), true);
	    camera.add("SensingMethod", metadata.getCameraInformation().getSensingMethod(), true);
	    camera.add("DigitalZoom", metadata.getCameraInformation().getDigitalZoom(), true);

	    tabC.setContent(camera);
	    getTabs().add(tabC);
	    
	    Tab tabD = new Tab();
	    tabD.setText("GPS");
	    //Add something in Tab
	    TopicPane gps = new TopicPane();
	    gps.setTitle("GPS");
	    gps.add("Latitude", metadata.getGpsProperties().getLatitude(), false);
	    gps.add("Longitude", metadata.getGpsProperties().getLongitude(), false);
	    gps.add("GPSTimeStamp", metadata.getGpsProperties().getGpsTimeStamp(), false);

	    tabD.setContent(gps);
	    getTabs().add(tabD);
	    
	    Tab tabE = new Tab();
	    tabE.setText("Copyright");
	    //Add something in Tab
	    TopicPane copyright = new TopicPane();
	    copyright.setTitle("Copyright");
	    copyright.add("Copyright", metadata.getCopyrightProperties().getCopyright(), false);
	    copyright.add("UsageRights", metadata.getCopyrightProperties().getUsageRights(), false);
	    copyright.add("CopyrightURL", metadata.getCopyrightProperties().getCopyrightURL(), false);

	    tabE.setContent(copyright);
	    getTabs().add(tabE);
	   
	}
}

