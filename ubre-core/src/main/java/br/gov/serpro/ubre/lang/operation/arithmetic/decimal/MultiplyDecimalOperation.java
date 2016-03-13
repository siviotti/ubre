package br.gov.serpro.ubre.lang.operation.arithmetic.decimal;

import java.math.BigDecimal;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.DecimalStatement;
import br.gov.serpro.ubre.lang.operation.DecimalOperation;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * Multiplicação de Decimais ou decimais com inteiros.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 13/10/2015
 */
public class MultiplyDecimalOperation extends DecimalOperation {
	
	public static final MultiplyDecimalOperation INSTANCE = new MultiplyDecimalOperation();

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Number r1 = safeResult(container, left);
		Number r2 = safeResult(container, right);
		BigDecimal big = new BigDecimal(r1.doubleValue() * r2.doubleValue());
		return new DecimalStatement(big);
	}

	public int precedence() {
		return 4;
	}

	public BigDecimal neutralResult() {
		return DECIMAL_ONE;
	}

}
