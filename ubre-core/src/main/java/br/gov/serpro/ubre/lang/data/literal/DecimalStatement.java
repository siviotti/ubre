package br.gov.serpro.ubre.lang.data.literal;

import java.math.BigDecimal;

import br.gov.serpro.ubre.internal.Str;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 28/10/2015
 */
public class DecimalStatement extends LiteralStatement {

	public DecimalStatement(String token) {
		super(token, StatementType.DECIMAL, new BigDecimal(token));
	}

	public DecimalStatement(BigDecimal value) {
		super((value != null) ? value.toPlainString() : Str.NULL_LITERAL,
				StatementType.DECIMAL, value);
	}

}
