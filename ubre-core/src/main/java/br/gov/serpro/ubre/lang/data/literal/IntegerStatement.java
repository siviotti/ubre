package br.gov.serpro.ubre.lang.data.literal;

import br.gov.serpro.ubre.internal.Str;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 28/10/2015
 */
public class IntegerStatement extends LiteralStatement {

	public IntegerStatement(String token) {
		super(token, StatementType.INTEGER, new Integer(token));
	}

	public IntegerStatement(Integer value) {
		super((value != null) ? value.toString() : Str.NULL_LITERAL,
				StatementType.INTEGER, value);
	}

}
