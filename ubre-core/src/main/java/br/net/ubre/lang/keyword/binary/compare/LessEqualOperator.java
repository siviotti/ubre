package br.net.ubre.lang.keyword.binary.compare;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.FalseStatement;
import br.net.ubre.lang.data.literal.TrueStatement;
import br.net.ubre.lang.statement.Statement;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class LessEqualOperator extends CompareKeyword {

	public LessEqualOperator(String token) {
		super(token);

	}

	public Statement perform(DataContainer container) {
		if (comparation.compare(left.result(container), right.result(container)) <= 0) {
			return TrueStatement.INSTANCE;
		} else {
			return FalseStatement.INSTANCE;
		}

	}
}
