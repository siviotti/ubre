package br.net.ubre.lang.operation.arithmetic.integer;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.IntegerStatement;
import br.net.ubre.lang.operation.IntegerOperation;
import br.net.ubre.lang.statement.Statement;

/**
 * Subtração de inteiros.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since (revisado 19/10/2015)
 */
public class SubtractIntegerOperation extends IntegerOperation {

	public static final SubtractIntegerOperation INSTANCE = new SubtractIntegerOperation();

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Integer r1 = safeResult(container, left);
		Integer r2 = safeResult(container, right);
		return new IntegerStatement(r1 - r2);
	}

	public int precedence() {
		return 5;
	}

	public Integer neutralResult() {
		return INTEGER_ZERO;
	}

}
