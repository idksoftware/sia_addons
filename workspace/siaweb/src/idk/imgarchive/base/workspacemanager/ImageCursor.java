package idk.imgarchive.base.workspacemanager;

import idk.imgarchive.base.workspacemanager.ClusterMap.ClusterMapItem;

import java.io.File;
import java.util.Iterator;

/**
 * This class is used to index images within the archive. A number of curses can
 * be created and provide both sequential and random accesses to the archive. In
 * mentor node an absolute image ID can be given to the cursor a tool will
 * return using the relevant function. An image address to that pasted image ID.
 * In sequential mode the image cursor provides standard Java iteration. Ihe
 * image cursor is the source of the iterator. And the item returned on each
 * iteration is a IndexAddress class.
 * 
 * This class is normally a read-only way of accessing images and therefore can
 * be used by multiple threads. If images are modified then some means of file
 * locking need to be implemented to prevent accidental corruption by other
 * threads.
 * 
 * @author iain
 * 
 */
public class ImageCursor implements Iterable<ImageAddress> {

	class ImageIterator implements Iterator<ImageAddress> {
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub

			return !isLast();
		}

		@Override
		public ImageAddress next() {
			currentIndex.current++;
			if (currentIndex.current > SequenceNumber.getCount()) {
				currentIndex.current = SequenceNumber.getCount();
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

	ClusterMapItem clusterMapItem = null;
	private final ImageAddress currentIndex = new ImageAddress();
	private final ImageIterator imageIterator = new ImageIterator();

	protected ImageCursor() {
	}

	/**
	 * Moves the cursor to the given image number in this workspace object.
	 * 
	 * @param row
	 * @return
	 */
	public boolean absolute(final long row) {
		if (row > SequenceNumber.getCount() || row <= 0) {
			return false;
		}
		currentIndex.current = row;
		changeCurrentAddress();
		return true;
	}

	private boolean changeCurrentAddress() {

		if (clusterMapItem == null || !clusterMapItem.isImageInRange(currentIndex.current)) {
			clusterMapItem = ClusterMap.findImage(currentIndex.current);
			if (clusterMapItem == null) {
				return false;
			}
			currentIndex.maxPartition = clusterMapItem.getImagesPerPartition();
		}

		if (clusterMapItem == null) {
			return false;
		}
		final long partition = clusterMapItem.findPartition(currentIndex.current);
		final String root = clusterMapItem.getRootPath();
		final String imagePath = root + File.separator + "DCIM" + File.separator + ImageAddress.makeAddressString(partition)
				+ File.separator + ImageAddress.makeAddressString(currentIndex.current);

		currentIndex.currentPartition = partition;
		currentIndex.path = imagePath;
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
	public ImageAddress getCurrent() {
		final long lastIndex = SequenceNumber.getCount();
		if (currentIndex.current > lastIndex) {
			currentIndex.current = lastIndex;
			return null;
		}
		changeCurrentAddress();
		return currentIndex;
	}

	/*
	 * private String getCurrent(Long idx) { if (clusterMapItem == null ||
	 * !clusterMapItem.isInRange(idx)) { clusterMapItem =
	 * ClusterMap.findClusterMap(idx); }
	 * 
	 * 
	 * if (clusterMapItem == null) { return null; } long partition =
	 * clusterMapItem.findPartition(idx); String root =
	 * clusterMapItem.getRootPath(); String imagePath = new String(root +
	 * File.separator + "DCIM" + File.separator +
	 * ImageAddress.makeAddressString(partition) + File.separator +
	 * ImageAddress.makeAddressString(idx)); return imagePath; }
	 */
	/*
	 * @Override public Iterator iterator() { // TODO Auto-generated method stub
	 * return null; }
	 */

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

	long getImage() {
		return currentIndex.current;

	}

	/**
	 * Retrieves whether the cursor is on the first row of this workspace
	 * object.
	 * 
	 * @return
	 */
	boolean isFirst() {
		if (currentIndex.current == 1) {
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
		if (SequenceNumber.getCount() == currentIndex.current) {
			return true; // Off the end of the archive.
		}
		return false;

	}

	@Override
	public Iterator<ImageAddress> iterator() {
		// TODO Auto-generated method stub
		return imageIterator;
	}

	/**
	 * Moves the cursor to the last row in this workspace object.
	 */
	public boolean last() {
		currentIndex.current = SequenceNumber.getCount();
		return true;
	}

	/**
	 * Moves the cursor down one row from its current position.
	 * 
	 * @return
	 */
	public ImageAddress next() {
		currentIndex.current++;
		if (currentIndex.current > SequenceNumber.getCount()) {
			currentIndex.current = SequenceNumber.getCount();
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
	public ImageAddress previous() {
		currentIndex.current--;
		if (currentIndex.current <= 0) {
			currentIndex.current = 1;
			return null;
		}
		changeCurrentAddress();
		return currentIndex;

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
