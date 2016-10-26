package testfx;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class PropertiesPane extends TabPane {

	public PropertiesPane() {

	
	   
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
	    media.editProps();
	    tabB.setContent(media);
	    getTabs().add(tabB);
	   
	    Tab tabC = new Tab();
	    tabC.setText("Camera");
	    //Add something in Tab
	    TopicPane camera = new TopicPane();
        
	    camera.setImageName("p1030517_24721881802_o.jpg.jpg");
	    camera.setTitle("Camera");

	    camera.add("Width", "0002", false);
	    camera.add("Height", "p1030517_24721881802_o.jpg", true);

	    tabC.setContent(camera);
	    getTabs().add(tabC);
	    
	}
	
}

/*
<?xml version="1.0" encoding="UTF-8"?>
<Metadata>
<AssetProperties>
<SequenceId>12</SequenceId>
<Filename>p1030465_24212813143_o.jpg</Filename>
<Filepath>C:\Users\cn012540\Pictures\pics/p1030465_24212813143_o.jpg</Filepath>
<OrginalName>p1030465_24212813143_o.jpg</OrginalName>
<UniqueId>16de6433-3205-11e6-a213-8ddd5089ca2c</UniqueId>
<Label>p1030465_24212813143_o.jpg</Label>
<Rating>0</Rating>
<MediaType>2229541678</MediaType>
<Md5>0a345eff8937e257b8707c3ace1d34d1</Md5>
<Crc>2229541678</Crc>
<FileSize>6658560</FileSize>
<DateCreate>2016.01.28.14.42.42</DateCreate>
<DateModified>2016.01.28.14.42.42</DateModified>
<DateAdded>2016.06.14.08.53.34</DateAdded>
</AssetProperties>
<MediaProerties>
<Width>4608</Width>
<Height>3456</Height>
<Resolution/>
<Depth/>
<ViewRotation>1</ViewRotation>
<SampleColor/>
<Page/>
<ColorSpace/>
<Compression/>
<PrimaryEncoding/>
</MediaProerties>
<CameraInformation>
<Maker>Panasonic</Maker>
<Model>DMC-FT5</Model>
<Software/>
<SourceURL/>
<ExifVersion/>
<CaptureDate>2016.01.28.07.42.42</CaptureDate>
<ExposureProgram/>
<ISOSpeedRating>1600</ISOSpeedRating>
<ExposureBias>0</ExposureBias>
<ExposureTime>0.0166667</ExposureTime>
<Aperture>3.3</Aperture>
<MeteringMode>5</MeteringMode>
<LightSource/>
<Flash/>
<FocalLength>4.9</FocalLength>
<SensingMethod/>
<DigitalZoom/>
</CameraInformation>
<GPS>
<Latitude/>
<Longitude/>
<GPSTimeStamp/>
</GPS>
<CopyrightProperties>
<Copyright/>
<UsageRights/>
<CopyrightURL/>
</CopyrightProperties>
</Metadata>

 */
