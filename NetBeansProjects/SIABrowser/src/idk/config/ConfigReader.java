package idk.config;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.Level;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import xmlutils.XMLReadHelper;

public class ConfigReader {
	static String errorMessage = null;

	public static final String getErrorMessage() {
		return errorMessage;
	}

	static public boolean read(final String homePath) {

		if (homePath == null) {
			// if this fails then the logger cannot log to a file as its
			// destination is not known.
			errorMessage = "SEVERE Error: Cant read enviroment variable \"" + ConfigInfo.SIA_HOME
					+ "\", check configuration?";
			return false;
		}
		final String configPath = homePath + File.separator + ConfigInfo.CONFIG_REL_FROM_ROOT;

		final File configFile = new File(configPath, ConfigInfo.CONFIG_FILE_NAME);
		if (configFile.exists() == false) {
			// if this fails then the logger cannot log to a file as its
			// destination is not known.
			errorMessage = "SEVERE Error: Can not find the configuration file \"" + ConfigInfo.CONFIG_FILE_NAME
					+ "\", check configuration?\rIf a new installation run Workspace Initialise command";
			return false;
		}
		Document document = null;
		try {
			document = XMLReadHelper.decode(configFile.getAbsolutePath());
		} catch (final ParseException e) {
			errorMessage = "SEVERE Error: Can not read the configuration file \"" + ConfigInfo.CONFIG_FILE_NAME
					+ "\", check configuration?\rIf a new installation run Workspace Initialise command";
		} catch (final IOException e) {
			errorMessage = "SEVERE Error: Can not open the configuration file \"" + ConfigInfo.CONFIG_FILE_NAME
					+ "\", check configuration?\rIf a new installation run Workspace Initialise command";
		}

		new ConfigInfo();

		ConfigInfo.setInstancePath(homePath);
		ConfigInfo.setConfigPath(configPath);

		final Element root = document.getDocumentElement();
		root.normalize();

		try {
			parseFile(root);
		} catch (final ParseException e) {
			errorMessage = "SEVERE Error: Cant read configuration file \"" + ConfigInfo.CONFIG_FILE_NAME
					+ "\", check configuration?\rIf a new installation run Workspace Initialise command";
			return false;
		}
        // put back latter
	//	if (doFolderCheck() == false) {
	//		return false;
	//	}
		return true;
	}

	static public boolean doFolderCheck() {
		
		File file = null;
		String rootPath = ConfigInfo.getInstancePath();
		file = new File(rootPath);
		if (file.exists() == false) {
			errorMessage = "SEVERE Error: Can not find the application configuration folder \""
								+ rootPath + "\"";
			return false;
		}
		String tempPath = ConfigInfo.getTempPath();
		file = new File(tempPath);
		if (file.exists() == false) {
			errorMessage = "SEVERE Error: Can not find the temp folder \""
					+ tempPath + "\"";
			return false;
		}
		
		String scriptsPath = ConfigInfo.getScriptPath();
		file = new File(scriptsPath);
		if (file.exists() == false) {
			errorMessage = "SEVERE Error: Can not find the scripts folder \""
					+ scriptsPath + "\"";
			return false;
		}
				
		String logPath = ConfigInfo.getLogPath();
		file = new File(logPath);
		if (file.exists() == false) {
			errorMessage = "SEVERE Error: Can not find the log folder \""
					+ logPath + "\"";
			return false;
		}
		
		String systemPath = ConfigInfo.getSystemPath();
		file = new File(systemPath);
		if (file.exists() == false) {
			errorMessage = "SEVERE Error: Can not find the system folder \""
					+ systemPath + "\"";
			return false;
		}
		
		String xslPath = ConfigInfo.getXslPath();
		file = new File(xslPath);
		if (file.exists() == false) {
			errorMessage = "SEVERE Error: Can not find the xsl folder \""
					+ xslPath + "\"";
			return false;
		}
		
		String cssPath = ConfigInfo.getCssPath();
		file = new File(cssPath);
		if (file.exists() == false) {
			errorMessage = "SEVERE Error: Can not find the css folder \""
					+ cssPath + "\"";
			return false;
		}
		
		String toolPath = ConfigInfo.getToolPath();
		file = new File(toolPath);
		if (file.exists() == false) {
			errorMessage = "SEVERE Error: Can not find the tool folder \""
					+ toolPath + "\"";
			return false;
		}
		
		
		
		String schemaPath = ConfigInfo.getSchemaPath();
		file = new File(schemaPath);
		if (file.exists() == false) {
			errorMessage = "SEVERE Error: Can not find the schema folder \""
					+ schemaPath + "\"";
			return false;
		}
		
		String pluginPath = ConfigInfo.getPluginPath();
		file = new File(pluginPath);
		if (file.exists() == false) {
			errorMessage = "SEVERE Error: Can not find the plugin folder \""
					+ pluginPath + "\"";
			return false;
		}
		return true;
	}
	
	static void parseFile(final Element root) throws ParseException {
		final String instancePath = XMLReadHelper.stringValue(root, "InstancePath", true);
		ConfigInfo.setInstancePath(instancePath);
                
                final String workspacePath = XMLReadHelper.stringValue(root, "WorkspacePath", true);
		ConfigInfo.setWorkspacePath(workspacePath);

		final String tempPath = XMLReadHelper.stringValue(root, "TempPath", true);
		ConfigInfo.setTempPath(tempPath);

		final String scriptsPath = XMLReadHelper.stringValue(root, "ScriptsPath", true);
		ConfigInfo.setScriptPath(scriptsPath);

		final Level logLevel = Level.toLevel(XMLReadHelper.stringValue(root, "LogLevel", true));
		ConfigInfo.setLogLevel(logLevel);

		final String logPath = XMLReadHelper.stringValue(root, "LogPath", true);
		ConfigInfo.setLogPath(logPath);

		final String systemPath = XMLReadHelper.stringValue(root, "SystemPath", true);
		ConfigInfo.setSystemPath(systemPath);

		final String xslPath = XMLReadHelper.stringValue(root, "XslPath", true);
		ConfigInfo.setXslPath(xslPath);

		final String cssPath = XMLReadHelper.stringValue(root, "CssPath", true);
		ConfigInfo.setCssPath(cssPath);

		final String toolPath = XMLReadHelper.stringValue(root, "ToolPath", true);
		ConfigInfo.setToolPath(toolPath);

		final String schemaPath = XMLReadHelper.stringValue(root, "SchemaPath", true);
		ConfigInfo.setSchemaPath(schemaPath);

		final String pluginPath = XMLReadHelper.stringValue(root, "PluginPath", true);
		ConfigInfo.setPluginPath(pluginPath);
		
		/*
		 * boolean removeProcessedFiles = XMLReadHelper.booleanValue(root,
		 * "RemoveProcessedFiles", false, false);
		 * ConfigInfo.setRemoveProcessedFiles(removeProcessedFiles);
		 * 
		 * 
		 * Move into Instance config int imagesPerPartition =
		 * XMLReadHelper.integerValue(root, "ImagesPerPartition", true, false);
		 * ConfigInfo.setImagesPerPartition(imagesPerPartition);
		 */
	}
}
