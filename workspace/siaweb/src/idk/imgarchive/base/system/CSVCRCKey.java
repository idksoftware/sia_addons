/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idk.imgarchive.base.system;

import java.util.StringTokenizer;

/**
 * 
 * @author Iain Ferguson
 */
public class CSVCRCKey {
	public long crc = 0;
	public long fileIndex = 0;
	public String md5 = null;

	public CSVCRCKey(final String row) {
		final StringTokenizer st = new StringTokenizer(row, "|");

		crc = Long.parseLong(st.nextToken(), 16);
		md5 = st.nextToken();
		fileIndex = Long.parseLong(st.nextToken(), 16);
	}

	// Lookup Constructor
	public CSVCRCKey(final String md5, final long crc) {
		this.crc = crc;
		this.md5 = md5;
	}

	// Update Constructor
	public CSVCRCKey(final String md5, final long crc, final long fileIndex) {
		this.crc = crc;
		this.md5 = md5;
		this.fileIndex = fileIndex;
	}

	// / The compare function Compares the Key row with the one passed.
	public int Compare(final String row) {
		final StringTokenizer st = new StringTokenizer(row, "|");
		final String[] items = new String[st.countTokens()];

		final long rowCRC = Long.parseLong(items[0], 16);

		if (crc == rowCRC) {
			// Compare MD5
			if (md5.matches(items[1]) == true) {
				fileIndex = Long.parseLong(items[2], 16);
				return 0;
			}
		}
		if (crc > rowCRC) {
			return -1;
		}
		return 1;
	}

	public long GetKey() {
		return crc;
	}

}
