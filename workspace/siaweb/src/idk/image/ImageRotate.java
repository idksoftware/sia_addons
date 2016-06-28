package idk.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageRotate {

	

	public static void rotate(final File inFile, final File outFile, int rotation) {
		try {
			final BufferedImage image = ImageIO.read(inFile);
			//final ImageIcon icon = new ImageIcon(image);
			final BufferedImage temp = rotate(image, rotation);
			ImageIO.write(temp, "jpg", outFile);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private static BufferedImage rotate(final BufferedImage image,
			final double degs) {
		final int width = image.getWidth(null);
		final int height = image.getHeight(null);
		BufferedImage temp = null;
		Graphics2D g2 = null;
		if (degs == 0) {
			temp = new BufferedImage(width, width,
					BufferedImage.TYPE_INT_RGB);
		} else if (degs == 90) {
			temp = new BufferedImage(height, width,
					BufferedImage.TYPE_INT_RGB);
			g2 = temp.createGraphics();
			g2.rotate(Math.toRadians(degs), height / 2, height / 2);
			
		} else if (degs == 180) {
			temp = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			g2 = temp.createGraphics();	//   l - r      u - d
			g2.rotate(Math.toRadians(degs), width / 2 , height / 2);
			
		} else if (degs == 270) {
			temp = new BufferedImage(height, width,
					BufferedImage.TYPE_INT_RGB);
			g2 = temp.createGraphics();
			g2.rotate(Math.toRadians(degs), width / 2, height / 2 );
		} else {
			return null;
		}
					
		g2.drawImage(image, 0, 0, Color.WHITE, null);
		g2.dispose();
		return temp;
	}

	public static BufferedImage rotate(final Image image, final double degs) {
		final int width = image.getWidth(null);
		final int height = image.getHeight(null);
		final BufferedImage temp = new BufferedImage(height, width,
				BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2 = temp.createGraphics();
		g2.rotate(Math.toRadians(degs), height / 2, height / 2);
		g2.drawImage(image, 0, 0, Color.WHITE, null);
		g2.dispose();
		return temp;
	}
}
