package br.net.ubre.slang.code.action;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.expression.Expression;
import br.net.ubre.slang.command.Command;

/**
 * Action cujo parâmetro é uma expressão que deve ser avaliada por Lang.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 10/10/2015
 */
public class ParameterCommandAction extends SimpleCommandAction {

	private Expression expression;

	public ParameterCommandAction(Command command, String targetToken, Expression expression) {
		super(command, targetToken);
		this.expression = expression;
	}

	public void execute(DataContainer container) {
		command.execute(container, targetToken, expression.getRoot());
	}

	@Override
	public String toSource() {
		return super.toSource() + ' ' + expression.getSource();
	}

	

}
