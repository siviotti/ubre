package br.net.ubre.lang.operation.arithmetic.decimal;

import java.math.BigDecimal;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.DecimalStatement;
import br.net.ubre.lang.operation.DecimalOperation;
import br.net.ubre.lang.operation.arithmetic.DivisionByZeroException;
import br.net.ubre.lang.statement.Statement;

/**
 * Divisão de decimais.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 13/10/2015
 */
public class DivideDecimalOperation extends DecimalOperation {
	
	public static final DivideDecimalOperation INSTANCE = new DivideDecimalOperation();

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Number r1 = safeResult(container, left);
		Number r2 = safeResult(container, right);
		if (r2.equals(DECIMAL_ZERO)) {
			throw new DivisionByZeroException("Divisão por zero: " + r1 + " / "
					+ r2 + ".");
		}
		BigDecimal big = new BigDecimal(r1.doubleValue() / r2.doubleValue());
		return new DecimalStatement(big);

	}

	public int precedence() {
		return 4;
	}

	public BigDecimal neutralResult() {
		return DECIMAL_ONE;
	}

}
