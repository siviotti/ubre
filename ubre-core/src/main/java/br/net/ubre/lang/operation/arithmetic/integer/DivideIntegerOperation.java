package br.net.ubre.lang.operation.arithmetic.integer;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.IntegerStatement;
import br.net.ubre.lang.operation.IntegerOperation;
import br.net.ubre.lang.operation.arithmetic.DivisionByZeroException;
import br.net.ubre.lang.statement.Statement;

/**
 * Divisão de inteiros.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 13/10/2015
 */
public class DivideIntegerOperation extends IntegerOperation {
	
	public static final DivideIntegerOperation INSTANCE = new DivideIntegerOperation();

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Integer r1 = safeResult(container, left);
		Integer r2 = safeResult(container, right);
		if (r2 == 0) {
			throw new DivisionByZeroException("Divisão por zero: " + r1 + " / " + r2 + ".");
		}
		return new IntegerStatement(r1 / r2);
	}

	public int precedence() {
		return 4;
	}
	
	public Integer neutralResult() {
		return INTEGER_ONE;
	}

}
