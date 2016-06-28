package idk.imgarchive.base.hooks;

import idk.archiveutils.FileUtils;
import idk.config.ConfigInfo;
import idk.imgarchive.base.log4j.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XmlHtml {
	public static void makeHtml(final String xslFile, final String xmlPath, final String htmlPath) throws ParseException,
			FileNotFoundException {
		
		Log.info("Processing \"" + xmlPath + "\" using \"" + xslFile + "\" to \"" + htmlPath + "\"");
		final TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		
		File xsl = FileUtils.fileExists(ConfigInfo.getXslPath(), xslFile);
		if (xsl == null) {
			Log.info("File not found: \"" + ConfigInfo.getXslPath() + File.pathSeparator + xslFile + "\"");
			throw new FileNotFoundException(ConfigInfo.getXslPath() + File.pathSeparator + xslFile);
		}
		File xml = FileUtils.fileExists(xmlPath);
		if (xml == null) {
			Log.info("File not found: \"" + xmlPath + "\"");
			throw new FileNotFoundException(xmlPath);
		}
		
		try {
			transformer = tFactory.newTransformer(new StreamSource(xsl.getAbsolutePath()));
		} catch (final TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			throw new ParseException(xsl.getAbsolutePath(), 0);
		}
		try {
			transformer.transform(new StreamSource(xmlPath), new StreamResult(new FileOutputStream(htmlPath)));
		} catch (final FileNotFoundException e) {
			Log.FileNotFoundException(htmlPath, e);
		} catch (final TransformerException e) {
			Log.TransformerException(xmlPath, htmlPath, e);
			throw new ParseException(xsl.getAbsolutePath(), 0);
		}
		File html = FileUtils.fileExists(htmlPath);
		if (html == null) {
			Log.info("File not found: \"" + htmlPath + "\"");
			throw new FileNotFoundException(htmlPath);
		}
	}
}
