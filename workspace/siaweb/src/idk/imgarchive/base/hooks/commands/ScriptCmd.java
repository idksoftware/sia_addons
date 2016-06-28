package idk.imgarchive.base.hooks.commands;

import idk.config.ConfigInfo;
import idk.imgarchive.base.hooks.HookCommand;
import idk.imgarchive.base.hooks.HookCommand.CommandType;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import xmlutils.XMLReadHelper;

/**
 * <?xml version="1.0" encoding="ISO-8859-1" ?> <IAScript> <OnIndexCatalog>
 * <Execute> <Command> <Name>ScriptHook</Name> <Arguments>
 * <ScriptFile>$(InstanceConfig)/</ScriptFile> </Arguments> </Command>
 * </Execute> </OnIndexCatalog> </IAScript>
 */
public class ScriptCmd extends HookCommand {

	String scriptName = null;

	public ScriptCmd(final String name) {
		super(name);
		setCommandType(CommandType.ScriptCmd);
	}

	static int execute(final String scriptName) throws IOException {
		/*
		 * String cmd = "dir"; // this is the command to execute in the Unix
		 * shell // create a process for the shell ProcessBuilder pb = new
		 * ProcessBuilder("cmd", "", cmd); pb.redirectErrorStream(true); // use
		 * this to capture messages sent to stderr Process shell = pb.start();
		 * InputStream shellIn = shell.getInputStream(); // this captures the
		 * output from the command //int shellExitStatus = shell.waitFor(); //
		 * wait for the shell to finish and get the return code // at this point
		 * you can process the output issued by the command // for instance,
		 * this reads the output and writes it to System.out: int c; while ((c =
		 * shellIn.read()) != -1) {System.out.write(c);} // close the stream try
		 * {shellIn.close();} catch (IOException ignoreMe) {} return c;
		 */
		final List<String> command = new ArrayList<String>();
		command.add(System.getenv("windir") + "\\system32\\" + "tree.com");
		// command.add("/A");
		command.add(ConfigInfo.getScriptPath() + File.separator + scriptName);
		final ProcessBuilder builder = new ProcessBuilder(command);
		final Map<String, String> environ = builder.environment();
		ScriptCmd.set(environ);
		builder.directory(new File(ConfigInfo.getScriptPath()));

		System.out.println("Directory : " + ConfigInfo.getScriptPath());
		final Process process = builder.start();
		final InputStream is = process.getInputStream();
		final InputStreamReader isr = new InputStreamReader(is);
		final BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println("Program terminated!");
		br.close();
		return 0;

	}

	static void set(final Map<String, String> environ) {

		environ.put("IMGA_ROOT", ConfigInfo.getRootPath());
		environ.put("IMGA_TEMP", ConfigInfo.getTempPath());
		environ.put("IMGA_TOOL", ConfigInfo.getToolPath());
		environ.put("IMGA_CONFIG", ConfigInfo.getConfigPath());
		// environ.put("IMGA_DATA", ConfigInfo.getDataPath());
		environ.put("IMGA_USER", ConfigInfo.getUsersPath());
		environ.put("IMGA_SCHEMA", ConfigInfo.getSchemaPath());
		environ.put("IMGA_XSL", ConfigInfo.getXslPath());
		environ.put("IMGA_SCRIPT", ConfigInfo.getScriptPath());
		environ.put("IMGA_SYS", ConfigInfo.getSystemPath());
		environ.put("IMGA_LOG", ConfigInfo.getLogPath());

	}

	@Override
	public boolean execute() throws IOException {
		final int res = ScriptCmd.execute(scriptName);
		return true;
	}

	@Override
	public void read(final Element rootElement) throws NumberFormatException, ParseException {
		scriptName = XMLReadHelper.stringValue(rootElement, "ScriptFile", true);

	}
}
