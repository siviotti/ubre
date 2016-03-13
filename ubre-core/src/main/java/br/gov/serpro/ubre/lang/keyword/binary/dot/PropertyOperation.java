package br.gov.serpro.ubre.lang.keyword.binary.dot;

import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Operação genérica das propriedades.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 *
 */
public abstract class PropertyOperation implements Operation {
	
	@Override
	public StatementType leftType() {
		return StatementType.OBJECT;
	}

	@Override
	public StatementType rightType() {
		return StatementType.OBJECT;
	}


	@Override
	public int precedence() {
		return 0;
	}

}
