/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SIABrowserApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.StatusBar;


/**
 *
 * @author Iain
 */
public class PreviewPane extends BorderPane {
    ImageView imageView = null;
    public PreviewPane(File file, double height) {
        try {
            imageView = new ImageView();
            Image image = null;
            image = new Image(new FileInputStream(file));
            imageView.setImage(image);
            imageView.setStyle("-fx-background-color: BLACK");
            imageView.setFitHeight(height - 10);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            setCenter(imageView);
            setStyle("-fx-background-color: BLACK");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PreviewPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final Menu menu1 = new Menu("File");
        final Menu menu2 = new Menu("Options");
        final Menu menu3 = new Menu("Help");
 
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        setTop(menuBar);
        
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(5);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    //cappuccino.setOpacity(new_val.doubleValue());
                    //opacityValue.setText(String.format("%.2f", new_val));
                    System.out.println(String.format("%.2f", new_val));
            }
        });
        
        Button r90Button = new Button("R90");
        r90Button.setOnAction(e -> rotateRight());
        Button l90Button = new Button("L90");
        l90Button.setOnAction(e -> rotateLeft());
        StatusBar statusBar = new StatusBar();
        statusBar.setText("");
        statusBar.getLeftItems().add(r90Button);
        statusBar.getLeftItems().add(l90Button);
        statusBar.getLeftItems().add(slider);
        statusBar.setProgress(.5);
        setBottom(statusBar);
    }
    
    void rotateRight() {
        imageView.setFitWidth(600);
        imageView.setPreserveRatio(true);
	imageView.setRotate(90);
    }
    void rotateLeft() {
        imageView.setFitWidth(600);
        imageView.setPreserveRatio(true);
	imageView.setRotate(270);
    }
}

/*
final Menu menu1 = new Menu("File");
 final Menu menu2 = new Menu("Options");
 final Menu menu3 = new Menu("Help");
 
 MenuBar menuBar = new MenuBar();
 menuBar.getMenus().addAll(menu1, menu2, menu3);
 
*/