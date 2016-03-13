package br.gov.serpro.ubre.lang.keyword.unary.function;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.lang.data.literal.IntegerStatement;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

public class IntFunction extends Function {

	public IntFunction(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		try {
			String result = (String) right.result(container);
			return new IntegerStatement(Integer.parseInt(result));
		} catch (Exception e) {
			throw new CTDException("Erro na conversão para inteiro.\n"+e.getMessage());
			
		}
	}

	public StatementType rightType() {
		return StatementType.STRING;
	}

	public StatementType resultType() {
		return StatementType.INTEGER;
	}

}