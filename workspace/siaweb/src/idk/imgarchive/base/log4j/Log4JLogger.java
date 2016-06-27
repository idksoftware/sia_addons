package idk.imgarchive.base.log4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.xml.XMLLayout;

public class Log4JLogger {
	private static final Level DEFAULT_LOGGER_LEVEL = Level.INFO;
	private static final int LOG_IO_BUFFER_SIZE_BYTES = 1024;
	private static final String LOG_PATTERN = "%-5p [%t]: %m%n";
	static final String LOG4J_CONFIG_FILE = "log4j.xml";
	private static Logger logger = Logger.getLogger(Log.class);
	private static final int MAX_LOG_BACKUP_FILES = 200;
	private static final String MAX_LOG_FILE_SIZE = "128KB";
	private static final String SYSTEM_OUT = "System.out";

	public static void createLogger(final String configPath, final String logPath) throws IOException {

		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		final Date date = new Date();
		final String dateStr = dateFormat.format(date);

		final String logFilename = "IA" + dateStr + "log.xml";
		final File logFile = new File(logPath, logFilename);

		// String bannerString = "IA" + dateStr + "log.xml";
		final Layout layout = new XMLLayout();
		final Logger logger = Logger.getLogger("IALog");
		final RollingFileAppender rfa = new RollingFileAppender(layout, logFile.getCanonicalPath(), true);
		logger.addAppender(rfa);
		// We want the logger to flush its output to the log file
		// stream immeaditely; if you don't have this set, then
		// Log4j will buffer the log file output which isn't ideal.
		rfa.setImmediateFlush(true);
		rfa.setBufferedIO(false);
		rfa.setBufferSize(LOG_IO_BUFFER_SIZE_BYTES);

		// Set the Max number of files and max size of each log
		// file to keep around.
		rfa.setMaxBackupIndex(MAX_LOG_BACKUP_FILES);
		rfa.setMaxFileSize(MAX_LOG_FILE_SIZE);

		// Set the default level of this logger.
		logger.setLevel(DEFAULT_LOGGER_LEVEL);

		logger.addAppender(rfa);
		final String fullPath = configPath + File.separator + LOG4J_CONFIG_FILE;
		DOMConfigurator.configure(fullPath);

		// Also log to the console so we can see what's going on.
		final Layout consoleLayout = new PatternLayout(LOG_PATTERN);
		final ConsoleAppender cp = new ConsoleAppender(consoleLayout, SYSTEM_OUT);
		cp.setImmediateFlush(true);
		logger.addAppender(cp);

		logger.info("Starting Image Archive 1.0");
		Log.setLogger(logger);
	}

	public static void createXMLLogger() throws FileNotFoundException {
		final FileOutputStream filename = new FileOutputStream("XMLWriter.xml");
		final WriterAppender writeappender = new WriterAppender(new XMLLayout(), filename);

		logger.addAppender(writeappender);
		logger.info("Starting Image Archive 1.0");
		Log.setLogger(logger);
	}

}
