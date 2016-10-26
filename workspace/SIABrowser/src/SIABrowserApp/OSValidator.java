package SIABrowserApp;

public class OSValidator {
	public enum eOSType {
		OS_Linux, OS_Mac, OS_Solaris, OS_UNIX, OS_Unknown, OS_Windows
	}

	private String os = null;
	private eOSType OSType = eOSType.OS_Unknown;

	public OSValidator() {
		os = System.getProperty("os.name").toLowerCase();
		// windows
		if (os.indexOf("win") >= 0) {
			OSType = eOSType.OS_Windows;
		} else if (os.indexOf("lin") >= 0) {
			OSType = eOSType.OS_Linux;
		} else if (os.indexOf("mac") >= 0) {
			OSType = eOSType.OS_Mac;
		} else if (os.indexOf("sol") >= 0) {
			OSType = eOSType.OS_Solaris;
		} else if (os.indexOf("hp-ux") >= 0 || os.indexOf("aix") >= 0 || os.indexOf("freebsd") >= 0 || os.indexOf("irix") >= 0
				|| os.indexOf("digital") >= 0)

		{
			OSType = eOSType.OS_UNIX;
		} else {
			OSType = eOSType.OS_Unknown;
		}
	}

	public String defaultApplicationPath() {
		if (OSType == eOSType.OS_Windows) {
			return System.getenv("ProgramFiles");
		} else if (OSType == eOSType.OS_Mac) {
			return "/Applications";
		} else {
			return "/usr/local/bin/";
		}
	}
	
	public String defaultProgramDataPath() {
		if (OSType == eOSType.OS_Windows) {
			return System.getenv("ProgramData");
		} else if (OSType == eOSType.OS_Mac) {
			return "/Applications";
		} else {
			return "/usr/local/bin/";
		}
	}
		
	public String defaultUserPath() {
		if (OSType == eOSType.OS_Windows) {
			return System.getenv("HOMEDIR");
		} else if (OSType == eOSType.OS_Mac) {
			return System.getenv("HOME");
		} else if (OSType == eOSType.OS_Linux) {
			return "/usr/local/bin/";
		}
		return null;
	}
	
	public String defaultUserName() {
		if (OSType == eOSType.OS_Windows) {
			return System.getenv("USERNAME");
		} else if (OSType == eOSType.OS_Mac) {
			return System.getenv("HOME");
		} else {
			return "/usr/local/bin/";
		}
	}
	
	public String getOSString() {
		return os;
	}

	public eOSType getOSType() {
		return OSType;
	}
}
