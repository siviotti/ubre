package br.gov.serpro.ubre.lang.keyword.parenthesis;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 20/04/2015
 *
 */
public class OpenParenthesisOperator extends ParenthesisOperator{

	public OpenParenthesisOperator(String token) {
		super(token);
	}

	public StatementType leftType() {
		return StatementType.VOID;
	}

	public StatementType rightType() {
		return StatementType.OBJECT;
	}

	public Statement perform(DataContainer container) {
		return right;
	}

	/* (non-Javadoc)
	 * @see br.gov.serpro.ctd.lang.keyword.parenthesis.ParenthesisOperator#resultType()
	 */
	@Override
	public StatementType resultType() {
		return right.resultType();
	}

	@Override
	public boolean isLiteral() {
		return right.isLiteral();
	}
	
}
