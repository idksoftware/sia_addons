package SIABrowser;


import SIABrowserApp.PreviewPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Screen;
import javafx.stage.Stage;
import testfx.ImageScaler;


public class ImageGalleryPane extends ScrollPane {
	
	Stage stage;
	
	private Label createCaptionedImage(ImageView imageView, String caption) {
        Label labeledImage = new Label(caption);

        labeledImage.setFont(Font.font("Segoe UI", FontPosture.REGULAR, 15));
        labeledImage.setStyle("-fx-background-color: white");
        labeledImage.setPadding(new Insets(0, 0, 5, 0));
        labeledImage.setGraphic(
                imageView
        );
        labeledImage.setContentDisplay(ContentDisplay.TOP);

        return labeledImage;
    }
    
    /*
    class CustomTitledPane extends TitledPane {

    	
        public CustomTitledPane(String titleText, ImageView img) {
            //super(titleText, img);
            //setAnimated(true);
            //setCollapsible(true);
            //ImageView img = new ImageView(new Image(getClass().getResource("unlock24.png").toExternalForm()));
            img.setFitHeight(150d);
            img.setPreserveRatio(true);
            img.setSmooth(true);
            setGraphic(img);
            setContentDisplay(ContentDisplay.BOTTOM);
        }
        
    	
    }
    
    class CustomTitledPane extends VBox {
    	public CustomTitledPane(String titleText, ImageView img) {
    		setPadding(new Insets(1));
    		Rectangle r1 = new Rectangle(150, 150);
    		
    		getChildren().addAll(r1);


        }
    }
    */
    
    public ImageGalleryPane(Stage stage, String path, boolean isArchive) {
        
        TilePane tile = new TilePane();
        this.stage = stage;
        
        setStyle("-fx-background-color: DAE6F3;");
        tile.setPadding(new Insets(15, 15, 15, 15));
        tile.setHgap(15);

        //String path = "C:\\Users\\cn012540\\Documents\\SIA Workspace\\2016\\2016-01-28";

        /*
        ContextMenu contextMenu = new ContextMenu();
        MenuItem previewItem = new MenuItem("Preview");
        MenuItem openItem = new MenuItem("Open");
        MenuItem propertiesItem = new MenuItem("Properties");
        propertiesItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("propertiesItem: " + arg0);
				
			}

           
        });

        contextMenu.getItems().addAll(previewItem, openItem, new SeparatorMenuItem(), propertiesItem);
        */
        String imagePath = path;
        if (isArchive) {
           imagePath += File.separator + "data"; 
        }
        File folder = new File(imagePath);
        File[] listOfFiles = folder.listFiles();
        
        
        for (final File file : listOfFiles) {
        		if (file.getName().equals(".sia")) {
        			continue;
        		}
                ImageView imageView;
                imageView = createImageView(file);
                
                Label label = createCaptionedImage(imageView, file.getName());
                
                ContextMenu contextMenu = new ContextMenu();
                MenuItem previewItem = new MenuItem("Preview");
                MenuItem openItem = new MenuItem("Open");
                MenuItem propertiesItem = new MenuItem("Properties");
                propertiesItem.setOnAction(new EventHandler<ActionEvent>() {

        			@Override
        			public void handle(ActionEvent arg0) {
        				
        				String xmlPath = null;
                                        if (isArchive) {
                                            xmlPath = path + File.separatorChar + "metadata" + File.separatorChar + file.getName() + ".xml";
                                        } else {
                                            xmlPath = path + File.separatorChar + ".sia" + File.separatorChar + file.getName() + ".xml";
                                        }
        				System.out.println("propertiesItem: " + xmlPath);
        				MetadataReader metadataReader = new MetadataReader();
        			
        				Metadata metadata = metadataReader.read(xmlPath);
        				if (metadata == null) {
        					// show error.
        					System.out.println("Unable to read metadata: " + xmlPath);
        				}
        				metadata.getAssetProperties().setFilename(file.getName());
        				PropertiesPane propertiesPane = new PropertiesPane(metadata);
        				Stage newStage = new Stage();
                        newStage.setWidth(stage.getWidth());
                        newStage.setHeight(stage.getHeight());
                        newStage.setTitle(file.getName());
                        Scene scene = new Scene(propertiesPane);
                        newStage.setScene(scene);
                        newStage.show();
        				
        			}

                   
                });
                
                MenuItem historyItem = new MenuItem("History");
                historyItem.setOnAction(new EventHandler<ActionEvent>() {

        			@Override
        			public void handle(ActionEvent arg0) {
        				
        				String xmlPath = null;
                                         if (isArchive) {
                                            xmlPath = path + File.separatorChar + "history" + File.separatorChar + file.getName() + ".xml";
                                        } else {
                                            xmlPath = path + File.separatorChar + ".sia" + File.separatorChar + file.getName() + ".xml";
                                        }
        				System.out.println("propertiesItem: " + xmlPath);
        				MetadataReader metadataReader = new MetadataReader();
        			
        				Metadata metadata = metadataReader.read(xmlPath);
        				if (metadata == null) {
        					// show error.
        					System.out.println("Unable to read metadata: " + xmlPath);
        				}
        				metadata.getAssetProperties().setFilename(file.getName());
        				
        				Stage newStage = new Stage();
        				newStage.setTitle(file.getName());
        				newStage.setWidth(450);
        				newStage.setHeight(500);
        				
                        HistoryScene historyScene = new HistoryScene(new Group());
                        newStage.setScene(historyScene);
                        newStage.show();
        				
        			}

                   
                });
                
                
                contextMenu.getItems().addAll(previewItem, openItem, new SeparatorMenuItem(), propertiesItem, historyItem);
                
                label.setContextMenu(contextMenu);
                
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                            if(mouseEvent.getClickCount() == 2){
                                //try {
                                    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                                    Stage newStage = new Stage();
                                    newStage.setWidth(primaryScreenBounds.getWidth()/2);
                                    newStage.setHeight(primaryScreenBounds.getHeight()/2);
                                    newStage.setTitle(file.getName());
                                    newStage.setX(100);
                                    newStage.setY(100);
                                    
                                    double x = primaryScreenBounds.getMinX();
                                    double y = primaryScreenBounds.getMinY();
                                    
                                    PreviewPane previewPane = new PreviewPane(file, newStage.getHeight());
                                    /*
                                    BorderPane borderPane = new BorderPane();
                                    ImageView imageView = new ImageView();
                                    Image image = new Image(new FileInputStream(file));
                                    imageView.setImage(image);
                                    imageView.setStyle("-fx-background-color: BLACK");
                                    imageView.setFitHeight(stage.getHeight() - 10);
                                    imageView.setPreserveRatio(true);
                                    imageView.setSmooth(true);
                                    imageView.setCache(true);
                                    borderPane.setCenter(imageView);
                                    borderPane.setStyle("-fx-background-color: BLACK");
                                    */
                                    /*Stage newStage = new Stage();
                                    newStage.setWidth(stage.getWidth());
                                    newStage.setHeight(stage.getHeight());
                                    newStage.setTitle(file.getName());
                                    */
                                    //Scene scene = new Scene(borderPane,Color.BLACK);
                                    Scene scene = new Scene(previewPane,Color.BLACK);
                                    newStage.setScene(scene);
                                    newStage.show();
                               
                                   
                                    //Logger.getLogger(siabrowser.PreviewPane.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        }
                    
                    
                });

            
                
                
                tile.getChildren().addAll(label);
        }


        setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        setFitToWidth(true);
        setContent(tile);

        //addGlowOnMouseOver(tile);
    }
    
    int imageSize = 150;
    int i = 0;
    
    private ImageView createImageView(final File imageFile) {
        // DEFAULT_THUMBNAIL_WIDTH is a constant you need to define
        // The last two arguments are: preserveRatio, and use smooth (slower)
        // resizing

        ImageView imageView = null;
        try {
            SIAImage dim = getImageDimension(imageFile);
            ImageScaler imageScaler = new ImageScaler(imageFile.getName());
            
            //imageScaler.saveScaledImage(file, imageType);
            Image image;
            if (dim.y < dim.x) {
        	
        		image = new Image(new FileInputStream(imageFile), imageSize, 0, true, true);
        		imageView = new ImageView(image);
                imageView.setFitWidth(imageSize);
                
        		
            } else {
            	image = new Image(new FileInputStream(imageFile), 0, imageSize, true, true);
            	imageView = new ImageView(image);
                imageView.setFitHeight(imageSize);
                
            }
            
            //final Image image = new Image(new FileInputStream(imageFile), imageSize, 0, true, true);
            System.out.println("h:" + image.getHeight() + "w:" + image.getWidth());
            
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {

                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                        if(mouseEvent.getClickCount() == 2){
                            try {
                                BorderPane borderPane = new BorderPane();
                                ImageView imageView = new ImageView();
                                Image image = new Image(new FileInputStream(imageFile));
                                imageView.setImage(image);
                                imageView.setStyle("-fx-background-color: BLACK");
                                imageView.setFitHeight(stage.getHeight() - 10);
                                imageView.setPreserveRatio(true);
                                imageView.setSmooth(true);
                                imageView.setCache(true);
                                borderPane.setCenter(imageView);
                                borderPane.setStyle("-fx-background-color: BLACK");
                                Stage newStage = new Stage();
                                newStage.setWidth(stage.getWidth());
                                newStage.setHeight(stage.getHeight());
                                newStage.setTitle(imageFile.getName());
                                Scene scene = new Scene(borderPane,Color.BLACK);
                                newStage.setScene(scene);
                                newStage.show();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            });
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e1) {
			e1.printStackTrace();
		}
        return imageView;
    }
    
    private void addGlowOnMouseOver(Node node) {
        Glow glow = new Glow();
        DropShadow shadow = new DropShadow(20, Color.GOLD);
        glow.setInput(shadow);

        node.setOnMousePressed(event -> node.setEffect(null));
        node.setOnMouseEntered(event -> node.setEffect(glow));
        node.setOnMouseExited(event -> node.setEffect(null));
    }
    /**
     * Gets image dimensions for given file 
     * @param imgFile image file
     * @return dimensions of image
     * @throws IOException if the file is not a known image
     */
    
    public class SIAImage {
    	public int x;
    	public int y;
    	
    	SIAImage(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    };
    
    public SIAImage getImageDimension(File imgFile) throws IOException {
      int pos = imgFile.getName().lastIndexOf(".");
      if (pos == -1)
        throw new IOException("No extension for file: " + imgFile.getAbsolutePath());
      String suffix = imgFile.getName().substring(pos + 1);
      Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
      if (iter.hasNext()) {
        ImageReader reader = iter.next();
        try {
          ImageInputStream stream = new FileImageInputStream(imgFile);
          reader.setInput(stream);
          int width = reader.getWidth(reader.getMinIndex());
          int height = reader.getHeight(reader.getMinIndex());
          return new SIAImage(width, height);
        } catch (IOException e) {
          //log.warn("Error reading: " + imgFile.getAbsolutePath(), e);
        } finally {
          reader.dispose();
        }
      }

      throw new IOException("Not a known image file: " + imgFile.getAbsolutePath());
    }

}
