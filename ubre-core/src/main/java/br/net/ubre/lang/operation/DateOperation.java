package br.net.ubre.lang.operation;

import br.net.ubre.lang.statement.StatementType;

/**
 * Operação genérica para Date.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 20/04/2015
 * 
 */
public abstract class DateOperation implements Operation{

	public StatementType leftType() {
		return StatementType.DATE;
	}

	public StatementType rightType() {
		return StatementType.NUMERIC;
	}

	public StatementType resultType() {
		return StatementType.DATE;
	}


}
