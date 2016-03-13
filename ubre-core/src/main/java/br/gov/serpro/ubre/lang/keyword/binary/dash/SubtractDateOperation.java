package br.gov.serpro.ubre.lang.keyword.binary.dash;

import java.util.Calendar;
import java.util.Date;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.data.var.Datex;
import br.gov.serpro.ubre.lang.data.literal.DateStatement;
import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

public class SubtractDateOperation implements Operation {

	public Statement perform(DataContainer container, Statement left, Statement right) {

		Date date = (Date) left.result(container);
		Integer dias = (Integer) right.result(container);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, dias.intValue() * -1);

		return new DateStatement(new Datex(cal.getTimeInMillis()));
	}

	public StatementType leftType() {
		return StatementType.DATE;
	}

	public StatementType rightType() {
		return StatementType.NUMERIC;
	}

	public StatementType resultType() {
		return StatementType.DATE;
	}

	public int precedence() {
		return 5;
	}

}
