package br.gov.serpro.ubre.lang.keyword.binary.dash;

import java.math.BigDecimal;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.DecimalStatement;
import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 07/05/2015
 * 
 */
public class NegateOperation implements Operation{

	public Statement perform(DataContainer container, Statement left, Statement right) {
		BigDecimal number = (BigDecimal) right.result(container);
		return new DecimalStatement(number.negate());
	}

	public StatementType leftType() {
		return StatementType.VOID;
	}

	public StatementType rightType() {
		return StatementType.NUMERIC;
	}

	public StatementType resultType() {
		return StatementType.DECIMAL;
	}

	public int precedence() {
		return 2;
	}

	public Number safeResult(DataContainer container, Statement statement) {
		// TODO Auto-generated method stub
		return null;
	}

	public Number neutralResult() {
		return null;
	}
}
