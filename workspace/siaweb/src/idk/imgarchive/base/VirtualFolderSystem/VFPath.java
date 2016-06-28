package idk.imgarchive.base.VirtualFolderSystem;

import idk.imgarchive.base.VirtualFolderSystem.VFError.Code;

import java.io.IOException;
import java.text.ParseException;
import java.util.Stack;

public class VFPath {
	final static String FILE_SEPARATOR = "/"; // Separator
	Code errorNo = VFError.Code.SUCCESS_OK;

	public enum PathType {
		Absolute, Relative, Unknown
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stack<VirtualFolder> folderList = new Stack<VirtualFolder>();
	private PathType pathType = PathType.Unknown;
	private Stack<VirtualFolder> rollBackList = null;

	private String rollBackPathString = null;
	private String vfsPathString = null;

	public VFPath() {
	}

	public VFPath(final VFPath oldPath) throws IOException, RuntimeException // Must
																				// be
	// Absolute
	{
		folderList.addAll(oldPath.folderList);
		vfsPathString = oldPath.getPathString();
	}

	public VFPath(final String initalPath) throws IOException, RuntimeException // Must
			// be
			// Absolute
			, ParseException {
		if (initalPath.startsWith(FILE_SEPARATOR) == false) {
			throw new RuntimeException(initalPath + " Not Absolute");
		}
		pathType = PathType.Absolute;
		vfsPathString = initalPath;
		final VirtualFolder vf = VirtualFolder.readRoot();
		folderList.push(vf);
		if (initalPath.matches("/")) {
			return; // loaded with root
		}
		final String[] pathNodes = initalPath.split(FILE_SEPARATOR);
		for (final String item : pathNodes) {

			System.out.println(pathNodes[0]);
			// single folder name
			if (folderList.peek().folderExists(item) == true) {
				folderList.push(folderList.peek().getFolder(item));
			} else {
				throw new RuntimeException(item + " Not not found");
			}
		}

	}

	public String makeFullPath(final String name) {
		return makeFullPath(getPathString(), name);
	}

	public String makeFullPath(String parent, final String name) {
		if (parent.compareTo(FILE_SEPARATOR) == 0) {
			// root
			return parent + name;
		}
		if (parent.endsWith(FILE_SEPARATOR) == true) {
			parent = parent + name;
		}
		if (parent.startsWith(FILE_SEPARATOR) == true) {
			return parent;
		}
		return getPathString() + FILE_SEPARATOR + parent;
	}

	public boolean changeFolder(String pathString) throws RuntimeException, IOException, ParseException {
		/*
		 * regionMatches(int toffset, String other, int ooffset, int len)
		 * toffset : the starting offset of the subregion in this string. other
		 * : the string argument. ooffset : the starting offset of the subregion
		 * in the string argument. len : the number of characters to compare.
		 */
		boolean res = false;
		setRollBackPoint();
		if (pathString.endsWith(FILE_SEPARATOR) == true) {
			pathString = pathString.substring(0, pathString.length() - 1);
		}
		if (pathString.startsWith(FILE_SEPARATOR) == true) {
			// See if the path is added i.e Current path /People/Famly/
			// New path /People/Famly/Mother
			if (pathString.regionMatches(0, getPathString(), 0, getPathString().length()) == true) { // Is
																										// a
																										// old
																										// path
																										// is
																										// a
				// sub path of the
				// new one so try
				// adding the extra
				// folders to the
				// end
				// Get relative bit of the path.
				String relativeString = pathString.substring(getPathString().length());
				if (relativeString.startsWith(FILE_SEPARATOR) == true) {
					relativeString = relativeString.substring(1);
				}

				final String relItems[] = relativeString.split(FILE_SEPARATOR);
				for (final String item : relItems) {
					if (folderList.peek().folderExists(item) == true) {
						folderList.push(folderList.peek().getFolder(item));
						res = true;
						break;
					} else {
						revert();
						return false;
					}
				}

			}
			// See if the path is substracted i.e Current path
			// /People/Famly/Mother
			// New path /People/Famly/
			else if (getPathString().regionMatches(0, pathString, 0, pathString.length()) == true) {
				String relativeString = getPathString().substring(pathString.length());
				if (relativeString.startsWith(FILE_SEPARATOR) == true) {
					relativeString = relativeString.substring(1);
				}

				final String relItems[] = relativeString.split(FILE_SEPARATOR);
				for (final String item : relItems) {
					folderList.pop();
				}
			} else { // re-build path
				final VirtualFolder rootVF = folderList.firstElement();
				pathString = pathString.substring(1);
				final String relItems[] = pathString.split(FILE_SEPARATOR);
				folderList.push(rootVF);
				for (final String item : relItems) {
					if (folderList.peek().folderExists(item) == true) {
						folderList.push(folderList.peek().getFolder(item));
						res = true;
						break;
					} else {
						revert();
						return false;
					}
				}
			}

		} else {
			pathString = makeFullPath(pathString);
			if (changeFolder(pathString) == true) { // this as a recursive call
				res = true;
			}
		}
		vfsPathString = pathString;
		/*
		 * Path newPath = new Path(VFSystem.path); if (newPath.getPathType() ==
		 * PathType.Absolute) { VFSystem.path = newPath; }
		 */
		return res;
	}

	public boolean deleteFolder(String pathString) throws RuntimeException, IOException, ParseException {
		if (pathString.matches(FILE_SEPARATOR) == true) {
			errorNo = Code.CANNOT_DELETE_ROOT;
			return false; // Cannot delete root folder
		}

		if (changeFolder(makeFullPath(pathString)) == false) {
			errorNo = Code.FOLDER_NOT_EXIST;
			return false;
		}

		VirtualFolder vf = getCWD();
		final DeleteFoldersAndImages dv = new DeleteFoldersAndImages(vf);
		dv.process();

		if (pathString.startsWith(FILE_SEPARATOR) == true) {
			pathString = pathString.substring(1);
		}

		final String relItems[] = pathString.split(FILE_SEPARATOR);
		for (@SuppressWarnings("unused")
		final String item : relItems) {
			folderList.pop();
		}
		vf = getCWD();
		final String pathItem = relItems[relItems.length - 1];
		vf.removeFolder(pathItem);

		if (pathString.endsWith(FILE_SEPARATOR) == true) {
			pathString = pathString.substring(0, pathString.length() - 1);
		}
		vfsPathString = FILE_SEPARATOR + pathString.substring(0, pathString.length() - pathItem.length());
		return true;
	}

	public VirtualFolder getCWD() {
		return folderList.peek();
	}

	public String getPathString() {
		return vfsPathString;
	}

	public PathType getPathType() {
		return pathType;
	}

	void revert() {
		vfsPathString = rollBackPathString;
		folderList = rollBackList;
	}

	void setRollBackPoint() {
		rollBackPathString = vfsPathString;
		rollBackList = new Stack<VirtualFolder>();
		rollBackList.addAll(folderList);
	}

	/**
	 * @return the errorNo
	 */
	public final VFError.Code getErrorNo() {
		return errorNo;
	}

}
