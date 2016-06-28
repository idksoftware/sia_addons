/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idk.imgarchive.base.workspacemanager;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * This class manages the sequence numbers. Each image is allocated a sequence
 * number is number is constantly incremented as new images are added. this also
 * maintains information of when a new partition needs to be created.
 * 
 * The sequence number class also maintains the last image in the sequence this
 * enables other applications to know the highest image ID in the archive.
 * 
 * @author Iain Ferguson
 */
public class SequenceNumber {

	public static class SqnFileFilter implements FileFilter {
		@Override
		public boolean accept(final File pathname) {
			if (pathname.getName().endsWith(".sys")) {
				return true;
			}
			return false;
		}
	}

	private static long count = 0;
	private static String current;
	private static long directory = 0;
	private static boolean isNewDirectory = false;
	private static long maxPartition = 0;
	private static long partitionCount = 0;
	private static String path;
	private static SqnFileFilter sqnFileFilter = null;

	private static SequenceNumber thisInstance = null;

	/**
	 * This formats the string used by the sequence number file.
	 * 
	 * @return
	 */
	private static String format() {
		return String.format("%08X@%08X.%03X", count, directory, partitionCount);
	}

	/**
	 * This returns the maximum image count. This will be the next ID the next
	 * image to be added to the archive.
	 * 
	 * @return
	 */
	public static long getCount() {
		return SequenceNumber.count;
	}

	/**
	 * This returns the number of partitions in the archive
	 * 
	 * @return
	 */
	public static long getDirectoryCount() {
		return SequenceNumber.directory;
	}

	/**
	 * This will initialise the sequence file with the next sequence number.
	 */
	public static void getNext() {
		readFile();
		count++;
		isNewDirectory = false;
		if (SequenceNumber.partitionCount >= maxPartition) {
			SequenceNumber.partitionCount = 0;
			SequenceNumber.directory++;
			isNewDirectory = true;
		}

		partitionCount++;
		// this.deleteFiles();
		final File oldSqnFile = new File(SequenceNumber.path, current);
		final File newSqnFile = new File(SequenceNumber.path, format() + ".sys");
		oldSqnFile.renameTo(newSqnFile);

	}

	/**
	 * This returns the number of partitions in the archive
	 * 
	 * @return
	 */
	public static long getPartitionCount() {
		return SequenceNumber.partitionCount;
	}

	/**
	 * This will initialise the sequence number object from the sequence file.
	 */
	public static void readFile() {
		final File cwd = new File(SequenceNumber.path);
		final File[] sqnFiles = cwd.listFiles(sqnFileFilter);
		if (sqnFiles.length == 0) {
			return;
		}
		current = sqnFiles[0].getName();
		final int endCount = current.indexOf('@');
		final String countString = current.substring(0, endCount);
		SequenceNumber.count = Integer.parseInt(countString, 16);

		final int endDir = current.indexOf('.', endCount);
		final String dirString = current.substring(endCount + 1, endDir);
		SequenceNumber.directory = Integer.parseInt(dirString, 16);

		final int endPar = current.indexOf('.', endDir + 1);
		final String parString = current.substring(endDir + 1, endPar);
		SequenceNumber.partitionCount = Integer.parseInt(parString, 16);

	}

	/*
	 * this test to see if sequence file exists
	 */
	static boolean seqFileExists() {
		final File folder = new File(SequenceNumber.path);

		final File[] files = folder.listFiles();
		if (files == null) {
			return false; // no sequence files exists
		}
		for (final File file : files) {
			// String name = files[i].getName();
			if (file.getName().endsWith(".sys") == true) {
				return true; //
			}
		}
		return false;
	}

	private SequenceNumber() {
		sqnFileFilter = new SqnFileFilter();
	}

	/**
	 * This creates the sequence number object.
	 * 
	 * @param p
	 *            - path to where the sequence file resides.
	 * @param l
	 *            - the number of images per partition.
	 */
	public SequenceNumber(final String p, final long l) {
		if (thisInstance == null) {
			thisInstance = new SequenceNumber();
			SequenceNumber.path = p;
			maxPartition = l;
		} else {
			throw new RuntimeException("Initalising sequence mumber more than once");
		}
	}

	public static boolean IsInstance() {
		if (thisInstance == null) {
			return false;
		}
		return true;
	}

	public static SequenceNumber getSequenceNumber() {
		return thisInstance;
	}

	public void reset() {
		SequenceNumber.count = 0;
		SequenceNumber.partitionCount = 0;
		SequenceNumber.directory = 0;
		readFile();
		final File oldSqnFile = new File(SequenceNumber.path, current);
		final File newSqnFile = new File(SequenceNumber.path, format() + ".sys");
		oldSqnFile.renameTo(newSqnFile);
	}

	/**
	 * This creates a new sequence file using the parameters passed.
	 * 
	 * @param partitionCount
	 * @param maxPartition
	 * @param directory
	 */
	void createSeqFile(final long count, final long partition, final long partitionCount) {
		SequenceNumber.count = count;
		SequenceNumber.partitionCount = partitionCount;
		SequenceNumber.directory = partition;
		final File newFile = new File(SequenceNumber.path, format() + ".sys");
		try {
			newFile.createNewFile();
		} catch (final IOException e) {
			System.err.println("I/O error: ");
		}
	}

	void InitSequenceNumberWithoutFile(final String currentClusterPath) throws IOException {
		final File partitionFolder = new File(currentClusterPath);
		final File[] partitionFiles = partitionFolder.listFiles();
		if (partitionFiles == null) {
			throw new IOException("No Archive exist at \"" + currentClusterPath + "\"");
		}
		long maxPartition = 0;
		long maxImageFiles = 0;
		int index = -1;
		for (int i = 0; i < partitionFiles.length; i++) {
			long temp = 0;
			// String name = files[i].getName();
			if (partitionFiles[i].getName().length() == 8) {
				if ((temp = Long.parseLong(partitionFiles[i].getName(), 16)) > maxPartition) {
					maxPartition = temp;
					index = i;
				}
			}
		}
		if (index == -1) {
			// No partition files so archive must be empty
			maxImageFiles = 0;
			maxPartition = 0;
			index = 0;
		} else {
			final File partition = partitionFiles[index];
			final File[] imageFiles = partition.listFiles();
			for (int i = 0; i < imageFiles.length; i++) {
				long temp = 0;
				// String name = files[i].getName();
				if (imageFiles[i].getName().length() == 8) {
					if ((temp = Long.parseLong(imageFiles[i].getName(), 16)) > maxPartition) {
						maxImageFiles = temp;
						index = i;
					}
				}
			}
		}
		createSeqFile(maxImageFiles, maxPartition, index);
	}

	public boolean IsNewDirectory() {
		return isNewDirectory;
	}

}
