package idk.imgarchive.base.manifest;

import idk.archiveutils.FileUtils;
import idk.config.ConfigInfo;
import idk.imgarchive.base.workspacemanager.ImageAddress;
import idk.imgarchive.base.workspacemanager.ImageCursor;
import idk.imgarchive.base.workspacemanager.PartitionAddress;
import idk.imgarchive.base.workspacemanager.PartitionCursor;
import idk.imgarchive.base.workspacemanager.Workspace;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;

public class ManifestManager {
	static String dataPath = null;
	static final ManifestManager manifestManager = new ManifestManager();
	static String systemPath = null;

	public static boolean CheckManifest() throws ParseException, NoSuchAlgorithmException, IOException {
		int errorCount = 0;
		final ImageCursor cursor = Workspace.createImageCursor();
		for (final ImageAddress item : cursor) {
			final String tempSystemPath = ManifestManager.systemPath + File.separator + "metadata" + File.separator
					+ ImageAddress.makeAddressString(item.getCurrentPartition()) + File.separator
					+ ImageAddress.makeAddressString(item.getCurrent());
			final ImageManifest imageManifest = new ImageManifest();
			if (imageManifest.CheckManifest(item.getPath(), tempSystemPath, item.getCurrent()) == false) {
				// Error
				errorCount++;
			}

		}
		final PartitionCursor pcursor = Workspace.createPartitionCursor();
		for (final PartitionAddress item : pcursor) {
			final String tempSystemPath = ManifestManager.systemPath + File.separator + "metadata" + File.separator
					+ ImageAddress.makeAddressString(item.getCurrent());

			final PartitionManifest partitionManifest = new PartitionManifest();
			if (partitionManifest.CheckManifest(tempSystemPath, item.getCurrent()) == false) {
				errorCount++;
			}

		}
		/*
		 * long psize = pcursor.getSize(); for (long i = 1) if (errorCount > 0)
		 * { return false; }
		 */
		final MasterManifest masterManifest = new MasterManifest();
		final String tempSystemPath = ManifestManager.systemPath + File.separator + "metadata";

		if (masterManifest.checkManifest(tempSystemPath) == false) {
			return false;
		}
		return true;
		/*
		
		*/

	}

	public static ImageManifest getImageManifest(final ImageAddress id) throws ParseException {
		final String tempSystemPath = systemPath + File.separator + "metadata" + File.separator
				+ ImageAddress.makeAddressString(id.getCurrentPartition()) + File.separator
				+ ImageAddress.makeAddressString(id.getCurrent());

		final ImageManifest imageManifest = new ImageManifest();
		if (imageManifest.readManifest(tempSystemPath, Manifest.makeImageManifestFilename(id.getCurrent())) == false) {
			return null;
		}
		return imageManifest;
	}

	public static ImageManifest getImageManifest(final long pid, final long id) throws IOException, ParseException {
		final ImageManifest imageManifest = new ImageManifest();
		final String tempSystemPath = ManifestManager.systemPath + File.separator + "metadata" + File.separator
				+ Workspace.makePartition(pid) + File.separator + ImageAddress.makeAddressString(id);

		if (imageManifest.readManifest(tempSystemPath, Manifest.makeImageManifestFilename(id)) == false) {
			return null;
		}

		return imageManifest;
	}

	public static PartitionInfoList getPartitionInfoList(final InfoFilter infoFilter) throws IOException, ParseException {
		final MasterManifest masterManifest = new MasterManifest();
		final String tempSystemPath = ManifestManager.systemPath + File.separator + "metadata" + File.separator + "MMF.xml";

		if (masterManifest.readMasterManifest(tempSystemPath, infoFilter) == false) {
			return null;
		}

		return masterManifest.getPartitionList();
	}

	public static boolean newItem(final long partition, final long id) throws ParseException {
		final String tempDataPath = dataPath + File.separator + ImageAddress.makeAddressString(partition);
		String tempSystemPath = systemPath + File.separator + "metadata" + File.separator
				+ ImageAddress.makeAddressString(partition);

		if (FileUtils.makePath(tempSystemPath) == false) {
			return false;
		}
		final ImageManifest manifest = new ImageManifest(tempDataPath, tempSystemPath, ImageAddress.makeAddressString(id));
		manifest.newItem();

		tempSystemPath = systemPath + File.separator + "metadata";
		final PartitionManifest partitionManifest = new PartitionManifest(tempSystemPath, dataPath,
				ImageAddress.makeAddressString(partition));
		partitionManifest.append(id);

		MasterManifest masterManifest = null;
		try {

			masterManifest = new MasterManifest(tempSystemPath, partition);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// Needs fixing
		if (masterManifest.updateLastPartition() == false) {
			return false;
		}

		return true;
	}

	public static void setPath(final String sysPath, final String dPath, final String schPath) {
		ManifestManager.systemPath = sysPath;
		ManifestManager.dataPath = dPath;
		Manifest.setSchemaPath(schPath);
	}

	public static boolean updateWithModifiedImage(final ImageAddress ia, final String filename) throws ParseException,
			NoSuchAlgorithmException, IOException {

		final String tempDataPath = dataPath + File.separator + ImageAddress.makeAddressString(ia.getCurrentPartition());
		final String tempSystemPath = systemPath + File.separator + "metadata" + File.separator
				+ ImageAddress.makeAddressString(ia.getCurrentPartition());

		final ImageManifest manifest = new ImageManifest(new File(ia.getPath()).getParent(), tempSystemPath,
				ImageAddress.makeAddressString(ia.getCurrent()));
		if (manifest.modifyImage(filename) == false) {
			// No image of that name found
			return false;
		}

		final PartitionManifest partitionManifest = new PartitionManifest(systemPath + File.separator + "metadata", dataPath,
				ImageAddress.makeAddressString(ia.getCurrentPartition()));
		partitionManifest.modify(ia.getCurrent());

		MasterManifest masterManifest = null;
		try {
			masterManifest = new MasterManifest(systemPath + File.separator + "metadata", ia.getCurrentPartition());
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// Needs fixing
		if (masterManifest.updateLastPartition() == false) {
			return false;
		}

		return true;

	}

	public static boolean updateWithNewImage(final ImageAddress ia, final String filename) throws ParseException,
			NoSuchAlgorithmException, IOException {
		final String tempSystemPath = systemPath + File.separator + "metadata" + File.separator
				+ ImageAddress.makeAddressString(ia.getCurrentPartition());

		final ImageManifest manifest = new ImageManifest(new File(ia.getPath()).getParent(), tempSystemPath,
				ImageAddress.makeAddressString(ia.getCurrent()));
		manifest.newImage(filename);

		final PartitionManifest partitionManifest = new PartitionManifest(systemPath, dataPath, ImageAddress.makeAddressString(ia
				.getCurrentPartition()));
		partitionManifest.modify(ia.getCurrent());

		MasterManifest masterManifest = null;
		try {
			masterManifest = new MasterManifest(systemPath, ia.getCurrentPartition());
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// Needs fixing
		if (masterManifest.updateLastPartition() == false) {
			return false;
		}

		return true;

	}

	public static Date getLastUpdated() {
		final String tempSystemPath = ConfigInfo.getInstanceSystemPath() + File.separator + "metadata";
		final File mmfFile = FileUtils.fileExists(tempSystemPath, "MMF.xml");
		if (mmfFile == null) {
			return null;
		}
		return new Date(mmfFile.lastModified());

	}

	private ManifestManager() {
	}
}
