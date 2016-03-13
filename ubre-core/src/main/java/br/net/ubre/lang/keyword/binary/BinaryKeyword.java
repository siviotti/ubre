package br.net.ubre.lang.keyword.binary;

import br.net.ubre.lang.keyword.KeywordStatement;
import br.net.ubre.lang.statement.Statement;

/**
 * Superclasse para as keywords binárias. [Statement TOKEN Statement].
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public abstract class BinaryKeyword extends KeywordStatement {

	public BinaryKeyword(String token) {
		super(token);
	}

	/**
	 * valida os tipos dos elementos ligados aos dois lados.
	 * 
	 * @see br.net.ubre.lang.statement.Statement#validate()
	 */
	public void link(Statement left, Statement right) {
		this.left = left;
		this.right = right;
		checkType(LEFT, leftType(), left.resultType());
		checkType(RIGHT, rightType(), right.resultType());
	}

	@Override
	public boolean isLiteral() {
		return left.isLiteral() && right.isLiteral();
	}

}
