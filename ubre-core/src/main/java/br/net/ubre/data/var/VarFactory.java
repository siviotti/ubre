package br.net.ubre.data.var;

import br.net.ubre.exception.CTDException;

/**
 * Fábrica de valores.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public class VarFactory {

	private static final String VAR_FACTORY_CANNOT_CREATE = "Impossível criar um valor sem o tipo (null)";

	public StandardVar create(String token, ValueType valueType) {
		if (valueType == null) {
			throw new CTDException(VAR_FACTORY_CANNOT_CREATE);
		}
		return new StandardVar(token, valueType);
	}

	/**
	 * Cria uma instância de InternalVar.
	 * 
	 * @param token
	 *            O token que a identifica.
	 * @param valueType
	 *            O tipo de valor.
	 * @param defaultValue
	 *            O valor defalut.
	 * @return A instãncia de <code>InternalVar</code>.
	 */
	public InternalVar createInternal(String token, ValueType valueType,
			Object defaultValue) {
		return new InternalVar(token, valueType, defaultValue);
	}
}
