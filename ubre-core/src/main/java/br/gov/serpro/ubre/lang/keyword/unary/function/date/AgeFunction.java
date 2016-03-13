package br.gov.serpro.ubre.lang.keyword.unary.function.date;

import java.util.Date;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.IntegerStatement;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Extrai a idade em anos a partir de uma Data.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since (revisado 19/10/2015)
 */
public class AgeFunction extends DateFunction {

	// 1000 * 60 * 60 * 24* 365.25 seconds / minutes / hours / days / years =
	// 31557600000
	private static final long YEARS = 31557600000l;

	public AgeFunction(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		Date today = new Date();
		Date birth = getDate(container);
		long dif = today.getTime() - birth.getTime();
		return new IntegerStatement(new Integer((int) (dif / YEARS)));
	}

	public StatementType resultType() {
		return StatementType.INTEGER;
	}

}