package br.gov.serpro.ubre.lang.data.literal;

import br.gov.serpro.ubre.internal.Str;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 31/03/2015
 * 
 */
public class NullStatement extends LiteralStatement {

	public static final NullStatement INSTANCE = new NullStatement();

	public NullStatement() {
		super(Str.NULL_LITERAL, StatementType.NULL, null);
	}

}
