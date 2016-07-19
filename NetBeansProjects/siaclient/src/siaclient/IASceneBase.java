package siaclient;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public abstract class IASceneBase {
	static Stage thestage;
	Scene sceneRoot;
	Scene sceneMain;
	
	String id;
	String title;
	BorderPane borderPane1;
	MenuBar menuBar;
	BorderPane topBoxMain;
	TilePane tile;
	
	private List<IASceneBase> sceneList = new ArrayList<IASceneBase>();
	
	abstract void Init();
	abstract void loadScenes();
		
	public IASceneBase() {
		thestage = null;
		sceneRoot = null;
		preinit();
	}
	
	public IASceneBase(String id, String title) {
		this.id = id;
		this.title = title;
		preinit();
	}
	
	public IASceneBase(Stage stage, Scene main) {
		thestage = stage;
		sceneRoot = main;
		preinit();
	}
	
	private void preinit() {
		borderPane1 = new BorderPane();
		sceneMain = new Scene(borderPane1,400,400);
		this.setSceneRoot(sceneMain);
		menuBar = new MenuBar();
		topBoxMain = addTopBox(menuBar, null);
		borderPane1.setTop(topBoxMain);
		
		sceneMain.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		/*
		tile = new TilePane();
	    tile.setPadding(new Insets(15, 15, 15, 15));
	    tile.setVgap(4);
	    tile.setHgap(4);
	    tile.setPrefColumns(4);
	    tile.setStyle("-fx-background-color: DAE6F3;");
	    
		borderPane1.setCenter(tile);
		*/
	}
	
	protected void ButtonClicked() {
		thestage.setScene(sceneRoot);	
	}
	
	public Scene getScene() {
		return sceneMain;
	}
	
	protected BorderPane addTopBox(MenuBar menuBar, String title) {
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(15, 15, 15, 15));
	    
		bp.setStyle("-fx-background-color: #336699;");
	    
	    
	    if (menuBar != null) {
	    	// set the menu
			StackPane menuStack = new StackPane();
		    
		    menuStack.setAlignment(Pos.CENTER_RIGHT); 
		    menuStack.getChildren().addAll(menuBar);  
		    
		    bp.setRight(menuStack);
		    
	    }
	    
	    if (title != null) {
	    	Button buttonBack = new Button("< " + title);
	    	buttonBack.setPrefSize(100, 10);
	    
	    	buttonBack.setId("Button");
	    	buttonBack.setOnAction(e -> ButtonClicked());
	    	bp.setLeft(buttonBack);
	    }
	    return bp;
	}
	
	protected boolean addScene(IASceneBase sceneBase) {
		sceneBase.setSceneRoot(this.getSceneRoot());
		sceneBase.setTheStage(this.getTheStage());
		sceneList.add(sceneBase);
		return true;
	}

	protected boolean addButton(IASceneBase sceneBase) {
		Button button = new Button(sceneBase.title); 
        button.setId(sceneBase.id);
        //buttonImport.setBackground(value);
        button.setPrefSize(100, 100);
        button.setOnAction(e -> sceneChangeClicked(e));
        tile.getChildren().add(button);
		return true;
	
	}
	
	public void sceneChangeClicked(ActionEvent e) {
		System.out.println("action base" + e.toString()); 
		System.out.println(((Control)e.getSource()).getId()); 
		//System.out.println(((Object) e).getActionCommand());
		
		//thestage.setScene(importScene.getScene());
		for(IASceneBase sceneBase : sceneList) {
			if (sceneBase.id.equals(((Control)e.getSource()).getId())) {
				thestage.setScene(sceneBase.getScene());	
			}
		}
	}
	
	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}
	/**
	 * @return the title
	 */
	public final String getTitle() {
		return title;
	}
	/**
	 * @return the thestage
	 */
	static final Stage getTheStage() {
		return thestage;
	}

	/**
	 * @param thestage the thestage to set
	 */
	protected final void setTheStage(Stage thestage) {
		this.thestage = thestage;
	}

	/**
	 * @return the sceneRoot
	 */
	protected final Scene getSceneRoot() {
		return sceneRoot;
	}

	/**
	 * @param sceneRoot the sceneRoot to set
	 */
	protected final void setSceneRoot(Scene sceneRoot) {
		this.sceneRoot = sceneRoot;
	}
}
