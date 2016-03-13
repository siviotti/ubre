package br.net.ubre.lang.keyword.unary;

import static br.net.ubre.lang.LangError.E20;

import br.net.ubre.lang.LangException;
import br.net.ubre.lang.keyword.KeywordStatement;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Palavra reservada que usa um membro à direita. [TOKEN Statement].
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public abstract class UnaryKeyword extends KeywordStatement {

	private static final String UNARY_KEYWORD_MEMBERS_ONLY_RIGHT = "Este operador só tem membros à direita.";

	public UnaryKeyword(String token) {
		super(token);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.gov.serpro.ctd.lang.keyword.KeywordStatement#compile(br.gov.serpro
	 * .ctd.lang.Statement, br.gov.serpro.ctd.lang.Statement)
	 */
	@Override
	public void link(Statement left, Statement right) {
		super.link(left, right);
		if (left != null) {
			throw new LangException(E20, UNARY_KEYWORD_MEMBERS_ONLY_RIGHT);
		}
		checkType(RIGHT, rightType(), right.resultType());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.gov.serpro.ctd.lang.Statement#leftType()
	 */
	public StatementType leftType() {
		return StatementType.VOID;
	}
	
	@Override
	public boolean isLiteral() {
		return right.isLiteral();
	}


}
