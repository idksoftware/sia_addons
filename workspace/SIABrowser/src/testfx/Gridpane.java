package testfx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Gridpane extends Application {
/*
	public class TopicItem {
		public String getProperty() {
			return property;
		}
		
		public void setProperty(String property) {
			this.property = property;
		}
		
		public int getPos() {
			return pos;
		}
		
		private String property;
		private int pos;
		
		public TopicItem(int pos, String property) {
			this.property = property;
			this.pos = pos;
		}
	}
	
	public class TopicPane extends GridPane {
		ArrayList<Button> editButtonList = new ArrayList<Button>();
		Map<String, TopicItem> properties = new HashMap<String, TopicItem>();
		boolean editingOn = false;
		boolean hasChanged = false;
		String imageName;
		
		int curInd = 1;
		
		public TopicPane() {
			setAlignment(Pos.TOP_CENTER);
	        setHgap(5);
	        setVgap(5);
	        setPadding(new Insets(15, 15, 15, 15));
	       
		}
		
		public void setImageName(String imageName) {
			this.imageName = imageName;
		}
		
		public void setTitle(String title) {
			Text scenetitle = new Text(title);
	        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        add(scenetitle, 0, 0, 2, 1);
		}
		
		public void add(String labelText, String propText, boolean enableEditing) {
			
			properties.put(labelText, new TopicItem(curInd, propText));
			Label label = new Label(labelText + ":");
	        add(label, 0, curInd);
	        Label prop = new Label(propText);
	        
	        
	        add(prop, 1, curInd);
	        
	        if (enableEditing) {
		        Button btn = new Button("");
		        editButtonList.add(btn);
		        btn.setVisible(false);
		        add(btn, 2, curInd);
		        
		        btn.setOnAction(new EventHandler<ActionEvent>() {
	
		            @Override
		            public void handle(ActionEvent e) {
		            	TopicItem topicItem = null;
		            	if(properties.containsKey(labelText)) {
	            			topicItem = properties.get(labelText);
	            			System.out.println("Found " + topicItem.getProperty() + " " + labelText + " \n"); 
	            		}
		            	final InputDialog inputDialog = new InputDialog(imageName, labelText, topicItem.getProperty());
		            	//inputDialog.setContentText(topicItem.getProperty());
		            	Optional<String> result = inputDialog.showAndWait();
		            	System.out.println(result);
		            	if (result.isPresent()) {
		            		//GridPane dPane = result.get();
		            		String res = result.get();
		            		System.out.println(res);
		            		for (Node node : getChildren()) {
		            		    if (node instanceof Label
		            		    		&& getColumnIndex(node) == 1
		            		    		&& getRowIndex(node) == topicItem.getPos()) {
		            		    	((Label)node).setText(res);
		            		    }
		            		}
		            	}
		            }
		        });
	        }
	        curInd++;
		}
		
		void editProps() {
			Button btn = new Button("Enable editing");
	        HBox hbBtn = new HBox(10);
	        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	        hbBtn.getChildren().add(btn);
	        super.add(hbBtn, 1, curInd);

	        //final Text actiontarget = new Text();
	        //add(actiontarget, 1, 6);
	        
	        btn.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	                //actiontarget.setFill(Color.FIREBRICK);
	                //actiontarget.setText("Sign in button pressed");
	            	
	            	if (editingOn) {
	            		btn.setText("Enable editing");
	            		for (Button button : editButtonList) {
	            			button.setVisible(false);
	            		}
	            		editingOn = false;
	            	} else {
	            		btn.setText("Disable editing");
	            		for (Button button : editButtonList) {
	            			button.setVisible(true);
	            		}
	            		editingOn = true;
	            	}
	            }
	        });
		}
	}
	*/
    public static void main(String[] args) {
        launch(args);
    }

    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("DSC_8964.jpg");
        TopicPane topic = new TopicPane();
        
        topic.setImageName("p1030517_24721881802_o.jpg.jpg");
        topic.setTitle("Assets");

        topic.add("Sequence Id", "0002", false);
        topic.add("Filename", "p1030517_24721881802_o.jpg", true);
        topic.add("Filepath", "2016-09-23/p1030517_24721881802_o.jpg", false);
        topic.add("Orginal Name", "p1030517_24721881802_o.jpg", false);
        topic.add("Unique Id", "0002", false);
        topic.add("Label", "Ice Climbing Pari", true);
        topic.add("Rating", "6", true);
        topic.add("MediaType", "JPG", false);
        topic.add("Md5", "f15c0dfad287e78b0a9329dcda202a1c", false);
        topic.add("Crc", "4129273169", false);
        topic.add("File Size", "6375936", false);
        topic.add("Date Create", "2016.01.28.20.03.46", false);
        topic.add("Date Modified", "2016.01.28.20.03.46", false);
        topic.add("Date Added", "2016.06.14.08.53.35", false);
        
        topic.editProps();
        /*
         <SequenceId>16</SequenceId>
<Filename>p1030517_24721881802_o.jpg</Filename>
<Filepath>C:\Users\cn012540\Pictures\pics/p1030517_24721881802_o.jpg</Filepath>
<OrginalName>p1030517_24721881802_o.jpg</OrginalName>
<UniqueId>172230f0-3205-11e6-bdff-8ddd5089ca2c</UniqueId>
<Label>p1030517_24721881802_o.jpg</Label>
<Rating>0</Rating>
<MediaType>4129273169</MediaType>
<Md5>f15c0dfad287e78b0a9329dcda202a1c</Md5>
<Crc>4129273169</Crc>
<FileSize>6375936</FileSize>
<DateCreate>2016.01.28.20.03.46</DateCreate>
<DateModified>2016.01.28.20.03.46</DateModified>
<DateAdded>2016.06.14.08.53.35</DateAdded>
         */
        
        
        Scene scene = new Scene(topic, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

