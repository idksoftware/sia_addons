package siaclient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ImportScene extends IAButtonScene {
	
	
	public ImportScene() {
		super("import-button", "Import");
	}

	
	public void Init() {
		
		
		MenuBar menuBar = new MenuBar();
	    menuBar.setStyle("-fx-background-color: #336699;"
	    		+ "-fx-text-fill: white;");
	    menuBar.setMaxSize(20.0, 25.0);
	    Menu menu = new Menu("+");
	    MenuItem menuAdd = new MenuItem("_add");
	    MenuItem menuEdit = new MenuItem("_edit");
	    MenuItem menuCancel = new MenuItem("_cancel");
	    menu.getItems().addAll(menuAdd, menuEdit, menuCancel);
	    
	    menuBar.getMenus().addAll(menu);
	    
		BorderPane topBoxMain = addTopBox(menuBar, this.getTitle());
		borderPane1.setTop(topBoxMain);
		sceneMain.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		menuAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				ImportAddScene importAddScene = new ImportAddScene();
				
				importAddScene.setSceneRoot(sceneMain);
				importAddScene.Init();
				
				thestage.setScene(importAddScene.getScene());	
			}
			
        });
         
		
	}


	@Override
	void loadScenes() {
		
		
	}
	
}
