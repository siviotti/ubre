package br.gov.serpro.ubre.lang.operation.arithmetic.date;

import java.util.Calendar;
import java.util.Date;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.data.var.Datex;
import br.gov.serpro.ubre.lang.data.literal.DateStatement;
import br.gov.serpro.ubre.lang.operation.DateOperation;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since (revisado 28/10/2015)
 */
public class AddDateOperation extends DateOperation {

	public static final AddDateOperation INSTANCE = new AddDateOperation();

	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Date date = (Date) left.result(container);
		Integer dias = (Integer) right.result(container);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, dias.intValue());

		return new DateStatement(new Datex(cal.getTimeInMillis()));
	}

	public int precedence() {
		return 5;
	}

}
