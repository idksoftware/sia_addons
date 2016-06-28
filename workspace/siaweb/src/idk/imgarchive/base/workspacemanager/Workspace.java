package idk.imgarchive.base.workspacemanager;

import idk.archiveutils.FileUtils;
import idk.config.ConfigInfo;
import idk.imgarchive.base.VirtualFolderSystem.VFError;
import idk.imgarchive.base.VirtualFolderSystem.VFSystem;
import idk.imgarchive.base.manifest.ManifestManager;
import idk.imgarchive.base.system.InitaliseConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.log4j.lf5.LogLevel;

/**
 * This class controls where the partitions are found on a spanned archive
 * (spanned across disks)
 * 
 * The workspace is where images are stored within the archive. The archive uses
 * partitions as a convenient method of breaking up a large number of images.
 * These partitions are convenient as the archive becomes manageable blocks of
 * data that can be handed much easier then a large number of smaller images.
 * Some image archives call these partitions, image buckets however this is also
 * used in large-scale databases in order to make the data stored within more
 * manageable. These databases normally call this breaking up of data
 * partitions. therefore in this application they are known as partitions.
 * 
 * Storing the photographic images today takes up a lots of disk space in order
 * to cater for this image archive enables the archive to be spanned across a
 * number of discs. Each one of these discs is called a cluster. Each cluster
 * will contain a number of partitions and each partition will contain a number
 * of images. As image archive numbers new images in a numerical sequence the
 * partitions wwill start from zero and increase as the database increases in
 * size and therefore the oldest images imported into the archive will have the
 * lowest numbers.the newest images will obviously have the highest numbers so
 * in this way we will have a range of partitions starting at zero and
 * increasing to the newest image. The complete range of partitions may not be
 * able to be contained within one disc so the range of partitions can be split
 * between a number of discs the lowest numbered disc being the oldest. Probably
 * will have the least number of accesses to the images contained within it on
 * the other hand the newest with the highest partition number will be probably
 * most accessed. In addition the older partitions will probably have been
 * backed up a number of times and therefore safe from corruption however the
 * newest ones. We have had less times backed up. If any so in order to secure
 * the newer images this disc the working disk would be mirrored in case of
 * failure. So the mix of typical archive may be having a number of clusters
 * containing older images which may be single discs then the working desk
 * holding the newest images will be mirrored. As this disc fills up then the
 * partitions on this disc will be backed up and placed on a new single disc and
 * the mirrored disks would then be emptied ready new images.
 * 
 * The workspace class manages the disk clusters. Information about these
 * clusters are stored in an XML file in the configurations directory along with
 * cluster information there is a entry for system path this system path
 * contains the data required to manage the clusters. As already mentioned the
 * partitions are indexed by an increasing number this number is held in a
 * sequence file. this file keeps the next index to be used for the next new
 * image and must be maintained between times when the application is shut down
 * and restarted.
 * 
 * On initialisation application will read the cluster file and working out its
 * validity. Each cluster will contain a path to where the partitions are stored
 * on disk in addition will contain the ID of the first and last partition. This
 * information will be verified against the disk and flaged if it is not
 * correct. If it isn't correct then possibly some other politicians may have
 * been deleted or corrupted or the information within a cluster file is
 * incorrect. In the way there is inconsistency between the file containing
 * information about clusters and the physical clusters held within the disk
 * drives which must be corrected before the archive can be used. The last drive
 * within the set of clusters will not have a highest partition ID that is
 * because this will be the working desk and new partitions will be added.
 * However it will have a maximum size and a size quota this will allow the user
 * to be informed when the drive is becoming full.
 * 
 * Within the archive there will be possibly older nonworking discs these will
 * contain older images but that does not mean that these images cannot be
 * updated. If we are updated the archive flags that they have been updated and
 * the next time a new incremental backup takes place these images will also be
 * backed up. However the backup system will not only backup the changed images
 * that will backup the entire partition of these images are contained within
 * this may take up more storage space that will make restoring the archive in
 * the event of mishap very much easier as a backup will contain ists of
 * partitions you
 * 
 * The archive will also support read-only discs
 * 
 * 
 * 
 * 
 */

public class Workspace {

	static public class ErrorCode {
		public enum Code {
			CANNOT_CREATE_DATA_PATH, CANNOT_CREATE_SYSTEM_PATH, CANNOT_READ_CLUSTER_FILE, INSTANCE_NAME_EXISTS, NO_INSTANCES, CANNOT_READ_HOME_ENV_VARIBLE, OK, UNKNOWN,
		}

		Code errorCode = Code.OK;

		public ErrorCode(final Code code) {
			errorCode = code;
		}

		/**
		 * @return the errorCode
		 */
		public final Code getErrorCode() {
			return errorCode;
		}

		/**
		 * @return the errorCode
		 */
		public final String getErrorCodeString() {
			switch (errorCode) {
			case OK:
				return "Ok";
			case NO_INSTANCES:
				return "No Instances";
			case INSTANCE_NAME_EXISTS:
				return "Instatnce name exists";
			case CANNOT_READ_CLUSTER_FILE:
				return "Cannot read cluster file";
			case CANNOT_CREATE_SYSTEM_PATH:
				return "Cannot create system path";
			case CANNOT_CREATE_DATA_PATH:
				return "Cannot create data path";
			case CANNOT_READ_HOME_ENV_VARIBLE:
				return "Can’t read enviroment variable";
			case UNKNOWN:
				return "Unknown";
			}
			return "Unknown";
		}
	}

	/**
     * 
     */

	static ClusterMap clusterMap = null;
	/**
	 * This array holds the cursor objects
	 */
	static private ArrayList<ImageCursor> cursorImageList = new ArrayList<ImageCursor>();

	/**
	 * This array holds the cursor objects
	 */
	static private ArrayList<PartitionCursor> cursorPartitionList = new ArrayList<PartitionCursor>();
	static ErrorCode errorCode = null;

	/**
	 * This is a system path where all the runtime configuration information is
	 * stored.
	 */
	static private String instancePath;
	/**
	 * This array holds the cluster information objects
	 */
	static private ArrayList<ClusterInfo> list = null;
	/**
	 * This is the sequence number object which is used to sequence the new
	 * images.
	 */
	static SequenceNumber sequenceNumber = null;

	public static ImageCursor createImageCursor() {
		final ImageCursor cursor = new ImageCursor();
		cursorImageList.add(cursor);
		return cursor;

	}

	public static PartitionCursor createPartitionCursor() {
		final PartitionCursor cursor = new PartitionCursor();
		cursorPartitionList.add(cursor);
		return cursor;

	}

	public static boolean finshNewImage() throws ParseException {
		final boolean res = ManifestManager.newItem(Workspace.getLastPartitionIdx(), Workspace.getCount());

		ClusterMap.updateLastIndex(Workspace.getCount());
		return res;
	}

	/**
	 * @return the clusterMap
	 */
	public static final ClusterMap getClusterMap() {
		return clusterMap;
	}

	/**
	 * this returns the current new image sequence number.
	 * 
	 * @return the sequence number.
	 */
	public static long getCount() {
		return SequenceNumber.getCount();
	}

	/**
	 * This returns the current working folder of the newest image.
	 * 
	 * @return - path to the new image folder.
	 */
	public static String getCurrentWorkingFolder() {
		return DirectoryManager.getImageDirectoryPath(SequenceNumber.getDirectoryCount(), SequenceNumber.getCount());
	}

	/**
	 * This returns a file object. This file object is a complete object
	 * containing a given file which will then be extended to the destination
	 * file of the same name.
	 * 
	 * @param file
	 *            - The file that will be used in the destination path
	 * @return - a file containing a complete destination path of a given file
	 *         past as a parameter.
	 */
	public static File GetDestinationFile(final File file) {
		final ClusterInfo workingCluster = getWorkingCluster();
		if (workingCluster == null) {
			return null;
		}

		SequenceNumber.readFile();
		if (sequenceNumber.IsNewDirectory()) {
			DirectoryManager.create(SequenceNumber.getDirectoryCount());
		}
		final String name = file.getName();
		final int dot = name.indexOf('.');
		final String fileExt = name.substring(dot);
		final String fileName = makeImageFilename(SequenceNumber.getCount(), fileExt);
		final String folder = DirectoryManager.getImageDirectoryPath(SequenceNumber.getDirectoryCount(), SequenceNumber.getCount());
		final File destFile = new File(folder, fileName);
		return destFile;
	}

	/**
	 * @return the errorCode
	 */
	public static final ErrorCode getErrorCode() {
		return errorCode;
	}

	public static String getImageRelativePath() {
		return DirectoryManager.getImageRelativePath();
	}

	/**
	 * @return the instancePath
	 */
	public static final String getInstancePath() {
		return instancePath;
	}

	public static long getLastPartitionIdx() {
		return SequenceNumber.getDirectoryCount();
	}

	/**
	 * this move this the image sequence number object onto the next image
	 * number in the sequence.
	 */
	public static void getNextWorkingFolder() {
		SequenceNumber.getNext();
		// Update current cluster
		ClusterMap.updateLastIndex(SequenceNumber.getCount());
	}

	public static long getPartitionCount() {
		return SequenceNumber.getDirectoryCount() + 1;
	}

	/**
	 * 
	 * This returns the current working cluster information object.
	 * 
	 * @return
	 */
	static ClusterInfo getWorkingCluster() {
		for (final ClusterInfo cluster : list) {
			if (cluster.lastPartitionId == Long.MAX_VALUE) {
				return cluster;
			}
		}
		return null;
	}

	public final static boolean isImagePath(final String input) {
		if (input.length() != 8) {
			return false;
		}
		try {
			Integer.parseInt(input, 16);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	/*
	 * public final static String makeExifFilename(long id) { return
	 * String.format("EXF%08X.xml", id); }
	 */
	public final static String makeImageFilename(final long id, final String ext) {
		return String.format("IMG%08X%s", id, ext);
	}

	/**
	 * 
	 * This class is used as the cursor into the image workspace. It works on
	 * both in a random access mode or as a sequence for iteration mode. The
	 * main reason for this class is to enable more than one clients to access
	 * the workspace.
	 * 
	 * @author iain
	 * 
	 */

	/*
	 * public final static String makeInfoFilename(long id) { return
	 * String.format("IFO%08X.xml", id); }
	 */

	public final static String makePartition(final long id) {
		return String.format("%08X", id);
	}

	public static boolean updateWithModifiedImage(final ImageAddress ia, final String filename) throws ParseException,
			NoSuchAlgorithmException, IOException {
		return ManifestManager.updateWithModifiedImage(ia, filename);
	}

	/**
	 * This returns the cluster that the past index parameter resides.
	 * 
	 * @param idx
	 *            - The target index.
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	/*
	 * private ClusterInfo getCluster(long idx) { for (ClusterInfo cluster :
	 * list) { if (cluster.firstPartitionId <= idx && cluster.lastPartitionId >=
	 * idx) {
	 * 
	 * return cluster; } } return null; }
	 */
	public static boolean updateWithNewImage(final ImageAddress ia, final String filename) throws ParseException,
			NoSuchAlgorithmException, IOException {
		return ManifestManager.updateWithNewImage(ia, filename);
	}

	/**
	 * This is the current configurations path this is where the cluster
	 * information file is read.
	 */
	private final String configPath;

	/**
	 * This is the current working cluster path.
	 */
	private String currentClusterPath;

	/**
	 * This is the current working cluster path.
	 */
	private String currentInstanceConfigFile;

	/**
	 * This is the current working cluster path.
	 */
	private String currentInstanceName;

	/**
	 * This is the directory manager object which is used to manipulate the
	 * directory storage areas.
	 */
	DirectoryManager directoryManager = null;

	/**
	 * 
	 * @param p
	 *            - this is the path to the configurations file that holds all
	 *            the cluster information file would be named "workspace.XML"
	 */
	public Workspace(final String p) {
		configPath = p;

	}

	public boolean Create(final String instanceName, final String instancePath, final String id, final long size,
			final int imagesPerPartition, final String clusterPath) throws NoSuchAlgorithmException, FileNotFoundException {

		if (SequenceNumber.IsInstance() == true) {
			errorCode = new ErrorCode(ErrorCode.Code.INSTANCE_NAME_EXISTS);
			return false;
		}

		WorkspaceInstancesReader.readFile(configPath);
		if (WorkspaceInstancesReader.IsPresent(instanceName) == true) {
			errorCode = new ErrorCode(ErrorCode.Code.INSTANCE_NAME_EXISTS);
			return false;
		}

		WorkspaceReader.writeDefault(configPath, instanceName, instancePath, id, size, imagesPerPartition, clusterPath);
		// this needs to be valid in the workspace instance file.

		if (FileUtils.makePath(clusterPath + File.separator + "DCIM") == false) {
			errorCode = new ErrorCode(ErrorCode.Code.CANNOT_CREATE_DATA_PATH);
			return false;
		}
		final String systemPath = instancePath + File.separator + "system";
		if (FileUtils.makePath(systemPath) == false) {
			errorCode = new ErrorCode(ErrorCode.Code.CANNOT_CREATE_SYSTEM_PATH);
			return false;
		}
		sequenceNumber = new SequenceNumber(systemPath, imagesPerPartition);
		sequenceNumber.createSeqFile(0, 0, 0);
		WorkspaceInstancesReader.SetInstance(instanceName);
		WorkspaceInstancesReader.setDefaultInstance(instanceName);
		WorkspaceInstancesReader.writeXmlFile(configPath);

		return true;
	}

	/**
	 * This returns current cluster path
	 * 
	 * @return
	 */
	public String getCurrentClusterPath() {
		return currentClusterPath;
	}

	/**
	 * 
	 * This would return the directory path given an image identifier.
	 * 
	 * @param i
	 * @return
	 */
	public String getDirectoryPath(final int i) {

		final String hexNumber = ImageAddress.makeAddressString(i);
		final File path = new File(currentClusterPath, hexNumber);
		return path.getAbsolutePath();
	}

	/**
	 * 
	 * This would return the directory path given an image identifier.
	 * 
	 * @param i
	 * @return
	 */
	public String getImageDirectoryPath(final int part, final int imageNumber) {

		final String hexNumber = ImageAddress.makeAddressString(part);
		final String imageFolderString = ImageAddress.makeAddressString(imageNumber);
		final File imageFolder = new File(currentClusterPath + File.separator + hexNumber + File.separator + imageFolderString);
		if (imageFolder.exists() == false) {
			if (imageFolder.mkdir() == false) {
				return null;
			}
		}
		return imageFolder.getAbsolutePath();
	}

	/**
	 * This function initialises the workspace. It reads the cluster file and
	 * verifies its contents against the physical drives that are detailed
	 * within the file.in addition will verify the validity of the sequence
	 * file. Creating a new one if necessary.
	 * 
	 * @return
	 * @throws IOException
	 */
	public boolean Init() throws IOException, RuntimeException {
		if (WorkspaceInstancesReader.readFile(configPath) == false) {
			errorCode = new ErrorCode(ErrorCode.Code.NO_INSTANCES);
			return false;
		}

		final WorkspaceReader workspaceReader = new WorkspaceReader();
		currentInstanceName = WorkspaceInstancesReader.getDefaultInstance();
		currentInstanceConfigFile = currentInstanceName + ".xml";
		if (workspaceReader.readInstanceFile(configPath, currentInstanceConfigFile) == false) {
			errorCode = new ErrorCode(ErrorCode.Code.CANNOT_READ_CLUSTER_FILE);
			return false;
		}
		list = workspaceReader.getList();
		final ClusterInfo workingCluster = getWorkingCluster();
		instancePath = WorkspaceReader.getInstancePath() + File.separator + "system";
		ConfigInfo.setInstancePath(WorkspaceReader.getInstancePath());
		currentClusterPath = workingCluster.getRootPath() + File.separator + "DCIM";
		ConfigInfo.setCurrentClusterPath(currentClusterPath);
		sequenceNumber = new SequenceNumber(instancePath, workingCluster.getImagesPerPartition());
		directoryManager = new DirectoryManager(currentClusterPath);
		if (SequenceNumber.seqFileExists() == false) {
			sequenceNumber.InitSequenceNumberWithoutFile(currentClusterPath);
		}
		ManifestManager.setPath(instancePath, currentClusterPath, ConfigInfo.getSchemaPath());
		// ManifestManager.setPath(instancePath, instancePath,
		// ConfigInfo.getSchemaPath());
		SequenceNumber.readFile();
		clusterMap = new ClusterMap(list, SequenceNumber.getDirectoryCount(), SequenceNumber.getCount());
		InitaliseVFSystem();
		return true;
	}

	public boolean Initialise(final LogLevel logLevel, final int imagesPerPartition, final String toolPath) throws IOException {
		final String homePath = System.getenv(ConfigInfo.SIA_HOME);
		if (homePath == null) {
			errorCode = new ErrorCode(ErrorCode.Code.CANNOT_READ_HOME_ENV_VARIBLE);
			return false;
		}

		InitaliseConfig.defaultInitalise(homePath, logLevel, toolPath);
		return false;
	}

	public static void InitaliseVFSystem() {
		final String rootPath = ConfigInfo.getInstanceSystemPath() + File.separator + "folders";
		FileUtils.makePath(rootPath);
		try {
			try {
				//
				if (VFSystem.initalise(rootPath) == VFError.Code.UNSUCCESSFULL) {
					try {
						VFSystem.makeFileSystem();
					} catch (final RuntimeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (final IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (final RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
