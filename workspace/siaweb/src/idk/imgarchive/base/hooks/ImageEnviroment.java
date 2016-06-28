package idk.imgarchive.base.hooks;

import idk.config.ConfigInfo;

import java.util.Map;

public class ImageEnviroment {
	public static void set(final Map<String, String> environ) {

		environ.put("IMGA_ROOT", ConfigInfo.getRootPath());
		environ.put("IMGA_TEMP", ConfigInfo.getTempPath());
		environ.put("IMGA_TOOL", ConfigInfo.getToolPath());
		environ.put("IMGA_CONFIG", ConfigInfo.getConfigPath());
		// environ.put("IMGA_DATA", ConfigInfo.getDataPath());
		environ.put("IMGA_USER", ConfigInfo.getUsersPath());
		environ.put("IMGA_SCHEMA", ConfigInfo.getSchemaPath());
		environ.put("IMGA_XSL", ConfigInfo.getXslPath());
		environ.put("IMGA_SYS", ConfigInfo.getSystemPath());
		environ.put("IMGA_LOG", ConfigInfo.getLogPath());

	}

	// ConfigInfo
}
