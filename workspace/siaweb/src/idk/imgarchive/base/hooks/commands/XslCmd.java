package idk.imgarchive.base.hooks.commands;


import idk.imgarchive.base.hooks.HookCommand;
import idk.imgarchive.base.hooks.HookCommand.CommandType;

import java.text.ParseException;

import org.w3c.dom.Element;

import xmlutils.XMLReadHelper;
import xmlutils.XMLUtil;

/**
 * 
 * @author iain
 * 
 * 
 * 
 *         <?xml version="1.0" encoding="ISO-8859-1" ?> <IAScript>
 *         <OnIndexCatalog> <Execute> <Command> <Name>MakeHtml</Name>
 *         <Arguments> <XSLFile>$(InstanceConfig)/IndexCatalog.xsl</XSLFile>
 *         <XMLFile>$(XMLFile)</XMLFile> <HTMLFile>$(HTMLFile)</HTMLFile>
 *         </Arguments> </Command> </Execute> </OnIndexCatalog> </IAScript>
 * 
 */
public class XslCmd extends HookCommand {

	String xslPath = null;
	String outpathPath = null;
	String inputPath = null;

	public XslCmd(final String name) {
		super(name);
		setCommandType(CommandType.XSLTCmd);
	}

	public int xslHook() {
		/*
		 * String htmlPath = item.getPath() + File.separator + "index.html";
		 * String xslPath = ConfigInfo.getConfigPath() + File.separator +
		 * "partition.xsl"; String styleSheetConfigPath =
		 * ConfigInfo.getConfigPath() + File.separator + "partition.css"; String
		 * styleSheetDestPath = xmlPath + File.separator + "style.css";
		 * XmlHtml.makeHtml(xslPath, fullXmlPath, htmlPath);
		 * FileUtils.copyIfNotExist(styleSheetDestPath, styleSheetConfigPath);
		 */
		return 0;
	}

	@Override
	public void read(final Element rootElement) throws NumberFormatException, ParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	public static void read(final Element body, final AssetPropertiesInfo assetPropertiesInfo) throws ParseException {
		final Element assetPropertiesElem = XMLUtil.getElement(body, "AssetProperties");
		if (assetPropertiesElem == null) {
			return;
		}
		assetPropertiesInfo.caption = XMLReadHelper.stringValue(assetPropertiesElem, "Caption", false);
		assetPropertiesInfo.category = XMLReadHelper.stringValue(assetPropertiesElem, "Category", false);
		assetPropertiesInfo.hardCopyLocation = XMLReadHelper.stringValue(assetPropertiesElem, "HardCopyLocation", false);
		assetPropertiesInfo.language = XMLReadHelper.stringValue(assetPropertiesElem, "Language", false);
		assetPropertiesInfo.type = XMLReadHelper.stringValue(assetPropertiesElem, "Type", false);
		assetPropertiesInfo.userComment = XMLReadHelper.stringValue(assetPropertiesElem, "UserComment", false);
		assetPropertiesInfo.editor = XMLReadHelper.stringValue(assetPropertiesElem, "Editor", false);
	}
	*/
}
