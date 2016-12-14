package MetadataTemplate;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PropertiesView extends TableView<PropertiesView.KeyValueItem> {
	 
    //private TableView<KeyValueItem> table = new TableView<KeyValueItem>();
    private final ObservableList<KeyValueItem> data =
            FXCollections.observableArrayList(
            new KeyValueItem("Lable", "Ice Climbing Cogne"),
            new KeyValueItem("Rating", "4"),
            new KeyValueItem("Title", "Williams"),
            new KeyValueItem("Emma", "Jones"),
            new KeyValueItem("Michael", "Brown"));
    final HBox hb = new HBox();
 
    
 
    public PropertiesView() {
    	
        final Label label = new Label("Metadata");
        label.setFont(new Font("Arial", 20));
 
        this.setEditable(true);
        
        Callback<TableColumn, TableCell> cellFactory =
             new Callback<TableColumn, TableCell>() {
                 public TableCell call(TableColumn p) {
                    return new EditingCell();
                	// return new TableCell();
                 }
             };
 
        TableColumn keyCol = new TableColumn("Keyword");
        keyCol.setEditable(false);
        keyCol.setMinWidth(150);
        keyCol.setCellValueFactory(
            new PropertyValueFactory<KeyValueItem, String>("Key"));
        keyCol.setCellFactory(cellFactory);
        keyCol.setOnEditCommit(
            new EventHandler<CellEditEvent<KeyValueItem, String>>() {
                @Override
                public void handle(CellEditEvent<KeyValueItem, String> t) {
                    ((KeyValueItem) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setKey(t.getNewValue());
                }
             }
        );
 
        TableColumn valueCol = new TableColumn("Value");
        valueCol.setMinWidth(100);
        valueCol.setCellValueFactory(
            new PropertyValueFactory<KeyValueItem, String>("Value"));
        valueCol.setCellFactory(cellFactory);
        valueCol.setOnEditCommit(
            new EventHandler<CellEditEvent<KeyValueItem, String>>() {
                @Override
                public void handle(CellEditEvent<KeyValueItem, String> t) {
                    ((KeyValueItem) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setValue(t.getNewValue());
                }
            }
        );
 
        this.setItems(data);
        for (int i = 0; i < data.size(); i++) {
			KeyValueItem item = data.get(i);
			System.out.println("Key: " + item.getKey() + " Value: " + item.getValue());
		}
        
        this.getColumns().addAll(keyCol, valueCol);
 
        final TextField addKey = new TextField();
        addKey.setPromptText("Key");
        addKey.setMaxWidth(keyCol.getPrefWidth());
        final TextField addValue = new TextField();
        addValue.setMaxWidth(valueCol.getPrefWidth());
        addValue.setPromptText("Value");
        
        /*
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new KeyValueItem(
                		addKey.getText(),
                		addValue.getText()));
                addKey.clear();
                addValue.clear();
            }
        });
 
        
        hb.getChildren().addAll(addKey, addValue, addButton);
        hb.setSpacing(3);
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
        
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
        */
    }
 
    public static class KeyValueItem {
 
        private final SimpleStringProperty key;
        private final SimpleStringProperty value;
 
        private KeyValueItem(String key, String value) {
            this.key = new SimpleStringProperty(key);
            this.value = new SimpleStringProperty(value);
            
        }
 
        public String getKey() {
            return key.get();
        }
 
        public void setKey(String key) {
            this.key.set(key);
        }
 
        public String getValue() {
            return value.get();
        }
 
        public void setValue(String value) {
            this.value.set(value);
        }
 
    }
 
    
    class EditingCell extends TableCell<KeyValueItem, String> {
 
        private TextField textField;
 
        public EditingCell() {
        }
 
        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
 
            setText((String) getItem());
            setGraphic(null);
        }
 
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }
 
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, 
                    Boolean arg1, Boolean arg2) {
                        if (!arg2) {
                        	String tmp = textField.getText();
                            commitEdit(textField.getText());
                        	//commitEdit("Testing Testing");
                            for (int i = 0; i < data.size(); i++) {
                    			KeyValueItem item = data.get(i);
                    			System.out.println("Key: " + item.getKey() + " Value: " + item.getValue());
                    		}               	
                        }
                }
            });
        }
 
        private String getString() {
            String tmp = getItem() == null ? "" : getItem().toString();
            return tmp;
        }
    }
    
}

