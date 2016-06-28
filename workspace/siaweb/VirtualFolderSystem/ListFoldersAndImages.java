package idk.imgarchive.base.VirtualFolderSystem;

import java.io.IOException;
import java.text.ParseException;

//MAX_FOLDER_DEPTH
public class ListFoldersAndImages extends VirtualFolder {
	private static class NodeInfo {
		private int numOfFiles = 0;
		private int numOfFolders = 0;
		private String xmlNodeString = null;

		private NodeInfo(final int numOfFolders, final int numOfFiles, final String xmlNodeString) {
			this.numOfFolders = numOfFolders;
			this.numOfFiles = numOfFiles;
			this.xmlNodeString = xmlNodeString;
		}
	}

	private final int noOfFolders = 0;
	private final VirtualFolder virtualFolder = null;

	public ListFoldersAndImages(final VirtualFolder vf) throws RuntimeException, IOException, ParseException {
		super(VFSystem.getRootPath(), vf.filename);

	}

	private String imageList(final FolderList type) {
		final StringBuilder builder = new StringBuilder();
		// builder.append(part1);
		// builder.append("		<FolderOf>" + path + "\n");

		if (type == FolderList.ImagesOnly || type == FolderList.All) {
			builder.append("	<ImageListing>\n");
			for (final String item : imageList) {
				builder.append("		<Image>" + item + "</Image>\n");
			}
			builder.append("	</ImageListing>\n");

			builder.append("	<NumberOfImagess>");
			builder.append(imageList.size());
			builder.append("</NumberOfImagess>\n");
		}
		/*
		 * if (type == FolderList.FoldersOnly || type == FolderList.All) {
		 * builder.append("	<FolderListing>\n"); for (VirtualFolderInfo item :
		 * folderList) { builder.append("		<Folder><Name>" + item.name +
		 * "</Name><Mod>" + item.lastModified + "</Mod></Folder>\n"); }
		 * builder.append("	</FolderListing>\n");
		 * 
		 * builder.append("	<NumberOfFolders>");
		 * builder.append(folderList.size());
		 * builder.append("</NumberOfFolders>\n"); }
		 */
		// builder.append("		 </FolderOf>\n");
		// builder.append(part2);
		return builder.toString();

	}

	public String process() throws RuntimeException, IOException, ParseException {
		final StringBuilder builder = new StringBuilder();
		// builder.append(part1);
		builder.append("		<FolderOf>" + name + "\n");
		builder.append("		<Mod>" + lastModified + "</Mod>\n");
		for (final VirtualFolderInfo item : folderList) {
			final VirtualFolder newVF = new VirtualFolder(VFSystem.getRootPath(), item.filename);
			final NodeInfo nodeInfo = ProcessFolder(newVF);
			builder.append(nodeInfo.xmlNodeString);
		}
		builder.append(imageList(FolderList.All));
		builder.append("		 </FolderOf>\n");
		// builder.append(part2);
		return builder.toString();
	}

	private NodeInfo ProcessFolder(final VirtualFolder vf) throws RuntimeException, IOException, ParseException {
		int numOfFolders = 0;
		int numOfFiles = 0;
		final StringBuilder builder = new StringBuilder();
		builder.append("		<FolderOf>" + vf.name + "\n");
		builder.append("		<Mod>" + vf.lastModified + "</Mod>\n");
		builder.append("		<FolderList>\n");
		for (final VirtualFolderInfo item : vf.folderList) {
			final VirtualFolder newVF = new VirtualFolder(VFSystem.getRootPath(), item.filename);
			if (newVF.folderList.size() != 0) {
				// expand folder system
				final NodeInfo newNodeInfo = ProcessFolder(newVF);
				builder.append(newNodeInfo.xmlNodeString);
				numOfFolders += newNodeInfo.numOfFolders;
				numOfFiles += newNodeInfo.numOfFiles;
			} else {
				// Folder has no child folders
				builder.append("		<Folder>" + newVF.name + "\n");
				builder.append("		<Mod>" + newVF.lastModified + "</Mod>\n");
				builder.append("		 </Folder>\n");
				builder.append(imageList(FolderList.All));
			}
		}

		builder.append("		</FolderList>\n");
		builder.append("	<NumberOfFolders>");
		numOfFolders += vf.folderList.size();
		builder.append(numOfFolders);
		builder.append("</NumberOfFolders>\n");
		builder.append(imageList(FolderList.All));
		builder.append("		 </FolderOf>\n");
		final NodeInfo nodeInfo = new NodeInfo(numOfFolders, numOfFiles, builder.toString());
		return nodeInfo;
	}

}
