package br.gov.serpro.ubre.lang.keyword.binary.arithmetic;

import java.util.HashSet;
import java.util.Set;

import br.gov.serpro.ubre.lang.keyword.binary.BinaryKeyword;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public abstract class ArithmeticKeyword extends BinaryKeyword {

	public ArithmeticKeyword(String token) {
		super(token);
	}

	public StatementType leftType() {
		return StatementType.ARITHMETIC;
	}

	public StatementType rightType() {
		return StatementType.ARITHMETIC;
	}

	public StatementType resultType() {
		return StatementType.ARITHMETIC;
	}

	@Override
	public int precedence() {
		return 4;
	}

}
