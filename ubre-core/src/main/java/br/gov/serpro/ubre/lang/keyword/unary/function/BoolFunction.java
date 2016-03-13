package br.gov.serpro.ubre.lang.keyword.unary.function;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.FalseStatement;
import br.gov.serpro.ubre.lang.data.literal.TrueStatement;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

public class BoolFunction extends Function {

	public BoolFunction(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		if (Boolean.parseBoolean((String) right.result(container))) {
			return TrueStatement.INSTANCE;
		} else {
			return FalseStatement.INSTANCE;
		}
	}

	public StatementType rightType() {
		return StatementType.STRING;
	}

	public StatementType resultType() {
		return StatementType.BOOLEAN;
	}

}