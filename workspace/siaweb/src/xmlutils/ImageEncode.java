package xmlutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Element;

public class ImageEncode {

	
	

	public static byte[] getBytesFromFile(File file) throws IOException {
		final InputStream is = new FileInputStream(file);
		// Get the size of the file
		final long length = file.length();

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			// File is too large
			is.close();
			throw new IOException("File exceeds max value: " + Integer.MAX_VALUE);
		}

		// Create the byte array to hold the data
		final byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			is.close();
			throw new IOException("Could not completely read file" + file.getName());

		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

	public void decode(String filePath, Element node) throws IOException {
		final File imageFile = new File(filePath);
		final OutputStream os = new FileOutputStream(imageFile);
		String encodedImage = node.getTextContent();
		// String decoded = decode(encodedImage);
		// os.write(decoded);
		final Runtime runtime = Runtime.getRuntime();
		System.out.println("Free memory : " + runtime.freeMemory());
		String[] sei = encodedImage.split("\r\n");
		// System.out.println(encodedImage);
		System.out.println("Free memory : " + runtime.freeMemory());
		for (final String element : sei) {
			final byte[] byteImage = Base64.decodeBase64(element);
			try {
				os.write(byteImage);
			} catch (final FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		os.close();
		System.out.println("Free memory : " + runtime.freeMemory());
		encodedImage = null;
		sei = null;
		System.gc();
		System.out.println("Free memory : " + runtime.freeMemory());
	}

	
	public void encode(String filePath, Element node) throws IOException {
		// ByteArrayOutputStream baos = null;
		try {
			final File imageFile = new File(filePath);
			final byte[] array = getBytesFromFile(imageFile);
			final String encodedImage = Base64.encodeBase64String(array);
			node.setTextContent(encodedImage); // store it inside node
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
