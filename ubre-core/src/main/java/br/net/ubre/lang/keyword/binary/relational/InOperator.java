package br.net.ubre.lang.keyword.binary.relational;

import java.util.List;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.FalseStatement;
import br.net.ubre.lang.data.literal.TrueStatement;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Operador para testes de presença em listas (contido/não contido).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class InOperator extends RelationalKeyword {

	public InOperator(String token) {
		super(token);

	}

	public Statement perform(DataContainer container) {
		Object leftResult = left.result(container);
		if (leftResult == null) {
			return FalseStatement.INSTANCE;
		}
		List list = (List) right.result(container);
		if (list == null) {
			return FalseStatement.INSTANCE;
		}
		if (list != null && list.contains(leftResult)) {
			return TrueStatement.INSTANCE;
		}
		return FalseStatement.INSTANCE;
	}

	@Override
	public StatementType rightType() {
		return StatementType.TUPLE;
	}

}
