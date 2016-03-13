package br.gov.serpro.ubre.lang.keyword.unary.function.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.IntegerStatement;
import br.gov.serpro.ubre.lang.data.literal.IntegerZeroStatement;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Funções de data que extraem uma parte específica da Data.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 19/10/2015
 */
public abstract class PartDateFunction extends DateFunction{
	
	public PartDateFunction(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		Date date = getDate(container);
		if (date == null) {
			return IntegerZeroStatement.INSTANCE;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return new IntegerStatement(calendar.get(getField()));
	}

	protected abstract int getField();

	public StatementType resultType() {
		return StatementType.INTEGER;
	}


}
