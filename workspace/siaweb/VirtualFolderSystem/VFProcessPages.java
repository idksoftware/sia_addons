package idk.imgarchive.base.VirtualFolderSystem;

import idk.archiveutils.FileUtils;
import idk.imgarchive.base.hooks.Css;
import idk.imgarchive.base.hooks.XmlHtml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.text.ParseException;

public class VFProcessPages {

	private static class PageFileFilter implements FilenameFilter {

		@Override
		public boolean accept(final File dir, final String name) {
			final int idxS = name.lastIndexOf("@");
			final int idxE = name.lastIndexOf(".");
			final String numStr = name.substring(idxS + 1, idxE);
			return isNum(numStr);
		}

		public static boolean isNum(final String s) {
			try {
				Integer.parseInt(s);
			} catch (final NumberFormatException nfe) {
				return false;
			}
			return true;
		}

	}

	public static void process(final String wwwroot) throws FileNotFoundException, ParseException {
		final File xmlRoot = new File(wwwroot, "xml");
		final File htmlRoot = new File(wwwroot, "html");
		final File cssRoot = new File(wwwroot, "css");
		if (xmlRoot.exists() == false) {
			return;
		}
		if (htmlRoot.exists() == false) {
			return;
		}
		if (cssRoot.exists() == false) {
			return;
		}
		final File[] xmlFiles = xmlRoot.listFiles(new PageFileFilter());
		for (final File item : xmlFiles) {
			final String filename = item.getName();
			final String filename_noext = FileUtils.stripFileExtension(filename);
			XmlHtml.makeHtml("foldercatalog.xsl", item.getAbsolutePath(), htmlRoot + File.separator + filename_noext + ".html");
			Css.Copy("AuthorIndex.css", cssRoot.getAbsolutePath(), "index.css");

		}
		return;
	}
}
