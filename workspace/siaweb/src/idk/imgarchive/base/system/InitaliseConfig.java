package idk.imgarchive.base.system;

import idk.config.ConfigInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.log4j.lf5.LogLevel;

public class InitaliseConfig {
	/**
	 * Supported encoding for persisted XML.
	 */
	public static final String ENCODING_UTF8 = "UTF-8";

	static public final void defaultInitalise(final String homePath, final LogLevel logLevel, final String toolPath)
			throws IOException {
		writeConfig(homePath, homePath, logLevel, toolPath);
		writelog4jConfig(homePath, homePath, homePath + "logs");
	}

	static public final void writeConfig(final String rootPath, final String configPath, LogLevel logLevel, final String toolPath)
			throws IOException {
		// log("Writing to file named " + fFileName + ". Encoding: " +
		// fEncoding);
		final Writer out = new OutputStreamWriter(new FileOutputStream(configPath + File.separator + ConfigInfo.CONFIG_FILE_NAME),
				ENCODING_UTF8);
		if (logLevel == null) {
			logLevel = LogLevel.FINE;
		}
		try {
			out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\r\n");
			out.write("<Config>" + "\r\n");
			out.write(String.format("<RootPath>%s", rootPath) + "</RootPath>" + "\r\n");
			out.write(String.format("<TempPath>%s", rootPath) + File.separator + "Temp</TempPath>" + "\r\n");
			out.write(String.format("<WebSitePath>%s", rootPath) + File.separator + "wwwRoot</WebSitePath>" + "\r\n");
			out.write(String.format("<ToolPath>%s", toolPath) + "</ToolPath>" + "\r\n");
			out.write(String.format("<LogPath>%s", rootPath) + File.separator + "logs</LogPath>" + "\r\n");
			out.write(String.format("<UsersPath>%s", rootPath) + File.separator + "users</UsersPath>" + "\r\n");
			out.write(String.format("<ConfigPath>%s", rootPath) + File.separator + "config</ConfigPath>" + "\r\n");
			out.write(String.format("<SystemPath>%s", rootPath) + File.separator + "system</SystemPath>" + "\r\n");
			out.write(String.format("<LogLevel>%s", logLevel.toString()) + "</LogLevel>" + "\r\n");
			out.write("</Config>" + "\r\n");
		} finally {
			out.close();
		}
	}

	/*
	 * <?xml version="1.0" encoding="UTF-8" ?> <!DOCTYPE log4j:configuration
	 * (View Source for full doctype...)> - <log4j:configuration
	 * xmlns:log4j="http://jakarta.apache.org/log4j/" debug="false"
	 * threshold="null" reset="false"> - <appender name="consoleAppender"
	 * class="org.apache.log4j.ConsoleAppender"> <param name="Threshold"
	 * value="INFO" /> - <layout class="org.apache.log4j.PatternLayout"> <param
	 * name="ConversionPattern" value="%d %-5p [%c{1}] %m %n" /> </layout>
	 * </appender> - <appender name="fileAppender"
	 * class="org.apache.log4j.RollingFileAppender"> <param name="Threshold"
	 * value="INFO" /> <param name="File" value="u:/sample.xml" /> - <layout
	 * class="org.apache.log4j.PatternLayout"> <param name="ConversionPattern"
	 * value="%d %-5p [%c{1}] %m %n" /> </layout> </appender>
	 * </log4j:configuration>
	 */
	static public final void writelog4jConfig(final String rootPath, final String configPath, final String logPath)
			throws IOException {

		final Writer out = new OutputStreamWriter(new FileOutputStream(configPath + File.separator + ConfigInfo.LOG4J_FILE_NAME),
				ENCODING_UTF8);
		try {

			out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\r\n");
			out.write("<!DOCTYPE log4j:configuration SYSTEM \"log4j.dtd\">" + "\r\n");
			out.write("<log4j:configuration xmlns:log4j=\"http://jakarta.apache.org/log4j/\" debug=\"false\">" + "\r\n");
			out.write("<appender name=\"consoleAppender\" class=\"org.apache.log4j.ConsoleAppender\">" + "\r\n");
			out.write("<param name=\"Threshold\" value=\"INFO\" />" + "\r\n");
			out.write("<layout class=\"org.apache.log4j.PatternLayout\">" + "\r\n");
			out.write("<param name=\"ConversionPattern\" value=\"%d %-5p [%c{1}] %m %n\" />" + "\r\n");
			out.write("</layout>" + "\r\n");
			out.write("</appender>" + "\r\n");
			out.write("<appender name=\"fileAppender\" class=\"org.apache.log4j.RollingFileAppender\">" + "\r\n");
			out.write("<param name=\"Threshold\" value=\"INFO\" />" + "\r\n");
			out.write("<param name=\"File\" value=\"" + logPath + "\" />" + "\r\n");
			out.write("<layout class=\"org.apache.log4j.PatternLayout\">" + "\r\n");
			out.write("<param name=\"ConversionPattern\" value=\"%d %-5p [%c{1}] %m %n\" />" + "\r\n");
			out.write("</layout>" + "\r\n");
			out.write("</appender>" + "\r\n");
			out.write("</log4j:configuration>" + "\r\n");

		} finally {
			out.close();
		}
	}

	String rootPath = null;

	String toolPath = null;

}
