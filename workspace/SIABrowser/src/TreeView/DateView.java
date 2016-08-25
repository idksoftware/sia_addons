package TreeView;

import java.io.File;
//from w  ww  .  j  a v a2s. co  m
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class DateView {

	private final Node rootIcon = new ImageView(
	        new Image(getClass().getResourceAsStream("/folder_16.png"))
	    );

	SIATreeView<String> tree = null;   
	    
	    
    public Pane process() {
    	
        
        TreeItem<String> rootItem = new TreeItem<String> ("Photos", rootIcon);
        //rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<String> ("Message" + i);            
            rootItem.getChildren().add(item);
        }        
        tree = new SIATreeView<String> (rootItem);
        tree.initialize();
        StackPane root = new StackPane();
        root.getChildren().add(tree);
        return root;
       
    }



}

