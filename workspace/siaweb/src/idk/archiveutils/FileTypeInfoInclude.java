package idk.archiveutils;

import java.io.File;
import java.io.FileFilter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Iain Ferguson
 */

public class FileTypeInfoInclude implements FileFilter {

	public FileTypeInfoInclude() {
	}

	@Override
	public boolean accept(final File pathname) {
		final String name = pathname.getName();
		final File fileName = new File(name.toLowerCase());

		if (fileName.getName().startsWith("ifo") && fileName.getName().endsWith(".xml")) {
			return true;
		}

		return false;
	}

}
