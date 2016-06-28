package idk.imgarchive.base.workspacemanager;

import idk.imgarchive.base.workspacemanager.ClusterMap.ClusterMapItem;

import java.io.File;
import java.util.Iterator;

/**
 * 
 * @author iain
 * 
 */
public class PartitionCursor implements Iterable<PartitionAddress> {

	class PartitionIterator implements Iterator<PartitionAddress> {
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub

			return !isLast();
		}

		@Override
		public PartitionAddress next() {
			currentIndex.current++;
			if (currentIndex.current > Workspace.getPartitionCount()) {
				currentIndex.current = Workspace.getPartitionCount();
				return null;
			}
			changeCurrentAddress();
			return currentIndex;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}
	}

	public static final long getSize() {
		return Workspace.getLastPartitionIdx();
	}

	ClusterMapItem clusterMapItem = null;
	private final PartitionAddress currentIndex = new PartitionAddress();
	private final PartitionIterator partitionIterator = new PartitionIterator();

	protected PartitionCursor() {
	}

	/**
	 * Moves the cursor to the given image number in this workspace object.
	 * 
	 * @param row
	 * @return
	 */
	public boolean absolute(final long row) {
		if (row > SequenceNumber.getDirectoryCount() - 1 || row < 0) {
			return false;
		}
		currentIndex.current = row;
		changeCurrentAddress();
		return true;
	}

	private boolean changeCurrentAddress() {

		if (clusterMapItem == null || !clusterMapItem.isPartitionInRange(currentIndex.current)) {
			clusterMapItem = ClusterMap.findPartition(currentIndex.current);
			currentIndex.maxPartition = clusterMapItem.getImagesPerPartition();
		}

		if (clusterMapItem == null) {
			return false;
		}

		// this is done as the partitions start from 00000000
		final String root = clusterMapItem.getRootPath();

		// clusterMapItem.lowestImage
		currentIndex.firstImage = currentIndex.current * clusterMapItem.imagesPerPartition + clusterMapItem.lowestImage;
		final long lastImage = currentIndex.firstImage + clusterMapItem.imagesPerPartition - 1;
		if (lastImage > clusterMapItem.highestImage) {
			currentIndex.lastImage = clusterMapItem.highestImage;
		} else {
			currentIndex.lastImage = lastImage;
		}
		final String partitionPath = root + File.separator + "DCIM" + File.separator
				+ ImageAddress.makeAddressString(currentIndex.current);

		currentIndex.path = partitionPath;
		return true;
	}

	/**
	 * Moves the cursor to the first Image in this workspace object.
	 */
	boolean first() {

		return false;

	}

	/**
	 * 
	 * @return
	 */
	public PartitionAddress getCurrent() {
		if (currentIndex.current > Workspace.getLastPartitionIdx()) {
			currentIndex.current = Workspace.getLastPartitionIdx();
			return null;
		}
		changeCurrentAddress();
		return currentIndex;
	}

	/**
	 * Retrieves the name of the cursor used by this workspace object.
	 * 
	 * @return
	 */
	String getCursorName() {
		return null;

	}

	/**
	 * Retrieves the current Image number.
	 */

	long getPartition() {
		return currentIndex.current;

	}

	/**
	 * Retrieves whether the cursor is on the first row of this workspace
	 * object.
	 * 
	 * @return
	 */
	boolean isFirst() {
		if (currentIndex.current == 0) {
			return true;
		}
		return false;

	}

	/**
	 * Retrieves whether the cursor is on the last row of this workspace object.
	 * 
	 * @return
	 */
	boolean isLast() {
		if (Workspace.getLastPartitionIdx() == currentIndex.current) {
			return true; // Off the end of the archive.
		}
		return false;

	}

	@Override
	public Iterator<PartitionAddress> iterator() {
		// TODO Auto-generated method stub
		return partitionIterator;
	}

	/**
	 * Moves the cursor to the last row in this workspace object.
	 */
	public boolean last() {
		currentIndex.current = Workspace.getLastPartitionIdx();
		return true;
	}

	/*
	 * @Override public Iterator iterator() { // TODO Auto-generated method stub
	 * return null; }
	 */

	/**
	 * Moves the cursor down one row from its current position.
	 * 
	 * @return
	 */
	public PartitionAddress next() {
		currentIndex.current++;
		if (currentIndex.current > Workspace.getLastPartitionIdx()) {
			currentIndex.current = Workspace.getLastPartitionIdx();
			return null;
		}
		changeCurrentAddress();
		return currentIndex;

	}

	/**
	 * Moves the cursor to the previous row in this ResultSet object.
	 * 
	 * @return
	 */
	boolean previous() {
		currentIndex.current--;
		if (currentIndex.current < 0) {
			currentIndex.current = 0;
			return false;
		}
		changeCurrentAddress();
		return true;

	}

	/**
	 * Moves the cursor a relative number of rows, either positive or negative.
	 * 
	 * @param rows
	 * @return
	 */
	boolean relative(final long rows) {
		// String getPartitionPath(String name)
		return false;

	}

}
