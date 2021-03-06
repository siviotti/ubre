package br.net.ubre.lang.data.literal;

import br.net.ubre.internal.Str;
import br.net.ubre.lang.statement.Statement;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class FalseStatement extends BooleanStatement {

	public static final FalseStatement INSTANCE = new FalseStatement();

	public FalseStatement() {
		super(Str.FALSE_LITERAL, Boolean.FALSE);
	}

	@Override
	public Statement inverse() {
		return TrueStatement.INSTANCE;
	}

}
