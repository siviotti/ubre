package br.net.ubre.lang.keyword.binary.arithmetic;

import static br.net.ubre.lang.statement.StatementType.INTEGER;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.operation.arithmetic.decimal.MultiplyDecimalOperation;
import br.net.ubre.lang.operation.arithmetic.integer.MultiplyIntegerOperation;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class MultiplyOperator extends NumericOperator {

	private Operation operation;

	public MultiplyOperator(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		return operation.perform(container, left, right);
	}

	@Override
	public void link(Statement left, Statement right) {
		if (left.resultType().equals(INTEGER)
				&& right.resultType().equals(INTEGER)) {
			operation= MultiplyIntegerOperation.INSTANCE;
		} else {
			operation = MultiplyDecimalOperation.INSTANCE;
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
