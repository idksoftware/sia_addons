/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idk.imgarchive.base.system;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * 
 * @author Iain Ferguson
 */
public class FileHash {
	static class CSVIndex {
		private final String file;
		private File idxFile = null;
		private boolean isRead = false;
		private final ArrayList<CSVCRCKey> lines = new ArrayList<CSVCRCKey>();
		private final String path;

		public CSVIndex(final String path, final String file) {
			this.path = path;
			this.file = file;

		};

		long find(final CSVCRCKey key) {

			if (!readFile()) {
				return 0;
			}

			for (final Object element : lines) {
				try {
					final CSVCRCKey item = (CSVCRCKey) element;
					item.GetKey();
					if (key.crc == item.crc && key.md5.contentEquals(item.md5)) {
						return item.fileIndex;
					}
				} catch (final NoSuchElementException e) {

					return -1;
				}

			}
			return 0;
		}

		boolean insert(final CSVCRCKey key) {
			if (!isRead) {
				readFile();
			}
			if (!lines.add(key)) {
				return false;
			}
			writeFile();
			return true;
		}

		boolean readFile() {
			idxFile = new File(path, file);
			if (!idxFile.exists()) {
				return false;
			}
			try {
				// Open the file that is the first

				// command line parameter
				final FileInputStream fstream = new FileInputStream(idxFile);
				// Get the object of DataInputStream
				final DataInputStream in = new DataInputStream(fstream);
				final BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				// Read File Line By Line
				while ((strLine = br.readLine()) != null) {
					final CSVCRCKey key = new CSVCRCKey(strLine);
					lines.add(key);
				}
				isRead = true;
				// Close the input stream
				in.close();
				br.close();
			} catch (final Exception e) {// Catch exception if any
				System.err.println("Error: " + e.getMessage());
				return false;
			}

			return true;
		}

		boolean writeFile() {
			try {
				idxFile = new File(path, file);
				final FileWriter outFile = new FileWriter(idxFile);
				final PrintWriter out = new PrintWriter(outFile);

				for (final CSVCRCKey item : lines) {
					try {
						item.GetKey();
						out.println(Long.toHexString(item.crc) + "|" + item.md5 + "|" + Long.toHexString(item.fileIndex));
					} catch (final NoSuchElementException e) {
						return false;
					}

				}
				out.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}

			return false;
		}

	}

	private CSVIndex curCVSIndex = null;
	private final String rootPath;

	public FileHash(final String path) {
		rootPath = path;
	}

	public boolean createHash() {
		return true;
	}

	/*
	 * bool Lookup( ARG_KEY key, VALUE& rValue ) const;
	 * 
	 * Return Value - Nonzero if the element was found; otherwise 0.
	 * 
	 * Parameters
	 * 
	 * ARG_KEY - Template parameter specifying the type of the key value.
	 * 
	 * key - Specifies the key that identifies the element to be looked up.
	 * 
	 * VALUE - Specifies the type of the value to be looked up. rValue -
	 * Receives the looked-up value.
	 */
	public long lookup(final CSVCRCKey key) {
		long dirName = key.crc;
		long fileName = key.crc;
		long contName = key.crc;

		contName &= 0x00000FFF;
		fileName &= 0x00FFF000;
		fileName >>= 4 * 3;
		dirName >>= 4 * 6;

		final String dirNameStr = Long.toHexString(dirName);
		final String fileNameStr = Long.toHexString(fileName) + ".csv";
		// String contNameStr = Long.toHexString(contName);

		final File rootFile = new File(rootPath);

		if (!rootFile.exists()) {
			if (!rootFile.mkdirs()) {
				return -1; // Root path does not exist
			}
		}

		// Create the control directory
		final File hashFile = new File(rootPath, "data");
		if (!hashFile.exists()) {
			if (!hashFile.mkdirs()) {
				return -1; // An Index system exists
			}

		}

		final File dirNameFile = new File(hashFile.getAbsolutePath(), dirNameStr);
		if (!dirNameFile.exists()) {
			if (!dirNameFile.mkdirs()) {
				return -1; // An Index system exists
			}

		}

		curCVSIndex = new CSVIndex(dirNameFile.getAbsolutePath(), fileNameStr);

		return curCVSIndex.find(key);
	}

	public int lookupAndInsert(final CSVCRCKey key) {

		if (lookup(key) == 0) {
			curCVSIndex.insert(key);
		}
		return 0;
	}

	/*
	 * int GetCount( ) const;
	 * 
	 * Return Value
	 * 
	 * The number of elements.
	 * 
	 * Remarks
	 * 
	 * Call this member function to retrieve the number of elements in the map.
	 */

	/*
	 * long GetCount() { return 0; }
	 */

	/*
	 * void SetAt(unsigned long key, ARG_VALUE newValue );
	 * 
	 * 
	 * key - Specifies the key of the new element.
	 * 
	 * newValue - Specifies the value of the new element.
	 * 
	 * Remarks
	 * 
	 * The primary means to insert an element in a map. First, the key is looked
	 * up. If the key is found, then the corresponding value is changed;
	 * otherwise a new key-value pair is created.
	 */
	void setAt(final long key, final long newValue) {

	};

}
