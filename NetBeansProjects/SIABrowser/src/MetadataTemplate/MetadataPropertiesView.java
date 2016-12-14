package MetadataTemplate;


import MetadataTemplate.MetadataTemplateView.KeyValueItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class MetadataPropertiesView extends MetadataTemplateView {
	
	private ObservableList<MetadataTemplateView.KeyValueItem> data =
            FXCollections.observableArrayList(
            		new KeyValueItem("Label", "4"),
            		new KeyValueItem("Rating", "4"),
            		new KeyValueItem("Headline", "4"),
            		new KeyValueItem("Discription", "4"),
                    new KeyValueItem("Category", "4"),
                    new KeyValueItem("Keywords", "4"),
                    new KeyValueItem("Source", "4"),
                    new KeyValueItem("Instructions", "4"),
                    new KeyValueItem("Scene", "4"));
                    
	public MetadataPropertiesView() {
		setData(data);
		init();
		
	}


}

/*
 
Copyright>Microsoft Corporation</Copyright>
<UsageRights/>
<CopyrightURL/>



*/