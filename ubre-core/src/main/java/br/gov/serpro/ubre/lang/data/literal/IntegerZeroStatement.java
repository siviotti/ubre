package br.gov.serpro.ubre.lang.data.literal;

import br.gov.serpro.ubre.internal.Str;

/**
 * Literal para 0
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 22/10/2015
 */
public class IntegerZeroStatement extends IntegerStatement {

	public static final IntegerZeroStatement INSTANCE = new IntegerZeroStatement();

	public IntegerZeroStatement() {
		super(Str.INTEGER_ZERO_LITERAL);
	}

}
