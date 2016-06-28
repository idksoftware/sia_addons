package idk.imgarchive.base.workspacemanager;

/**
 * This class  provides the cluster  map  across the entire instance  it contains a number of cluster map items each of which is a cluster..
 * This is the runtime object that enables the addressing across the entire  instance
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClusterMap {
	protected static class ClusterMapItem {
		protected boolean currentWorking = false;
		protected long firstPartitionId = 0;

		protected long highestImage;
		protected long imagesPerPartition = 0; // number of Partitions
		protected long lastPartitionId = 0;
		protected long lowestImage;
		protected String rootPath = null; // Root path to Cluster
		protected long size = 0; // Size in bytes

		/**
		 * 
		 * This constructor is used to create the cluster information object.
		 * This is normally created when the cluster information file is read.
		 * 
		 * @param size
		 * @param firstPartitionId
		 * @param lastPartitionId
		 * @param imagesPerPartition
		 * @param rootPath
		 */
		protected ClusterMapItem(final long size, final long firstPartitionId, final long lastPartitionId,
				final long imagesPerPartition, final String rootPath) {
			this.size = size; // Size in bytes
			this.imagesPerPartition = imagesPerPartition; // number of
			// Partitions
			this.firstPartitionId = firstPartitionId;
			this.lastPartitionId = lastPartitionId;
			this.rootPath = rootPath; // Root path to Cluster.
		}

		long findPartition(final long idx) {
			// get the off set into the cluster
			final long idxOffset = idx - lowestImage;
			final long partition = idxOffset / imagesPerPartition;
			return partition + firstPartitionId;
		}

		/**
		 * Gets the first partition ID
		 * 
		 * @return
		 */
		protected long getFirstPartitionId() {
			return firstPartitionId;
		}

		public long getHighImage() {
			return highestImage;
		}

		/**
		 * Gets the number of images per partition
		 * 
		 * @return
		 */
		protected long getImagesPerPartition() {
			return imagesPerPartition;
		}

		/**
		 * Gets the last partition ID
		 * 
		 * @return
		 */
		protected long getLastPartitionId() {
			return lastPartitionId;
		}

		/**
		 * returns the lowest the image address
		 * 
		 * @return
		 */

		public long getLowestImage() {
			return lowestImage;
		}

		/**
		 * gets the rootpath
		 * 
		 * @return
		 */
		public String getRootPath() {
			return rootPath;
		}

		/**
		 * Gets the size of the disk
		 * 
		 * @return
		 */
		protected long getSize() {
			return size;
		}

		/**
		 * @return the currentWorking
		 */
		public final boolean isCurrentWorking() {
			return currentWorking;
		}

		boolean isImageInRange(final long idx) {

			if (idx >= getLowestImage() && idx <= getHighImage()) {
				return true;
			}

			return false;
		}

		boolean isPartitionInRange(final long idx) {
			if (idx >= getFirstPartitionId() && idx <= getLastPartitionId()) {
				return true;
			}
			return false;
		}

		/**
		 * @param currentWorking
		 *            the currentWorking to set
		 */

		public final void setCurrentWorking(final boolean currentWorking) {
			this.currentWorking = currentWorking;
		}

		public void sethighestImage(final long highest) {
			highestImage = highest;
		}

		public void setLowestImage(final long lowestImage) {
			this.lowestImage = lowestImage;
		}
	}

	enum ImageRange {
		HighestImage, LowestImage
	}

	static class SortMap implements Comparator<ClusterMap.ClusterMapItem> {

		@Override
		public int compare(final ClusterMapItem arg0, final ClusterMapItem arg1) {
			final long lowest0 = arg0.getLowestImage();
			final long lowest1 = arg1.getLowestImage();

			if (lowest0 > lowest1) {
				return 1;
			} else if (lowest0 < lowest1) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	static ArrayList<ClusterMapItem> clusterMapList = new ArrayList<ClusterMapItem>();
	private static ClusterMapItem workingMapItem = null;

	static final ClusterMapItem findImage(final long idx) {

		for (final ClusterMapItem item : clusterMapList) {
			if (idx >= item.getLowestImage() && idx <= item.getHighImage()) {
				return item;
			}
		}
		return null;
	}

	static final ClusterMapItem findPartition(final long idx) {

		for (final ClusterMapItem item : clusterMapList) {
			if (idx >= item.getFirstPartitionId() && idx <= item.getLastPartitionId()) {
				return item;
			}
		}
		return null;
	}

	static String getImagePath(final long idx) {
		final ClusterMapItem item = findImage(idx);
		if (item == null) {
			return null;
		}
		final long partition = item.findPartition(idx);
		final String root = item.getRootPath();
		final String imagePath = root + File.separator + "DCIM" + File.separator + ImageAddress.makeAddressString(partition)
				+ File.separator + ImageAddress.makeAddressString(idx);
		return imagePath;
	}

	public static void updateLastIndex(final long lastIdx) {
		if (workingMapItem != null) {
			workingMapItem.highestImage = lastIdx;
			if (workingMapItem.lowestImage == 0) {
				// lowest Image can not be 0 if there are images in storage.
				// can only be 0 if empty
				workingMapItem.lowestImage = 1;
			}
		}
	}

	ClusterMap(final ArrayList<ClusterInfo> list, final long lastPartitionId, final long count) throws RuntimeException {
		for (final ClusterInfo item : list) {
			final ClusterMapItem clusterMapItem = new ClusterMapItem(item.getSize(), item.getFirstPartitionId(),
					item.getLastPartitionId(), item.getImagesPerPartition(), item.getRootPath());
			final String root = item.getRootPath();
			final File firstPartition = new File(root + File.separator + "DCIM", ImageAddress.makeAddressString(item
					.getFirstPartitionId()));
			if (firstPartition.exists() == false) {
				if (count == 0) {
					clusterMapItem.sethighestImage(0);
					clusterMapItem.setCurrentWorking(true);
					workingMapItem = clusterMapItem;
					clusterMapList.add(clusterMapItem);
					return;
				} else {
					throw new RuntimeException("No first partition exists: count: " + count + " Partition: "
							+ item.getFirstPartitionId());
				}

			}
			final long lowest = findImage(firstPartition, ImageRange.LowestImage);
			long highest = 0;
			clusterMapItem.setLowestImage(lowest);
			File lastPartition = null;
			if (item.getLastPartitionId() == Long.MAX_VALUE) {
				lastPartition = new File(root + File.separator + "DCIM", ImageAddress.makeAddressString(lastPartitionId));
				highest = findImage(lastPartition, ImageRange.HighestImage);
				clusterMapItem.sethighestImage(highest);
				clusterMapItem.setCurrentWorking(true);
				workingMapItem = clusterMapItem;
			} else {
				lastPartition = new File(root + File.separator + "DCIM", ImageAddress.makeAddressString(item.getLastPartitionId()));
				highest = findImage(lastPartition, ImageRange.HighestImage);
				clusterMapItem.sethighestImage(highest);
			}
			clusterMapList.add(clusterMapItem);

		}
		Collections.sort(clusterMapList, new SortMap());

	}

	boolean check() {
		long lowest = 0;
		long highest = Long.MAX_VALUE;

		for (final ClusterMapItem item : clusterMapList) {
			if (lowest < item.getLowestImage()) {
				lowest = item.lowestImage;
			} else {
				return false;
			}
			if (highest < item.getHighImage()) {
				highest = item.getHighImage();
			} else {
				return false;
			}
			if (item.getLowestImage() > item.getHighImage()) {
				return false;
			}

		}
		return false;

	}

	long findImage(final File partition, final ImageRange imageRange) {
		long lastMatch;

		if (imageRange == ImageRange.LowestImage) {
			lastMatch = Long.MAX_VALUE;
		} else {
			lastMatch = 0;
		}

		final File[] imageFiles = partition.listFiles();
		if (partition.getName().matches("00000000") && imageFiles.length == 0) {
			// No images in archive?
			return 0;
		}
		for (final File imageFile : imageFiles) {
			long temp = 0;
			// String name = files[i].getName();
			if (imageFile.getName().length() == 8) {

				if (imageRange == ImageRange.LowestImage) {
					if ((temp = Long.parseLong(imageFile.getName(), 16)) < lastMatch) {
						lastMatch = temp;
					}
				} else {
					if ((temp = Long.parseLong(imageFile.getName(), 16)) > lastMatch) {
						lastMatch = temp;
					}
				}
			}
		}
		return lastMatch;
	}

	long GetLastPartition(String rootPath, final String LastClusterPath) {
		rootPath = rootPath + File.separator + "DCIM";
		final File partitionFolder = new File(LastClusterPath);
		final File[] partitionFiles = partitionFolder.listFiles();
		long maxPartition = 0;

		for (final File partitionFile : partitionFiles) {
			long temp = 0;
			// String name = files[i].getName();
			if (partitionFile.getName().length() == 8) {
				if ((temp = Long.parseLong(partitionFile.getName(), 16)) > maxPartition) {
					maxPartition = temp;
				}
			}
		}
		return maxPartition;
	}
}