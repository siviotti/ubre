package br.gov.serpro.ubre.lang.keyword.unary.function;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.StringStatement;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

public class StrFunction extends Function {

	public StrFunction(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		Object result = right.result(container);
		return new StringStatement((result != null) ? result.toString() : null);
	}

	public StatementType rightType() {
		return StatementType.OBJECT;
	}

	public StatementType resultType() {
		return StatementType.STRING;
	}

}