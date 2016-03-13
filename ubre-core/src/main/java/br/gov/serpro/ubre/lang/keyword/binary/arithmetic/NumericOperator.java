package br.gov.serpro.ubre.lang.keyword.binary.arithmetic;

import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public abstract class NumericOperator extends ArithmeticKeyword {
	
	public NumericOperator(String token) {
		super(token);
	}

	@Override
	public StatementType resultType() {
		return StatementType.NUMERIC;
	}

	@Override
	public StatementType leftType() {
		return StatementType.NUMERIC;
	}

	@Override
	public StatementType rightType() {
		return StatementType.NUMERIC;
	}

}
