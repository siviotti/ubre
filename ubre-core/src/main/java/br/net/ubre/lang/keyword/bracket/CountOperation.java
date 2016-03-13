package br.net.ubre.lang.keyword.bracket;

import java.math.BigDecimal;
import java.util.List;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.DecimalStatement;
import br.net.ubre.lang.data.literal.NullStatement;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Operação de contagem
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since (revisão 19/10/2015)
 */
public class CountOperation implements Operation {

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		if (left.resultType().equals(StatementType.TUPLE)) {
			List list = (List) left.result(container);
			return new DecimalStatement(new BigDecimal(list.size()));
		}
		Object obj = left.result(container);
		if (obj == null) {
			return new NullStatement();
		}
		return new DecimalStatement(new BigDecimal(obj.toString().length()));
	}

	public StatementType leftType() {
		return StatementType.STRING;
	}

	public StatementType rightType() {
		return StatementType.VOID;
	}

	public StatementType resultType() {
		return StatementType.NUMERIC;
	}

	public int precedence() {
		return 1;
	}

}
