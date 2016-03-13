package br.gov.serpro.ubre.lang.keyword.binary.logical;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.FalseStatement;
import br.gov.serpro.ubre.lang.data.literal.TrueStatement;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class OrOperator extends LogicalKeyword {

	public OrOperator(String token) {
		super(token);
	}

	@Override
	public int precedence() {
		return 13;
	}

	public Statement perform(DataContainer container) {
		if ((Boolean) left.result(container) || (Boolean) right.result(container)) {
			return TrueStatement.INSTANCE;
		}
		return FalseStatement.INSTANCE;
	}
}
