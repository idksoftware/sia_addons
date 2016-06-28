package idk.imgarchive.base.hooks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.w3c.dom.Element;

/**
 * Built-in Hook commands. These commands can be included in hook scripts as
 * commands that the hook script can execute directly for example: <?xml
 * version="1.0" encoding="ISO-8859-1" ?> <IAScript> <OnIndexCatalog> <Execute>
 * <Command> <Name>MakeHtml</Name> <Arguments>
 * <XSLFile>$(InstanceConfig)/IndexCatalog.xsl</XSLFile>
 * <XMLFile>$(XMLFile)</XMLFile> <HTMLFile>$(HTMLFile)</HTMLFile> </Arguments>
 * </Command> </Execute> </OnIndexCatalog> </IAScript>
 * 
 * @author iain
 * 
 */
public abstract class HookCommand {

	/**
	 * @return the error
	 */
	protected final Boolean getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	protected final void setError(final Boolean error) {
		this.error = error;
	}

	public enum CommandType {
		CopyCmd, ScriptCmd, XSLTCmd, ExecCmd, InternalCmd
	}

	Boolean error = false;
	protected final ArrayList<String> response = new ArrayList<String>(10);
	protected ArrayList<String> argList = null;

	/**
	 * @return the argList
	 */
	final ArrayList<String> getArgList() {
		return argList;
	}

	private String name = null;

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	private CommandType type = null;

	protected void setCommandType(final CommandType type) {
		this.type = type;
	}

	protected HookCommand(final String name) {
		this.name = name;
	}

	void setArgs(final ArrayList<String> args) {
		argList = args;

	}

	void addArg(final String arg) {
		if (argList == null) {
			argList = new ArrayList<String>();
		}
		argList.add(arg);
	}

	public void doSubs(final SubstitutionProcessor sp) {

		String[] args = new String[argList.size()];
		args = argList.toArray(args);
		argList.clear();
		for (final String item : args) {
			argList.add(sp.processString(item));

		}
	}

	/**
	 * This function reads the arguments for the derived command. The XML
	 * parsing will start at <Arguments> tag
	 * 
	 * @param rootElement
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	abstract public void read(Element rootElement) throws NumberFormatException, ParseException;

	/**
	 * This function executes the command after the script has been read
	 * 
	 * @throws IOException
	 */
	abstract public boolean execute() throws IOException, FileNotFoundException;

}
