package Keywords;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class KeywordSet extends ArrayList<String> {
	
	public void print() {
		for (String s : this) {
			System.out.println("Word: " + s);
		}
	}
}
