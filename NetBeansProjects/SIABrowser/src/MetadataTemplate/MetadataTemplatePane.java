package MetadataTemplate;

import SIABrowser.TopicPane;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MetadataTemplatePane extends VBox {
	
	MetadataPropertiesView imagePropertiesView = new MetadataPropertiesView();
	MetadataLocationView locationView = new MetadataLocationView();
	MetadataCopyrightView copyrightView = new MetadataCopyrightView();
	MetadataCreatorView creatorView = new MetadataCreatorView();
	MetadataCreatorView creatorView1 = new MetadataCreatorView();
	
	public MetadataTemplatePane() {
		final Label label = new Label("test");
		label.setFont(new Font("Arial", 20));

		setSpacing(5);
		setPadding(new Insets(10, 0, 0, 10));
		
		TabPane tabPane = new TabPane();
		
	
		//Create Tabs
	      Tab tabA = new Tab();
	      tabA.setText("Image Properties");
	      //Add something in Tab
	      
	      tabA.setContent(imagePropertiesView);
	      
	      Tab tabB = new Tab();
	      tabB.setText("Location");
	      //Add something in Tab
	      
	      tabB.setContent(locationView);
	      
	      Tab tabC = new Tab();
	      tabC.setText("Copyright");
	      //Add something in Tab
	      
	      tabC.setContent(copyrightView);
	      
	      Tab tabD = new Tab();
	      tabD.setText("Creator Details");
	      //Add something in Tab
	      
	      tabD.setContent(creatorView);
	      
	      Tab tabE = new Tab();
	      tabE.setText("Summary");
	      VBox vbox = new VBox();
	      vbox.setSpacing(5);
	      vbox.setPadding(new Insets(10, 0, 0, 10));
	      final Label createrLabel = new Label("Creator Details");
	      
	      vbox.getChildren().addAll(createrLabel, creatorView1);
	      
	      tabE.setContent(vbox);
	      
	      tabPane.getTabs().add(tabA);
	      tabPane.getTabs().add(tabB);
	      tabPane.getTabs().add(tabC);
	      tabPane.getTabs().add(tabD);
	      tabPane.getTabs().add(tabE);
	      

		
		getChildren().addAll(label, tabPane);
	}

}
