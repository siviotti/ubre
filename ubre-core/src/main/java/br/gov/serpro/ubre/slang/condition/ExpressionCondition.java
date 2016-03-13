package br.gov.serpro.ubre.slang.condition;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.Lang;
import br.gov.serpro.ubre.lang.data.literal.TrueStatement;
import br.gov.serpro.ubre.lang.expression.BooleanExpression;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * Representa uma condição.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class ExpressionCondition implements Condition {

	private BooleanExpression expression;

	public ExpressionCondition(BooleanExpression expression) {
		super();
		this.expression = expression;
	}

	/**
	 * determina se a condição é verdadeira.
	 * 
	 * @return
	 */
	public boolean isTrue(DataContainer container) {
		return expression.result(container);
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return expression.getSource();
	}

}
