package br.gov.serpro.ubre.slang.command;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.slang.keyword.Keyword;

/**
 * Interface que define o método de execução de um comando que deve rodar dentro
 * de uma açao.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public interface Command {

	/**
	 * A Keyword com a meta infrmações sobre o comando.
	 * 
	 * @return A keyword..
	 */
	Keyword getKeyword();

	/**
	 * Executa o comando com os parâmetrospassados.
	 * 
	 * @param container
	 *            O DataContainer com os dados de sessão.
	 * @param targetToken
	 *            O token do alvo que será obtido no container somente durante a
	 *            execução.
	 * @param parameter
	 *            O statement que representa o parâmetro e que terá seu valor
	 *            definido dentro do método execute quando será possível ter
	 *            acesso a um DataContainer.
	 */
	void execute(DataContainer container, String targetToken, Statement parameter);

}
