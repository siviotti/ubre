package br.gov.serpro.ubre.lang.keyword.binary.dot.date;

import java.util.Calendar;

/**Extrai o dia de uma data.
 * @author Douglas Siviotti (073.116.317-69).
 * @since 29/10/2015
 */
public class DayOperation extends DateOperation{

	public static final DayOperation INSTANCE = new DayOperation();

	@Override
	protected int getField() {
		return Calendar.DAY_OF_MONTH;
	}


}
