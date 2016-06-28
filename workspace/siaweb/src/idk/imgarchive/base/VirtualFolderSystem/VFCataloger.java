package idk.imgarchive.base.VirtualFolderSystem;

import idk.imgarchive.base.VirtualFolderSystem.VirtualFolder.VirtualFolderInfo;
import idk.imgarchive.base.workspacemanager.ImageAddress;
import idk.imgarchive.base.workspacemanager.ImageCursor;
import idk.imgarchive.base.workspacemanager.Workspace;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import xmlutils.DateUtils;
import xmlutils.XmlWriter;

public class VFCataloger {
	static class Path extends ArrayList<String> {
		void pop(final String folder) {
			final int size = size();
			if (size > 0) {
				remove(size - 1);
			}
		}

		void push(final String folder) {
			add(folder);
		}
	}

	class VFolder {
		VirtualFolder vf = null;

		public VFolder(final VirtualFolder vf) {
			this.vf = vf;
		}

		void Process() {

		}

		public void write(final VirtualFolder vf) {

			final XmlWriter xmlWriter = new XmlWriter(xmlPath, vf.filename);
			xmlWriter.startElement("VirtualFolder");
			xmlWriter.startElement("Name");
			xmlWriter.element(vf.name);
			xmlWriter.endElement("Name");
			xmlWriter.startElement("Filename");
			xmlWriter.element(vf.filename);
			xmlWriter.endElement("Filename");
			xmlWriter.startElement("LastModified");
			String dateString = DateUtils.formatDDMMYYYY(vf.getLastModified());
			xmlWriter.element(dateString);
			xmlWriter.endElement("LastModified");
			xmlWriter.startElement("LastModified");
			dateString = DateUtils.formatDDMMYYYY(vf.getCreatedDate());
			xmlWriter.element(dateString);
			xmlWriter.endElement("LastModified");

			// Parent folder List
			xmlWriter.startElement("ParentFolderList");
			for (final String item : currentPath) {
				xmlWriter.startElement("Folder");
				xmlWriter.element(item);
				xmlWriter.endElement("Folder");
			}
			xmlWriter.endElement("ParentFolderList");
			// Folder List
			xmlWriter.startElement("FolderList");
			final ArrayList<VirtualFolderInfo> folderList = vf.getFolderList();
			for (final VirtualFolderInfo item : folderList) {
				xmlWriter.startElement("Folder");
				xmlWriter.startElement("Name");
				xmlWriter.element(item.name);
				xmlWriter.endElement("Name");
				xmlWriter.startElement("Filename");
				xmlWriter.element(item.filename);
				xmlWriter.endElement("Filename");
				xmlWriter.endElement("Folder");
			}
			xmlWriter.endElement("FolderList");

			// Image List
			xmlWriter.startElement("ImageList");
			final ArrayList<String> imageList = vf.getImageList();
			final ImageCursor cursor = Workspace.createImageCursor();
			for (final String item : imageList) {
				xmlWriter.startElement("Name");
				xmlWriter.element(item);
				xmlWriter.endElement("Name");
				cursor.absolute(ImageAddress.makeLong(item));
				final ImageAddress ia = cursor.getCurrent();
			}
			xmlWriter.endElement("ImageList");
			xmlWriter.endElement("ImageSetsCatalog");
			xmlWriter.close();
		}
	}

	Path currentPath = new Path();
	VirtualFolder currVF = null;
	String htmlPath;

	VirtualFolder parentVF = null;

	String rootPath;
	String xmlPath;

	public VFCataloger(final String rootPath, final String xmlPath, final String htmlPath) {
		this.rootPath = rootPath;
		this.xmlPath = xmlPath;
		this.htmlPath = htmlPath;
	}

	public boolean Init() throws ParseException {
		final File rootFile = new File(rootPath, "00000000.xml");
		if (rootFile.exists() == false) {
			return false;
		}

		try {
			currVF = new VirtualFolder(rootPath, "00000000.xml");
			parentVF = currVF;
		} catch (final RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentPath.add("/");
		return true;
	}

	public void Process() {
		// Write out root
		write(currVF);
		final ArrayList<VirtualFolderInfo> folderList = currVF.getFolderList();
		for (final VirtualFolderInfo item : folderList) {

		}
	}

	/*
	 * <?xml version="1.0" encoding="ISO-8859-1" ?> - <VirtualFolder>
	 * <Name>root</Name> <LastModified>25/01/2011 12:32:28</LastModified>
	 * <Created>25/01/2011 12:32:28</Created> - <FolderList>
	 * <Folder>P1400000.xml</Folder> <Folder>L5320000.xml</Folder>
	 * <Folder>H4320000.xml</Folder> </FolderList> <ImageList />
	 * </VirtualFolder>
	 */
	public void write(final VirtualFolder vf) {

		final XmlWriter xmlWriter = new XmlWriter(xmlPath, vf.filename);
		xmlWriter.startElement("VirtualFolder");
		xmlWriter.startElement("Name");
		xmlWriter.element(vf.name);
		xmlWriter.endElement("Name");
		xmlWriter.startElement("Filename");
		xmlWriter.element(vf.filename);
		xmlWriter.endElement("Filename");
		xmlWriter.startElement("LastModified");
		String dateString = DateUtils.formatDDMMYYYY(vf.getLastModified());
		xmlWriter.element(dateString);
		xmlWriter.endElement("LastModified");
		xmlWriter.startElement("Created");
		dateString = DateUtils.formatDDMMYYYY(vf.getCreatedDate());
		xmlWriter.element(dateString);
		xmlWriter.endElement("Created");

		// Parent folder List
		xmlWriter.startElement("ParentFolderList");
		for (final String item : currentPath) {
			xmlWriter.startElement("Folder");
			xmlWriter.element(item);
			xmlWriter.endElement("Folder");
		}
		xmlWriter.endElement("ParentFolderList");
		// Folder List
		xmlWriter.startElement("FolderList");
		final ArrayList<VirtualFolderInfo> folderList = vf.getFolderList();
		for (final VirtualFolderInfo item : folderList) {
			xmlWriter.startElement("Folder");
			xmlWriter.startElement("Name");
			xmlWriter.element(item.name);
			xmlWriter.endElement("Name");
			xmlWriter.startElement("Filename");
			xmlWriter.element(item.filename);
			xmlWriter.endElement("Filename");
			xmlWriter.endElement("Folder");
		}
		xmlWriter.endElement("FolderList");

		// Image List
		xmlWriter.startElement("ImageList");
		final ArrayList<String> imageList = vf.getImageList();
		final ImageCursor cursor = Workspace.createImageCursor();
		for (final String item : imageList) {
			xmlWriter.startElement("Name");
			xmlWriter.element(item);
			xmlWriter.endElement("Name");
			cursor.absolute(ImageAddress.makeLong(item));
			final ImageAddress ia = cursor.getCurrent();
		}
		xmlWriter.endElement("ImageList");
		xmlWriter.endElement("ImageSetsCatalog");
		xmlWriter.close();
	}

}

/*
 * import java.io.File;
 * 
 * public class VisitorTester { public static void main(String[] args) {
 * DirectoryNode node = new DirectoryNode(new File("..")); node.visit(new
 * PrintVisitor()); } }
 * 
 * class PrintVisitor { int level = 0; public void visitFileNode(FileNode node)
 * { for (int i = 0; i < level; i++) System.out.print(" ");
 * System.out.println(node.getFile().getName()); }
 * 
 * public void visitDirectoryNode(DirectoryNode node) { for (int i = 0; i <
 * level; i++) System.out.print(" ");
 * System.out.println(node.getDirectory().getName()); level++; for
 * (FileSystemNode c : node.getChildren()) c.visit(this); level--; } }
 * 
 * interface FileSystemNode { void visit(PrintVisitor v); }
 * 
 * class FileNode implements FileSystemNode { public FileNode(File file) {
 * this.file = file; }
 * 
 * public File getFile() { return file; }
 * 
 * public void visit(PrintVisitor v) { v.visitFileNode(this); }
 * 
 * private File file; }
 * 
 * class DirectoryNode implements FileSystemNode { public DirectoryNode(File
 * directory) { this.directory = directory; }
 * 
 * public void visit(PrintVisitor v) { v.visitDirectoryNode(this); }
 * 
 * public File getDirectory() { return directory; }
 * 
 * public FileSystemNode[] getChildren() { File[] files = directory.listFiles();
 * FileSystemNode[] children = new FileSystemNode[files.length]; for (int i = 0;
 * i < files.length; i++) { File f = files[i]; if (f.isDirectory()) children[i]
 * = new DirectoryNode(f); else children[i] = new FileNode(f); } return
 * children; }
 * 
 * private File directory; }
 */
