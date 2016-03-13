package br.net.ubre.lang.keyword.bracket;

import static br.net.ubre.lang.statement.StatementType.NUMERIC;
import static br.net.ubre.lang.statement.StatementType.OBJECT;
import static br.net.ubre.lang.statement.StatementType.TUPLE;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.LangError;
import br.net.ubre.lang.LangException;
import br.net.ubre.lang.data.TupleStatement;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since (revisado 28/10/2015)
 */
public class TupleIndexOperation implements Operation {

	private static final String INDEX_OUT_OF_BOUNDS = "Índice inválido:";

	public Statement perform(DataContainer container, Statement left,
			Statement right) {

		TupleStatement tupleStatement = (TupleStatement) left
				.perform(container);
		Integer index = (Integer) right.result(container);
		Integer tupleSize = tupleStatement.size();
		if (index < 0) {
			index = tupleSize + index;
		}
		if (index < 0 || index >= tupleSize) {
			throw new LangException(LangError.E41, INDEX_OUT_OF_BOUNDS + index);
		}
		return tupleStatement.get(index);
	}

	public StatementType leftType() {
		return TUPLE;
	}

	public StatementType rightType() {
		return NUMERIC;
	}

	public StatementType resultType() {
		return OBJECT;
	}

	public int precedence() {
		return 1;
	}

}
