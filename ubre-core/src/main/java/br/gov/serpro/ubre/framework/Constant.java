package br.gov.serpro.ubre.framework;

import java.util.Date;

import br.gov.serpro.ubre.data.map.DataMap;
import br.gov.serpro.ubre.data.var.ValueType;
import br.gov.serpro.ubre.lang.expression.Expression;
import br.gov.serpro.ubre.util.Debuggable;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 30/03/2015
 * 
 */
public class Constant extends DataElement implements Debuggable {

	public Constant(String id, String label, ValueType type, Object value,
			Date begin, Date end, String tags) {
		super(id, label, type, value, begin, end, tags);
	}

	public Constant(String id, String label, Expression expression,
			DataMap dataMap, Date begin, Date end, String tags) {
		super(id, label, expression, dataMap, begin, end, tags);
	}

	public Constant(String id, ValueType valueType, Object value) {
		this(id, id, valueType, value, null, null, null);
	}

	public String toDebug() {
		return "  CONST " + getId() + "(" + getType() + ")="
				+ getType().getConverter().asString(getValue());
	}

}
