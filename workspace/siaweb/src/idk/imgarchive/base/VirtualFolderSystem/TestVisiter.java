package idk.imgarchive.base.VirtualFolderSystem;

import java.io.File;

class DirectoryNode implements FileSystemNode {
	private final File directory;

	public DirectoryNode(final File directory) {
		this.directory = directory;
	}

	public FileSystemNode[] getChildren() {
		final File[] files = directory.listFiles();
		final FileSystemNode[] children = new FileSystemNode[files.length];
		for (int i = 0; i < files.length; i++) {
			final File f = files[i];
			if (f.isDirectory()) {
				children[i] = new DirectoryNode(f);
			} else {
				children[i] = new FileNode(f);
			}
		}
		return children;
	}

	public File getDirectory() {
		return directory;
	}

	@Override
	public void visit(final PrintVisitor v) {
		v.visitDirectoryNode(this);
	}
}

class FileNode implements FileSystemNode {
	private final File file;

	public FileNode(final File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	@Override
	public void visit(final PrintVisitor v) {
		v.visitFileNode(this);
	}
}

interface FileSystemNode {
	void visit(PrintVisitor v);
}

class PrintVisitor {
	int level = 0;

	public void visitDirectoryNode(final DirectoryNode node) {
		for (int i = 0; i < level; i++) {
			System.out.print(" ");
		}
		System.out.println(node.getDirectory().getName());
		level++;
		for (final FileSystemNode c : node.getChildren()) {
			c.visit(this);
		}
		level--;
	}

	public void visitFileNode(final FileNode node) {
		for (int i = 0; i < level; i++) {
			System.out.print(" ");
		}
		System.out.println(node.getFile().getName());
	}
}