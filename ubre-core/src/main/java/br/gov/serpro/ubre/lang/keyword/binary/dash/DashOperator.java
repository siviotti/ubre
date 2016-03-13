package br.gov.serpro.ubre.lang.keyword.binary.dash;

import static br.gov.serpro.ubre.lang.statement.StatementType.DATE;
import static br.gov.serpro.ubre.lang.statement.StatementType.INTEGER;
import static br.gov.serpro.ubre.lang.statement.StatementType.NUMERIC;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.LangError;
import br.gov.serpro.ubre.lang.LangException;
import br.gov.serpro.ubre.lang.keyword.KeywordStatement;
import br.gov.serpro.ubre.lang.keyword.MultiOperationKeyword;
import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.operation.arithmetic.decimal.SubtractDecimalOperation;
import br.gov.serpro.ubre.lang.operation.arithmetic.integer.SubtractIntegerOperation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

public class DashOperator extends KeywordStatement implements
		MultiOperationKeyword {

	private Operation operation;

	public DashOperator(String token) {
		super(token);

	}

	public Statement perform(DataContainer container) {
		return operation.perform(container, left, right);
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

	@Override
	public int precedence() {
		return operation.precedence();
	}

	public void defineOperation(Statement left, Statement right) {
		if (left == null || left instanceof KeywordStatement) {
			operation = new NegateOperation();
		} else if (left.resultType().equals(INTEGER)
				&& right.resultType().equals(INTEGER)) {
			operation = SubtractIntegerOperation.INSTANCE;
		} else if (NUMERIC.isComparableTo(left.resultType())) {
			operation = SubtractDecimalOperation.INSTANCE;
		} else if (left.resultType().equals(DATE)
				&& right.resultType().equals(INTEGER)) {
			operation = new SubtractDateOperation();
		} else {
			throw new LangException(LangError.E31,
					"Uso incorreto do operador -");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.gov.serpro.ctd.lang.keyword.KeywordStatement#link(br.gov.serpro.ctd
	 * .lang.Statement, br.gov.serpro.ctd.lang.Statement)
	 */
	@Override
	public void link(Statement left, Statement right) {
		if (operation instanceof NegateOperation) { // Negate
			left = null;
			if (right.resultType().equals(INTEGER)) {
				operation = new IntegerNegateOperation();
			}
		}
		super.link(left, right);
	}

	@Override
	public boolean isLiteral() {
		if (left != null) {
			return left.isLiteral() && right.isLiteral();
		}
		return right.isLiteral();
	}

}
