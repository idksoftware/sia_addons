package idk.imgarchive.base.workspacemanager;

/**
 * This class provides an image address of the currently indexed partition. This
 * is normally a helper class used by the partition cursor class.
 * 
 * @author iain
 * 
 */
public class PartitionAddress {
	final public static String makeAddressString(final long id) {
		return String.format("%08X", id);
	}

	final public static long makeLong(final String id) throws NumberFormatException {
		return Long.parseLong(id, 16);
	}

	long current = -1;
	long firstImage = -1;
	long lastImage = -1;

	long maxPartition = 0;
	String path;

	/**
	 * @return the current partition
	 */
	public long getCurrent() {
		return current;
	}

	/**
	 * @return the firstImage
	 */
	public final long getFirstImage() {
		return firstImage;
	}

	/**
	 * @return the lastImage
	 */
	public final long getLastImage() {
		return lastImage;
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
}
