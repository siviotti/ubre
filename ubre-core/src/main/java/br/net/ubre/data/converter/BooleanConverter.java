package br.net.ubre.data.converter;

import java.math.BigDecimal;

import br.net.ubre.exception.CTDException;


/**
 * Converte Boolean/String/Boolean
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public class BooleanConverter implements Converter {

	public String asString(Object object) {
		if (object == null) {
			return null;
		}
		if (!(object instanceof Boolean)) {
			throw new CTDException(
					"Erro de conversão. Tipo esperado: Boolean tipo encontrado:"
							+ object.getClass().getSimpleName());
		}
		return object.toString();
	}

	public Object asObject(String string) {
		if (string == null) {
			return null;
		}
		return new Boolean(string);

	}

}
