package idk.imgarchive.base.workspacemanager;

import java.io.File;
import java.io.FilenameFilter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Iain Ferguson
 */

public class FileTypeManifestInclude implements FilenameFilter {

	public FileTypeManifestInclude() {

	}

	@Override
	public boolean accept(final File arg0, final String arg1) {
		// TODO Auto-generated method stub
		final String fileName = arg1.toLowerCase();
		if (fileName.startsWith("imf") && fileName.endsWith(".xml")) {
			return true;
		}
		return false;

	}

}
