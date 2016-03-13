package br.gov.serpro.ubre.lang.data.literal;

import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 28/10/2015
 */
public class StringStatement extends LiteralStatement {

	public StringStatement(String token) {
		super(token, StatementType.STRING, token);
	}

}
