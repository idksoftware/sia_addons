package SIABrowserApp;

public enum ExitCodes {
	ExtentionsFileNotFound("Extentions file not found"),
	CanNotReadClusterFile("Cannot read cluster file"),
	ClusterFileNotFound("Cluster file not found"),
	DefaultNotFound("Default not found"),
	DefaultNotSet("Default not set"),
	InstatnceNameExists("Instatnce name exists"),
	NoError("No Error"),
	NoInstancesFound("No Instances found"),
	SystemPathNotSpecified("System path not specified"),
	CannotCreateSystemPath("Cannot create system path"),
	ValidTag("Valid tag"),
	CantReadEnviromentVariable("Can’t read enviroment variable"),
	Ok("No errors"),
	NoCommandFound("No command found"),
	ErrorReadingCommandFile("Error reading command file"),
	InvalidCommandFound("Invalid command found"),
	NoConfigFile("No configuation file found"),
	ErrorReadingConfigFile("File error reading config file"),
	NeedToCreateArchive("Need to create archive"),
	CommandFailed("Command failed"),
	WorkspaceProblem("Workspace problem");

	/*
	 * private static ErrorCode errorCode = ErrorCode.NoError;
	 * 
	 * 
	 * public static synchronized final ErrorCode getErrorCode() { return
	 * errorCode; }
	 * 
	 * 
	 * public static synchronized final void setErrorCode(ErrorCode errorCode) {
	 * ErrorCode.errorCode = errorCode; }
	 */
	private String value;

	ExitCodes(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return getValue();
	}

	/*
	 * public static ErrorCode getEnum(String value) { if (value != null &&
	 * value.equals(StartHere.getValue())) { return StartHere; } else if (value
	 * != null && value.equals(StopHere.getValue())) { return StartHere; } else
	 * { throw new IllegalArgumentException(); } }
	 */

}
