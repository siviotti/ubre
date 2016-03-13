package br.net.ubre.slang.condition;

import br.net.ubre.data.container.DataContainer;

/**
 * Define o método de decisão se uma condição e verdadeira a partir de uma
 * instância da área de dados (DataContainer).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 01/04/2015
 * 
 */
public interface Condition {

	/**
	 * A expressão fonte que gerou a Condição compilada.
	 * 
	 * @return
	 */
	String getSource();

	/**
	 * Determina se a condição é verdadeira.
	 * 
	 * @param container
	 *            A área de dados específica da sessão do usuário.
	 * @return se a condição é verdadeira.
	 */
	boolean isTrue(DataContainer container);

}
