package br.net.ubre.slang.code.action;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.expression.Expression;

/**
 * Action que É uma expressão. A execução da Action é a avaliação da expressão.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 11/10/2015
 */
public class ExpressionAction extends Action {

	private Expression expression;

	public ExpressionAction(Expression expression) {
		this.expression = expression;
	}

	public void execute(DataContainer container) {
		expression.eval(container);
	}

	@Override
	public String toSource() {
		return expression.getSource();
	}

}
