package br.gov.serpro.ubre.data.converter;

import java.math.BigDecimal;

import br.gov.serpro.ubre.exception.CTDException;

/**
 * Decimal/String/Decimal
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public class DecimalConverter implements Converter {

	public String asString(Object object) {
		if (object == null) {
			return null;
		}
		if (!(object instanceof BigDecimal)) {
			throw new CTDException(
					"Erro de conversão. Tipo esperado: BigDecimal tipo encontrado:"
							+ object.getClass().getSimpleName());
		}
		return ((BigDecimal) object).toPlainString();
	}

	public Object asObject(String string) {
		if (string == null) {
			return new BigDecimal("0.0");
		}
		return new BigDecimal(string);
	}

}
