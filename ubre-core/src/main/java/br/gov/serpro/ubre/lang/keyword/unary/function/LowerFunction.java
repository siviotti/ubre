package br.gov.serpro.ubre.lang.keyword.unary.function;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.StringStatement;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

public class LowerFunction extends Function{

	public LowerFunction(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		String str = (String) right.result(container);
		return new StringStatement(str.toLowerCase());
	}

	public StatementType rightType() {
		return StatementType.STRING;
	}

	public StatementType resultType() {
		return StatementType.STRING;
	}

}