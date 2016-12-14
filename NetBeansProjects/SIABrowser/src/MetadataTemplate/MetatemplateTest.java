package MetadataTemplate;


import javafx.application.Application;


import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class MetatemplateTest extends Application {
 
	private PropertiesView propertiesView = new PropertiesView();
    
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Metadata");
        stage.setWidth(450);
        stage.setHeight(550);
 
        MetadataTemplatePane metadataTemplatePane = new MetadataTemplatePane();
        
        ((Group) scene.getRoot()).getChildren().addAll(metadataTemplatePane);
 
        stage.setScene(scene);
        stage.show();
    }
 

}

