package idk.archiveutils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipPartition {
	static final int BUFFER = 2048;
	ZipOutputStream zipOut = null;

	public ZipPartition(final String zipFile) {
		FileOutputStream dest = null;
		try {
			dest = new FileOutputStream(zipFile);
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zipOut = new ZipOutputStream(new BufferedOutputStream(dest));
	}

	public void closeZip() {
		try {
			zipOut.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void compressImages(final String partPath, final String destFolder) {
		final File partDir = new File(partPath);
		final String imageFolders[] = partDir.list();

		for (final String imageFolder2 : imageFolders) {
			System.out.println("Adding: " + imageFolder2);
			final String imageFolder = partPath + File.separator + imageFolder2;

			final File f = new File(imageFolder);
			final String files[] = f.list();
			FileInputStream fi = null;
			BufferedInputStream origin = null;

			for (final String file : files) {
				System.out.println("Adding: " + file);

				final byte data[] = new byte[BUFFER];

				FileInfo fileInfo = null;
				try {
					fileInfo = new FileInfo(imageFolder, file);
				} catch (final NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (final IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					fi = new FileInputStream(imageFolder + File.separator + file);
				} catch (final FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				origin = new BufferedInputStream(fi, BUFFER);
				ZipEntry entry = null;
				if (destFolder == null) {
					entry = new ZipEntry(file);
				} else {
					entry = new ZipEntry(destFolder + File.separator + file);
				}
				entry.setCrc(fileInfo.getCrc());
				entry.setSize(fileInfo.getSize());
				entry.setTime(fileInfo.getLastModified().getTime());

				try {
					zipOut.putNextEntry(entry);
				} catch (final IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int count;
				try {
					while ((count = origin.read(data, 0, BUFFER)) != -1) {
						zipOut.write(data, 0, count);
					}
				} catch (final IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					origin.close();
				} catch (final IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public void compressPartition(final String folder) {
		compressImages(folder, null);
		closeZip();
	}

}
