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
/**
 * 
 * This returns a picture file. RAW file are not to be included
 * as they may not be processes correctly.
 *
 */
public class FileTypeRawInclude implements FileFilter {

	public FileTypeRawInclude() {

	}

	@Override
	public boolean accept(final File pathname) {
		final String name = pathname.getName();
		return accept(name);
	}

	
	public boolean accept(final String name) {
		final File fileName = new File(name.toLowerCase());
		if (fileName.getName().startsWith("img")) {
			//return true;
			FileExtentionList list = FileExtentions.getRawExtentions();
			int idx = fileName.getName().lastIndexOf("."); String ext =
			fileName.getName().substring(idx+1,fileName.getName().length());
			for (String item : list) { 
				if (item.compareToIgnoreCase(ext) == 0) {
					return true;
				}
			}
		}
		return false;
	}

}
