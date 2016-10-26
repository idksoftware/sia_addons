package testfx;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;

public class InputDialog extends TextInputDialog {

    //private ButtonType apply = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
    //private ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

    public InputDialog(String title, String label, String prop) {
    	super(prop);
        setTitle(title);
        setHeaderText(null);
        setContentText(label + ":");
        
    }
}
