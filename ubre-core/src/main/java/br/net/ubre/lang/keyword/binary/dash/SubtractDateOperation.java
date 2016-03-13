package br.net.ubre.lang.keyword.binary.dash;

import java.util.Calendar;
import java.util.Date;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.data.var.Datex;
import br.net.ubre.lang.data.literal.DateStatement;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

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
