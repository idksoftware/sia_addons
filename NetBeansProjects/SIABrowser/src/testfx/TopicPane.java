package testfx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class TopicPane extends GridPane {
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
            	/*
            	final InputDialog inputDialog = new InputDialog("hhhhh");
            	inputDialog.show();
            	*/
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
