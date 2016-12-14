package MetadataTemplate;


import MetadataTemplate.MetadataTemplateView.KeyValueItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class MetadataCreatorView extends MetadataTemplateView {
	
	private ObservableList<MetadataTemplateView.KeyValueItem> data =
            FXCollections.observableArrayList(
            		new KeyValueItem("Creator", "4"),
                    new KeyValueItem("JobTitle", "4"),
                    new KeyValueItem("Address", "4"),
                    new KeyValueItem("City", "4"),
                    new KeyValueItem("State", "4"),
                    new KeyValueItem("PostalCode", "4"),
                    new KeyValueItem("Country", "4"),
                    new KeyValueItem("Phone", "4"),
                    new KeyValueItem("Email", "4"),
                    new KeyValueItem("WebSite", "4"));
	public MetadataCreatorView() {
		setData(data);
		init();
		
	}
	

}
