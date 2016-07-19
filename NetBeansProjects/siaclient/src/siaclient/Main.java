package siaclient;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.Optional;




public class Main extends Application {
	
	private class RootScene extends IASceneBase {
		
		public RootScene(Stage primaryStage) {
			super(primaryStage, null);
			
		}
		ImportScene importScene;
		
		public void Init() {
			
		    menuBar.setStyle("-fx-background-color: #336699;"
		    		+ "-fx-text-fill: white;");
		    menuBar.setMaxSize(20.0, 25.0);
		    Menu menuFile = new Menu("+");
		    Menu menuEdit = new Menu("-");
		    menuBar.getMenus().addAll(menuFile,menuEdit);
		    
		    tile = new TilePane();
		    tile.setPadding(new Insets(15, 15, 15, 15));
		    tile.setVgap(4);
		    tile.setHgap(4);
		    tile.setPrefColumns(4);
		    tile.setStyle("-fx-background-color: DAE6F3;");
		    
			borderPane1.setCenter(tile);
			
		    loadScenes();
			
		}
		
                @Override
		public void loadScenes() {
	        
	        importScene = new ImportScene();
	        this.addScene(importScene);
	        this.addButton(importScene);
			importScene.Init();
			
                ExportScene exportScene = new ExportScene();
			this.addButton(exportScene);
                
	        addScene(exportScene);
	        exportScene.Init();
	        
	        SearchScene searchScene = new SearchScene();
			this.addButton(searchScene);
	        addScene(searchScene);
	        searchScene.Init();
	        
	        ViewScene viewScene = new ViewScene();
			this.addButton(viewScene);
	        addScene(viewScene);
	        viewScene.Init();
	        
	        MetadataScene metadataScene = new MetadataScene();
			this.addButton(metadataScene);
	        addScene(metadataScene);
	        metadataScene.Init();
	    }
		
		
	}
	
	
	
	
	Scene scene1;
	Scene scene2;
	Scene sceneMain;
    //Stage thestage;
    
    Button buttonBack;
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			RootScene rootScene = new RootScene(primaryStage);
			rootScene.Init();
			sceneMain = rootScene.getScene();
			
			
			
			
			
			primaryStage.setTitle("Image Archive Client");
			primaryStage.setScene(sceneMain);
			primaryStage.show();
			//thestage = primaryStage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

	/*
	public void ButtonImportClicked(ActionEvent e) {
		System.out.println("action " + e.toString()); 
		System.out.println(((Control)e.getSource()).getId()); 
		//System.out.println(((Object) e).getActionCommand());
		thestage.setScene(importScene.getScene());	
	}
	*/
	
	
	
}
