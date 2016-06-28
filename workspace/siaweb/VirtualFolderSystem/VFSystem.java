package idk.imgarchive.base.VirtualFolderSystem;

import idk.image.ImageList;
import idk.imgarchive.base.VirtualFolderSystem.VFError.Code;
import idk.imgarchive.base.VirtualFolderSystem.VirtualFolder.FolderList;
import idk.imgarchive.base.workspacemanager.ImageAddress;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class VFSystem {
	private static VFPath path = null;
	private static String rootPath = null;
	// private static VirtualFolder rootFolder = null;
	// private static VirtualFolder currentFolder = null;
	private static VFSystem virtualFolderSystem = null;

	public static boolean addImage(final String imagePath) {
		path.getCWD().addImage(imagePath);
		return true;
	}

	public static boolean addImage(final ImageList imageNewList) {
		for (final Long item : imageNewList) {
			path.getCWD().addImage(ImageAddress.makeAddressString(item));
		}
		return true;
	}

	public static boolean changeFolder(final String pathString) throws RuntimeException, IOException, ParseException {
		return VFSystem.path.changeFolder(pathString);
	}

	public static VFError.Code deleteFolder(final String pathString) throws RuntimeException, IOException, ParseException {
		final VFError.Code resCode = Code.SUCCESS_OK;
		if (VFSystem.path.deleteFolder(pathString) == false) {
			return VFSystem.path.getErrorNo();
		}
		return resCode;
	}

	public static boolean deleteImage(final String path) {
		return false;
	}

	public static boolean exists() {
		final File rootFile = new File(rootPath, "00000000.xml");
		if (rootFile.exists() == true) {
			return true;
		}
		return false;
	}

	public static boolean folderExists(final String name) throws IOException {
		return path.getCWD().folderExists(name);
	}

	public static String getCurrentWorkingFolder() {
		return VFSystem.path.getPathString();
	}

	protected static String getRootPath() {
		return rootPath;
	}

	public static VFSystem getSystem() {
		return virtualFolderSystem;
	}

	protected static String getVfsPath() {
		return VFSystem.path.getPathString();
	}

	public static VFError.Code initalise(final String p) throws IOException, RuntimeException, ParseException {

		if (virtualFolderSystem == null) {
			virtualFolderSystem = new VFSystem(p);
		}
		if (exists() == true) {
			VFSystem.path = new VFPath("/");
			return Code.SUCCESS_OK;
		}
		return Code.UNSUCCESSFULL;
	}

	public static String list(final FolderList type, final boolean recursive) throws RuntimeException, IOException, ParseException {
		if (recursive) {
			final ListFoldersAndImages rl = new ListFoldersAndImages(path.getCWD());
			return rl.process();
		}
		return path.getCWD().list(path.getPathString(), type);
	}

	public static VFError.Code makeFileSystem() throws RuntimeException, IOException, ParseException {
		// Create root Folder
		// File rootFolder = nrootPath

		VirtualFolder.createRoot();
		VFSystem.path = new VFPath("/");
		return Code.SUCCESS_OK;
	}

	public static VFError.Code MakeFolder(final String name) throws IOException, RuntimeException, ParseException {
		if (path.getCWD().createFolder(name) == null) {
			return Code.CANNOT_CREATE_FOLDER; // name exists
		}
		return Code.SUCCESS_OK; // created
	}

	public static VFError.Code renameFolder(final String oldName, final String newName) throws RuntimeException, IOException,
			ParseException {
		if (path.getCWD().folderExists(oldName) == true) {
			return Code.ORIGINAL_FOLDER_NOT_FOUND;
		}
		if (path.getCWD().folderExists(newName) == true) {
			return Code.NEW_FOLDER_EXISTS;
		}
		if (path.getCWD().createFolder(newName) == null) {
			return Code.CANNOT_CREATE_FOLDER; // name exists
		}
		if (VFSystem.path.deleteFolder(oldName) == false) {
			return VFSystem.path.getErrorNo();
		}
		return Code.SUCCESS_OK;
	}

	private VFSystem(final String rootPath) throws IOException {

		VFSystem.rootPath = rootPath;
		final File file = new File(rootPath);

		if (file.exists() == false) {
			file.mkdirs();
			if (file.exists() == false) {
				throw new IOException();
			}
		} else {
			if (file.isDirectory() == false) {
				throw new IOException();
			}
		}
	}
}
