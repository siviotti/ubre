package br.gov.serpro.ubre.behave;

import br.gov.serpro.ubre.data.container.DataContainer;

/**
 * Interface que define o método de execução de comando.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public interface Executable {

	/**
	 * Executa a ação definida no comando específico.
	 * 
	 * @param session
	 *            A área de memória da sessão do usuário.
	 */
	public void execute(DataContainer container);

}
