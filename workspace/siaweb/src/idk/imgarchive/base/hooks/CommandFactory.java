package idk.imgarchive.base.hooks;

import idk.imgarchive.base.hooks.commands.CopyCmd;
import idk.imgarchive.base.hooks.commands.ExecCmd;
import idk.imgarchive.base.hooks.commands.InternalCmd;
import idk.imgarchive.base.hooks.commands.ScriptCmd;
import idk.imgarchive.base.hooks.commands.XslCmd;

public class CommandFactory {
	static HookCommand Create(final String name, final HookCommand.CommandType type) {
		switch (type) {
		case CopyCmd:
			return new CopyCmd(name);
		case ScriptCmd:
			return new ScriptCmd(name);
		case XSLTCmd:
			return new XslCmd(name);
		case ExecCmd:
			return new ExecCmd(name);
		case InternalCmd:
			return new InternalCmd(name);
		}
		return null;
	}
}
