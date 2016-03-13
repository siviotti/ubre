package br.gov.serpro.ubre.lang.keyword.unary;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.BooleanStatement;
import br.gov.serpro.ubre.lang.data.literal.FalseStatement;
import br.gov.serpro.ubre.lang.data.literal.TrueStatement;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

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
