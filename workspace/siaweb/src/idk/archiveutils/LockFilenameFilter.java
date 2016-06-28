package idk.archiveutils;

import java.io.File;
import java.io.FilenameFilter;

public class LockFilenameFilter implements FilenameFilter {

	@Override
	public boolean accept(final File arg0, final String arg1) {
		// TODO Auto-generated method stub
		final String fileName = arg1.toLowerCase();
		if (fileName.startsWith(".") && fileName.endsWith(".lck")) {
			return true;
		}
		return false;
	}

}
