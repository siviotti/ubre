package br.gov.serpro.ubre.shell.command;

import br.gov.serpro.ubre.data.container.DataContainer;

/**
 * INterface que define os métodos de um comando do Shell.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/04/2015
 * 
 */
public interface ShellCommand {
	
	public static final String HELP_PARAMETER = "--help";

	/**
	 * Executa o comando.
	 * 
	 * @param session
	 *            A sessão sobre a qual o comando é executado.
	 * @return
	 */
	String execute(DataContainer container, String ... par);

	/**O token que identifica o comando.
	 * @return
	 */
	String token();

	String help();
}
