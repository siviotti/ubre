package br.gov.serpro.ubre.lang.keyword.unary.function.date;

import java.util.Date;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.keyword.unary.function.Function;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Função genérica de datas.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 19/10/2015
 */
public abstract class DateFunction extends Function {

	public DateFunction(String token) {
		super(token);
	}

	protected Date getDate(DataContainer container){
		return (Date) right.result(container);
	}

	public StatementType rightType() {
		return StatementType.DATE;
	}


}
