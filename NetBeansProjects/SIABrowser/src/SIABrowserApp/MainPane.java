package SIABrowserApp;

import SIABrowser.ArchiveFolderReader;
import SIABrowser.ImageGalleryPane;
import SIABrowser.SIATreeView;

import SIABrowser.ArchiveFolderReader.DayList;
import SIABrowser.ArchiveFolderReader.YearList;
import idk.config.ConfigInfo;
import java.io.File;
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
	private boolean isArchive = false;
       
        String archivePath = null;
        
	public MainPane(Stage stage, boolean isArchive) {
            String root = null;
            this.isArchive = isArchive;
            if (isArchive) {
               archivePath = ConfigInfo.getInstanceShadowPath();
            } else {
               archivePath = ConfigInfo.getWorkspacePath();
            }
            
            
            
            
		ArchiveFolderReader archiveFolderReader = new ArchiveFolderReader(archivePath);
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
                            String path = null;
                            if (selectedItem.getValue().length() <=4) {
                                setCenter(new YearPane());
                            } else {
                                if (isArchive) {
                                    String yearStr = selectedItem.getValue().substring(0, 4);
                                    //String path = "F:\\sia\\workspace\\2016\\2016-04-23";
                                    path = archivePath + File.separator + yearStr + File.separator + selectedItem.getValue();
                                           
                                                                                    ;
                                } else {
                                    String yearStr = selectedItem.getValue().substring(0, 4);
                                    //String path = "F:\\sia\\workspace\\2016\\2016-04-23";
                                    path = archivePath + File.separator + yearStr + File.separator +
                                                                                    selectedItem.getValue();
                                }
                                ImageGalleryPane imageGalleryPane = new ImageGalleryPane(stage, path, isArchive);
                                //imagePane.getChildren().add(imageGalleryPane);
                                setCenter(imageGalleryPane);
                            }		
			}

          });
	setLeft(tree);
        /*
        // Test Image Gallery Pane
        String path = "F:\\sia\\workspace\\2016\\2016-04-23";
        
		ImageGalleryPane imageGalleryPane = new ImageGalleryPane(stage, path);
        
        //imagePane.getChildren().add(imageGalleryPane);
        setCenter(imageGalleryPane);
        */
	}

    
}
