package idk.imgarchive.base.mirror;

import idk.archiveutils.FileCopier;
import idk.imgarchive.base.mirror.MirrorManager.MirrorType;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class MirrorItem {
	protected String hostname = null;
	protected String id = null;
	protected MirrorType mirrorType = MirrorType.Unknown;
	protected String password = null;
	protected String path = null;
	protected String userName = null;

	public void CreateMirror(final String tempPath) throws NumberFormatException, IOException, ParseException,
			TransformerException, ParserConfigurationException {

		final FileLister fileLister = new FileLister(MirrorManager.primaryMirrorPath + File.separator + "DCIM");
		if (fileLister.Process() == false) {
			return;
		}
		final File dir = new File(tempPath);
		File tmpFile = null;
		try {
			tmpFile = File.createTempFile("cpy", ".xml", dir);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileLister.writeXmlFile(tmpFile.getAbsolutePath());
		final FileCopier fileCopier = new FileCopier(MirrorManager.primaryMirrorPath + File.separator + "DCIM", path
				+ File.separator + "DCIM", tmpFile.getAbsolutePath());
		if (fileCopier.process()) {
			return;
		}
		return;
	}
}
