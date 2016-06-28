package idk.archiveutils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDirectory {
	public static enum FilterType {
		exclude, include
	}

	static final int BUFFER = 2048;
	FileFilter fileFilter = null;
	FilterType filterType = null;
	ZipOutputStream zipOut = null;

	public ZipDirectory(final String zipFile) {
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

	private void compress(final String folder, final String destFolder) {
		final File f = new File(folder);
		final String files[] = f.list();
		FileInputStream fi = null;
		BufferedInputStream origin = null;

		if (files == null) {
			return;
		}

		for (final String file : files) {
			System.out.println("Adding: " + file);

			final byte data[] = new byte[BUFFER];

			FileInfo fileInfo = null;
			try {
				fileInfo = new FileInfo(folder, file);
			} catch (final NoSuchAlgorithmException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (final IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			if (fileInfo.isDirectory == true) {
				if (fileFilter.accept(new File(folder + File.separator + file)) == true) {
					if (filterType == FilterType.exclude) {
						continue;
					}
				} else {
					// returned false
					if (filterType == FilterType.include) {
						continue;
					}
				}
				if (destFolder == null) {
					compress(folder + File.separator + file, file);
				} else {
					compress(folder + File.separator + file, destFolder + File.separator + file);
				}
			} else {
				try {
					fileInfo.readFile();
				} catch (final NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (final FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					fi = new FileInputStream(folder + File.separator + file);
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

	public void compressFile(final String filePath, final String relPath) {
		FileInputStream fi = null;
		BufferedInputStream origin = null;

		System.out.println("Adding: " + filePath);

		final byte data[] = new byte[BUFFER];
		FileInfo fileInfo = null;
		try {
			fileInfo = new FileInfo(filePath);
		} catch (final NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			fi = new FileInputStream(filePath);
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		origin = new BufferedInputStream(fi, BUFFER);
		ZipEntry entry = null;
		entry = new ZipEntry(relPath);
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

	public void compressFolder(final String folder) {

		compress(folder, null);
		closeZip();
	}

	public void compressFolder(final String folder, final FileFilter fileFilter, final FilterType filterType) {
		this.filterType = filterType;
		this.fileFilter = fileFilter;
		compress(folder, null);
		closeZip();
	}

}
