package testfx;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ImageGallery extends Application {

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
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        ScrollPane root = new ScrollPane();
        TilePane tile = new TilePane();
        
        root.setStyle("-fx-background-color: DAE6F3;");
        tile.setPadding(new Insets(15, 15, 15, 15));
        tile.setHgap(15);

        String path = "C:\\Users\\cn012540\\Documents\\SIA Workspace\\2016\\2016-01-28";

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (final File file : listOfFiles) {
        		if (file.getName().equals(".sia")) {
        			continue;
        		}
                ImageView imageView;
                imageView = createImageView(file);
                
                Label label = createCaptionedImage(imageView, file.getName());
                //tile.getChildren().addAll(imageView);
                tile.getChildren().addAll(label);
        }


        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        root.setFitToWidth(true);
        root.setContent(tile);

        //primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        //primaryStage.setHeight(Screen.getPrimary().getVisualBounds()
        //						.getHeight());
        primaryStage.setWidth(500);
        primaryStage.setHeight(400);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

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
    
    private void addGlowOnMouseOver(Node node) {
        Glow glow = new Glow();
        DropShadow shadow = new DropShadow(20, Color.GOLD);
        glow.setInput(shadow);

        node.setOnMousePressed(event -> node.setEffect(null));
        node.setOnMouseEntered(event -> node.setEffect(glow));
        node.setOnMouseExited(event -> node.setEffect(null));
    }
    
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
          SIAImage siaImage = new SIAImage(width, height);
          return siaImage;
        } catch (IOException e) {
          //log.warn("Error reading: " + imgFile.getAbsolutePath(), e);
        } finally {
          reader.dispose();
        }
      }

      throw new IOException("Not a known image file: " + imgFile.getAbsolutePath());
    }

	
    public static void main(String[] args) {
        launch(args);
    }

}


