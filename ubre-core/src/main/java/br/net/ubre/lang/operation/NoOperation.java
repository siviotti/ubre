package br.net.ubre.lang.operation;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.NullStatement;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 19/10/2015
 */
public class NoOperation implements Operation {

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		return NullStatement.INSTANCE;
	}

	public StatementType leftType() {
		return StatementType.VOID;
	}

	public StatementType rightType() {
		return StatementType.VOID;
	}

	public StatementType resultType() {
		return StatementType.VOID;
	}

	public int precedence() {
		return -1;
	}

}
