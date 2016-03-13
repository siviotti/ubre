package br.gov.serpro.ubre.lang.data.literal;

import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Classe genérica para True e False.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 18/05/2015
 * 
 */
public abstract class BooleanStatement extends LiteralStatement {

	public BooleanStatement(String token, Boolean value) {
		super(token, StatementType.BOOLEAN, value);
	}

	@Override
	public StatementType resultType() {
		return StatementType.BOOLEAN;
	}

	public abstract Statement inverse();

}
