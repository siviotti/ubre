package br.gov.serpro.ubre.lang.keyword.binary.dash;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.IntegerStatement;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Negação para inteiros.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 08/05/2015
 * 
 */
public class IntegerNegateOperation extends NegateOperation {

	public Statement perform(DataContainer container, Statement left, Statement right) {
		Integer number = (Integer) right.result(container);
		return new IntegerStatement(-(number));
	}

	public StatementType leftType() {
		return StatementType.VOID;
	}

	public StatementType rightType() {
		return StatementType.INTEGER;
	}

	public StatementType resultType() {
		return StatementType.INTEGER;
	}

}
