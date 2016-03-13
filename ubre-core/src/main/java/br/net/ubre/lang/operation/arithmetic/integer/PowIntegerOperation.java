package br.net.ubre.lang.operation.arithmetic.integer;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.IntegerStatement;
import br.net.ubre.lang.operation.IntegerOperation;
import br.net.ubre.lang.statement.Statement;

/**
 * Potência entre inteiros.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 23/10/2015
 */
public class PowIntegerOperation extends IntegerOperation {
	
	public static final PowIntegerOperation INSTANCE = new PowIntegerOperation();


	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Integer r1 = safeResult(container, left);
		Integer r2 = safeResult(container, right);
		Double d = Math.pow(r1, r2);
		return new IntegerStatement(d.intValue());
	}

	public int precedence() {
		return 5;
	}

	public Integer neutralResult() {
		return INTEGER_ONE;
	}
}
