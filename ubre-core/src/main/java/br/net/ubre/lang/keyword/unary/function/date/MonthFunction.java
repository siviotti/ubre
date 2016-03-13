package br.net.ubre.lang.keyword.unary.function.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.IntegerStatement;
import br.net.ubre.lang.data.literal.IntegerZeroStatement;
import br.net.ubre.lang.statement.Statement;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 26/10/2015
 */
public class MonthFunction extends PartDateFunction {

	public MonthFunction(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		Date date = getDate(container);
		if (date == null) {
			return IntegerZeroStatement.INSTANCE;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return new IntegerStatement(calendar.get(Calendar.MONTH) + 1);
	}

	@Override
	protected int getField() {
		return Calendar.MONTH;
	}

}
