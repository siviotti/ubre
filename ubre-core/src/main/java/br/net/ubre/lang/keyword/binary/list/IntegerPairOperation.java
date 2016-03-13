package br.net.ubre.lang.keyword.binary.list;

import br.net.ubre.lang.statement.StatementType;

/**
 * Oparação para par formado por dois inteiros.Normalmenteusado em colchetes.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 19/10/2015
 */
public class IntegerPairOperation extends PairOperation {

	public StatementType leftType() {
		return StatementType.INTEGER;
	}

	public StatementType rightType() {
		return StatementType.INTEGER;
	}

	public StatementType resultType() {
		return StatementType.INTEGER;
	}

}
