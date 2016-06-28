package idk.imgarchive.base.hooks.commands;

/*
 <Args>-size "200x200>"</Args>
 <Args>-thumbnail "100x100>"</Args>
 */

import idk.imgarchive.base.hooks.HookCommand;
import idk.imgarchive.base.hooks.SubstitutionProcessor;
import idk.imgarchive.base.hooks.HookCommand.CommandType;
import idk.imgarchive.base.hooks.commands.InternalHookCommands.CommandName;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xmlutils.XMLReadHelper;

public class InternalCmd extends HookCommand {

	String executableName = null;

	boolean isHeightWidthSet = false;

	private final int height = 0;
	private final int width = 0;

	public InternalCmd(final String name) {
		super(name);
		setCommandType(CommandType.InternalCmd);
	}

	@Override
	public void read(final Element rootElement) throws NumberFormatException, ParseException {

		executableName = XMLReadHelper.stringValue(rootElement, "Executable", true);
		final Element cmdArgsElem = XMLReadHelper.elementValue(rootElement, "ExecArgs", true);
		argList = new ArrayList<String>();

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

	}

	@Override
	public boolean execute() throws IOException, FileNotFoundException {

		String[] args = new String[argList.size()];
		args = argList.toArray(args);
		try {
			switch (InternalHookCommands.CommandName.valueOf(executableName)) {
			case copyFileNoOverwrite:
				InternalHookCommands.copyFileNoOverwrite(args);
				break;
			case copyFileWithOverwrite:
				InternalHookCommands.copyFileWithOverwrite(args);
				break;
			case copyFileWithVersioning:
				InternalHookCommands.copyFileWithVersioning(args);
				break;
			case moveFileNoOverwrite:
				InternalHookCommands.copyFileNoOverwrite(args);
				break;
			case moveFileWithOverwrite:
				InternalHookCommands.copyFileWithOverwrite(args);
				break;
			case moveFileWithVersioning:
				InternalHookCommands.copyFileWithVersioning(args);
				break;
			case deleteFile:
				InternalHookCommands.deleteFile(args[0]);
				break;
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	@Override
	public void doSubs(final SubstitutionProcessor sp) {
		executableName = sp.processString(executableName);
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

}
