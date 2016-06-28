package idk.imgarchive.base.hooks.commands;

import idk.archiveutils.FileUtils;

import java.io.File;
import java.io.IOException;

public class InternalHookCommands {
	static public enum CommandName {
		copyFileNoOverwrite,
		copyFileWithOverwrite,
		copyFileWithVersioning,
		moveFileNoOverwrite,
		moveFileWithOverwrite,
		moveFileWithVersioning,
		deleteFile
	}

	static public void copyFileNoOverwrite(final String[] args) {
		final File sourceFile = new File(args[0]);
		final File destFile = new File(args[1]);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static public void copyFileWithOverwrite(final String[] args) {
		final File sourceFile = new File(args[0]);
		final File destFile = new File(args[1]);

		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static public void copyFileWithVersioning(final String[] args) {
		final File sourceFile = new File(args[0]);
		final File destFile = new File(args[1]);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public void moveFileNoOverwrite(final String[] args) {
		copyFileNoOverwrite(args);
		deleteFile(args[0]);
	}

	static public void moveFileWithOverwrite(final String[] args) {
		copyFileWithOverwrite(args);
		deleteFile(args[0]);
	}

	static public void moveFileWithVersioning(final String[] args) {
		copyFileWithOverwrite(args);
		deleteFile(args[0]);
	}
	static public void deleteFile(final String arg) {
		final File file = new File(arg);
		file.delete();
	}
}
