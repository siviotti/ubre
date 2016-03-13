package br.net.ubre.lang.keyword.unary.function;

import br.net.ubre.lang.keyword.KeywordStatement;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 21/05/2015
 *
 */
public abstract class Function extends KeywordStatement {
	
	protected static final String FUNCTION_INVALID_TYPE = "A função '%s' não pode ser usada sobre valores do tipo '%s'";
	
	public Function(String token) {
		super(token);
	}

	public StatementType leftType() {
		return StatementType.VOID;
	}

	@Override
	public int precedence() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see br.gov.serpro.ctd.lang.keyword.KeywordStatement#link(br.gov.serpro.ctd.lang.Statement, br.gov.serpro.ctd.lang.Statement)
	 */
	@Override
	public void link(Statement left, Statement right) {
		super.link(null, right);
		checkType(RIGHT, rightType(), right.resultType());
	}

	@Override
	public boolean isLiteral() {
		return right.isLiteral();
	}

}
