package br.gov.serpro.ubre.lang.operation.arithmetic.integer;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.IntegerStatement;
import br.gov.serpro.ubre.lang.operation.IntegerOperation;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * Adição entre inteiros.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 29/04/2015
 * 
 */
public class AddIntegerOperation extends IntegerOperation {
	
	public static final AddIntegerOperation INSTANCE = new AddIntegerOperation();
	
	private AddIntegerOperation(){
		
	}

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Integer r1 = safeResult(container, left);
		Integer r2 = safeResult(container, right);
		return new IntegerStatement(r1 + r2);
	}

	public int precedence() {
		return 5;
	}

	public Integer neutralResult() {
		return INTEGER_ZERO;
	}

}
