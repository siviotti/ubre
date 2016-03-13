package br.net.ubre.lang.operation.arithmetic.integer;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.IntegerStatement;
import br.net.ubre.lang.operation.IntegerOperation;
import br.net.ubre.lang.operation.arithmetic.DivisionByZeroException;
import br.net.ubre.lang.statement.Statement;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 19/10/2015
 */
public class ModIntegerOperation extends IntegerOperation{
	
	public static final ModIntegerOperation INSTANCE = new ModIntegerOperation();

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Integer r1 = safeResult(container, left);
		Integer r2 = safeResult(container, right);
		if (r2 == 0) {
			throw new DivisionByZeroException("Mod por zero: " + r1 + " / " + r2 + ".");
		}
		return new IntegerStatement(r1 % r2);
	}

	public int precedence() {
		return 4;
	}

	public Integer neutralResult() {
		return INTEGER_ONE;
	}

}
