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

public class FileTypeAllInfoInclude implements FileFilter {

	public FileTypeAllInfoInclude() {
	}

	@Override
	public boolean accept(final File pathname) {
		final String name = pathname.getName();
		final File fileName = new File(name.toLowerCase());

		if (fileName.getName().endsWith(".xml")) {
			if (fileName.getName().startsWith("ifo")) {
				return true;
			} else if (fileName.getName().startsWith("exf")) {
				return true;
			} else if (fileName.getName().startsWith("ipc")) {
				return true;
			}
		}

		return false;
	}

}
