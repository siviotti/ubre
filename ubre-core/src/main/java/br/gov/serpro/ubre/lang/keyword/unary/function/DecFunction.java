package br.gov.serpro.ubre.lang.keyword.unary.function;

import java.math.BigDecimal;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.lang.data.literal.DecimalStatement;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

public class DecFunction extends Function {

	public DecFunction(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		try {
			String result = (String) right.result(container);
			return new DecimalStatement(new BigDecimal(result));
		} catch (Exception e) {
			throw new CTDException("Erro na conversão para decimal.\n"+e.getMessage());
			
		}
	}

	public StatementType rightType() {
		return StatementType.STRING;
	}

	public StatementType resultType() {
		return StatementType.DECIMAL;
	}

}