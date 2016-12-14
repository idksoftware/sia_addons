package Import;

import javafx.scene.control.TextInputDialog;

public class ImportInfomation extends TextInputDialog {

    //private ButtonType apply = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
    //private ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

    public ImportInfomation(String title, String label, String prop) {
    	super(prop);
        setTitle(title);
        setHeaderText(null);
        setContentText(label + ":");
        
    }

}
