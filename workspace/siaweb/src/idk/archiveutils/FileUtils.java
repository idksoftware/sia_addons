package idk.archiveutils;

import idk.imgarchive.base.log4j.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtils {
	public static void copyFile(final File sourceFile, final File destFile) throws IOException {
		if (!sourceFile.exists()) {
			throw new FileNotFoundException();
		}
		if (!destFile.exists()) {
			if (destFile.createNewFile() == false) {
				throw new IOException("Cannot create file \"" + destFile + "\"");
			}
		}
		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public static void copyFileWithOverWrite(final File sourceFile, final File destFile) throws IOException {
		if (!sourceFile.exists()) {
			throw new FileNotFoundException();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public static void copyFileWithVersioning(final File sourceFile, final File destFile) throws IOException {
		if (!sourceFile.exists()) {
			throw new FileNotFoundException();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public static boolean copyIfNotExist(final String path, final String path2File2Copy) throws FileNotFoundException {
		final File pathFile = new File(path);
		final File parentFile = new File(pathFile.getParent());

		if (pathFile.exists() == true) {
			return true;
		}
		if (parentFile.exists() == false) {
			if (parentFile.mkdirs() == false) {
				return false;
			}
		}
		final File path2CopyFile = new File(path2File2Copy);
		if (pathFile.exists() == true) {
			return false;
		}
		try {
			FileUtils.copyFile(path2CopyFile, pathFile);
		} catch (final IOException e) {
			Log.CopyFileException(path2CopyFile, pathFile, e);
		}
		return true;
	}

	static public boolean deleteDirectory(final File path) {
		if (path.exists()) {
			final File[] files = path.listFiles();
			for (final File file : files) {
				if (file.isDirectory()) {
					deleteDirectory(file);
				} else {
					if (file.delete() == false) {
						return false;
					}
				}
			}
		}
		return path.delete();
	}

	public static File fileExists(final String path) {
		final File pathFile = new File(path);
		if (pathFile.exists() == true) {
			return pathFile;
		}
		return null;
	}

	
	public static File fileExists(final String path, final String file) {
		final File pathFile = new File(path, file);
		if (pathFile.exists() == true) {
			return pathFile;
		}
		return null;
	}

	public static boolean makePath(final String path) {
		final File pathFile = new File(path);
		if (pathFile.exists() == true) {
			if (pathFile.isDirectory() == false) {
				return false;
			}
			return true;
		}
		if (pathFile.mkdirs() == true) {
			return true;
		}
		return false;
	}

	/**
	 * Strips extension from the file name.
	 */
	public static String stripFileExtension(final String fileName) {
		final int dotInd = fileName.lastIndexOf('.');

		// if dot is in the first position,
		// we are dealing with a hidden file rather than an extension
		return dotInd > 0 ? fileName.substring(0, dotInd) : fileName;
	}

	public static String getFileExtension(final String fileName, final boolean toUpperCase) {
		final int mid = fileName.lastIndexOf(".");
		String ext = fileName.substring(mid + 1, fileName.length());
		if (toUpperCase) {
			ext = ext.toUpperCase();
		} else {
			ext = ext.toLowerCase();
		}
		return ext;
	}

}
