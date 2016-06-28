package idk.archiveutils;

import static org.w3c.dom.Node.ELEMENT_NODE;
import idk.imgarchive.base.log4j.Log;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlutils.XMLUtil;

public class FileExtentions {
	static FileExtentionList imageExtentions = null;
	static FileExtentionList pictureExtentions = null;
	static FileExtentionList rawExtentions = null;
	static boolean readOnce = false;

	public static final String extention(final String file) {
		return file.toLowerCase().substring(file.indexOf(".") + 1, file.length());
	}

	/**
	 * @return the imageExtentions
	 */
	public static final FileExtentionList getImageExtentions() {
		return imageExtentions;
	}

	/**
	 * @return the pictureExtentions
	 */
	public static final FileExtentionList getPictureExtentions() {
		return pictureExtentions;
	}

	/**
	 * @return the rawExtentions
	 */
	public static final FileExtentionList getRawExtentions() {
		return rawExtentions;
	}

	/**
	 * @return the pictureExtentions
	 */
	public static final boolean isImageAndRawExtentions(final String ext) {
		if (isImageExtention(ext) == false) {
			return isRawExtention(ext);
		}
		return true;
	}

	/**
	 * @return the imageExtentions
	 */
	public static final boolean isImageExtention(final String ext) {

		for (final String item : imageExtentions) {
			if (ext.matches(item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the pictureExtentions
	 */
	public static final boolean isPictureExtentions(final String ext) {

		for (final String item : pictureExtentions) {
			if (ext.matches(item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the rawExtentions
	 */
	public static final boolean isRawExtention(final String ext) {
		for (final String item : rawExtentions) {
			if (ext.matches(item)) {
				return true;
			}
		}
		return false;
	}

	public static final String ext(final String file) {
		return file.substring(file.indexOf(".")+1,file.length());
	}
	
	public static final String name(final String file) {
		return file.substring(0, file.indexOf("."));
	}

	public static boolean read(final String path) throws IOException, ParseException {
		if (readOnce == true) {
			return true; // read once already
		}
		readOnce = true;
		rawExtentions = new FileExtentionList();
		imageExtentions = new FileExtentionList();
		pictureExtentions = new FileExtentionList();

		final File extFile = new File(path);
		if (extFile.exists() == false) {
			return false; // cannot find user
		}

		Document doc = null;
		try {
			doc = XMLUtil.parse(extFile.getAbsolutePath());
		} catch (final IOException e2) {
			Log.IOException(extFile.getName(), e2);
		} catch (final ParseException e2) {
			Log.ParseException(extFile.getName(), e2);
		}
		final Element rootElement = doc.getDocumentElement();
		rootElement.normalize();

		final Element rawExtElement = XMLUtil.getElement(rootElement, "RawExt");
		if (rawExtElement.hasChildNodes() == true) {
			final NodeList rawExtList = rawExtElement.getChildNodes();
			final int n = rawExtList.getLength();
			for (int i = 0; i < n; i++) {
				final Node extNode = rawExtList.item(i);
				final short type = extNode.getNodeType();
				String name = null;
				if (type == ELEMENT_NODE) {
					name = XMLUtil.getStringValue((Element) extNode);
					rawExtentions.add(name);
				}
			}
		}
		final Element imageExtElement = XMLUtil.getElement(rootElement, "ImageExt");
		if (imageExtElement.hasChildNodes() == true) {
			final NodeList imageExtList = imageExtElement.getChildNodes();
			final int n = imageExtList.getLength();
			for (int i = 0; i < n; i++) {
				final Node extNode = imageExtList.item(i);
				final short type = extNode.getNodeType();
				String name = null;
				if (type == ELEMENT_NODE) {
					name = XMLUtil.getStringValue((Element) extNode);
					imageExtentions.add(name);
				}
			}
		}
		final Element pictureExtElement = XMLUtil.getElement(rootElement, "PictureExt");
		if (pictureExtElement.hasChildNodes() == true) {
			final NodeList pictureExtList = pictureExtElement.getChildNodes();
			final int n = pictureExtList.getLength();
			for (int i = 0; i < n; i++) {
				final Node extNode = pictureExtList.item(i);
				final short type = extNode.getNodeType();
				String name = null;
				if (type == ELEMENT_NODE) {
					name = XMLUtil.getStringValue((Element) extNode);
					pictureExtentions.add(name);
				}
			}
		}

		return true;
	}
}
