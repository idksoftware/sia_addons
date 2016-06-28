/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idk.imgarchive.base.workspacemanager;

import java.io.File;

/**
 * This class is used to manage the directory is associated with the currently
 * imported image. It enables the import classes to manage absolute and relative
 * powers to accompany imported image. It is a static class as there is only one
 * currently imported image in the system at one time.
 * 
 * @author Iain Ferguson
 */
public class DirectoryManager {

	/**
	 * This is the current partition number.
	 */
	static private long directoryCount = 0;
	/**
	 * This is a string representation of the current partition
	 */
	static String hexNumber = null;
	/**
	 * This is a string representation of the current image ID.
	 */
	static String imageFolderString = null;
	/**
	 * This is the path to the current working cluster root directory.
	 * 
	 */
	static private String path;

	/**
	 * This is used to create a new partition.
	 * 
	 * @param l
	 */
	static public void create(final long directoryCount) {
		final String hexNumber = ImageAddress.makeAddressString(directoryCount);
		final File newFile = new File(path, hexNumber);
		newFile.mkdir();
	}

	/**
	 * This provides an absolute path to the current partition.
	 * 
	 * @param i
	 * @return
	 */
	static public String getDirectoryPath(final long i) {
		hexNumber = ImageAddress.makeAddressString(i);
		final File pathFile = new File(path, hexNumber);
		return pathFile.getAbsolutePath();
	}

	/**
	 * This provides an absolute path to the currently imported image having
	 * both the partition and image ID passed as parameters.
	 * 
	 * @param l
	 * @param m
	 * @return
	 */
	static public String getImageDirectoryPath(final long l, final long m) {

		hexNumber = ImageAddress.makeAddressString(l);
		imageFolderString = ImageAddress.makeAddressString(m);
		final File imageFolder = new File(path + File.separator + hexNumber + File.separator + imageFolderString);
		if (imageFolder.exists() == false) {
			if (imageFolder.mkdirs() == false) {
				return null;
			}
		}
		return imageFolder.getAbsolutePath();
	}

	/**
	 * This provides a relative path to the current image. It consists of both
	 * the partition and the image.
	 * 
	 * @return
	 */
	static public String getImageRelativePath() {

		return hexNumber + File.separator + imageFolderString;
	}

	/**
	 * This returns the current path to the current cluster.
	 * 
	 * @return
	 */
	static public String getPath() {
		return path;
	}

	/**
	 * This constructor is used to initialise the directory manager with the
	 * current cluster root directory.
	 * 
	 * @param p
	 */
	public DirectoryManager(final String p) {
		path = p;
	}
}
