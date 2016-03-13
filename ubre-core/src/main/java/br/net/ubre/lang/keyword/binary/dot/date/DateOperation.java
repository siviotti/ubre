package br.net.ubre.lang.keyword.binary.dot.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.IntegerStatement;
import br.net.ubre.lang.data.literal.IntegerZeroStatement;
import br.net.ubre.lang.keyword.binary.dot.PropertyOperation;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Operação genérica para extração departe de uma data.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 29/10/2015
 */
public abstract class DateOperation extends PropertyOperation {
	
	
	@Override
	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Date date = (Date) left.result(container);
		if (date == null) {
			return IntegerZeroStatement.INSTANCE;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return new IntegerStatement(calendar.get(getField()));	}

	protected abstract int getField();

	@Override
	public StatementType leftType() {
		return StatementType.DATE;
	}

	@Override
	public StatementType rightType() {
		return StatementType.INTEGER;
	}

	@Override
	public StatementType resultType() {
		return StatementType.INTEGER;
	}


}
