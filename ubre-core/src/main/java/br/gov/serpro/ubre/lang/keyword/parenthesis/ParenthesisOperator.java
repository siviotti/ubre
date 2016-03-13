package br.gov.serpro.ubre.lang.keyword.parenthesis;

import br.gov.serpro.ubre.lang.keyword.KeywordStatement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Operador de parênteses.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 17/04/2015
 * 
 */
public abstract class ParenthesisOperator extends KeywordStatement {
	
	public ParenthesisOperator(String token) {
		super(token);
	}

	public StatementType resultType() {
		return StatementType.OBJECT;
	}

	@Override
	public int precedence() {
		return 17;
	}

}
