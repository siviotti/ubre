package br.net.ubre.framework.check;

import br.net.ubre.data.field.Field;

/**
 * Pendência não impeditiva.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public class Warning extends Pendency {

	public Warning(Field field, int index, String message) {
		super(field, index, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isBlock() {
		return false;
	}

}
