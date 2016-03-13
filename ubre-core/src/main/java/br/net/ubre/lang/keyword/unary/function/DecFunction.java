package br.net.ubre.lang.keyword.unary.function;

import java.math.BigDecimal;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.exception.CTDException;
import br.net.ubre.lang.data.literal.DecimalStatement;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

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