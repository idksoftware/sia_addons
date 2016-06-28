package idk.imgarchive.base.workspacemanager;

/**
 * 
 * This class contains the cluster information. This provides the following:
 * 
 * The size of the disk in megabytes. The number of images per partition. The
 * first partition ID. The last partition ID ( providing it is not the working
 * desk ) the root path to where the politicians are stored within the disc. The
 * type of media the cluster contains.
 * 
 * @author iain
 * 
 */

public class ClusterInfo {

	/**
	 * The media type contains The properties of the media i.e. if it is
	 * read-only or read/write etc.
	 * 
	 * @author iain
	 * 
	 */
	enum MediaType {
		/** < Fixed Hard Disk Media */
		FixedHardDisk,
		/** < Removable Media */
		Removable,
		/** < Removable ReadOnly Media */
		RemovableReadOnly,

	}

	protected long firstPartitionId = 0;
	protected long imagesPerPartition = 0; // number of Partitions
	protected long lastPartitionId = 0;
	protected String rootPath = null; // Root path to Cluster
	protected long size = 0; // Size in bytes

	/**
	 * 
	 * This constructor is used to create the cluster information object. This
	 * is normally created when the cluster information file is read.
	 * 
	 * @param size
	 * @param firstPartitionId
	 * @param lastPartitionId
	 * @param imagesPerPartition
	 * @param rootPath
	 */
	protected ClusterInfo(final long size, final long firstPartitionId, final long lastPartitionId, final long imagesPerPartition,
			final String rootPath) {
		this.size = size; // Size in bytes
		this.imagesPerPartition = imagesPerPartition; // number of Partitions
		this.firstPartitionId = firstPartitionId;
		this.lastPartitionId = lastPartitionId;
		this.rootPath = rootPath; // Root path to Cluster.
	}

	/**
	 * Gets the first partition ID
	 * 
	 * @return
	 */
	public long getFirstPartitionId() {
		return firstPartitionId;
	}

	/**
	 * Gets the number of images per partition
	 * 
	 * @return
	 */
	public long getImagesPerPartition() {
		return imagesPerPartition;
	}

	/**
	 * Gets the last partition ID
	 * 
	 * @return
	 */
	public long getLastPartitionId() {
		return lastPartitionId;
	}

	/**
	 * Gets the root path to where the partition are stored.
	 * 
	 * @return
	 */

	public String getRootPath() {
		return rootPath;
	}

	/*
	 * protected long getFirstImageId() { return firstImageId; }
	 * 
	 * protected void setFirstImageId(long firstImageId) { this.firstImageId =
	 * firstImageId; }
	 * 
	 * protected long getLastImageId() { return lastImageId; }
	 * 
	 * protected void setLastImageId(long lastImageId) { this.lastImageId =
	 * lastImageId; }
	 */

	/**
	 * Gets the size of the disk
	 * 
	 * @return
	 */
	public long getSize() {
		return size;
	}
}
