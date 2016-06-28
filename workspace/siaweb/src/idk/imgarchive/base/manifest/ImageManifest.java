package idk.imgarchive.base.manifest;

import idk.archiveutils.FileInfo;
import idk.archiveutils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

/**
 * @author Iain Ferguson
 * 
 */

public class ImageManifest extends Manifest {

	static final String DOCROOT = "ImageInfo";

	/**
	 * This is used to create a Read an existing ImageManifest file
	 * 
	 * @param file
	 * @param id
	 */
	public ImageManifest() {
	}

	/**
	 * This is used to create a Read an existing ImageManifest file
	 * 
	 * @param string
	 * @param tempSystemPath
	 * @param tempDataPath
	 * 
	 * @param file
	 * @param id
	 */
	public ImageManifest(final String pathToImageFolder, final String pathToSystemPath, final String id) {
		Init(pathToImageFolder, pathToSystemPath, id);
		rootXMLTagName = DOCROOT;
	}

	public boolean CheckManifest(final String pathToImageFolder, final String pathToSystemPath, final long ld)
			throws ParseException, NoSuchAlgorithmException, IOException {
		boolean error = false;
		final String idStr = String.format("%08X", ld);
		final String manifestName = makeImageManifestFilename(ld);
		final File mFile = new File(pathToSystemPath, manifestName);
		if (mFile.exists() == false) {

			AddLogItem(idStr, "Error: Missing Manifest file");
			return false;
		}
		readManifest(pathToSystemPath, makeImageManifestFilename(ld));
		final File imgFolder = new File(pathToImageFolder);
		final String[] fileList = imgFolder.list();
		for (final String element : fileList) {
			final FileInfo fileInfo = new FileInfo(pathToImageFolder, element);
			FileInfo manifestInfo = null;

			if (element.matches(manifestName)) {
				// Not an error
				continue;
			}
			try {
				fileInfo.readFile();
			} catch (final NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (fileInfo.isError() == true) {
				AddLogItem(idStr, fileInfo.getErrorMsg());
				error = true;
				continue;
			}
			if (element.startsWith(".") && element.startsWith(".lck") || element.matches("Thumbs.db")) {
				// ignore lock files
				continue;
			}

			if ((manifestInfo = find(element)) == null) {
				AddLogItem(idStr, "Error: " + element + ": Not in Manifest");
				System.out.println(element + ": Not in Manifest");
				error = true;
				continue;
			}
			if (manifestInfo.isEqual(fileInfo) != true) {
				AddLogItem(idStr, "Error: " + fileInfo.getOriginalFile() + ": Corrupt");
				System.out.println(fileInfo.getOriginalFile() + ": Corrupt");
				error = true;
				continue;
			}

		}
		if (logMissing(idStr) == false) {
			error = true;
		}
		return !error;
	}

	@Override
	protected void createFile() {
		if (FileUtils.makePath(dataFolder.getAbsolutePath()) == false) {
			return;
		}
		super.readFolder();
		super.writeFile(rootXMLTagName, dataFolder.getAbsolutePath() + File.separator + manifestName());
	}

	private String manifestName() {
		return "IMF" + id + ".xml";
	}

	public boolean modifyImage(final String filename) throws ParseException, NoSuchAlgorithmException, IOException {
		FileInfo fileInfo = null;
		final String manifestName = manifestName();
		readManifest(dataFolder.getAbsolutePath(), manifestName);
		fileInfo = find(filename);
		if (fileInfo == null) {
			return false;
		}
		final FileInfo newFileInfo = new FileInfo(folder.getAbsolutePath(), filename);

		list.remove(fileInfo);
		list.add(newFileInfo);
		WriteFile();
		return true;
	}

	public void newImage(final String filename) throws ParseException, NoSuchAlgorithmException, IOException {
		final FileInfo fileInfo = new FileInfo(folder.getAbsolutePath(), filename);
		final String manifestName = manifestName();
		readManifest(dataFolder.getAbsolutePath(), manifestName);

		list.add(fileInfo);
		WriteFile();
	}

	public void newItem() {
		createFile();
	}

	protected void WriteFile() {
		writeFile(rootXMLTagName, dataFolder.getAbsolutePath() + File.separator + manifestName());
	}

}
