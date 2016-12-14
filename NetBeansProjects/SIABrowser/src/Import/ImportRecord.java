package Import;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ImportRecord {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty path;
    private final SimpleStringProperty status;
    int statusInt;
    
    public enum EStatus {
    	Pending,
        Inprogress,
        Complete,
        Errors,
        Fatal
    }
    
    static final String Status[] = {
            "Pending",
            "Inprogress",
            "Complete",
            "Errors",
            "Fatal"};
     
     
    ImportRecord(int i, String path, int status ) {
        this.id = new SimpleIntegerProperty(i);
        this.path = new SimpleStringProperty(path);
        this.status = new SimpleStringProperty(Status[status]);
    }
     
    public int getId() {
        return id.get();
    }

    public void setId(int v) {
        id.set(v);
    }
     
    public String getPath() {
        return path.get();
    }

    public void setPath(String v) {
        path.set(v);
    }
     
    public String getStatus() {
        return status.get();
    }

    public void setStatus(String v) {
        status.set(v);
    }
     
    public void setStatus(EStatus v) {
        status.set(v.toString());
    }

    
};
