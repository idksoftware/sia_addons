package idk.Variant;

import siawebapp.ExitCodes;



@SuppressWarnings("serial")
public class InvalidFormatException extends Exception {
	String msg = null;
	String detailedMessage = null;
	ExitCodes exitCode = null;

	public InvalidFormatException() {
	}

	public InvalidFormatException(final int i, final String detailedMessage) {
		super();
		this.detailedMessage = detailedMessage;
	}

	@Override
	public String toString() {
		// Thread.dumpStack();
		return msg;
	}

}


