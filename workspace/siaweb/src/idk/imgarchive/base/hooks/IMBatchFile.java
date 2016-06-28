package idk.imgarchive.base.hooks;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import idk.archiveutils.FileUtils;
import idk.archiveutils.ImageUtils;
import idk.archiveutils.ImageUtils.ImageDimensions;
import idk.imgarchive.base.log4j.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Uses a Runtime.exec()to use imagemagick to perform the given conversion
 * operation. Returns true on success, false on failure. Does not check if
 * either file exists.
 * 
 * @param in
 *            Description of the Parameter
 * @param out
 *            Description of the Parameter
 * @param newSize
 *            Description of the Parameter
 * @param quality
 *            Description of the Parameter
 * @return Description of the Return Value
 */
public class IMBatchFile {

	private static boolean enable = false;
	private static int height = 0;
	private static String toolsFolder = null;
	private static int width = 0;
	private static boolean useInternalIdentify = true;

	public static boolean convert(final File in, final File out, final int width, final int height, int quality) {
		System.out.println("convert(" + in.getPath() + ", " + out.getPath() + ", " + quality);
		if (quality < 0 || quality > 100) {
			quality = 75;
		}

		final ArrayList<String> command = new ArrayList<String>(10);

		// note: CONVERT_PROG is a class variable that stores the location of
		// ImageMagick's convert command
		// it might be something like "/usr/local/magick/bin/convert" or
		// something else, depending on where you installed it.
		final String convertPath = toolsFolder + File.separator + "convert";
		command.add(convertPath);
		command.add("-geometry");
		command.add(width + "x" + height);
		command.add("-quality");
		command.add("" + quality);
		command.add(in.getAbsolutePath());
		command.add(out.getAbsolutePath());

		Log.getLogger().info(command);

		return exec(command.toArray(new String[1]));
	}

	public static boolean convertThumbnail(final File in, final File out, final int width, final int height, int quality) {
		System.out.println("convert(" + in.getPath() + ", " + out.getPath() + ", " + quality);
		if (quality < 0 || quality > 100) {
			quality = 75;
		}

		final ArrayList<String> command = new ArrayList<String>(10);

		// note: CONVERT_PROG is a class variable that stores the location of
		// ImageMagick's convert command
		// it might be something like "/usr/local/magick/bin/convert" or
		// something else, depending on where you installed it.
		final String convertPath = toolsFolder + File.separator + "convert";
		command.add(convertPath);
		command.add("-size");
		command.add("\"200x200>\"");
		command.add("-thumbnail");
		command.add("" + "\"100x100>\"");
		command.add(in.getAbsolutePath());
		command.add(out.getAbsolutePath());
		// Log.warn(command);

		return exec(command.toArray(new String[1]));
	}

	public static boolean convertToEighth(final String pathIn, final String pathOut, final String file) {
		final File in = new File(pathIn + File.separator + file);
		final int dot = file.lastIndexOf(".");
		final String ext = file.substring(dot, file.length());
		final String outFile = file.substring(0, dot) + "_8" + ext;
		final File out = new File(pathOut + File.separator + outFile);
		return convert(in, out, IMBatchFile.width / 8, IMBatchFile.height / 8, 85);
	}

	/*
	 * convert -size 200x200 hatching.jpg -thumbnail '100x100>' \ -gravity
	 * center -crop 120x120+0+0\! \ -background skyblue -flatten pad_view.gif
	 */
	public static boolean convertToHalf(final String pathIn, final String pathOut, final String file) {
		final File in = new File(pathIn + File.separator + file);
		final int dot = file.lastIndexOf(".");
		final String ext = file.substring(dot, file.length());
		final String outFile = file.substring(0, dot) + "_2" + ext;
		// final String outFile = file.substring(0, dot) + "_2" + ".jpg";
		final File out = new File(pathOut + File.separator + outFile);
		return convert(in, out, IMBatchFile.width / 2, IMBatchFile.height / 2, 85);
	}

	public static boolean convertToQuarter(final String pathIn, final String pathOut, final String file) {
		final File in = new File(pathIn + File.separator + file);
		final int dot = file.lastIndexOf(".");
		final String ext = file.substring(dot, file.length());
		final String outFile = file.substring(0, dot) + "_4" + ext;
		final File out = new File(pathOut + File.separator + outFile);
		return convert(in, out, IMBatchFile.width / 4, IMBatchFile.height / 4, 85);
	}

	public static boolean convertToThumbnail(final String pathIn, final String pathOut, final String file) {
		final File in = new File(pathIn + File.separator + file);
		final int dot = file.lastIndexOf(".");
		final String ext = file.substring(dot, file.length());
		final String outFile = file.substring(0, dot) + "_T" + ext;
		final File out = new File(pathOut + File.separator + outFile);
		return convertThumbnail(in, out, IMBatchFile.width / 2, IMBatchFile.height / 2, 85);
	}

	public static boolean doAll(final String pathIn, final String pathOut, final String file) {
		if (enable == true) {

			// Use internal identify
			if (useInternalIdentify == true) {
				final ImageDimensions imageDimensions = ImageUtils.originalSize(pathIn + File.separator + file);
				height = imageDimensions.getHeight();
				width = imageDimensions.getWidth();
			} else {
				if (IMBatchFile.identify(new File(pathIn + File.separator + file)) == false) {
					return false;
				}
			}
			if (IMBatchFile.convertToHalf(pathIn, pathOut, file) == false) {
				return false;
			}
			if (IMBatchFile.convertToQuarter(pathIn, pathOut, file) == false) {
				return false;
			}
			if (IMBatchFile.convertToEighth(pathIn, pathOut, file) == false) {
				return false;
			}
			if (IMBatchFile.convertToThumbnail(pathIn, pathOut, file) == false) {
				return false;
			}
		}
		return true;
	}

	public static boolean doJournal(final String pathIn, final String pathOut, final String file) {

		if (IMBatchFile.identify(new File(pathIn + File.separator + file)) == false) {
			return false;
		}

		if (IMBatchFile.convertToQuarter(pathIn, pathOut, file) == false) {
			return false;
		}

		if (IMBatchFile.convertToThumbnail(pathIn, pathOut, file) == false) {
			return false;
		}

		return true;
	}

	/**
	 * Tries to exec the command, waits for it to finish, logs errors if exit
	 * status is nonzero, and returns true if exit status is 0 (success).
	 * 
	 * @param command
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	private static boolean exec(final String[] command) {
		Process proc;

		try {
			// System.out.println("Trying to execute command " +
			// Arrays.asList(command));
			proc = Runtime.getRuntime().exec(command);
		} catch (final IOException e) {

			Log.warn("IOException while trying to execute " + command[0]);
			return false;
		}

		// System.out.println("Got process object, waiting to return.");

		int exitStatus;

		while (true) {
			try {
				exitStatus = proc.waitFor();
				break;
			} catch (final java.lang.InterruptedException e) {
				Log.warn("Interrupted: Ignoring and waiting");
			}
		}
		if (exitStatus != 0) {
			Log.warn("Error executing command: " + exitStatus);
		}
		return exitStatus == 0;
	}

	/*
	
	 */

	public static boolean identify(final File in) {

		final ArrayList<String> command = new ArrayList<String>(10);
		final ArrayList<String> reponce = new ArrayList<String>(10);
		// note: CONVERT_PROG is a class variable that stores the location of
		// ImageMagick's convert command
		// it might be something like "/usr/local/magick/bin/convert" or
		// something else, depending on where you installed it.
		final String path = toolsFolder + File.separator + "identify";
		if (FileUtils.fileExists(toolsFolder, "identify.exe") == null) {
			return false;
		}
		command.add(path);
		command.add(in.getAbsolutePath());

		Log.info(command.toString());

		final String[] args = command.toArray(new String[1]);
		try {
			String line;

			final Process proc = Runtime.getRuntime().exec(args);
			final BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			while ((line = input.readLine()) != null) {
				// System.out.println(line);
				reponce.add(line);
			}
			input.close();
			IMBatchFile.parseSize(reponce.get(0));

		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * @return the enable
	 */
	public static boolean isEnabled() {
		return enable;
	}

	private static void parseSize(final String line) {
		final String[] args = line.split(" ");
		for (final String arg : args) {
			if (arg.indexOf("x") > 0) {
				final String[] ws = arg.split("x");
				try {
					IMBatchFile.width = Integer.parseInt(ws[0]);
					IMBatchFile.height = Integer.parseInt(ws[1]);
					break;
				} catch (final NumberFormatException e) {
					continue;
				}
			}
		}

	}

	/**
	 * @param enable
	 *            the enable to set
	 */
	public static void setEnabled(final boolean enable) {
		IMBatchFile.enable = enable;
	}

	public static boolean SetToolFolder(final String tf) {
		toolsFolder = tf;
		final File toolsFolderObj = new File(toolsFolder);
		if (toolsFolderObj.exists() == true) {
			enable = true;
			return true;
		}
		return false;
	}

}