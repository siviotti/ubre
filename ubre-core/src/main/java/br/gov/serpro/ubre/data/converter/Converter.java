package br.gov.serpro.ubre.data.converter;

/**
 * Converte Strng para valor e vice-versa.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public interface Converter {

	/**
	 * Converte um objeto para String aplicando um eventual formato.
	 * 
	 * @param object
	 *            O objeto a ser convertido.
	 * @param format
	 *            O formato usado na conversão.
	 * @return A String correspondente ao objeto no formato especificado,.
	 */
	String asString(Object object);

	/**
	 * Converte uma String para um objeto de acordo com o tipo e o formato.
	 * 
	 * @param string
	 *            A String que dará origem ao objeto.
	 * @param format
	 *            O formato usado na conversão.
	 * @return Uma instância do objeto convertido ou <code>null</code>.
	 */
	Object asObject(String string);

}
