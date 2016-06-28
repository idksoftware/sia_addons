package idk.archiveutils;

import java.util.ArrayList;

public final class ImageFilename {

	@SuppressWarnings("serial")
	public static class IAFilerList extends ArrayList<IAFileType> {
		public boolean containsType(final IAFileType type) {
			for (final IAFileType item : this) {
				if (item == type) {
					return true;
				}
			}
			return false;
		}

	}

	static public enum IAFileType {
		EXF, IFO, IMG, IPC, UNK,
	}

	static public enum ImageSize {
		Full, Large, Medium, Small, Thumbnail, Tiny
	}

	static public ImageSize getImageSize(final String size) {

		if (size == null) {
			return ImageSize.Full;
		}

		if (size.matches("T")) {
			return ImageSize.Thumbnail;
		} else if (size.matches("t")) {
			return ImageSize.Tiny;
		} else if (size.matches("8")) {
			return ImageSize.Small;
		} else if (size.matches("4")) {
			return ImageSize.Medium;
		} else if (size.matches("2")) {
			return ImageSize.Large;
		}
		return ImageSize.Full;
	}

	static public String getImageSizeString(final ImageSize size) {
		switch (size) {
		case Full:
			return "";
		case Thumbnail:
			return "_T";
		case Tiny:
			return "_t";
		case Small:
			return "_8";
		case Medium:
			return "_4";
		case Large:
			return "_2";
		default:
			return "";
		}
	}

	public static boolean isImageManifestFile(final String filename) {
		final String extension = filename.substring(filename.length() - 3, filename.length());
		final String prefix = filename.substring(0, 3);
		if (prefix.matches("IMF") && extension.matches("xml")) {
			return true;
		}
		return false;
	}

	// ---------------------------------------------------------------------------------------------------------
	/**
	 * 
	 * @param Prefix
	 * @param id
	 * @param extension
	 * @return
	 */
	public static final String makeImageFilename(final IAFileType prefix, final long id, final String extension) {
		return String.format("%s%08X.%s", prefix.toString(), id, extension);
	}

	/**
	 * 
	 * @param Prefix
	 * @param id
	 * @param extension
	 * @return
	 */
	public static final String makeImageFilename(final String Prefix, final Long id, final String extension) {
		return String.format("%s%08X.%s", Prefix, id, extension);
	}

	/**
	 * 
	 * @param Prefix
	 * @param id
	 * @param extension
	 * @return
	 */
	public static final String makeImageFilenameNoPrefix(final String newFilename, final String extension) {
		return String.format("%s.%s", newFilename, extension);
	}

	// ---------------------------------------------------------------------------------------------------------
	/**
	 * 
	 * @param Prefix
	 * @param id
	 * @param extension
	 * @param version
	 * @return
	 */
	public static final String makeImageFilename(final String Prefix, final long id, final String extension, final int version) {
		return String.format("%s%08X@%02X.%s", Prefix, id, version, extension);
	}

	/**
	 * 
	 * @param Prefix
	 * @param id
	 * @param extension
	 * @param version
	 * @return
	 */
	public static final String makeImageFilename(final String Prefix, final String newFilename, final String extension,
			final int version) {
		return String.format("%s%s@%02X.%s", Prefix, newFilename, version, extension);
	}

	/**
	 * 
	 * @param Prefix
	 * @param id
	 * @param extension
	 * @param version
	 * @return
	 */
	public static final String makeImageFilenameNoPrefix(final String newFilename, final String extension, final int version) {
		return String.format("%s@%02X.%s", newFilename, version, extension);
	}

	// ---------------------------------------------------------------------------------------------------------
	/**
	 * 
	 * @param Prefix
	 * @param id
	 * @param Postfix
	 * @param extension
	 * @return
	 */
	public static final String makeImageFilename(final long id, final ImageSize size, final String extension) {
		return String.format("IMG%08X%s.%s", id, getImageSizeString(size), extension);
	}

	/**
	 * 
	 * @param Prefix
	 * @param id
	 * @param Postfix
	 * @param extension
	 * @return
	 */
	public static final String makeImageFilename(final String Prefix, final long id, final String Postfix, final String extension) {
		return String.format("%s%08X_%s.%s", Prefix, id, Postfix, extension);
	}

	/**
	 * 
	 * @param Prefix
	 * @param id
	 * @param Postfix
	 * @param extension
	 * @return
	 */
	public static final String makeImageFilename(final String Prefix, final String newFilename, final String Postfix,
			final String extension) {
		return String.format("%s%s_%s.%s", Prefix, newFilename, Postfix, extension);
	}

	/**
	 * 
	 * @param Prefix
	 * @param id
	 * @param Postfix
	 * @param extension
	 * @return
	 */
	public static final String makeImageFilenameNoPrefix(final String newFilename, final String Postfix, final String extension) {
		return String.format("%s_%s.%s", newFilename, Postfix, extension);
	}

	// ---------------------------------------------------------------------------------------------------------

	/**
	 * 
	 * @param Prefix
	 * @param id
	 * @param Postfix
	 * @param extension
	 * @param version
	 * @return
	 */
	public static final String makeImageFilename(final String Prefix, final long id, final String Postfix, final String extension,
			final int version) {
		return String.format("%s%08X@%02X_%s.%s", Prefix, id, version, Postfix, extension);
	}

	/**
	 * 
	 * @param Prefix
	 * @param id
	 * @param Postfix
	 * @param extension
	 * @param version
	 * @return
	 */
	public static final String makeImageFilename(final String Prefix, final String newFilename, final String Postfix,
			final String extension, final int version) {
		return String.format("%s%s@%02X_%s.%s", Prefix, newFilename, version, Postfix, extension);
	}

	/**
	 * 
	 * @param Prefix
	 * @param id
	 * @param Postfix
	 * @param extension
	 * @param version
	 * @return
	 */
	public static final String makeImageFilenameNoPrefix(final String newFilename, final String postfix, final String extension,
			final int version) {
		return String.format("%s@%02X_%s.%s", newFilename, version, postfix, extension);
	}

	@SuppressWarnings("unused")
	public static final boolean validateFilename(final String filename) {
		boolean foundVersion = false;
		final String iaFileTypeStr = filename.substring(0, 3);
		IAFileType iaFileType = null;
		try {
			iaFileType = IAFileType.valueOf(iaFileTypeStr);
		} catch (final IllegalArgumentException e) {
			return false;
		}

		String strID = null;
		int idx = -1;
		if ((idx = filename.indexOf("@")) != -1) {

			foundVersion = true;
			strID = filename.substring(3, idx);

		} else if ((idx = filename.indexOf("_")) != -1) {
			strID = filename.substring(3, idx);
		} else {
			idx = filename.indexOf(".");
			strID = filename.substring(3, idx);
		}
		long id = 0;
		try {
			id = Long.parseLong(strID, 16);
		} catch (final NumberFormatException e) {
			return false;
		}
		boolean foundPostfix = false;
		final String prefix = filename.substring(0, 3);
		if (foundVersion == true) {
			int ix = -1;
			if ((ix = filename.indexOf("_")) != -1) {
				foundPostfix = true;

			} else {
				ix = filename.indexOf(".");

			}
			strID = filename.substring(idx + 1, ix);
			int version = -1;
			try {
				version = Integer.parseInt(strID, 16);
			} catch (final NumberFormatException e) {
				return false;
			}
			idx = ix;
		}
		String postfix = null;
		String extension = null;
		ImageSize imageSize = null;
		if (foundPostfix == true) {
			int ix = -1;
			ix = filename.indexOf(".");
			postfix = filename.substring(idx + 1, ix);
			idx = ix;
			imageSize = getImageSize(postfix);
		} else {
			imageSize = ImageSize.Full;
		}
		extension = filename.substring(idx + 1, filename.length());
		return true;
	}

	boolean includePreviewImages = false;
	String extension = null;;
	IAFileType iaFileType = IAFileType.UNK;
	long id = -1;
	ImageSize imageSize = null;
	boolean invalidFilename = false;
	String postfix = null;
	String prefix = null;
	int version = 1; // if the version is not found it must be version 1

	public ImageFilename(final String filename) throws NumberFormatException {

		boolean foundVersion = false;
		boolean foundPostfix = false;

		iaFileType = fileIAFileType(filename);
		String strID = null;
		int idx = -1;
		if ((idx = filename.indexOf("@")) != -1) {

			foundVersion = true;
			strID = filename.substring(3, idx);

		} else if ((idx = filename.indexOf("_")) != -1) {
			strID = filename.substring(3, idx);
			foundPostfix = true;
		} else {
			idx = filename.indexOf(".");
			strID = filename.substring(3, idx);
		}
		try {
			id = Long.parseLong(strID, 16);
		} catch (final NumberFormatException e) {
			invalidFilename = true;
		}
		prefix = filename.substring(0, 3);
		if (foundVersion == true) {
			int ix = -1;
			if ((ix = filename.indexOf("_")) != -1) {
				foundPostfix = true;

			} else {
				ix = filename.indexOf(".");

			}
			strID = filename.substring(idx + 1, ix);
			try {
				version = Integer.parseInt(strID, 16);
			} catch (final NumberFormatException e) {
				invalidFilename = true;
			}
			idx = ix;
		}
		if (foundPostfix == true) {
			int ix = -1;
			ix = filename.indexOf(".");
			postfix = filename.substring(idx + 1, ix);
			idx = ix;
			imageSize = getImageSize(postfix);
		} else {
			imageSize = ImageSize.Full;
		}
		extension = filename.substring(idx + 1, filename.length());

	}

	public boolean fileFilter(final IAFilerList ialist, final boolean allVersions, final ArrayList<String> typsList,
			final ArrayList<String> extList) {
		if (ialist != null) {
			boolean prefixFound = false;
			final IAFileType filePrefix = IAFileType.valueOf(prefix);
			for (final IAFileType type : IAFileType.values()) {
				if (ialist.containsType(type)) {
					if (filePrefix == type) {
						prefixFound = true;
						break;
					}
				}
			}
			if (prefixFound == false) {
				return false;
			}
		}
		if (allVersions == false) {
			if (version >= 0) {
				return false;
			}
		}
		if (typsList != null) {

			return false;
		}
		if (includePreviewImages == false) {
			if (imageSize != ImageSize.Full) {
				return false;
			}
		}
		if (extList != null) {
			return false;
		}
		return true;
	}

	private IAFileType fileIAFileType(final String filename) {
		final String iaFileTypeStr = filename.substring(0, 3);
		IAFileType iaFileType = null;
		try {
			iaFileType = IAFileType.valueOf(iaFileTypeStr);
		} catch (final IllegalArgumentException e) {
			invalidFilename = true;
		}
		return iaFileType;
	}

	/**
	 * @return the includePreviewImages
	 */
	public final boolean isIncludePreviewImages() {
		return includePreviewImages;
	}

	/**
	 * @param includePreviewImages
	 *            the includePreviewImages to set
	 */
	public final void setIncludePreviewImages(final boolean includePreviewImages) {
		this.includePreviewImages = includePreviewImages;
	}

	/**
	 * @return the extension
	 */
	public final String getExtension() {
		return extension;
	}

	/**
	 * @return the iaFileType
	 */
	public final IAFileType getIaFileType() {
		return iaFileType;
	}

	/**
	 * @return the id
	 */
	public final long getId() {
		return id;
	}

	/**
	 * @return the imageSize
	 */
	public final ImageSize getImageSize() {
		return imageSize;
	}

	/**
	 * @return the postfix
	 */
	public final String getPostfix() {
		return postfix;
	}

	/**
	 * @return the postfix
	 */
	public final ImageSize getPostfixType() {
		return getImageSize(postfix);
	}

	/**
	 * @return the prefix
	 */
	public final String getPrefix() {
		return prefix;
	}

	/**
	 * @return the version
	 */
	public final int getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public final void incVersion() {
		version++;
	}

	/**
	 * @return the invalidFilename
	 */
	public final boolean isInvalidFilename() {
		return invalidFilename;
	}

	/**
	 * 
	 * @return
	 */
	public String makeImageFilename() {
		if (version == 1 && postfix != null) {
			return makeImageFilename(prefix, id, postfix, extension);
		} else if (version >= 1 && postfix == null) {
			return makeImageFilename(prefix, id, extension, version);
		} else if (version == 1 && postfix == null) {
			return makeImageFilename(prefix, id, extension);
		}
		return makeImageFilename(prefix, id, postfix, extension, version);
	}

	public String makeImageFilename(final String newFilename) {
		if (prefix.matches("IMG") == true) {
			if (version == 1 && postfix != null) {
				return makeImageFilenameNoPrefix(newFilename, postfix, extension);
			} else if (version == 1 && postfix == null) {
				return makeImageFilenameNoPrefix(newFilename, extension);
			} else if (version > 1 && postfix == null) {
				return makeImageFilenameNoPrefix(newFilename, extension, version);
			}
			return makeImageFilenameNoPrefix(newFilename, postfix, extension, version);
		}

		if (version == 1 && postfix != null) {
			return makeImageFilename(prefix, newFilename, postfix, extension);
		} else if (version >= 1 && postfix == null) {
			return makeImageFilename(prefix, newFilename, extension, version);
		} else if (version == 1 && postfix == null) {
			return makeImageFilename(prefix, id, extension);
		}
		return makeImageFilename(prefix, id, postfix, extension, version);

	}

	public boolean match(final ImageFilename imageFilename) {
		if (imageFilename.extension.matches(extension) && imageFilename.prefix.matches(prefix)) {
			if (postfix != null && imageFilename.postfix != null) {
				if (imageFilename.postfix.matches(postfix)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	public final void setExtension(final String extension) {
		this.extension = extension;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(final long id) {
		this.id = id;
	}

	/**
	 * @param postfix
	 *            the postfix to set
	 */
	public final void setPostfix(final String postfix) {
		this.postfix = postfix;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public final void setPrefix(final String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public final void setVersion(final int version) {
		this.version = version;
	}
}
