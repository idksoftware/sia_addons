package idk.imgarchive.base.hooks;

import idk.imgarchive.base.hooks.HookCommand.CommandType;
import idk.imgarchive.base.hooks.SubstitutionProcessor.SubType;
import idk.imgarchive.base.hooks.commands.ExecCmd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xmlutils.XMLReadHelper;
import xmlutils.XMLUtil;

public class HookPoint {
	protected class HookPointVariable {
		String name = null;
		String expression = null;

		HookPointVariable(final String name, final String expression) {
			this.name = name;
			this.expression = expression;
		}
	}

	@SuppressWarnings("serial")
	protected class HookPointVariableList extends ArrayList<HookPointVariable> {
	}

	private HookPointVariableList definedVariablesList = null;
	private CommandList definedCommandList = null;
	private CommandList executeList = null;
	protected PresetVariables presetVariables = new PresetVariables();

	public void readDefinedVariables(final Element rootElement) throws NumberFormatException, ParseException, RuntimeException {

		if (rootElement != null) {
			final NodeList itemList = rootElement.getChildNodes();
			final int n = itemList.getLength();
			Element itemElem = null;
			for (int i = 0; i < n; i++) {
				if ((itemElem = XMLReadHelper.isElement(itemList, i)) != null) {
					if (itemElem.getNodeName().matches("Variable")) {

						if (definedVariablesList == null) {
							definedVariablesList = new HookPointVariableList();
						}
						final String name = XMLReadHelper.stringValue(itemElem, "Name", true);
						final String expression = XMLReadHelper.stringValue(itemElem, "Expression", true);

						definedVariablesList.add(new HookPointVariable(name, expression));
					} else {
						throw new RuntimeException("The node: \"" + itemElem.getNodeName() + "\" must match \"Command\" Tag");
					}
				}
			}
		}
	}

	public void readDefinedCmds(final Element rootElement) throws NumberFormatException, ParseException, RuntimeException {

		if (rootElement != null) {
			final NodeList itemList = rootElement.getChildNodes();
			final int n = itemList.getLength();
			Element itemElem = null;
			for (int i = 0; i < n; i++) {
				if ((itemElem = XMLReadHelper.isElement(itemList, i)) != null) {
					if (itemElem.getNodeName().matches("Command")) {
						final HookCommand hookCommand = ReadCommand(itemElem);
						if (definedCommandList == null) {
							definedCommandList = new CommandList();
						}
						definedCommandList.add(hookCommand);
					} else {
						throw new RuntimeException("The node: \"" + itemElem.getNodeName() + "\" must match \"Command\" Tag");
					}
				}
			}
		}
	}

	public void readExecList(final Element rootElement) throws NumberFormatException, ParseException, RuntimeException {

		if (rootElement != null) {
			final NodeList itemList = rootElement.getChildNodes();
			final int n = itemList.getLength();
			Element itemElem = null;
			for (int i = 0; i < n; i++) {
				if ((itemElem = XMLReadHelper.isElement(itemList, i)) != null) {
					if (itemElem.getNodeName().matches("Command")) {
						final String commandName = itemElem.getTextContent();
						if (executeList == null) {
							executeList = new CommandList();
						}
						if (commandName == null) {
							throw new RuntimeException("Command name not found");
						}
						final HookCommand hookCommand = find(commandName);
						if (hookCommand == null) {
							throw new RuntimeException("The command: \"" + commandName + "\" not found as a Command");
						}
						executeList.add(hookCommand);
					} else {
						throw new RuntimeException("The node: \"" + itemElem.getNodeName() + "\" must match \"Command\" Tag");
					}
				}
			}
		}
	}

	HookCommand find(final String name) {
		for (final HookCommand item : definedCommandList) {
			if (item.getName().matches(name) == true) {
				return item;
			}
		}
		return null;
	}

	private void doVariables(final SubstitutionProcessor sp) {
		if (definedVariablesList == null) {
			return; // No Variables defined
		}
		for (final HookPointVariable variable : definedVariablesList) {
			// variable.expression = sp.processString(variable.expression);
			// variable.expression =
			// Integer.toString(MathParser.processEquation(variable.expression));
			sp.add(SubType.Expression, variable.name, variable.expression);
		}
	}

	public boolean execute() throws IOException {
		final SubstitutionProcessor sp = new SubstitutionProcessor(presetVariables);
		doVariables(sp);
		for (final HookCommand cmd : executeList) {
			cmd.doSubs(sp);
			try {
				cmd.execute();
				// String string = cmd.getClass().getSimpleName();
				if (cmd.getClass().getSimpleName().matches("ExecCmd") == true) {
					if (((ExecCmd) cmd).isHeightWidthSet() == true) {
						sp.add(SubType.String, SubstitutionProcessor.WIDTH, Integer.toString(((ExecCmd) cmd).getWidth()));
						sp.add(SubType.String, SubstitutionProcessor.HEIGHT, Integer.toString(((ExecCmd) cmd).getHeight()));
					}
				}
			} catch (FileNotFoundException e) {
				return false;
			}
		}
		return true;
	}

	private HookCommand ReadCommand(final Element commandElement) throws ParseException {
		final String commandName = XMLReadHelper.stringValue(commandElement, "Name", true);
		final String type = XMLReadHelper.stringValue(commandElement, "Type", true);
		final CommandType commandType = CommandType.valueOf(type);
		final HookCommand hookCommand = CommandFactory.Create(commandName, commandType);
		if (hookCommand == null) {
			return null;
		}
		final Element argumentsElem = XMLUtil.getElement(commandElement, "Arguments");
		hookCommand.read(argumentsElem);
		return hookCommand;
	}

	protected static Element decode2DefineVariable(final Document document) throws ParseException {
		final Element rootElement = document.getDocumentElement();
		rootElement.normalize();

		return XMLUtil.getElement(rootElement, "DefineVariables");
	}

	protected static Element decode2DefineCommands(final Document document) throws ParseException {
		final Element rootElement = document.getDocumentElement();
		rootElement.normalize();

		return XMLUtil.getElement(rootElement, "DefineCommands");
	}

	protected static Element decode2Execute(final Document document) throws ParseException {
		final Element rootElement = document.getDocumentElement();
		rootElement.normalize();

		return XMLUtil.getElement(rootElement, "Execute");
	}
}
