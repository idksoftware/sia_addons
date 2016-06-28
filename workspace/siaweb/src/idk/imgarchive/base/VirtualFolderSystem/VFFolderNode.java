package idk.imgarchive.base.VirtualFolderSystem;

import idk.imgarchive.base.VirtualFolderSystem.VirtualFolder.VirtualFolderInfo;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class VFFolderNode implements VFSystemNode {
	private final VirtualFolder virtualFolder;

	public VFFolderNode(final VirtualFolder vf) {
		virtualFolder = vf;
	}

	public VFSystemNode[] getChildren() throws ParseException {
		final ArrayList<String> imgList = virtualFolder.getImageList();
		final ArrayList<VirtualFolderInfo> folderList = virtualFolder.getFolderList();
		final VFSystemNode[] children = new VFSystemNode[imgList.size() + folderList.size()];
		int i = 0; // NOTE this is used to index array so DO NOT re-init for the
		// second
		// for loop
		for (final String imageNo : imgList) {
			children[i++] = new VFImageNode(imageNo);
		}
		for (final VirtualFolderInfo vfInfo : folderList) {
			VirtualFolder vf = null;
			try {
				vf = virtualFolder.getFolder(vfInfo.name);
			} catch (final RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			children[i++] = new VFFolderNode(vf);
		}
		// if (children == )
		return children;
	}

	public VirtualFolder getDirectory() {
		return virtualFolder;
	}

	/*
	 * @Override public void visit(CatalogVisitor v) throws ParseException {
	 * v.visitDirectoryNode(this); }
	 */

	@Override
	public void visit(final FolderCatalogerBase v) throws ParseException, IOException {
		v.visitNode(this);

	}
}
