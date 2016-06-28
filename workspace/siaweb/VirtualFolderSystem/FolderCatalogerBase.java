package idk.imgarchive.base.VirtualFolderSystem;

import idk.imgarchive.base.cataloger.CatalogerBase;

import java.io.IOException;
import java.text.ParseException;

public abstract class FolderCatalogerBase extends CatalogerBase {
	public abstract void visitNode(VFSystemNode vfFolderNode) throws ParseException, IOException;

}
