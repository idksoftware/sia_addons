package SIABrowserApp;

import SIABrowser.ArchiveFolderReader;
import SIABrowser.ImageGalleryPane;
import SIABrowser.SIATreeView;
import SIABrowser.ArchiveFolderReader.DayList;
import SIABrowser.ArchiveFolderReader.YearList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainPane extends BorderPane {
	
	private Node rootIcon = new ImageView(new Image(getClass().getResourceAsStream("/root.png")));
	
	public MainPane(Stage stage) {
		
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
        Pane imagePane = new Pane();
        
        tree.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
/*
            @Override
            public void changed(ObservableValue observable, Object oldValue,
                    Object newValue) {

                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                System.out.println("Selected Text : " + selectedItem.getValue());
                // do what ever you want 
            }
*/
			@Override
			public void changed(ObservableValue arg0, Object arg1, Object newValue) {
				TreeItem<String> selectedItem = (TreeItem<String>) newValue;
				
                System.out.println("Selected Text : " + selectedItem.getValue());
                String path = "C:\\Users\\cn012540\\Documents\\SIA Workspace\\2016\\2016-01-28";
                ImageGalleryPane imageGalleryPane = new ImageGalleryPane(stage, path);
                //imagePane.getChildren().add(imageGalleryPane);
                setCenter(imageGalleryPane);
				
			}

          });
		
        String path = "C:\\Users\\cn012540\\Documents\\SIA Workspace\\2016\\2016-01-28";
        
		ImageGalleryPane imageGalleryPane = new ImageGalleryPane(stage, path);
        setLeft(tree);
        //imagePane.getChildren().add(imageGalleryPane);
        setCenter(imageGalleryPane);
	}
}
