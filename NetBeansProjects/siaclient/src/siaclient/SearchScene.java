package siaclient;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

public class SearchScene extends IAButtonScene {

	public SearchScene() {
		super("search-button", "Search");
	}

	@Override
	public void Init() {
		BorderPane BorderPane1 = new BorderPane();
		sceneMain = new Scene(BorderPane1,400,400);
		
		MenuBar menuBar = new MenuBar();
	    menuBar.setStyle("-fx-background-color: #336699;"
	    		+ "-fx-text-fill: white;");
	    menuBar.setMaxSize(20.0, 25.0);
	    Menu menuFile = new Menu("+");
	    Menu menuEdit = new Menu("-");
	    menuBar.getMenus().addAll(menuFile,menuEdit);
	    
		BorderPane topBoxMain = addTopBox(menuBar, this.getTitle());
		BorderPane1.setTop(topBoxMain);
		sceneMain.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		
	}

	@Override
	void loadScenes() {
		// TODO Auto-generated method stub
		
	}


}
