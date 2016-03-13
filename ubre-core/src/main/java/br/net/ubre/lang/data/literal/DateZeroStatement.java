package br.net.ubre.lang.data.literal;

import br.net.ubre.data.var.Datex;

/**
 * Statement literal para a data "zero" com ano, mês e dia zerados.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 28/10/2015
 */
public class DateZeroStatement extends DateStatement {

	public static final DateZeroStatement INSTANCE = new DateZeroStatement();

	private DateZeroStatement() {
		super(new Datex(0, 0, 0));
	}

}
