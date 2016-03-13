package br.gov.serpro.ubre.lang.keyword.binary.logical;

import br.gov.serpro.ubre.lang.keyword.binary.BinaryKeyword;
import br.gov.serpro.ubre.lang.statement.StatementType;

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
