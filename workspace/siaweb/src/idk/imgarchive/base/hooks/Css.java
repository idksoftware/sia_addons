package idk.imgarchive.base.hooks;

import idk.archiveutils.FileUtils;
import idk.config.ConfigInfo;
import idk.imgarchive.base.log4j.Log;

import java.io.File;
import java.io.FileNotFoundException;

public class Css {

	static public boolean Copy(final String cssFilename, final String destPath) throws FileNotFoundException {
		Log.info("Copying \"" + cssFilename + "\" to \"" + destPath + "\"");

		final File cssFile = new File(ConfigInfo.getCssPath(), cssFilename);
		final File destFile = new File(destPath, cssFilename);
		if (destFile.exists() == true) {
			// if source is newer copy else not
			if (cssFile.lastModified() > destFile.lastModified()) {
				return FileUtils.copyIfNotExist(destFile.getAbsolutePath(), cssFile.getAbsolutePath());
			}
		} else {
			return FileUtils.copyIfNotExist(destFile.getAbsolutePath(), cssFile.getAbsolutePath());
		}
		return true; // do nothing
	}

	public static boolean Copy(final String cssFilename, final String destPath, final String destFilename)
			throws FileNotFoundException {
		Log.info("Copying \"" + cssFilename + "\" to \"" + destPath + "\" file name changed to \"" + destFilename + "\"");

		final File cssFile = new File(ConfigInfo.getCssPath(), cssFilename);
		final File destFile = new File(destPath, destFilename);
		if (destFile.exists() == true) {
			// if source is newer copy else not
			if (cssFile.lastModified() > destFile.lastModified()) {
				return FileUtils.copyIfNotExist(destFile.getAbsolutePath(), cssFile.getAbsolutePath());
			}
		} else {
			return FileUtils.copyIfNotExist(destFile.getAbsolutePath(), cssFile.getAbsolutePath());
		}
		return true; // do nothing
	}
}
