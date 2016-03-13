package br.gov.serpro.ubre.lang.operation.arithmetic.decimal;

import java.math.BigDecimal;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.DecimalStatement;
import br.gov.serpro.ubre.lang.operation.DecimalOperation;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * Potência entre decimais ou entre inteiros e decimais.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 23/10/2015
 */
public class PowDecimalOperation extends DecimalOperation {
	
	public static final PowDecimalOperation INSTANCE = new PowDecimalOperation();

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Number r1 = safeResult(container, left);
		Number r2 = safeResult(container, right);
		Double d = Math.pow(r1.doubleValue(), r2.doubleValue());
		return new DecimalStatement(new BigDecimal(d.doubleValue()));
	}

	public int precedence() {
		return 5;
	}

	public BigDecimal neutralResult() {
		return DECIMAL_ONE;
	}
}