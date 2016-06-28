package idk.archiveutils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.XMLFormatter;

//This custom formatter formats parts of a log record to a single line
public class XMLLogFormatter extends XMLFormatter {
	private String calcDate(final long millisecs) {
		final SimpleDateFormat date_format = new SimpleDateFormat("MM,dd,yyyy HH:mm");
		final Date resultdate = new Date(millisecs);
		return date_format.format(resultdate);
	}

	// This method is called for every log records
	@Override
	public String format(final LogRecord rec) {
		final StringBuffer buf = new StringBuffer(1000);
		// Bold any levels >= WARNING
		buf.append("<rec>\n");

		// if (rec.getLevel().intValue() >= Level.WARNING.intValue())
		// {
		buf.append("<l>\n");
		buf.append(rec.getLevel());
		buf.append("\n</l>\n");
		// } else
		// {
		// buf.append(rec.getLevel());
		// }
		buf.append("<td>\n");
		buf.append(calcDate(rec.getMillis()));
		buf.append("</td>\n");
		buf.append("\n<m>\n");
		buf.append(formatMessage(rec));
		buf.append("\n</m>\n");
		buf.append("</rec>\n");
		return buf.toString();
	}

	// This method is called just after the handler using this
	// formatter is created
	@Override
	public String getHead(final Handler h) {
		return "<?xml version=\"1.0\" encoding=\"windows-1252\" standalone=\"no\"?>\n"
		// + "<!DOCTYPE log SYSTEM \"logger.dtd\">\n"
				+ "<log>\n";

		// return "<HTML>\n<HEAD>\n" + (new Date()) +
		// "\n</HEAD>\n<BODY>\n<PRE>\n"
		// + "<table border>\n  "
		// + "<tr><th>Time</th><th>Log Message</th></tr>\n";
	}

	// This method is called just after the handler using this
	// formatter is closed
	@Override
	public String getTail(final Handler h) {
		return "</log>\n";
	}
}
