package br.net.ubre.lang.operation.arithmetic.decimal;

import java.math.BigDecimal;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.DecimalStatement;
import br.net.ubre.lang.operation.DecimalOperation;
import br.net.ubre.lang.statement.Statement;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 13/10/2015
 */
public class SubtractDecimalOperation extends DecimalOperation{
	
	public static final SubtractDecimalOperation INSTANCE = new SubtractDecimalOperation();

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Number r1 = safeResult(container, left);
		Number r2 = safeResult(container, right);
		BigDecimal big = new BigDecimal(r1.doubleValue() - r2.doubleValue());
		return new DecimalStatement(big);
	}

	public int precedence() {
		return 5;
	}
	
	public BigDecimal neutralResult() {
		return DECIMAL_ZERO;
	}


}
