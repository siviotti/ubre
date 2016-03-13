package br.gov.serpro.ubre.lang.keyword.binary.relational;

import br.gov.serpro.ubre.lang.keyword.KeywordStatement;
import br.gov.serpro.ubre.lang.keyword.binary.BinaryKeyword;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 *
 */
public abstract class RelationalKeyword extends BinaryKeyword{

	public RelationalKeyword(String token) {
		super(token);
	}

	public StatementType leftType() {
		return StatementType.OBJECT;
	}

	public StatementType rightType() {
		return StatementType.OBJECT;
	}

	public StatementType resultType() {
		return StatementType.BOOLEAN;
	}

	@Override
	public int precedence() {
		return 8;
	}

}
