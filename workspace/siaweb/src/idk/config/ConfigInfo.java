/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idk.config;

import java.io.File;

import org.apache.log4j.Level;


/**
 * This class is used to provide configurations information to all classes
 * within the application that need this information. This information is read
 * once out of a file. but maybe further updated within the application. There
 * is only one copy of this information therefore it is a static class.
 * 
 * @author Iain Ferguson
 */
public final class ConfigInfo {
	static VersionType versionType = VersionType.SingleUser;

	static public final String SIA_HOME = "SIA_HOME";
	static public final String IMGARCHIVE_USERHOME = "IMGARCHIVE_USERHOME";

	static public final String IMGARCHIVE = "imgarchive";
	static public final String IMGARCHIVE_WIN = "IDK ImgArchive";

	static public final String HIDDEN_FOLDER = ".iga";
	static public final String EXPORT_FILE = "exportFile.xml";
	static public final String TEMPLATES_FOLDER = "Templates";
	static public final String CONFIG_DIR_NAME = "config";
	static public final String CONFIG_FILE_NAME = "config.xml";
	static public final String CONFIG_REL_FROM_ROOT = "config";
	static public final String DATA_FOLDER = "config";
	static public final String DEFAULT_SITE_CONFIG_FILE_NAME = "defaultsite.xml";
	static public final String FILE_EXTENTION_FILE = "ext.xml";
	static public final String IMAGEINFO_FILE_SCHEMA_NAME = "infofile.xsd";
	static public final String INSTANCES_FILE_NAME = "instances.xml";
	static public final String LOG4J_FILE_NAME = "log4j.xml";
	static public final String PARTITION_SITE_CONFIG_FILE = "partitionsite.xml";
	static public final String SYSTEM_DIR_NAME = "system";

	static public final String WEBSITE_CONFIG_FILE_NAME = "website.xml";

	static private String configPath = null;
	static private String currentClusterPath = null;
	static private String dataPath = null;
	static private String dupPath = null;
	static private int imagesPerPartition = 100;
	static private String instancePath = null;
	static private Level logLevel = Level.ALL;
	static private String logPath = null;
	static private boolean pairImages = false;
	static private boolean removeProcessedFiles = false;
	static private String rootPath = null;
	static private String schemaPath = null;
	static private String scriptPath = null;
	static private String systemPath = null;
	static private String tempPath = null;
	static private String toolPath = null;
	static private String pluginPath = null;
	static private String usersPath = null;
	static private String websitePath = null;
	static private String xslPath = null;
	static private String cssPath = null;

	static private String singleUserName = null;

	/**
	 * 
	 * @return
	 */
	static public String getConfigPath() {
		return configPath;
	}

	/**
	 * @return the instancePath
	 */
	public static String getCurrentClusterPath() {
		return currentClusterPath;
	}

	/**
	 * 
	 * @return
	 */
	static public String getDataPath() {
		return dataPath;
	}

	/**
	 * 
	 * @return
	 */
	static public String getDupPath() {
		return dupPath;
	}

	/**
	 * 
	 * @return
	 */
	static public int getImagesPerPartition() {
		return imagesPerPartition;
	}

	/**
	 * @return the instancePath
	 */
	public static String getInstanceConfigPath() {
		return instancePath + File.separator + "config";
	}

	/**
	 * @return the instancePath
	 */
	public static String getInstancePath() {
		return instancePath;
	}

	/**
	 * @return the instancePath
	 */
	public static String getInstanceSystemPath() {
		return instancePath + File.separator + "system";
	}

	/**
	 * @return the instancePath
	 */
	public static String getInstanceWWWPath() {
		return instancePath + File.separator + "www";
	}

	/**
	 * 
	 * @return
	 */
	static public Level getLogLevel() {
		return logLevel;
	}

	/**
	 * 
	 * @return
	 */
	static public String getLogPath() {
		return logPath;
	}

	/**
	 * @return the pluginPath
	 */
	public static final String getPluginPath() {
		return pluginPath;
	}

	/**
	 * @param pluginPath the pluginPath to set
	 */
	public static final void setPluginPath(String pluginPath) {
		ConfigInfo.pluginPath = pluginPath;
	}
	/**
	 * 
	 * @return
	 */
	static public boolean getPairImages() {
		return pairImages;
	}

	/**
	 * 
	 * @return
	 */
	static public boolean getRemoveProcessedFiles() {
		return removeProcessedFiles;
	}

	/**
	 * 
	 * @return
	 */
	static public String getRootPath() {
		return rootPath;
	}

	/**
	 * 
	 * @return
	 */
	static public String getSchemaPath() {
		return schemaPath;
	}

	/**
	 * @return the scriptPath
	 */
	public static String getScriptPath() {
		return scriptPath;
	}

	/**
	 * @return the singleUserName
	 */
	public static final String getSingleUserName() {
		return singleUserName;
	}

	public static final void setSingleUserName(final String name) {
		singleUserName = name;
	}

	/**
	 * @return the versionType
	 */
	public static final VersionType getVersionType() {
		return versionType;
	}

	/**
	 * @param versionType
	 *            the versionType to set
	 */
	public static final void setVersionType(final VersionType versionType) {
		ConfigInfo.versionType = versionType;
	}

	public static String getUserTemplatePath() {
		if (versionType == VersionType.SingleUser) {
			return usersPath + File.separator + "template";
		}
		return usersPath + File.separator + singleUserName + File.separator + "template";
	}

	public static String geteUserExportPath() {
		if (versionType == VersionType.SingleUser) {
			return usersPath + File.separator + "export";
		}
		return usersPath + File.separator + singleUserName + File.separator + "export";
	}

	public static String getUserImportPath() {
		if (versionType == VersionType.SingleUser) {
			return usersPath + File.separator + "import";
		}
		return usersPath + File.separator + singleUserName + File.separator + "import";
	}

	public static String getUserInboxPath() {
		if (versionType == VersionType.SingleUser) {
			return usersPath + File.separator + "inbox";
		}
		return usersPath + File.separator + singleUserName + File.separator + "inbox";
	}

	public static String getUserOutboxPath() {
		if (versionType == VersionType.SingleUser) {
			return usersPath + File.separator + "outbox";
		}
		return usersPath + File.separator + singleUserName + File.separator + "outbox";
	}

	public static String getUserHistoryPath() {
		if (versionType == VersionType.SingleUser) {
			return usersPath + File.separator + "history";
		}
		return usersPath + File.separator + singleUserName + File.separator + "history";
	}
	
	public static String getUserDupPath() {
		if (versionType == VersionType.SingleUser) {
			return usersPath + File.separator + "duplicates";
		}
		return usersPath + File.separator + singleUserName + File.separator + "duplicates";
	}
	/**
	 * @return the systemPath
	 */
	static public String getSystemPath() {
		return systemPath;
	}

	/**
	 * 
	 * @return
	 */
	static public String getTempPath() {
		return tempPath;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	static public String getToolPath() {
		return toolPath;
	}

	public static String getUserInboxPath(final String user) {
		return usersPath + File.separator + singleUserName + File.separator + user + File.separator + "inbox";
	}

	public static String getUserOutboxPath(final String user) {
		return usersPath + File.separator + user + File.separator + "outbox";
	}

	

	/**
	 * 
	 * @param p
	 */
	public static String getUserDupPath(final String user) {
		
		return usersPath + File.separator + user + File.separator + "duplicates";
	}

	/**
	 * 
	 * @return
	 */
	static public String getUsersPath() {
		return usersPath;
	}

	/**
	 * 
	 * @return
	 */
	// static public String getImportPath() {
	// return importPath;
	// }
	/**
	 * 
	 * @param p
	 */
	// static public void setImportPath(String p) {
	// importPath = p;
	// }
	/**
	 * 
	 * @return
	 */
	static public String getWebsitePath() {
		return websitePath;
	}

	/**
	 * @return the xslPath
	 */
	public static String getXslPath() {
		return xslPath;
	}

	/**
	 * @return the xslPath
	 */
	public static String getCssPath() {
		return cssPath;
	}

	/**
	 * 
	 * @param p
	 */
	static public void setConfigPath(final String p) {
		configPath = p;
	}

	/**
	 * @param instancePath
	 *            the instancePath to set
	 */
	public static void setCurrentClusterPath(final String path) {
		currentClusterPath = path;
	}

	/**
	 * 
	 * @param i
	 */
	static public void setImagesPerPartition(final int i) {
		imagesPerPartition = i;
	}

	/**
	 * @param instancePath
	 *            the instancePath to set
	 */
	public static void setInstancePath(final String instancePath) {
		ConfigInfo.instancePath = instancePath;
	}

	/**
	 * 
	 * @param i
	 */
	static public void setLogLevel(final Level i) {
		logLevel = i;
	}

	/**
	 * 
	 * @param usersPath
	 */
	static public void setLogPath(final String path) {
		logPath = path;
	}

	/**
	 * 
	 * @param b
	 */
	static public void setPairImages(final boolean b) {
		pairImages = b;
	}

	/**
	 * 
	 * @param b
	 */
	static public void setRemoveProcessedFiles(final boolean b) {
		removeProcessedFiles = b;
	}

	/**
	 * 
	 * @param p
	 */
	static public void setRootPath(final String p) {
		rootPath = p;
	}

	/**
	 * 
	 * @param schemaPath
	 */
	static public void setSchemaPath(final String p) {
		schemaPath = p;
	}

	/**
	 * @param scriptPath
	 *            the scriptPath to set
	 */
	public static void setScriptPath(final String scriptPath) {
		ConfigInfo.scriptPath = scriptPath;
	}

	/**
	 * @param systemPath
	 *            the systemPath to set
	 */
	static public void setSystemPath(final String p) {
		systemPath = p;
	}

	/**
	 * 
	 * @param p
	 */
	static public void setTempPath(final String p) {
		tempPath = p;
	}

	/**
	 * 
	 * @param p
	 */
	static public void setToolPath(final String p) {
		toolPath = p;
	}

	/**
	 * 
	 * @param usersPath
	 */
	static public void setUsersPath(final String path) {
		usersPath = path;
	}

	/**
	 * 
	 * @param p
	 */
	static public void setWebsitePath(final String p) {
		websitePath = p;
	}

	/**
	 * @param xslPath
	 *            the xslPath to set
	 */
	public static void setXslPath(final String xslPath) {
		ConfigInfo.xslPath = xslPath;
	}

	/**
	 * @param xslPath
	 *            the xslPath to set
	 */
	public static void setCssPath(final String cssPath) {
		ConfigInfo.cssPath = cssPath;
	}

	/**
	 * 
	 * @param p
	 */
	static public void setDataPath(final String p) {
		dataPath = p;
	}
	/**
     * 
     */
	public ConfigInfo() {

	}
}
