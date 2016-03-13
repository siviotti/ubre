package br.net.ubre.lang.keyword.bracket;

import static br.net.ubre.lang.statement.StatementType.TUPLE;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.LangError;
import br.net.ubre.lang.LangException;
import br.net.ubre.lang.data.PairStatement;
import br.net.ubre.lang.data.TupleStatement;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since (revisão 28/10/2015)
 */
public class TupleSliceOperation implements Operation {

	public Statement perform(DataContainer container, Statement left,
			Statement right) {

		TupleStatement tupleStatement = (TupleStatement) left
				.perform(container);
		PairStatement pair = (PairStatement) right.perform(container);
		Integer b1Size = tupleStatement.size();

		int p1 = ((Integer) pair.getLeft().result(container)).intValue();
		int p2 = ((Integer) pair.getRight().result(container)).intValue();
		int tmpP1 = p1;
		int tmpP2 = p2;

		if (p1 < 0) {
			p1 = b1Size + p1;
		}

		/*
		 * O zero no fim será interpretado como size ** p2<1 ** (p2<0) [0:0] ->
		 * tudo
		 */
		if (p2 < 1) {
			p2 = b1Size - Math.abs(p2);
		}

		if (p1 > p2 || p2 > b1Size || p1 < 0) {
			throw new LangException(LangError.E41, "Valores inválidos: ["
					+ tmpP1 + ":" + tmpP2 + "]");
		}

		return new TupleStatement(tupleStatement.subList(p1, p2));
	}

	public StatementType leftType() {
		return TUPLE;
	}

	public StatementType rightType() {
		return StatementType.PAIR;
	}

	public StatementType resultType() {
		return TUPLE;
	}

	public int precedence() {
		return 1;
	}

}
