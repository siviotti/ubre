package br.gov.serpro.ubre.lang.keyword.bracket;

import static br.gov.serpro.ubre.lang.statement.StatementType.DATE;
import static br.gov.serpro.ubre.lang.statement.StatementType.INTEGER;
import static br.gov.serpro.ubre.lang.statement.StatementType.PAIR;
import static br.gov.serpro.ubre.lang.statement.StatementType.STRING;
import static br.gov.serpro.ubre.lang.statement.StatementType.TUPLE;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.LangError;
import br.gov.serpro.ubre.lang.LangException;
import br.gov.serpro.ubre.lang.data.PairStatement;
import br.gov.serpro.ubre.lang.keyword.KeywordStatement;
import br.gov.serpro.ubre.lang.operation.NoOperation;
import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Classe de abertura de Colchete que também á a classe que é lincada com o
 * interior do colchete.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @version 27/04/2015 (revisado em 10/10/2015)
 * 
 */
public class OpenBracketOperator extends KeywordStatement {

	private Operation operation;

	public OpenBracketOperator(String token) {
		super(token);
	}

	@Override
	public void link(Statement left, Statement right) {

		if (operation != null) {
			return;
		}

		if (right.resultType().equals(PAIR)) {
			if (left.resultType().equals(STRING)) {
				operation = new StrSliceOperation();
			} else if (left.resultType().equals(TUPLE)) {
				operation = new TupleSliceOperation();
			} else {
				throw new LangException(LangError.E31,
						"Uso incorreto do operador []");
			}
		} else if (right.resultType().equals(INTEGER)) {
			if (left.resultType().equals(STRING)) {
				operation = new StrIndexOperation();
			} else if (left.resultType().equals(TUPLE)) {
				operation = new TupleIndexOperation();
			} else if (left.resultType().equals(DATE)) {
				operation = new DateIndexOperation();
			} else {
				throw new LangException(LangError.E31,
						"Uso incorreto do operador []");
			}
		} else {
			throw new LangException(LangError.E31,
					"Uso incorreto do operador []");
		}
		super.link(left, right);
	}

	public StatementType leftType() {
		return operation.leftType();
	}

	public StatementType rightType() {
		return operation.rightType();
	}

	public StatementType resultType() {
		return operation.resultType();
	}

	public Statement perform(DataContainer container) {
		return operation.perform(container, left, right);
	}

	@Override
	public int precedence() {
		return operation.precedence();
	}
	
	@Override
	public boolean isLiteral() {
		return right.isLiteral();
	}

}
