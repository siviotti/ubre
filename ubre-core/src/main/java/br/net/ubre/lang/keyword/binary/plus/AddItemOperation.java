package br.net.ubre.lang.keyword.binary.plus;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.TupleStatement;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Adiciona um item a uma lista.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 29/10/2015
 */
public class AddItemOperation implements Operation {

	@Override
	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		TupleStatement tupleStatement = (TupleStatement) left;
		tupleStatement.add(right);
		return tupleStatement;
	}

	@Override
	public StatementType leftType() {
		return StatementType.TUPLE;
	}

	@Override
	public StatementType rightType() {
		return StatementType.OBJECT;
	}

	@Override
	public StatementType resultType() {
		return StatementType.TUPLE;
	}

	@Override
	public int precedence() {
		return 5;
	}

}
