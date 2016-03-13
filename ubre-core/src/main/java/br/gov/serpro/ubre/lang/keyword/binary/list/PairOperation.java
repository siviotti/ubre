package br.gov.serpro.ubre.lang.keyword.binary.list;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.PairStatement;
import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 19/10/2015
 */
public abstract class PairOperation implements Operation{

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		return new PairStatement(left, right);
	}

	public int precedence() {
		return 16;
	}

}
