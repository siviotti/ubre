package br.net.ubre.lang.keyword.binary.logical;

import br.net.ubre.lang.keyword.binary.BinaryKeyword;
import br.net.ubre.lang.statement.StatementType;

/**
 * Operador Lógico Genérico. [Boolean TOKEN Boolean].
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public abstract class LogicalKeyword extends BinaryKeyword {

	public LogicalKeyword(String token) {
		super(token);
	}

	public StatementType leftType() {
		return StatementType.BOOLEAN;
	}

	public StatementType rightType() {
		return StatementType.BOOLEAN;
	}

	public StatementType resultType() {
		return StatementType.BOOLEAN;
	}


}
