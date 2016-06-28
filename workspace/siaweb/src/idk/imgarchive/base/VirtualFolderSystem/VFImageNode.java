package idk.imgarchive.base.VirtualFolderSystem;

import java.io.IOException;
import java.text.ParseException;

public class VFImageNode implements VFSystemNode {
	private final String image;

	public VFImageNode(final String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	/*
	 * @Override public void visit(CatalogVisitor v) { v.visitFileNode(this); }
	 */

	@Override
	public void visit(final FolderCatalogerBase v) throws ParseException, IOException {
		v.visitNode(this);

	}
}
