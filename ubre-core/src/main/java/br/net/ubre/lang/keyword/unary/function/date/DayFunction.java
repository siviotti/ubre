package br.net.ubre.lang.keyword.unary.function.date;

import java.util.Calendar;

/**
 * Função que extrai o "dia" de uma data.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 19/10/2015
 */
public class DayFunction extends PartDateFunction {

	public DayFunction(String token) {
		super(token);
	}

	@Override
	protected int getField() {
		return Calendar.DAY_OF_MONTH;
	}

}
