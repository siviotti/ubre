package br.net.ubre.data.var;


/**
 * Implementação padrão de <code>Var</code>.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * @see Var
 * 
 */
public class StandardVar implements Var {

	private String token;

	private ValueType type;
	private Object value;

	StandardVar(String token, ValueType type) {
		super();
		this.type = type;
		this.token = token;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getToken() {
		return token;
	}

	public ValueType getType() {
		return type;
	}

	public void setString(String string) {
		value = type.getConverter().asObject(string);
	}

	public String getString() {
		return type.getConverter().asString(value);
	}

}
