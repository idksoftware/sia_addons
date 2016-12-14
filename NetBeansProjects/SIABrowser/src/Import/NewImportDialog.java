package Import;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;


public class NewImportDialog  extends Dialog<ImportRecord> {

    //private ButtonType apply = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
    //private ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

    public NewImportDialog(String title, String label, String prop) {
    	
        setTitle(title);
        setHeaderText(null);
        setContentText(label + ":");
    



        setResizable(true);

        // Widgets
        Label label1 = new Label("Image path: ");
        //Label label2 = new Label("Phone: ");
        TextField text1 = new TextField();
        //TextField text2 = new TextField();
	
        // Create layout and add to dialog
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 35, 20, 35));
        grid.add(label1, 1, 1); // col=1, row=1
        grid.add(text1, 2, 1);
        //grid.add(label2, 1, 2); // col=1, row=2
        //grid.add(text2, 2, 2);
        
        Button btnFolderChooser = new Button();
        btnFolderChooser.setText("Browse");
        btnFolderChooser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                Stage stage = (Stage)grid.getScene().getWindow();
                File selectedDirectory = 
                        directoryChooser.showDialog(stage);
                 
                if(selectedDirectory != null){
                	label1.setText(selectedDirectory.getAbsolutePath());
                }
            }
        });

        grid.add(btnFolderChooser, 2, 2);
        
        getDialogPane().setContent(grid);
        
        // Add button to dialog
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(buttonTypeOk );

        // Result converter for dialog
        setResultConverter(new Callback<ButtonType, ImportRecord>() {
        	@Override
        	public ImportRecord call(ButtonType b) {

        		if (b == buttonTypeOk) {

        			return new ImportRecord(1,text1.getText(), 1);
        		}

        		return null;
        	}
        });
    }
	
}
