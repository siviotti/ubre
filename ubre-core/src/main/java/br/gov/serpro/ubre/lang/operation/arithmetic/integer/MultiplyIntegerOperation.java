package br.gov.serpro.ubre.lang.operation.arithmetic.integer;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.IntegerStatement;
import br.gov.serpro.ubre.lang.operation.IntegerOperation;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * Multiplicação de Inteiros.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 13/10/2015
 */
public class MultiplyIntegerOperation extends IntegerOperation {
	
	public static final MultiplyIntegerOperation INSTANCE = new MultiplyIntegerOperation();

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Integer r1 = safeResult(container, left);
		Integer r2 = safeResult(container, right);
		return new IntegerStatement(r1 * r2);
	}

	public int precedence() {
		return 4;
	}
	
	public Integer neutralResult() {
		return INTEGER_ONE;
	}

}
