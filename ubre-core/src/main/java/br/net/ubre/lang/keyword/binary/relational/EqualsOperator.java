package br.net.ubre.lang.keyword.binary.relational;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.FalseStatement;
import br.net.ubre.lang.data.literal.TrueStatement;
import br.net.ubre.lang.statement.Statement;

/**
 * COmpara se os valores das partes (esquerda e direita) são iguais.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public class EqualsOperator extends RelationalKeyword {

	public EqualsOperator(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		Object leftResult = left.result(container);
		Object rightResult = right.result(container);
		if (leftResult != null) {
			return (leftResult.equals(rightResult)) ? TrueStatement.INSTANCE
					: FalseStatement.INSTANCE;
		}
		return (rightResult == null) ? TrueStatement.INSTANCE
				: FalseStatement.INSTANCE;

	}
}
