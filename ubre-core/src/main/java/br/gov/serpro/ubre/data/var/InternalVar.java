package br.gov.serpro.ubre.data.var;

import br.gov.serpro.ubre.exception.FreezeException;

/**
 * Instância imutável (Freezable) para uso interno do DataMap.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 20/10/2015
 */
public class InternalVar implements Var {

	private String token;
	private ValueType type;
	private Object value;

	InternalVar(String token, ValueType type, Object value) {
		super();
		this.token = token;
		this.type = type;
		this.value = value;
	}

	public String getToken() {
		return token;
	}

	public ValueType getType() {
		return type;
	}

	public void setType(ValueType type) {
		throw new FreezeException(getClass());
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		throw new FreezeException(getClass());
	}

	public void setString(String string) {
		throw new FreezeException(getClass());
	}

	public String getString() {
		return type.getConverter().asString(value);
	}

}
