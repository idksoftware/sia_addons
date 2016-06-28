package idk.imgarchive.base.hooks;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.w3c.dom.Document;

import xmlutils.XMLUtil;

public class main {
	static Document xmlDocument = null;

	public static void main(final String[] args) {

		final String homePath = System.getenv("IMGARCHIVE_HOME");
		if (homePath == null) {

			return;
		}
		final File file = new File(homePath + File.separator + "scripts", "importimages.xml");
		try {
			try {
				xmlDocument = XMLUtil.parse(file.getAbsolutePath());
			} catch (final ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (final IOException e) {

			return;
		}
	}
}
