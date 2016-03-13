package br.net.ubre.lang.data.literal;

import br.net.ubre.data.var.Datex;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 28/10/2015
 */
public class DateStatement extends LiteralStatement {


	public DateStatement(String token) {
		super(token, StatementType.DATE, new Datex(token));
	}

	public DateStatement(Datex datex) {
		super(datex.toString(), StatementType.DATE, datex);
	}

}
