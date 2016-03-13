package br.net.ubre.lang.operation;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Operação genérica para operadores sobrecarregados.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public interface Operation {

	public abstract Statement perform(DataContainer container, Statement left,
			Statement right);

	/**
	 * Tipo aceito para o Statement a esquerda.
	 * 
	 * @return O tipo de dado do Statement a esquerda.
	 */
	public abstract StatementType leftType();

	/**
	 * Tipo aceito para o Statement à direita.
	 * 
	 * @return O tipo de dado do Statement à direita.
	 */
	public abstract StatementType rightType();

	/**
	 * Tipo de dado do resultado retornado.
	 * 
	 * @return O tipo de dado do resultado.
	 */
	public abstract StatementType resultType();

	/**
	 * Define a precedência do operador que utilizaa operação.
	 * 
	 * @return
	 */
	public abstract int precedence();


}
