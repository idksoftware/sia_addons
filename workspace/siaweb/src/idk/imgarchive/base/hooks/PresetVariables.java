package idk.imgarchive.base.hooks;

/*
 destiasion
 */
public class PresetVariables {
	String toolPath = null;
	String sourcePath = null;
	String destinationPath = null;
	String currentImageName = null;
	String currentArchiveName = null;
	String currentImageExtention = null;

	int width = 0;
	int height = 0;
	int quality = 0;

	/**
	 * @return the toolPath
	 */
	public final String getToolPath() {
		return toolPath;
	}

	/**
	 * @param toolPath
	 *            the toolPath to set
	 */
	public final void setToolPath(final String toolPath) {
		this.toolPath = toolPath;
	}

	/**
	 * @return the sourcePath
	 */
	public final String getSourcePath() {
		return sourcePath;
	}

	/**
	 * @param sourcePath
	 *            the sourcePath to set
	 */
	public final void setSourcePath(final String sourcePath) {
		this.sourcePath = sourcePath;
	}

	/**
	 * @return the destinationPath
	 */
	public final String getDestinationPath() {
		return destinationPath;
	}

	/**
	 * @param destinationPath
	 *            the destinationPath to set
	 */
	public final void setDestinationPath(final String destinationPath) {
		this.destinationPath = destinationPath;
	}

	/**
	 * @return the currentImageName
	 */
	public final String getCurrentImageName() {
		return currentImageName;
	}

	/**
	 * @param currentImageName
	 *            the currentImageName to set
	 */
	public final void setCurrentImageName(final String currentImageName) {
		this.currentImageName = currentImageName;
	}
	/**
	 * @return the currentArchiveName
	 */
	public final String getCurrentArchiveName() {
		return currentArchiveName;
	}

	/**
	 * @param currentArchiveName the currentArchiveName to set
	 */
	public final void setCurrentArchiveName(String currentArchiveName) {
		this.currentArchiveName = currentArchiveName;
	}

	/**
	 * @return the currentImageExtention
	 */
	public final String getCurrentImageExtention() {
		return currentImageExtention;
	}

	/**
	 * @param currentImageExtention
	 *            the currentImageExtention to set
	 */
	public final void setCurrentImageExtention(final String currentImageExtention) {
		this.currentImageExtention = currentImageExtention;
	}

	/**
	 * @return the width
	 */
	public final int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public final void setWidth(final int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public final int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public final void setHeight(final int height) {
		this.height = height;
	}

	/**
	 * @return the quality
	 */
	public final int getQuality() {
		return quality;
	}

	/**
	 * @param quality
	 *            the quality to set
	 */
	public final void setQuality(final int quality) {
		this.quality = quality;
	}
}
