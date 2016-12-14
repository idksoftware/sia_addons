package Import;

import java.util.Optional;
import java.util.Random;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
 
/**
 * @web http://java-buddy.blogspot.com/
 * 
 * fields
 * <id> <import path> <status> <button to extended view> 
 */
public class JavaFXDynTable extends Application {
     
    private TableView tableView = new TableView();
    private Button btnNew = new Button("New Record");
     
    static Random random = new Random();
    /* 
    static final String Status[] = {
        "Pending",
        "Inprogress",
        "Complete",
        "Errors",
        "Fatal"};
 */
    
    ObservableList<ImportRecord> data = FXCollections.observableArrayList();
     
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Import images");
        //tableView.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                     
                    @Override
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };
         
        btnNew.setOnAction(btnNewHandler);
         
        //init table
        //Un-editable column of "id"
        TableColumn col_id = new TableColumn("ID");
        tableView.getColumns().add(col_id);
        col_id.setCellValueFactory(
                    new PropertyValueFactory<ImportRecord, String>("id"));
         
        //Editable columns
        /*
        for(int i=0; i<Status.length; i++){
            TableColumn col = new TableColumn(Status[i]);
            col.setCellValueFactory(
                    new PropertyValueFactory<ImportRecord, String>(
                            "value_" + String.valueOf(i)));
            tableView.getColumns().add(col);
            col.setCellFactory(cellFactory);
        }
        */
        TableColumn col_path = new TableColumn("Folder");
        tableView.getColumns().add(col_path);
        col_path.setCellValueFactory(
                    new PropertyValueFactory<ImportRecord, String>("path"));
        
        TableColumn col_status = new TableColumn("Status");
        tableView.getColumns().add(col_status);
        col_status.setCellValueFactory(
                    new PropertyValueFactory<ImportRecord, String>("status"));
         
        //Insert Button
        TableColumn col_action = new TableColumn<>("Info");
        col_action.setSortable(false);
         
        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<ImportRecord, Boolean>, 
                ObservableValue<Boolean>>() {
 
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ImportRecord, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
 
        col_action.setCellFactory(
                new Callback<TableColumn<ImportRecord, Boolean>, TableCell<ImportRecord, Boolean>>() {
 
            @Override
            public TableCell<ImportRecord, Boolean> call(TableColumn<ImportRecord, Boolean> p) {
                return new ButtonCell();
            }
         
        });
        tableView.getColumns().add(col_action);
        
        ImportRecord newRec = new ImportRecord(
                newId,
                "Documents", 
                0);
        
        data.add(newRec);
        tableView.setItems(data);
         
        Group root = new Group();
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(btnNew, tableView);
        root.getChildren().add(vBox);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

public static void main(String[] args) {
        launch(args);
    }
     
    //Define the button cell
    private class ButtonCell extends TableCell<ImportRecord, Boolean> {
        final Button cellButton = new Button("Action");
         
        ButtonCell(){
             
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
 
                @Override
                public void handle(ActionEvent t) {
                	System.out.println("Got Here");
                	ImportInfomation importInfomation = new ImportInfomation("FFFFF", "DDDDD", "gggg");
                	Optional<String> result = importInfomation.showAndWait();
	            	System.out.println(result);
	            	if (result.isPresent()) {
	            		//GridPane dPane = result.get();
	            		String res = result.get();
	            		System.out.println(res);
	            		
	            	}
                }
            });
        }
 
        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }
    static int newId = 0; 
    EventHandler<ActionEvent> btnNewHandler = 
            new EventHandler<ActionEvent>(){
 
        @Override
        public void handle(ActionEvent t) {
             
        	
        	NewImportDialog newImportDialog = new NewImportDialog("FFFFF", "DDDDD", "gggg");
        	Optional<ImportRecord> result = newImportDialog.showAndWait();
        	System.out.println(result);
        	if (result.isPresent()) {
        		//GridPane dPane = result.get();
        		//String res = result.get();
        		//System.out.println(res);
        		
        	}
            //generate new Record with random number
            newId++;
            ImportRecord newRec = new ImportRecord(
                    newId,
                    "Documents", 
                    0);
            data.add(newRec);
             
        }
    };
     
    class EditingCell extends TableCell<XYChart.Data, Number> {
          
        private TextField textField;
          
        public EditingCell() {}
          
        @Override
        public void startEdit() {
              
            super.startEdit();
              
            if (textField == null) {
                createTextField();
            }
              
            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.selectAll();
        }
          
        @Override
        public void cancelEdit() {
            super.cancelEdit();
              
            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
          
        @Override
        public void updateItem(Number item, boolean empty) {
            super.updateItem(item, empty);
              
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setGraphic(textField);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                } else {
                    setText(getString());
                    setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }
        }
          
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
            textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
                  
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(Integer.parseInt(textField.getText()));
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }
          
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
 
}
 


