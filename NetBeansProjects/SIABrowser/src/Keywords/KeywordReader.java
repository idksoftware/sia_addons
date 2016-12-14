package Keywords;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import SIABrowser.Metadata;
import idk.config.ConfigInfo;
import xmlutils.XMLReadHelper;
import xmlutils.XMLUtil;

public class KeywordReader {
	static String errorMessage = null;

	public static final String getErrorMessage() {
		return errorMessage;
	}
	static public KeywordSet read(final String xmlPath) {

		final File xmlFile = new File(xmlPath);
		if (xmlFile.exists() == false) {
			// if this fails then the logger cannot log to a file as its
			// destination is not known.
			errorMessage = "SEVERE Error: Can not find the configuration file \"" + ConfigInfo.IMAGE_FILE_NAME
					+ "\", check configuration?\rIf a new installation run Workspace Initialise command";
			return null;
		}
		Document document = null;
		try {
			document = XMLReadHelper.decode(xmlFile.getAbsolutePath());
		} catch (final ParseException e) {
			errorMessage = "SEVERE Error: Can not read the configuration file \"" + ConfigInfo.IMAGE_FILE_NAME
					+ "\", check configuration?\rIf a new installation run Workspace Initialise command";
		} catch (final IOException e) {
			errorMessage = "SEVERE Error: Can not open the configuration file \"" + ConfigInfo.IMAGE_FILE_NAME
					+ "\", check configuration?\rIf a new installation run Workspace Initialise command";
		}

		KeywordSet keywordSet = new KeywordSet();

		final Element root = document.getDocumentElement();
		root.normalize();

		try {
			parseFile(keywordSet, root);
		} catch (final ParseException e) {
			errorMessage = "SEVERE Error: Cant read configuration file \"" + ConfigInfo.IMAGE_FILE_NAME
					+ "\", check configuration?\rIf a new installation run Workspace Initialise command";
			return null;
		}

		return keywordSet;
	}

	
	
	static void parseFile(KeywordSet keywordSet, final Element root) throws ParseException {
		Element element = XMLUtil.getElement(root, "AssetProperties");

	}
}
