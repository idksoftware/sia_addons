package idk.archiveutils;

import java.io.File;
import java.io.FileFilter;

public class IAFilerFiles implements FileFilter {
	boolean metadata = false;

	public IAFilerFiles(final boolean metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean accept(final File arg) {

		final String name = arg.getName().toLowerCase();
		if (metadata == true) {
			if (name.endsWith("xml") == true) {
				return true;
			}
			if (name.endsWith("xmp") == true) {
				return true;
			}
		}
		final FileExtentionList rawList = FileExtentions.getRawExtentions();
		for (final String item : rawList) {
			if (name.endsWith(item)) {
				return true;
			}
		}
		final FileExtentionList imgList = FileExtentions.getImageExtentions();
		for (final String item : imgList) {
			if (name.endsWith(item)) {
				return true;
			}
		}
		return false;
	}

}
