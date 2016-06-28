package idk.imgarchive.base.hooks.commands;

import idk.archiveutils.FileUtils;
import idk.imgarchive.base.hooks.HookCommand;
import idk.imgarchive.base.hooks.HookCommand.CommandType;

import java.io.IOException;
import java.text.ParseException;

import org.w3c.dom.Element;

public class CopyCmd extends HookCommand {
	public static int copyHook(final String destPath, final String sourcePath) throws IOException {
		FileUtils.copyIfNotExist(destPath, sourcePath);
		return 0;
	}

	public CopyCmd(final String name) {
		super(name);
		setCommandType(CommandType.CopyCmd);
	}

	@Override
	public void read(final Element rootElement) throws NumberFormatException, ParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return true;
	}
}
