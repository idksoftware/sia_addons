package idk.imgarchive.base.mirror;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class MirrorCopy {
	public static void copyFile(final File sourceFile, final File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	String path = null;

	public MirrorCopy() {

	}
}
