package br.gov.serpro.ubre.lang.data;

import java.util.ArrayList;
import java.util.List;

import br.gov.serpro.ubre.internal.Const;
import br.gov.serpro.ubre.lang.LangError;
import br.gov.serpro.ubre.lang.LangException;
import br.gov.serpro.ubre.lang.data.literal.LiteralStatement;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Tupla de dois.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 27/03/2015
 * 
 */

public class PairStatement extends LiteralStatement {

	private static final String PAIR_DIREFENT_TYPES = "Os dois elementos de um par devem ser do mesmo tipo (";

	private static List<Statement> createList(Statement left, Statement right) {
		List<Statement> list = new ArrayList<Statement>();
		list.add(left);
		list.add(right);
		return list;
	}

	private Statement left;
	private Statement right;

	public PairStatement(Statement left, Statement right) {
		super(left.asToken() + Const.DOT_OPERATOR + right.asToken(),
				StatementType.PAIR, createList(left, right));
		this.left = left;
		this.right = right;
	}

	public Statement getLeft() {
		return left;
	}

	public Statement getRight() {
		return right;
	}

	@Override
	public StatementType leftType() {
		return left.resultType();
	}

	@Override
	public StatementType rightType() {
		return right.resultType();
	}

	public StatementType pairType() {
		return left.resultType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.gov.serpro.ctd.lang.data.DataStatement#link(br.gov.serpro.ctd.lang
	 * .Statement, br.gov.serpro.ctd.lang.Statement)
	 */
	@Override
	public void link(Statement left, Statement right) {
		if (!left.resultType().equals(right.resultType())) {
			throw new LangException(LangError.E31, PAIR_DIREFENT_TYPES + left
					+ ":" + right + ")");
		}
	}

}
