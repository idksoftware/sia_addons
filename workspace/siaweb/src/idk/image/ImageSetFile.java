package idk.image;


import idk.archiveutils.RotateDirection;

public class ImageSetFile {

	private boolean doImageSubset = false;
	private boolean doReadExif = false;
	private FileType fileType = FileType.UNKNOWN;
	private RotateDirection imageRotateDirection = RotateDirection.none;
	private String path = null;
	private boolean primary = false;

	public ImageSetFile(final FileType fileType, final boolean primary, final String path, final boolean doReadExif,
			final RotateDirection imageRotate, final boolean doImageSubset) {
		this.fileType = fileType;
		this.doReadExif = doReadExif;
		this.primary = primary;
		this.path = path;
		this.imageRotateDirection = imageRotate;
		this.doImageSubset = doImageSubset;
	}

	/**
	 * @return the fileType
	 */
	public final FileType getFileType() {
		return fileType;
	}

	/**
	 * @return the imageRotate
	 */
	public final RotateDirection getImageRotate() {
		return imageRotateDirection;
	}

	public String getPath() {
		return path;
	}

	/**
	 * @return the doImageSubset
	 */
	public final boolean isDoImageSubset() {
		return doImageSubset;
	}

	public boolean isDoReadExif() {
		return doReadExif;
	}

	public boolean isPrimary() {
		return primary;
	}

}
