package br.gov.serpro.ubre.lang.operation.arithmetic.decimal;

import java.math.BigDecimal;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.DecimalStatement;
import br.gov.serpro.ubre.lang.operation.DecimalOperation;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * Adição de decimais.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class AddDecimalOperation extends DecimalOperation {
	
	public static final AddDecimalOperation INSTANCE = new AddDecimalOperation();

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Number r1 = safeResult(container, left);
		Number r2 = safeResult(container, right);
		BigDecimal big = new BigDecimal(r1.doubleValue() + r2.doubleValue());
		return new DecimalStatement(big);
	}

	public int precedence() {
		return 5;
	}

	public Number neutralResult() {
		return DECIMAL_ZERO;
	}

}
