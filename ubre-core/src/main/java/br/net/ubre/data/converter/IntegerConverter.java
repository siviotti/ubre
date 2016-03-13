package br.net.ubre.data.converter;

import br.net.ubre.exception.CTDException;

/**
 * Convete String/Integer/String.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class IntegerConverter implements Converter {

	public String asString(Object object) {
		if (object == null) {
			return null;
		}
		if (!(object instanceof Integer)) {
			throw new CTDException(
					"Erro de conversão. Tipo esperado: Integer tipo encontrado:"
							+ object.getClass().getSimpleName());
		}
		return object.toString();
	}

	public Object asObject(String string) {
		if (string == null) {
			return new Integer("0");
		}
		return new Integer(string);
	}

}
