package br.net.ubre.shell.command;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.shell.Shell;

/**
 * Liga e desliga o modo Script do Shell.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 13/10/2015
 */
public class ScriptCmd implements ShellCommand {

	private Shell shell;

	public ScriptCmd(Shell shell) {
		super();
		this.shell = shell;
	}

	public String execute(DataContainer container, String... par) {
		shell.setScript(!shell.isScript());
		return token() + " = " + shell.isScript();
	}

	public String token() {
		return "script";
	}

	public String help() {
		return "script";
	}

}
