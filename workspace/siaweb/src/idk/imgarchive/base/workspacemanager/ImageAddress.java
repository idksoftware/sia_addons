package idk.imgarchive.base.workspacemanager;

import java.io.File;

/**
 * This class provides an image address of the currently indexed image. This is
 * normally a helper class used by the image cursor class.
 * 
 * This class provides indexing across the entire instance so it is not
 * constrained to a partition or cluster.
 * 
 * @author iain
 * 
 */
public class ImageAddress {
	final public static String makeAddressString(final long id) {
		return String.format("%08X", id);
	}

	final public static long makeLong(final String id) throws NumberFormatException {
		return Long.parseLong(id, 16);
	}

	long current;
	long currentPartition = 0;

	long maxPartition = 0;

	String path;

	public ImageAddress() {
	}

	public ImageAddress(final ImageAddress ia) {
		path = ia.path;
		current = ia.current;
		maxPartition = ia.maxPartition;
		currentPartition = ia.currentPartition;
	}

	/**
	 * @return the current
	 */
	public long getCurrent() {
		return current;
	}

	/**
	 * @return the currentPartition
	 */
	public long getCurrentPartition() {
		return currentPartition;
	}

	/**
	 * @return the maxPartition
	 */
	public long getMaxPartition() {
		return maxPartition;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	final public String makeRelativeAddressString() {
		return String.format("%08X", currentPartition) + File.separator + String.format("%08X", current);
	}
}
