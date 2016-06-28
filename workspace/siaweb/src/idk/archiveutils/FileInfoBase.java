package idk.archiveutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;
import java.util.zip.CRC32;

import org.w3c.dom.Element;

import xmlutils.XmlWriter;

public abstract class FileInfoBase {

	protected long crc = 0;
	protected String errorMsg = null;
	protected File file = null;
	public String fileName = "";
	public boolean isDirectory = false;
	protected boolean isError = false;

	protected Date lastModified = null;
	protected String md5 = "";
	protected UUID uuid = null;

	public String path;

	protected long size = 0;

	public FileInfoBase(final Element rootElement) throws ParseException {
		readInfo(rootElement);
	}

	public FileInfoBase(final File f) throws IOException, NoSuchAlgorithmException {
		file = f;
		init();
		readFile();
	}

	public FileInfoBase(final String path) throws IOException, NoSuchAlgorithmException {
		file = new File(path);
		fileInit(file);
	}

	public FileInfoBase(final String name, final Date lastModified, final long size, final long crc, final String md5) {
		fileName = name;
		this.md5 = md5;
		this.crc = crc;
		this.size = size;
		this.lastModified = lastModified;
		uuid = null;
	}

	public FileInfoBase(final String path, final String name) throws IOException, NoSuchAlgorithmException {

		file = new File(path, name);
		fileInit(file);
	}

	protected void fileInit(final File file) throws IOException, NoSuchAlgorithmException {
		fileName = file.getName();
		init();
		readFile();
	}

	/**
	 * @return the crc
	 */
	public final long getCrc() {
		return crc;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @return the file
	 */
	public final File getFile() {
		return file;
	}

	/**
	 * @return the fileName
	 */
	public final String getFileName() {
		return fileName;
	}

	/**
	 * @return the lastModified
	 */
	public final Date getLastModified() {
		return lastModified;
	}

	

	/**
	 * @return the md5
	 */
	public final String getMd5() {
		return md5;
	}

	/**
	 * @return the originalFile
	 */
	public final String getOriginalFile() {
		return fileName;
	}

	public final String getPath() {
		return path;
	}

	/**
	 * @return the size
	 */
	public final long getSize() {
		return size;
	}

	protected void init() throws IOException {

		fileName = file.getName();
		
		if (!file.exists()) {
			System.out.println(fileName + ": File do'es not exist");
			isError = true;
			errorMsg = fileName + ": File do'es not exist";
			throw new IOException(fileName + ": File do'es not exist");

		}

		if (!file.isFile()) {
			isDirectory = true;
			throw new IOException(fileName + ": is a directory");
		}

	}

	/**
	 * @return the isDirectory
	 */
	public final boolean isDirectory() {
		return isDirectory;
	}

	public boolean isEqual(final FileInfoBase fileInfo) {
		if (fileName.compareTo(fileInfo.fileName) != 0) {
			return false;
		}
		if (md5.compareTo(fileInfo.md5) != 0) {
			return false;
		}
		if (crc != fileInfo.crc) {
			return false;
		}
		if (size != fileInfo.size) {
			return false;
		}
		final long thisDate = lastModified.getTime() / 1000;
		final long fiDate = fileInfo.lastModified.getTime() / 1000;
		if (thisDate - 20 < fiDate || thisDate + 20 > fiDate) {
			return true;
		}
		return false;
	}

	public final boolean isError() {
		return isError;
	}

	public void readFile() throws NoSuchAlgorithmException, FileNotFoundException {

		final MessageDigest digest = MessageDigest.getInstance("MD5");
		final CRC32 crc32 = new CRC32();

		if (!file.exists()) {
			throw new RuntimeException("File do'es not exist");
		}
		if (!file.isFile()) {
			throw new RuntimeException("Not a file");
		}

		size = file.length();
		final long lm = file.lastModified();
		final Date date = new Date(lm);
		lastModified = date;

		final InputStream is = new FileInputStream(file);
		final byte[] buffer = new byte[8192];
		int read = 0;
		try {
			while ((read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
				crc32.update(buffer, 0, read);
			}
			final byte[] md5sum = digest.digest();
			final BigInteger md5 = new BigInteger(1, md5sum);
			this.md5 = md5.toString(16);
			crc = crc32.getValue();
			makeUUID();

			// System.out.println("MD5: " + outputMD5 + outputCRC.toString());
		} catch (final IOException e) {
			throw new RuntimeException("Unable to process file for MD5", e);
		}

		finally {
			try {

				is.close();
			} catch (final IOException e) {
				throw new RuntimeException("Unable to close input stream for MD5 calculation", e);
			}
		}
	}

	public final void makeUUID() {
		uuid = UUID.randomUUID();
	}

	protected abstract void readInfo(Element rootElement) throws ParseException;

	public abstract XmlWriter write(XmlWriter xmlWriter);

}
