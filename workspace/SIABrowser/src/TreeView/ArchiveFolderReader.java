package TreeView;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ArchiveFolderReader {
	@SuppressWarnings("serial")
	abstract class ListItem extends ArrayList<String> {
		public void printList() {
			for (String y : this) {
				System.out.println(y);
			}
		}
	}
	
	@SuppressWarnings("serial")
	class YearList extends ListItem {
	
		public YearList() {
			File f = new File(root.toString());
			File[] fl = f.listFiles();
			for (File file : fl) {
				add(file.getName());
			}
		}
		
		
	};
	
	@SuppressWarnings("serial")
	class ImageList extends ListItem {
		public ImageList(String day) {
			String year = day.substring(0,4);
			String path = root.toString() +
							File.separator +
							year +
							File.separator +
							day;
			File f = new File(path);
			File[] fl = f.listFiles();
			for (File file : fl) {
				add(file.getAbsolutePath());
			}
		}
	};
	
	
	@SuppressWarnings("serial")
	class DayList extends ListItem {
		DayList(String year) {
			File f = new File(root.toString() + File.separator + year);
			File[] fl = f.listFiles();
			for (File file : fl) {
				add(file.getName());
			}
		}
	}
	YearList yearList;
	Path root = null;
	String currYear;
	ArchiveFolderReader(String path) {
		this.root = Paths.get(path);
		yearList = new YearList();
	}
	void resetYear() {
		
	}
	YearList getYear() {
		return yearList;
	}
	
	DayList getDay(String year) {
		DayList dayList = new DayList(year);
		return dayList; 
	}
	
	ImageList getImage(String day) {
		ImageList imageList = new ImageList(day);
		return imageList; 
	}
}

