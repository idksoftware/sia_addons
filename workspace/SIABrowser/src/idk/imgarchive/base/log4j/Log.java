package idk.imgarchive.base.log4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.Logger;

public class Log {
	public enum LogLevel {
		SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST,
	}

	private static Logger logger = null;

	public final static void CopyFileException(final File path2CopyFile, final File pathFile, final Throwable t)
			throws FileNotFoundException {
		logger.fatal("Unable to copy \"" + path2CopyFile.getName() + "\" to \"" + pathFile.getName() + "\"", t);
		final FileNotFoundException thrown = new FileNotFoundException(t.getMessage());
		throw thrown;
	}

	public static final void debug(final String message) {
		logger.debug(message);
	}

	public static final void debug(final String message, final Throwable t) {
		logger.debug(message, t);
	}

	public static final void error(final String message) {
		logger.error(message);
	}

	public static final void error(final String message, final Throwable t) {
		logger.error(message, t);
	}

	public static final void fatal(final String message) {
		logger.fatal(message);
	}

	public static final void fatal(final String message, final Throwable t) {
		logger.fatal(message, t);
	}
	public final static void FileNotFound(final String message) {
		logger.error("Unable to find file \"" + message + "\"");
	}
	public final static void FileNotFoundException(final String filename, final Throwable t) throws FileNotFoundException {
		logger.fatal("Unable to find file \"" + filename + "\"", t);
		final FileNotFoundException thrown = new FileNotFoundException(t.getMessage());
		throw thrown;
	}

	public static final Logger getLogger() {
		return logger;
	}

	public static final void info(final String message) {
		logger.info(message);
	}

	public static final void info(final String message, final Throwable t) {
		logger.info(message, t);
	}

	public final static void IOException(final String filename, final Throwable t) throws IOException {
		logger.fatal("IO Error in file \"" + filename + "\"", t);
		final IOException thrown = new IOException(t.getMessage());
		throw thrown;
	}

	public final static void ParseException(final String filename, final Throwable t) throws ParseException {
		logger.fatal("Unable to parse file \"" + filename + "\"", t);
		final ParseException thrown = new ParseException(t.getMessage(), 0);
		throw thrown;
	}

	/**
	 * @param logger
	 *            the logger to set
	 */
	public static final void setLogger(final Logger logger) {
		Log.logger = logger;
	}

	public static final void trace(final String message) {
		logger.trace(message);
	}

	public static final void trace(final String message, final Throwable t) {
		logger.trace(message, t);
	}

	public final static void TransformerException(final String filename1, final String filename2, final Throwable t)
			throws ParseException {
		logger.fatal("Unable to Transform files \"" + filename1 + "\"\"" + filename2 + "\"", t);
		final ParseException thrown = new ParseException(t.getMessage(), 0);
		throw thrown;
	}

	public static final void warn(final String message) {
		logger.warn(message);
	}

	public static final void warn(final String message, final Throwable t) {
		logger.warn(message, t);
	}

}
