package br.net.ubre.framework.check;

import br.net.ubre.data.field.Field;

/**
 * Uma pendência é algo que não está correto na coleta e deve ser tratado.
 * Algumas são impeditivas para a continuidade da coleta (erros) e outras não
 * (avisos).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public abstract class Pendency {

	private Field field;

	private int index;

	private String message;

	public Pendency(Field field, int index, String message) {
		super();
		this.field = field;
		this.index = index;
		this.message = message;
	}

	/**
	 * Determina se a pendência é ou não impeditiva para prosseguimento da
	 * coleta.
	 * 
	 * @return <code>true>/code> se for impeditivo ou <code>false</code> se não
	 *         for.
	 */
	public abstract boolean isBlock();

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the field
	 */
	public Field getField() {
		return field;
	}

}
