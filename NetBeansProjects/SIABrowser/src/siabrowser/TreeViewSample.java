package SIABrowser;

import java.io.InputStream;

import SIABrowser.ArchiveFolderReader.DayList;
import SIABrowser.ArchiveFolderReader.YearList;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

 
public class TreeViewSample extends Application {

	private Node rootIcon = new ImageView(new Image(getClass().getResourceAsStream("/root.png")));
    
    public TreeViewSample() {
    	
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tree View Sample");        
        
        
        
        ArchiveFolderReader archiveFolderReader = new ArchiveFolderReader("C:\\Users\\cn012540\\Documents\\SIA Workspace");
        YearList yearList = archiveFolderReader.getYear();
        TreeItem<String> rootItem = new TreeItem<String> ("Photos", rootIcon);
        rootItem.setExpanded(true);
        SIATreeView tree = new SIATreeView(rootItem);
        for (String y : yearList) {
            TreeItem<String> yearItem = new TreeItem<String> (y, new ImageView(tree.getFolderIcon()));            
            rootItem.getChildren().add(yearItem);
           
            DayList day = archiveFolderReader.getDay(y);
        	day.printList();
        	for (String d : day) {
        		TreeItem<String> monthItem = new TreeItem<String> (d, new ImageView(tree.getFolderIcon()));            
            	yearItem.getChildren().add(monthItem);
            	//ImageList imageList = archiveFolderReader.getImage(d);
            	//imageList.printList();
            	
            }
            
        }        
        
        //tree.setCellFactory(arg0);
        StackPane root = new StackPane();
        root.getChildren().add(tree);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
