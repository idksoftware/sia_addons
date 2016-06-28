/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idk.imgarchive.base.cataloger;

import idk.imgarchive.base.log4j.Log;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.w3c.dom.Document;

import xmlutils.XMLUtil;

/**
 * 
 * @author Iain Ferguson
 */
public class ReadFile {

	private final File inputFile;

	/**
	 * The DOM representation of the metadata.
	 */
	protected Document xmpDocument;

	public ReadFile(final File ifile) {
		inputFile = ifile;
	}

	private void decode() throws ParseException, IOException {
		try {
			xmpDocument = XMLUtil.parse(inputFile.getAbsolutePath());
		} catch (final IOException e2) {
			Log.IOException(inputFile.getName(), e2);
		} catch (final ParseException e2) {
			Log.ParseException(inputFile.getName(), e2);
		}
	}

	public Document getDocument() {
		return xmpDocument;
	}

	public void process() throws ParseException, IOException {
		decode();
	}

}