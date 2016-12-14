package MetadataTemplate;


import MetadataTemplate.MetadataTemplateView.KeyValueItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class MetadataLocationView extends MetadataTemplateView {
	
	private ObservableList<MetadataTemplateView.KeyValueItem> data =
            FXCollections.observableArrayList(
            		new KeyValueItem("Location", "Ice Climbing Cogne"),
            		new KeyValueItem("Latitude", "Ice Climbing Cogne"),
                    new KeyValueItem("Longitude", "4"));
	
	public MetadataLocationView() {
		setData(data);
		init();
		
	}
	

}

/*
 new KeyValueItem("Location", "Ice Climbing Cogne"),
            		new KeyValueItem("Latitude", "Ice Climbing Cogne"),
                    new KeyValueItem("Longitude", "4"));
 */
