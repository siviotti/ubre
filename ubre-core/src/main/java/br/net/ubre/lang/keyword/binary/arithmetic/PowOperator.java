package br.net.ubre.lang.keyword.binary.arithmetic;

import static br.net.ubre.lang.statement.StatementType.INTEGER;

import java.math.BigDecimal;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.LiteralStatement;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.operation.arithmetic.decimal.PowDecimalOperation;
import br.net.ubre.lang.operation.arithmetic.integer.PowIntegerOperation;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Clecio Lopes (080.685.887.79)
 * @author Douglas Siviotti (23/10/2015)
 * @version 26/03/2015
 * 
 *          ** X^(A+B)= (X^A) * (X^B) **
 */
public class PowOperator extends NumericOperator {

	private Operation operation;

	public PowOperator(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		return operation.perform(container, left, right);
	}

	@Override
	public void link(Statement left, Statement right) {
		if (left.resultType().equals(INTEGER)
				&& right.resultType().equals(INTEGER)) {
			operation = PowIntegerOperation.INSTANCE;
		} else {
			operation = PowDecimalOperation.INSTANCE;
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
