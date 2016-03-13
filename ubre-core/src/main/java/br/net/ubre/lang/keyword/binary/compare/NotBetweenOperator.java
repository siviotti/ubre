package br.net.ubre.lang.keyword.binary.compare;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.PairStatement;
import br.net.ubre.lang.data.literal.BooleanStatement;
import br.net.ubre.lang.data.literal.FalseStatement;
import br.net.ubre.lang.data.literal.TrueStatement;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Operador que testa se um determinado valor não está entre o primeiro e o
 * último de um par (Pair).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 27/03/2015
 * 
 * @author Clecio Lopes (080.685.887-79)
 * @version 27/03/2015
 * 
 */

public class NotBetweenOperator extends BetweenOperator {

	public NotBetweenOperator(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		return ((BooleanStatement) super.perform(container)).inverse();
	}

}
