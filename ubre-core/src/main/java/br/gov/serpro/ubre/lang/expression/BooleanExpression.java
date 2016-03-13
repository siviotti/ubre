package br.gov.serpro.ubre.lang.expression;

import static br.gov.serpro.ubre.lang.LangError.E31;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.LangException;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Expressão que tem como resultado um valor booleano.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class BooleanExpression extends Expression {

	public BooleanExpression(String source, Statement root) {
		super(source, root);
		if (!root.resultType().equals(StatementType.BOOLEAN)) {
			throw new LangException(E31, EXPRESSION_ROOT_MUST_BE_BOOLEAN);
		}
	}

	/**
	 * retorna o valor booleano da expressão.
	 * 
	 * @param container
	 *            A área de dados usada pela expressão para executar.
	 * @return true ou false.
	 */
	public boolean result(DataContainer container) {
		return (Boolean) eval(container);
	}

}
