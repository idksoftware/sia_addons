package idk.archiveutils;

import static org.w3c.dom.Node.ELEMENT_NODE;
import static org.w3c.dom.Node.TEXT_NODE;
import idk.imgarchive.base.log4j.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xmlutils.DateUtils;
import xmlutils.XMLUtil;

public final class FileCopier {
	public static void copyFile(final File sourceFile, final File destFile) throws IOException {
		if (!destFile.exists()) {
			try {
				if (!destFile.createNewFile()) {
					throw new IOException("Unable to create new file " + destFile.getName());
				}
			} catch (final IOException e) {
				Log.IOException("Unable to create new file " + destFile.getName(), e);
			}

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

	String destRoot = null;
	String sourceRoot = null;
	Document xmlDoc = null;

	String xmlFile = null;

	public FileCopier(final String sourceRoot, final String destRoot, final String xmlFile) {
		this.sourceRoot = sourceRoot;
		this.destRoot = destRoot;
		this.xmlFile = xmlFile;
	}

	public boolean process() throws IOException, ParseException {

		try {
			xmlDoc = XMLUtil.parse(xmlFile);
		} catch (final IOException e2) {
			Log.IOException(xmlFile, e2);
		} catch (final ParseException e2) {
			Log.ParseException(xmlFile, e2);
		}

		final Element rootElement = xmlDoc.getDocumentElement();
		if (rootElement.hasChildNodes()) {
			final NodeList pmList = rootElement.getChildNodes();
			final int n = pmList.getLength();
			for (int i = 0; i < n; i++) {
				final Node fileNode = pmList.item(i);
				final short type = fileNode.getNodeType();
				String name = null;
				Date lastModified = null;

				if (type == ELEMENT_NODE) {

					final Node childNode = fileNode.getFirstChild();
					if (childNode.getNodeType() == TEXT_NODE) {
						name = childNode.getTextContent();
					}

					final Element LastModified = XMLUtil.getElement((Element) fileNode, "ModDate");
					if (LastModified != null) {
						final String lastModifiedString = LastModified.getTextContent();
						try {
							lastModified = DateUtils.parseDDMMYYYY(lastModifiedString);
						} catch (final ParseException e) {
							Log.getLogger().error("", e);
						}
					}
					final String sourceFilePath = sourceRoot + name;
					final String destFilePath = destRoot + name;
					final File sourceFile = new File(sourceFilePath);
					final File destFile = new File(destFilePath);
					try {
						final File destParentDir = new File(destFile.getParent());
						if (destParentDir.mkdirs() == false) {
							System.out.println("Mkdirs failed");
						}
						copyFile(sourceFile, destFile);
						try {
							if (lastModified != null) {
								destFile.setLastModified(lastModified.getTime());
							}
						} catch (final Exception e) {
							e.printStackTrace();
						}

					} catch (final IOException e1) {
						Log.error("", e1);
						return false;
					}
				}

			}
		}
		return true;
	}
}
