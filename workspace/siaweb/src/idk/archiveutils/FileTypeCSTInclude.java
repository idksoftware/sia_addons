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

public class FileTypeCSTInclude implements FileFilter {

	public FileTypeCSTInclude() {

	}

	@Override
	public boolean accept(final File pathname) {
		final String name = pathname.getName();
		return accept(name);
	}

	public boolean accept(final String name) {
		final File fileName = new File(name.toLowerCase());
		if (fileName.getName().startsWith("mcf") && fileName.getName().endsWith(".xml")) {
			return true;

		}
		return false;
	}

}
