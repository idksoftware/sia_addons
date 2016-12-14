package MetadataTemplate;


import MetadataTemplate.MetadataTemplateView.KeyValueItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class MetadataCopyrightView extends MetadataTemplateView {
	
	private ObservableList<MetadataTemplateView.KeyValueItem> data =
            FXCollections.observableArrayList(
            		new KeyValueItem("Copyright", "4"),
            		new KeyValueItem("UsageRights", "4"),
            		new KeyValueItem("CopyrightURL", "4"));
	
	public MetadataCopyrightView() {
		setData(data);
		init();
		
	}
	

}
