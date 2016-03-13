package br.net.ubre.lang.keyword.unary;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.BooleanStatement;
import br.net.ubre.lang.data.literal.FalseStatement;
import br.net.ubre.lang.data.literal.TrueStatement;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 17/04/2015
 * 
 */
public class NotOperator extends UnaryKeyword {

	public NotOperator(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		return ((Boolean) right.result(container)) ? FalseStatement.INSTANCE
				: TrueStatement.INSTANCE;
	}

	public StatementType rightType() {
		return StatementType.BOOLEAN;
	}

	public StatementType resultType() {
		return StatementType.BOOLEAN;
	}

	@Override
	public int precedence() {
		return 2;
	}

}
