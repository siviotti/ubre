package br.net.ubre.slang.condition;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.Lang;
import br.net.ubre.lang.data.literal.TrueStatement;
import br.net.ubre.lang.expression.BooleanExpression;
import br.net.ubre.lang.statement.Statement;

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
