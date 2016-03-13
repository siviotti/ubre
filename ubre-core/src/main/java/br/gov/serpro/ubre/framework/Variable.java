package br.gov.serpro.ubre.framework;

import java.util.Date;

import br.gov.serpro.ubre.data.map.DataMap;
import br.gov.serpro.ubre.data.var.ValueType;
import br.gov.serpro.ubre.lang.expression.Expression;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 20/05/2015
 * 
 */
public class Variable extends DataElement {

	public Variable(String id, String label, ValueType type, Object value,
			Date begin, Date end, String tags) {
		super(id, label, type, value, begin, end, tags);
	}

	public Variable(String id, String label, Expression expression,
			DataMap dataMap, Date begin, Date end, String tags) {
		super(id, label, expression, dataMap, begin, end, tags);
	}

	public Variable(String id, ValueType integer, Object initValue) {
		this(id, id, integer, initValue, null, null, null);
	}

	public String toDebug() {
		return "  VAR   " + getId() + "(" + getType() + ")="
				+ getType().getConverter().asString(getValue());
	}

}
