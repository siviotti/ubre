package br.gov.serpro.ubre.lang.keyword.binary.compare;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.FalseStatement;
import br.gov.serpro.ubre.lang.data.literal.TrueStatement;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class GreaterOperator extends CompareKeyword {

	public GreaterOperator(String token) {
		super(token);

	}

	public Statement perform(DataContainer container) {
		Object leftResult = left.result(container);
		Object rightResult = right.result(container);
		if (isNull(leftResult, rightResult)) {
			return FalseStatement.INSTANCE;
		}
		if (comparation.compare(leftResult, rightResult) > 0) {
			return TrueStatement.INSTANCE;
		} else {
			return FalseStatement.INSTANCE;
		}

	}
}
