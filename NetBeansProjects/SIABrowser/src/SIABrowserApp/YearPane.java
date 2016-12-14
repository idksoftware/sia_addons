/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SIABrowserApp;



import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import static javafx.scene.layout.VBox.setMargin;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Iain
 */
public class YearPane extends VBox {
   
    public YearPane() {
        
        setPadding(new Insets(10));
        setSpacing(8);

        Text title = new Text("Data");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        getChildren().add(title);

        Text options[] = new Text[] {
            new Text("Sales"),
            new Text("Marketing"),
            new Text("Distribution"),
            new Text("Costs")};

        for (int i=0; i<4; i++) {
            setMargin(options[i], new Insets(0, 0, 0, 8));
            getChildren().add(options[i]);
        }

    }
}

