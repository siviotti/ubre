package br.net.ubre.framework;

import java.math.BigDecimal;
import java.util.Date;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.data.container.StandardDataContainer;
import br.net.ubre.data.map.DataMap;
import br.net.ubre.data.var.ValueType;
import br.net.ubre.data.var.Var;
import br.net.ubre.lang.expression.Expression;

/**
 * Superclasse para os elementos que armazenam dados: Variable, Constant e
 * Message.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 15/10/2015
 */
public class DataElement extends ContextElement implements Var {

	private ValueType type;
	private Object value;

	public DataElement(String id, String label, ValueType type, Object value,
			Date begin, Date end, String tags) {
		super(id, label, begin, end, tags);
		this.type = type;
		if (type.equals(ValueType.DECIMAL) && !(value instanceof BigDecimal)) {
			this.value = new BigDecimal(value.toString());
		} else {
			this.value = value;
		}
	}

	public DataElement(String id, String label, Expression expression,
			DataMap dataMap, Date begin, Date end, String tags) {
		super(id, label, begin, end, tags);
		type = expression.getRoot().resultType().getValueType();
		DataContainer container = new StandardDataContainer("id", dataMap);
		value = expression.eval(container);
	}

	/**
	 * @return the type
	 */
	public ValueType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ValueType type) {
		testFrozen();
		this.type = type;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value) {
		testFrozen();
		this.value = value;
	}

	public String getToken() {
		return getId();
	}


	@Override
	public String toString() {
		return (value != null) ? value.toString() : "";
	}

	public void setString(String string) {
		value = type.getConverter().asObject(string);
		
	}

	public String getString() {
		return type.getConverter().asString(value);
	}

}
