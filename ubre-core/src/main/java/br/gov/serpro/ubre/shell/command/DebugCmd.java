package br.gov.serpro.ubre.shell.command;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.shell.Shell;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 18/05/2015
 *
 */
public class DebugCmd implements ShellCommand {

	private Shell shell;

	public DebugCmd(Shell shell) {
		super();
		this.shell = shell;
	}

	public String execute(DataContainer container, String... par) {
		shell.setDebug(!shell.isDebug());
		return token() + " = " + shell.isDebug();
	}

	public String token() {
		return "debug";
	}

	public String help() {
		return "debug";
	}

}
