package br.gov.serpro.ubre.lang.data.literal;

import br.gov.serpro.ubre.internal.Str;

/**
 * Statement literal para 0.0
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 22/10/2015
 */
public class DecimalZeroStatement extends DecimalStatement {

	public static final DecimalZeroStatement INSTANCE = new DecimalZeroStatement();

	public DecimalZeroStatement() {
		super(Str.DECIMAL_ZERO_LITERAL);
	}

}
