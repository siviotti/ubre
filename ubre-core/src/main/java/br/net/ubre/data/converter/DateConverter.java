package br.net.ubre.data.converter;

import java.text.DateFormat;
import java.util.Date;

import br.net.ubre.data.var.Datex;
import br.net.ubre.exception.CTDException;

/**
 * Converte Date/String/Date.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public class DateConverter implements Converter {

	public String asString(Object object) {
		if (object == null) {
			return null;
		}
		if (object instanceof Datex) {
			return ((Datex) object).asFormatted();
		}
		if ((object instanceof Date)) {
			return DateFormat.getDateInstance().format(object);
		}
		throw new CTDException(object + " não é uma data");
	}


	@Override
	public Object asObject(String string) {
		return new Datex(string);
	}
	

}
