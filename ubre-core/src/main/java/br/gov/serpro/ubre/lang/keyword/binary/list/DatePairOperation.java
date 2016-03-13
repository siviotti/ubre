package br.gov.serpro.ubre.lang.keyword.binary.list;

import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Operação do par formado por duas datas. Usado pelo operador between e not
 * between (entre e fora).
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 19/10/2015
 */
public class DatePairOperation extends PairOperation {

	public StatementType leftType() {
		return StatementType.DATE;
	}

	public StatementType rightType() {
		return StatementType.DATE;
	}

	public StatementType resultType() {
		return StatementType.DATE;
	}

}
