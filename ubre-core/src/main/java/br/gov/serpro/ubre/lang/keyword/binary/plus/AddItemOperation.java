package br.gov.serpro.ubre.lang.keyword.binary.plus;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.TupleStatement;
import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

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
