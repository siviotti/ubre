package br.gov.serpro.ubre.lang.operation.arithmetic.integer;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.IntegerStatement;
import br.gov.serpro.ubre.lang.operation.IntegerOperation;
import br.gov.serpro.ubre.lang.operation.arithmetic.DivisionByZeroException;
import br.gov.serpro.ubre.lang.statement.Statement;

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
