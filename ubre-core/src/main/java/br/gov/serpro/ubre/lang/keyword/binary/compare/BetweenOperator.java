package br.gov.serpro.ubre.lang.keyword.binary.compare;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.PairStatement;
import br.gov.serpro.ubre.lang.data.literal.FalseStatement;
import br.gov.serpro.ubre.lang.data.literal.TrueStatement;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Operador que testa se um determinado valor está entre o primeiro e o último
 * de uma par (Pair).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 27/03/2015
 * 
 * @author Clecio Lopes (080.685.887-79)
 * @version 27/03/2015
 * 
 */

public class BetweenOperator extends CompareKeyword {

	public BetweenOperator(String token) {
		super(token);
	}


	public Statement perform(DataContainer container) {

		Object b1 = left.result(container);

		PairStatement pair = (PairStatement) right.perform(container);

		Object begin = pair.getLeft().result(container);
		Object end = pair.getRight().result(container);

		if ((comparation.compare(b1, begin) >= 0)
				&& (comparation.compare(b1, end) <= 0)) {
			return TrueStatement.INSTANCE;
		} else {
			return FalseStatement.INSTANCE;
		}

	}
	public StatementType rightType() {
		return StatementType.PAIR;
	}


	/* (non-Javadoc)
	 * @see br.gov.serpro.ctd.lang.keyword.binary.compare.CompareKeyword#checkCompare(br.gov.serpro.ctd.lang.Statement, br.gov.serpro.ctd.lang.Statement)
	 */
	@Override
	protected void checkCompare(Statement left, Statement right) {
		
	}

}
