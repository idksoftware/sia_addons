package SIABrowserApp;

public class ApplicationError {
	public enum ErrorCode {
		CanNotReadClusterFile, ClusterFileNotFound, DefaultNotFound, DefaultNotSet, InstatnceNameExists, NoError, NoInstancesFound, SystemPathNotSpecified, ValidTag,
	}

	private static ErrorCode errorCode = ErrorCode.NoError;

	/**
	 * @return the errorCode
	 */
	public static synchronized final ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public static synchronized final void setErrorCode(final ErrorCode errorCode) {
		ApplicationError.errorCode = errorCode;
	}

}
