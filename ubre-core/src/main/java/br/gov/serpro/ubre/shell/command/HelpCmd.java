package br.gov.serpro.ubre.shell.command;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.shell.Shell;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/04/2015
 *
 */
public class HelpCmd implements ShellCommand{
	
	private Shell shell;

	public HelpCmd(Shell shell) {
		super();
		this.shell = shell;
	}

	public String execute(DataContainer container, String... par) {
		if (par.length > 0){
			return shell.getCommand(par[0]).help();
		}
		StringBuilder sb = new StringBuilder();
		for (ShellCommand command: shell.getCommands()){
			sb.append(command.help());
			sb.append("\n");
		}
		return sb.toString();
	}

	public String token() {
		return "help";
	}

	public String help() {
		return "help [command]";
	}

}
