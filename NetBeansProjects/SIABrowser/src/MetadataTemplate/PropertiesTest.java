package MetadataTemplate;


import javafx.application.Application;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class PropertiesTest extends Application {
 
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
 
        
        TabPane tabPane = new TabPane();
        
        Tab tabA = new Tab();
        tabA.setText("Image Properties");
        
        
        
        final Label label = new Label("test");
        label.setFont(new Font("Arial", 20));
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, propertiesView); //, hb);
        tabA.setContent(vbox);
        tabPane.getTabs().add(tabA);

        Tab tabB = new Tab();
        tabB.setText("Tab B");
        //Add something in Tab
        StackPane tabB_stack = new StackPane();
        tabB_stack.setAlignment(Pos.CENTER);
        tabB_stack.getChildren().add(new Label("Label@Tab B"));
        tabB.setContent(tabB_stack);
        tabPane.getTabs().add(tabB);

        
        
        ((Group) scene.getRoot()).getChildren().addAll(tabPane);
 
        stage.setScene(scene);
        stage.show();
    }
 

}

