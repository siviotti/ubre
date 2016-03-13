package br.net.ubre.lang.keyword.binary.plus;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.StringStatement;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class ConcatOperation implements Operation {
	
	public static final ConcatOperation INSTANCE = new ConcatOperation();

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		String s1 = (String) left.result(container);
		if (s1 == null) {
			s1 = "";
		}
		Object rightResult = right.result(container);
		;
		String s2 = (rightResult != null) ? rightResult.toString() : "";
		return new StringStatement(s1.concat(s2));
	}

	public StatementType leftType() {
		return StatementType.STRING;
	}

	public StatementType rightType() {
		return StatementType.OBJECT;
	}

	public StatementType resultType() {
		return StatementType.STRING;
	}

	public int precedence() {
		return 5;
	}

}
