package br.net.ubre.shell.command;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.exception.CTDException;
import br.net.ubre.shell.Shell;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/04/2015
 * 
 */
public class ImportCmd implements ShellCommand {

	private Shell shell;

	public ImportCmd(Shell shell) {
		super();
		this.shell = shell;
	}

	public String execute(DataContainer container, String... par) {
		if (par.length < 1) {
			throw new CTDException(
					"O comando import exite um parâmetro que é a classe do comando a ser importado. Help: "
							+ help());
		}
		
		return null;
	}

	public String token() {
		return "import";
	}

	public String help() {
		return "import CommandClassName";
	}

}
