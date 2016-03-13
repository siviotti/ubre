package br.gov.serpro.ubre.lang.data.literal;

import br.gov.serpro.ubre.internal.Str;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * Statement que representa o literal "true".
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class TrueStatement extends BooleanStatement {
	
	public static final TrueStatement INSTANCE = new TrueStatement();

	private TrueStatement() {
		super(Str.TRUE_LITERAL, Boolean.TRUE);
	}

	@Override
	public Statement inverse() {
		return FalseStatement.INSTANCE;
	}


}
