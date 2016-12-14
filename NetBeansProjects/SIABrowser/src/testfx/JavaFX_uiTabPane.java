package testfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
 
import javafx.stage.Stage;
 
/**
*
* @web http://java-buddy.blogspot.com/
*/
public class JavaFX_uiTabPane extends Application {
 
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
      launch(args);
  }
 
  @Override
  public void start(Stage primaryStage) {
      primaryStage.setTitle("dsc_56454");
      Group root = new Group();
      Scene scene = new Scene(root, 500, 400, Color.WHITE);
     
      PropertiesPane propertiesPane = new PropertiesPane();
      BorderPane mainPane = new BorderPane();
     
      
      mainPane.setCenter(propertiesPane);
     
      mainPane.prefHeightProperty().bind(scene.heightProperty());
      mainPane.prefWidthProperty().bind(scene.widthProperty());
     
      root.getChildren().add(mainPane);
      primaryStage.setScene(scene);
      primaryStage.show();
  }
}

