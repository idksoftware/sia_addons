package testfx;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class HistoryView extends Application {
 
    private TableView<HistoryItem> table = new TableView<HistoryItem>();
    private final ObservableList<HistoryItem> data =
        FXCollections.observableArrayList(
            new HistoryItem("0001", "12.02.15.12.23.00", "Added to archive"),
            new HistoryItem("0002", "12.02.15.14.07.67", "Despotted"),
            new HistoryItem("0003", "12.02.15.14.15.09", "Cleaned up")
        );
   
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
        stage.setHeight(500);
 
        final Label label = new Label("History");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn versionCol = new TableColumn("Version");
        versionCol.setMinWidth(50);
        versionCol.setCellValueFactory(
                new PropertyValueFactory<HistoryView, String>("Version"));
 
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(150);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<HistoryView, String>("Date"));
 
        TableColumn commentCol = new TableColumn("Comment");
        commentCol.setMinWidth(200);
        commentCol.setCellValueFactory(
                new PropertyValueFactory<HistoryView, String>("Comment"));
 
        table.setItems(data);
        table.getColumns().addAll(versionCol, dateCol, commentCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static class HistoryItem {
 
        private final SimpleStringProperty version;
        private final SimpleStringProperty date;
        private final SimpleStringProperty comment;
 
        private HistoryItem(String ver, String date, String comment) {
            this.version = new SimpleStringProperty(ver);
            this.date = new SimpleStringProperty(date);
            this.comment = new SimpleStringProperty(comment);
        }
 
        public String getVersion() {
            return version.get();
        }
 
        public void setVersion(String fName) {
            version.set(fName);
        }
 
        public String getDate() {
            return date.get();
        }
 
        public void setDate(String fName) {
            date.set(fName);
        }
 
        public String getComment() {
            return comment.get();
        }
 
        public void setComment(String fcomment) {
            comment.set(fcomment);
        }
    }
} 

