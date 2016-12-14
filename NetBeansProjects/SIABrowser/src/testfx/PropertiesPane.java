package testfx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PropertiesPane extends TabPane {

	PropertiesPane() {
		
	    
	   
	    //Create Tabs
	    Tab tabA = new Tab();
	    tabA.setText("Assets");
	    TopicPane assets = new TopicPane();
        
	    assets.setImageName("p1030517_24721881802_o.jpg.jpg");
        assets.setTitle("Assets");

        assets.add("Sequence Id", "0002", false);
        assets.add("Filename", "p1030517_24721881802_o.jpg", true);
        assets.add("Filepath", "2016-09-23/p1030517_24721881802_o.jpg", false);
        assets.add("Orginal Name", "p1030517_24721881802_o.jpg", false);
        assets.add("Unique Id", "0002", false);
        assets.add("Label", "Ice Climbing Pari", true);
        assets.add("Rating", "6", true);
        assets.add("MediaType", "JPG", false);
        assets.add("Md5", "f15c0dfad287e78b0a9329dcda202a1c", false);
        assets.add("Crc", "4129273169", false);
        assets.add("File Size", "6375936", false);
        assets.add("Date Create", "2016.01.28.20.03.46", false);
        assets.add("Date Modified", "2016.01.28.20.03.46", false);
        assets.add("Date Added", "2016.06.14.08.53.35", false);
        
        assets.editProps();
	    
	    tabA.setContent(assets);
	    getTabs().add(tabA);
	   
	    Tab tabB = new Tab();
	    tabB.setText("Media");
	    //Add something in Tab
	    TopicPane media = new TopicPane();
        
	    media.setImageName("p1030517_24721881802_o.jpg.jpg");
	    media.setTitle("Media");

	    media.add("Width", "0002", false);
	    media.add("Height", "p1030517_24721881802_o.jpg", true);
	    media.add("Resolution", "0002", false);
	    media.add("Depth", "p1030517_24721881802_o.jpg", true);
	    media.add("ViewRotation", "0002", false);
	    media.add("SampleColor", "p1030517_24721881802_o.jpg", true);
	    media.add("Page", "0002", false);
	    media.add("ColorSpace", "p1030517_24721881802_o.jpg", true);
	    media.add("Compression", "0002", false);
	    media.add("PrimaryEncoding", "p1030517_24721881802_o.jpg", true);
	    
	    tabB.setContent(media);
	    getTabs().add(tabB);
	   
	    Tab tabC = new Tab();
	    tabC.setText("Camera");
	    //Add something in Tab
	    TopicPane camera = new TopicPane();
        
	    camera.setImageName("p1030517_24721881802_o.jpg.jpg");
	    camera.setTitle("Camera");

	    camera.add("Maker", "0002", false);
	    camera.add("Model", "p1030517_24721881802_o.jpg", true);
	    camera.add("Software", "p1030517_24721881802_o.jpg", true);
	    camera.add("SourceURL", "p1030517_24721881802_o.jpg", true);
	    camera.add("ExifVersion", "p1030517_24721881802_o.jpg", true);
	    camera.add("CaptureDate", "p1030517_24721881802_o.jpg", true);
	    camera.add("ExposureProgram", "p1030517_24721881802_o.jpg", true);
	    camera.add("ISOSpeedRating", "p1030517_24721881802_o.jpg", true);
	    camera.add("ExposureBias", "p1030517_24721881802_o.jpg", true);
	    camera.add("ExposureTime", "p1030517_24721881802_o.jpg", true);
	    camera.add("Aperture", "p1030517_24721881802_o.jpg", true);
	    camera.add("MeteringMode", "p1030517_24721881802_o.jpg", true);
	    camera.add("LightSource", "p1030517_24721881802_o.jpg", true);
	    camera.add("Flash", "p1030517_24721881802_o.jpg", true);
	    camera.add("FocalLength", "p1030517_24721881802_o.jpg", true);
	    camera.add("SensingMethod", "p1030517_24721881802_o.jpg", true);
	    camera.add("DigitalZoom", "p1030517_24721881802_o.jpg", true);

	    tabC.setContent(camera);
	    getTabs().add(tabC);
	    
	    Tab tabD = new Tab();
	    tabD.setText("GPS");
	    //Add something in Tab
	    TopicPane gps = new TopicPane();
	    
	    gps.add("Latitude", "0002", false);
	    gps.add("Longitude", "0002", false);
	    gps.add("GPSTimeStamp", "0002", false);

	    tabD.setContent(gps);
	    getTabs().add(tabD);
	    
	    Tab tabE = new Tab();
	    tabE.setText("Copyright");
	    //Add something in Tab
	    TopicPane copyright = new TopicPane();
	    
	    copyright.add("Copyright", "0002", false);
	    copyright.add("UsageRights", "0002", false);
	    copyright.add("CopyrightURL", "0002", false);

	    tabE.setContent(copyright);
	    getTabs().add(tabE);
	   
	}
}

