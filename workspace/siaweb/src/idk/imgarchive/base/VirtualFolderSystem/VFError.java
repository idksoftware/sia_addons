package idk.imgarchive.base.VirtualFolderSystem;

public class VFError {
	public final static String UNSUCCESSFULL_STRING = "Operation unsuccessfull";
	public final static String SUCCESS_OK_STRING = "Operation completed successfully";
	public final static String CANNOT_CREATE_FOLDER_STRING = "Cannot create folder \"%s\" as it exists already";
	public final static String CANNOT_DELETE_ROOT_STRING = "Cannot delete root folder";
	public final static String FOLDER_NOT_EXIST_STRING = "Folder \"%s\" not found";
	public final static String ORIGINAL_FOLDER_NOT_FOUND = "Original folder \"%s\" not found";
	public final static String NEW_FOLDER_EXISTS = "New folder \"%s\" exists";
	// Success command strings
	public final static String SUCCESSFULLY_DELETED_FOLDER_STRING = "Successfully deleted folder \"%s\"";

	public static enum Code {
		SUCCESS_OK, UNSUCCESSFULL, CANNOT_CREATE_FOLDER, CANNOT_DELETE_ROOT, FOLDER_NOT_EXIST, ORIGINAL_FOLDER_NOT_FOUND, NEW_FOLDER_EXISTS
	}
}
