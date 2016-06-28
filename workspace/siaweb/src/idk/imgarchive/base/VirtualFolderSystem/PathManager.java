package idk.imgarchive.base.VirtualFolderSystem;

import java.util.Stack;

public class PathManager {
	private static VirtualFolder rootFolder = null;
	private static Stack<VirtualFolder> vfsPath = new Stack<VirtualFolder>();
	private static String vfsPathString = "/";
}
