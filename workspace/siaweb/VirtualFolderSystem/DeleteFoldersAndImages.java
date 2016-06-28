package idk.imgarchive.base.VirtualFolderSystem;

import java.io.IOException;
import java.text.ParseException;

//MAX_FOLDER_DEPTH
public class DeleteFoldersAndImages extends VirtualFolder {

	public DeleteFoldersAndImages(final VirtualFolder vf) throws RuntimeException, IOException, ParseException {
		super(VFSystem.getRootPath(), vf.filename);

	}

	public void process() throws RuntimeException, IOException, ParseException {
		for (final VirtualFolderInfo item : folderList) {
			final VirtualFolder newVF = new VirtualFolder(VFSystem.getRootPath(), item.filename);
			ProcessFolder(newVF);
		}
		if (deleteFolderFile() == false) {
			throw new RuntimeException("Cannot delete file \"" + name + "\"");
		}

	}

	private void ProcessFolder(final VirtualFolder vf) throws RuntimeException, IOException, ParseException {
		for (final VirtualFolderInfo item : vf.folderList) {
			final VirtualFolder newVF = new VirtualFolder(VFSystem.getRootPath(), item.filename);
			if (newVF.folderList.size() != 0) {
				// expand folder system
				ProcessFolder(newVF);
			} else {
				// Folder has no child folders
				if (newVF.deleteFolderFile() == false) {
					throw new RuntimeException("Cannot delete file \"" + item + "\"");
				}

			}
		}
		if (vf.deleteFolderFile() == false) {
			throw new RuntimeException("Cannot delete file \"" + name + "\"");
		}
	}
}
