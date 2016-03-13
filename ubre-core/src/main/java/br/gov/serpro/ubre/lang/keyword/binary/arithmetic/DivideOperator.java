package br.gov.serpro.ubre.lang.keyword.binary.arithmetic;

import static br.gov.serpro.ubre.lang.statement.StatementType.INTEGER;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.operation.arithmetic.decimal.DivideDecimalOperation;
import br.gov.serpro.ubre.lang.operation.arithmetic.integer.DivideIntegerOperation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * @author Clecio Lopes (080.685.887.79)
 * @version 26/03/2015
 * 
 */
public class DivideOperator extends NumericOperator {

	private Operation operation;

	public DivideOperator(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		return operation.perform(container, left, right);
	}

	@Override
	public void link(Statement left, Statement right) {
		if (left.resultType().equals(INTEGER)
				&& right.resultType().equals(INTEGER)) {
			operation = DivideIntegerOperation.INSTANCE;
		} else {
			operation = DivideDecimalOperation.INSTANCE;
		}
		super.link(left, right);
	}
	
	@Override
	public StatementType resultType() {
		return operation.resultType();
	}

	@Override
	public StatementType leftType() {
		return operation.leftType();
	}

	@Override
	public StatementType rightType() {
		return operation.rightType();
	}


}
