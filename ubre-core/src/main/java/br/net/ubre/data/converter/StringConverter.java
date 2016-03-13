package br.net.ubre.data.converter;

import br.net.ubre.exception.CTDException;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 *
 */
public class StringConverter implements Converter{

	public String asString(Object object) {
		if (object == null){
			return null;
		}
		if (!(object instanceof String)) {
			throw new CTDException(
					"Erro de conversão. Tipo esperado: String tipo encontrado:"
							+ object.getClass().getSimpleName());
		}
		return object.toString();
	}

	public Object asObject(String string) {
		return string;
	}

}
