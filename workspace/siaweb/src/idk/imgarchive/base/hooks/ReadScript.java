/**
 * This cless reads the hook script
 */
package idk.imgarchive.base.hooks;

import idk.imgarchive.base.hooks.commands.CopyCmd;
import idk.imgarchive.base.hooks.commands.ExecCmd;
import idk.imgarchive.base.hooks.commands.XslCmd;
import idk.imgarchive.base.log4j.Log;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import xmlutils.XMLReadHelper;
import xmlutils.XMLUtil;

public class ReadScript {

	File file = null;
	CommandList forEachClusterList = new CommandList();
	CommandList forEachImageList = new CommandList();
	CommandList forEachInstanceList = new CommandList();

	CommandList forEachPartitionList = new CommandList();
	Document xmlDocument = null;

	public ReadScript(final String path, final String fileStr) throws ParseException {
		file = new File(path, fileStr);

		try {
			readSet();
		} catch (final ParserConfigurationException e) {

			final String errorStr = "Error reading script file \"" + file.getName() + "\"";
			Log.error(errorStr, e);

			throw new ParseException(errorStr, 0);
		} catch (final SAXException e) {

			final String errorStr = "Error reading script file \"" + file.getName() + "\"";
			Log.error(errorStr, e);

			throw new ParseException(errorStr, 0);
		}
	}

	void commandElement(final Element perentElem, final CommandList commandList) {

		final Element xSLCmdElem = XMLUtil.getElement(perentElem, "XSLTCmd");
		if (xSLCmdElem != null) {
			final HookCommand hookCommand = new XslCmd("XslCmd");
			/*
			 * <XSLFile>$XSL$/importimage_main.xsl</XSLFile>
			 * <XMLFile>$PartitionPath$/www/xml/$ImageName$.xml</XMLFile>
			 * <HtmlFile>$PartitionPath$/www/html/$ImageName$.html</HtmlFile>
			 */
			try {
				hookCommand.addArg(XMLReadHelper.stringValue(xSLCmdElem, "XSLFile", true));
				hookCommand.addArg(XMLReadHelper.stringValue(xSLCmdElem, "XMLFile", true));
				hookCommand.addArg(XMLReadHelper.stringValue(xSLCmdElem, "HtmlFile", true));
			} catch (final ParseException e) {
				Log.error("Invalid argument in XSLTCmd command", e);
				e.printStackTrace();
			}
			commandList.add(hookCommand);
		}
		final Element copyCmdElem = XMLUtil.getElement(perentElem, "CopyCmd");
		if (copyCmdElem != null) {
			final HookCommand hookCommand = new CopyCmd("CopyCmd");
			/*
			 * <Src>$CSS$/</Src> <Dest />
			 */
			try {
				hookCommand.addArg(XMLReadHelper.stringValue(xSLCmdElem, "Src", true));
				hookCommand.addArg(XMLReadHelper.stringValue(xSLCmdElem, "Dest", true));

			} catch (final ParseException e) {
				Log.error("Invalid argument in CopyCmd command", e);
				e.printStackTrace();
			}
			commandList.add(hookCommand);
		}
		final Element executeCmdElem = XMLUtil.getElement(perentElem, "ScriptCmd");
		if (executeCmdElem != null) {
			final HookCommand hookCommand = new ExecCmd("ExecCmd");
			/*
			 * <ScriptName>$CSS$/</ScriptName>
			 */
			try {
				hookCommand.addArg(XMLReadHelper.stringValue(xSLCmdElem, "ScriptName", true));
			} catch (final ParseException e) {

				Log.error("Invalid argument in ScriptCmd command", e);
				e.printStackTrace();
			}
			commandList.add(hookCommand);
		}

		return;
	}

	void forEachClusterElement(final Element e) {
		if (e.hasChildNodes() != true) {
			return;
		}
		final NodeList list = e.getChildNodes();
		commandElement(e, forEachClusterList);
	}

	void forEachImageElement(final Element e) {
		if (e.hasChildNodes() != true) {
			return;
		}
		final NodeList list = e.getChildNodes();
		commandElement(e, forEachImageList);
	}

	void forEachInstanceElement(final Element e) {
		if (e.hasChildNodes() != true) {
			return;
		}
		final NodeList list = e.getChildNodes();
		commandElement(e, forEachInstanceList);
	}

	void forEachPartitionElement(final Element e) {
		if (e.hasChildNodes() != true) {
			return;
		}
		final NodeList list = e.getChildNodes();
		commandElement(e, forEachPartitionList);
	}

	public CommandList getForEachClusterList() {
		return forEachClusterList;
	}

	public CommandList getForEachImageList() {
		return forEachImageList;
	}

	public CommandList getForEachInstanceList() {
		return forEachInstanceList;
	}

	public CommandList getForEachPartitionList() {
		return forEachPartitionList;
	}

	private boolean readSet() throws ParseException, ParserConfigurationException, SAXException {

		if (file.exists() == false) {

			return false;
		}
		try {
			xmlDocument = XMLUtil.parse(file.getAbsolutePath());
		} catch (final IOException e) {
			Log.error("Unable to parse Manifest" + file.getName());
			return false;
		}

		final Element rootElement = xmlDocument.getDocumentElement();
		rootElement.normalize(); // ement scriptElement =
		XMLUtil.getElement(rootElement, "IAScript");
		if (rootElement.hasChildNodes() == true) {
			final NodeList imageList = rootElement.getChildNodes();
			final int n = imageList.getLength();
			for (int i = 0; i < n; i++) {
				final Element forEachImageElem = XMLUtil.getElement(rootElement, "ForEachImage");
				if (forEachImageElem != null) {
					forEachImageElement(forEachImageElem);
				}
				final Element forEachPartitionElem = XMLUtil.getElement(rootElement, "ForEachPartition");
				if (forEachPartitionElem != null) {
					forEachPartitionElement(forEachPartitionElem);
				}
				final Element forEachClusterElem = XMLUtil.getElement(rootElement, "ForEachCluster");
				if (forEachClusterElem != null) {
					forEachClusterElement(forEachClusterElem);
				}
				final Element forEachInstanceElem = XMLUtil.getElement(rootElement, "ForEachInstance");
				if (forEachInstanceElem != null) {
					forEachInstanceElement(forEachInstanceElem);
				}
			}
		}
		return true;
	}
}

/*
 * owner = XMLReadHelper.stringValue(rootElement, "Owner", true); description =
 * XMLReadHelper.stringValue(rootElement, "Description", false); name =
 * XMLReadHelper.stringValue(rootElement, "Name", true); imageSetTypeName =
 * XMLReadHelper.stringValue(rootElement, "Type", true); // filename =
 * XMLReadHelper.stringValue(rootElement, "Filename", true); // lastModified =
 * XMLReadHelper.dateValue(rootElement, "LastModified", true); // size =
 * XMLReadHelper.longValue(rootElement, "Size", true, false); crc =
 * XMLReadHelper.longValue(rootElement, "Crc", true, true); // md5String =
 * XMLReadHelper.stringValue(rootElement, "Md5", true);
 * 
 * Element imageListElement = XMLUtil.getElement(rootElement, "ImageList"); if
 * (imageListElement.hasChildNodes() == true) { NodeList imageList =
 * imageListElement.getChildNodes(); int n = imageList.getLength(); for (int i =
 * 0; i < n; i++) { Node catNode = imageList.item(i); short type =
 * catNode.getNodeType(); String name = null; if(type == ELEMENT_NODE) { name =
 * XMLUtil.getStringValue((Element)catNode); imageSetsList.add(name); } } }
 */
