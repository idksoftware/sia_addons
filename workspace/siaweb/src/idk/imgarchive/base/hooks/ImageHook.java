package idk.imgarchive.base.hooks;


import java.io.File;
import java.text.ParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ImageHook extends HookPoint {
	public ImageHook() {
	}

	public void Setup(final File tools, final File in, final File out) {
		presetVariables.setToolPath(tools.getAbsolutePath());
		presetVariables.setSourcePath(in.getParent());
		presetVariables.setDestinationPath(out.getParent());
		
		final String filename = in.getName();
		final int dot = filename.lastIndexOf(".");

		final String name = filename.substring(0, dot);
		presetVariables.setCurrentImageName(name);

		final String ext = filename.substring(dot + 1, filename.length());
		presetVariables.setCurrentImageExtention(ext);
		
		final String archiveName = out.getName();
		final int aDot = archiveName.lastIndexOf(".");

		final String arcname = archiveName.substring(0, aDot);
		presetVariables.setCurrentArchiveName(arcname);
	}

	public void read(final Document document) throws ParseException {
		Element argumentsElement = HookPoint.decode2DefineVariable(document);
		readDefinedVariables(argumentsElement);
		argumentsElement = HookPoint.decode2DefineCommands(document);
		readDefinedCmds(argumentsElement);
		argumentsElement = decode2Execute(document);
		readExecList(argumentsElement);

	}
}
