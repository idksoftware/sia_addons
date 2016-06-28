package idk.imgarchive.base.manifest;

import idk.archiveutils.FileInfo;
import idk.imgarchive.base.workspacemanager.ImageAddress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

/**
 * 
 * The Partition manifest reads the Partition manifest file. PartitionInfo read
 * the manifest and the physical folder and compares the two
 * 
 * 
 * @author Iain Ferguson
 * 
 */

public class PartitionManifest extends Manifest {

	static final String DOCROOT = "PartitionInfo";

	public PartitionManifest() {
	}

	public PartitionManifest(final String pathToImageFolder, final String pathToDataFolder, final String id) {
		rootXMLTagName = DOCROOT;
		Init(pathToImageFolder, pathToDataFolder, id);

	}

	void Add(String path, final long id) {
		path = path + File.separator + ImageAddress.makeAddressString(id);
		try {
			final FileInfo fileInfo = new FileInfo(path, makeImageManifestFilename(id));

			list.add(fileInfo);
		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void AddPartition(String path, final int pid) {
		path = path + File.separator + ImageAddress.makeAddressString(pid);
		try {
			final FileInfo fileInfo = new FileInfo(path, makeImageManifestFilename(pid));

			list.add(fileInfo);
		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modify(final long curImageId) throws ParseException {
		curId = Long.parseLong(id);

		if (folder.isDirectory() == false) {
			return;
		}

		final File pmFile = new File(folder, "PMF" + id + ".xml");
		if (pmFile.exists() == false) {
			final String[] files = folder.list();
			if (files.length != 1 || files[0].equals(ImageAddress.makeAddressString(curImageId)) == false) // Only
			// the
			// current
			// image
			// will
			// be in
			// the
			// partition
			{
				// Found Images with no partition manifest. so error.
				return;
			}
			createFile(); // New partition
		}
		// New partition
		// this.id = id;
		readManifest(folder.getAbsolutePath(), "PMF" + id + ".xml");
		Modify(folder.getAbsolutePath(), curImageId);
		WriteFile(curId);
	}

	public void append(final long curImageId) throws ParseException {
		curId = Long.parseLong(id, 16);

		if (folder.isDirectory() == false) {
			return;
		}

		final File pmFile = new File(folder, "PMF" + id + ".xml");
		if (pmFile.exists() == false) {
			final String[] files = folder.list();
			if (files.length != 1 || files[0].equals(ImageAddress.makeAddressString(curImageId)) == false) // Only
			// the
			// current
			// image
			// will
			// be in
			// the
			// partition
			{
				// Found Images with no partition manifest. so error.
				return;
			}
			createFile(); // New partition
		}
		// New partition
		// this.id = id;
		readManifest(folder.getAbsolutePath(), "PMF" + id + ".xml");
		Add(folder.getAbsolutePath(), curImageId);
		WriteFile(curId);
	}

	public boolean CheckManifest(final String systemPath, final long id) throws ParseException {

		final String manifestName = makePartitionManifestFilename(id);

		if (readManifest(systemPath, manifestName) == false) {
			return false;
		}
		final File systemFolder = new File(systemPath);
		final String[] fileList = systemFolder.list();
		for (final String element : fileList) {

			FileInfo manifestInfo = null;

			if (element.matches(manifestName)) {
				continue;
			}
			final String filename = "IMF" + element + ".xml";
			FileInfo fileInfo = null;
			try {
				fileInfo = new FileInfo(systemPath + File.separator + element, filename);
			} catch (final NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (fileInfo.isError() == true) {
				AddLogItem(element, fileInfo.getErrorMsg());
				isError = true;
				continue;
			}

			if ((manifestInfo = find(filename)) == null) {
				System.out.println(element + ": Not in Manifest");
				AddLogItem(element, element + ": Not in Manifest");
				isError = true;
				continue;
			}
			if (manifestInfo.isEqual(fileInfo) != true) {
				System.out.println(fileInfo.fileName + ": Corrupt");
				AddLogItem(element, fileInfo.fileName + ": Not in Manifest");
				isError = true;
				continue;
			}
		}
		if (isError) {
			AddRemedyItem(ImageAddress.makeAddressString(id), "Replace partition " + id);
		}
		return true;
	}

	@Override
	public void createFile() {
		super.createFile(rootXMLTagName, folder.getAbsolutePath() + File.separator + makePartitionManifestFilename(curId));
	}

	void Modify(String path, final long id) {
		path = path + File.separator + ImageAddress.makeAddressString(id);
		try {
			final FileInfo fileInfo = new FileInfo(path, makeImageManifestFilename(id));

			replaceItem(fileInfo);
		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void ModifyPartition(String path, final int pid) {
		path = path + File.separator + ImageAddress.makeAddressString(pid);
		try {
			final FileInfo fileInfo = new FileInfo(path, makeImageManifestFilename(pid));

			list.add(fileInfo);
		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void WriteFile(final long curId) {
		writeFile(rootXMLTagName, folder.getAbsolutePath() + File.separator + makePartitionManifestFilename(curId));
	}

	boolean replaceItem(final FileInfo fileInfo) {
		int i = 0;
		for (final FileInfo item : list) {
			if (item.fileName.matches(fileInfo.getFileName())) {
				list.set(i, fileInfo);
				return true;
			}
			i++;
		}
		return false;
	}
}
