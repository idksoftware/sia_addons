package idk.imgarchive.base.hooks.commands;

/*
 <Args>-size "200x200>"</Args>
 <Args>-thumbnail "100x100>"</Args>
 */

import idk.imgarchive.base.hooks.HookCommand;
import idk.imgarchive.base.hooks.SubstitutionProcessor;
import idk.imgarchive.base.hooks.HookCommand.CommandType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xmlutils.XMLReadHelper;

public class ExecCmd extends HookCommand {

	String workingFolder = null;
	String executableName = null;
	String executablePath = null;
	boolean isHeightWidthSet = false;

	private int height = 0;
	private int width = 0;
	int sizeArgNo = -1;

	public ExecCmd(final String name) {
		super(name);
		setCommandType(CommandType.ExecCmd);
	}

	@Override
	public void read(final Element rootElement) throws NumberFormatException, ParseException {
		workingFolder = XMLReadHelper.stringValue(rootElement, "WorkingFolder", true);
		executableName = XMLReadHelper.stringValue(rootElement, "Executable", true);
		executablePath = XMLReadHelper.stringValue(rootElement, "Path", true);
		final Element cmdArgsElem = XMLReadHelper.elementValue(rootElement, "ExecArgs", true);

		argList = new ArrayList<String>();
		argList.add("\"" + executablePath + File.separator + executableName + "\"");
		if (cmdArgsElem != null) {
			final NodeList itemList = cmdArgsElem.getChildNodes();
			final int n = itemList.getLength();
			Element itemElem = null;
			for (int i = 0; i < n; i++) {
				if ((itemElem = XMLReadHelper.isElement(itemList, i)) != null) {
					if (itemElem.getNodeName().matches("Args")) {
						String item = null;
						if ((item = itemElem.getTextContent()) == null) {
							return;
						}
						argList.add(item);
					}
				}
			}
		}
		sizeArgNo = XMLReadHelper.integerValue(rootElement, "SizeArgNo", false, false);
	}

	@Override
	public boolean execute() throws IOException, FileNotFoundException {
		// Runtime runtime = Runtime.getRuntime();
		String[] args = new String[argList.size()];
		args = argList.toArray(args);
		File runFolderFile = null;
		if (workingFolder == null) {
			runFolderFile = new File(".");
		} else {
			runFolderFile = new File(workingFolder);
		}
		if (runFolderFile.exists() == false) {
			throw new FileNotFoundException();
		}
		if (runFolderFile.exists() == false) {
			throw new FileNotFoundException("Working folder not found");
		}
		File cmd = new File(args[0]);
		if (cmd.exists() == false) {
			throw new FileNotFoundException("Command Not found");
		}
		execute(args, runFolderFile);
		return true;
	}

	public void execute(final String[] args, final File runFolderFile) throws IOException {
		// Runtime runtime = Runtime.getRuntime();

		final ProcessBuilder processBuilder = new ProcessBuilder(args);
		processBuilder.directory(runFolderFile);
		final Process process = processBuilder.start();

		final InputStream is = process.getInputStream();
		final InputStreamReader isr = new InputStreamReader(is);
		final BufferedReader br = new BufferedReader(isr);
		String line;
		System.out.printf("Output of running %s is:", Arrays.toString(args));
		try {
			process.waitFor();
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(process.exitValue());

		while ((line = br.readLine()) != null) {
			System.out.println(line);
			response.add(line);
		}
		if (sizeArgNo != -1) {
			line = response.get(0);
			parseSize(line);
		}

		System.out.println("Completed");
	}

	@Override
	public void doSubs(final SubstitutionProcessor sp) {
		workingFolder = sp.processString(workingFolder);
		executableName = sp.processString(executableName);
		executablePath = sp.processString(executablePath);
		super.doSubs(sp);
	}

	/**
	 * @return the isHeightWidthSet
	 */
	public final boolean isHeightWidthSet() {
		return isHeightWidthSet;
	}

	/**
	 * @return the height
	 */
	public final int getHeight() {
		return height;
	}

	/**
	 * @return the width
	 */
	public final int getWidth() {
		return width;
	}

	private void parseSize(final String line) {
		final String[] args = line.split(" ");
		for (final String arg : args) {
			if (arg.indexOf("x") > 0) {
				final String[] ws = arg.split("x");
				try {
					width = Integer.parseInt(ws[0]);
					height = Integer.parseInt(ws[1]);
					isHeightWidthSet = true;
					break;
				} catch (final NumberFormatException e) {
					continue;
				}
			}
		}

	}
}
