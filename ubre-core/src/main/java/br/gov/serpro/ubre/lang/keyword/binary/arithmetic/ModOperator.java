package br.gov.serpro.ubre.lang.keyword.binary.arithmetic;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.operation.arithmetic.integer.ModIntegerOperation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * @author Clecio Lopes (080.685.887.79)
 * @version 26/03/2015
 * 
 */
public class ModOperator extends NumericOperator {

	private Operation operation = ModIntegerOperation.INSTANCE;

	public ModOperator(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		return operation.perform(container, left, right);
	}

	@Override
	public StatementType resultType() {
		return StatementType.INTEGER;
	}

	@Override
	public StatementType leftType() {
		return StatementType.INTEGER;
	}

	@Override
	public StatementType rightType() {
		return StatementType.INTEGER;
	}
}
