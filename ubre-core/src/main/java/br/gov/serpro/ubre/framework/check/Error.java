package br.gov.serpro.ubre.framework.check;

import br.gov.serpro.ubre.data.field.Field;

/**
 * pendência impeditiva.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public class Error extends Pendency{

	public Error(Field field, int index, String message) {
		super(field, index, message);
	}

	@Override
	public boolean isBlock() {
		return true;
	}

}
