package idk.archiveutils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

public class ImageUtils {
	static public class ImageDimensions {

		private int width = 0;
		private int height = 0;

		public ImageDimensions(final int width, final int height) {
			this.width = width;
			this.height = height;
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

	}

	public static ImageDimensions originalSize(final String path) {
		ImageDimensions imageDimensions = null;

		//final String[] list = ImageIO.getWriterFileSuffixes();

		final String suffix = ImageUtils.getFileSuffix(path);
		final Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);

		if (iter.hasNext()) {
			final ImageReader reader = iter.next();
			try {
				final ImageInputStream stream = new FileImageInputStream(new File(path));
				reader.setInput(stream);
				final int width = reader.getWidth(reader.getMinIndex());
				final int height = reader.getHeight(reader.getMinIndex());
				imageDimensions = new ImageDimensions(width, height);
				return imageDimensions;
			} catch (final IOException e) {
				System.out.println("No reader found for given format: " + suffix);
			} finally {
				reader.dispose();
			}
		}
		return imageDimensions;
	}

	private static String getFileSuffix(final String path) {
		String result = null;
		if (path != null) {
			result = "";
			if (path.lastIndexOf('.') != -1) {
				result = path.substring(path.lastIndexOf('.'));
				if (result.startsWith(".")) {
					result = result.substring(1);
				}
			}
		}
		return result;
	}
	/*
	 * public static ImageDimensions originalSize(String filename) throws
	 * InterruptedException {
	 * 
	 * Image inImage = Toolkit.getDefaultToolkit().getImage(filename);
	 * MediaTracker mediaTracker = new MediaTracker(new Container());
	 * mediaTracker.addImage(inImage, 0); mediaTracker.waitForID(0); int w =
	 * inImage.getWidth(null); int h = inImage.getHeight(null); ImageDimensions
	 * imageDimensions = new ImageDimensions(w,h); return imageDimensions; }
	 */
}
