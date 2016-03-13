package br.net.ubre.lang.keyword.binary.dash;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.IntegerStatement;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

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
