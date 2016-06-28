package idk.imgarchive.base.VirtualFolderSystem;

import java.io.IOException;
import java.text.ParseException;

interface VFSystemNode {
	// void visit(CatalogerBase catalogerBase) throws ParseException;

	void visit(FolderCatalogerBase v) throws ParseException, IOException;
}
