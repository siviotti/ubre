package br.gov.serpro.ubre.lang.keyword.bracket;

import static br.gov.serpro.ubre.lang.LangError.E41;
import static br.gov.serpro.ubre.lang.statement.StatementType.DATE;

import java.util.Calendar;
import java.util.Date;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.LangException;
import br.gov.serpro.ubre.lang.data.literal.IntegerStatement;
import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Index de um dos elementos de uma data.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since (revisado 19/10/2015)
 * @see Calendar
 */
public class DateIndexOperation implements Operation {

	private static final String DATE_INDEX_INVALID = "Índice de elemento inválido em uma data: [";

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Date date = (Date) left.result(container);
		Integer index = (Integer) right.result(container);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (index < Calendar.YEAR || index >= Calendar.SECOND) {
			throw new LangException(E41, DATE_INDEX_INVALID + index + "]");
		}

		return new IntegerStatement(calendar.get(index));
	}

	public StatementType leftType() {
		return DATE;
	}

	public StatementType rightType() {
		return StatementType.INTEGER;
	}

	public StatementType resultType() {
		return StatementType.INTEGER;
	}

	public int precedence() {
		return 1;
	}

}
