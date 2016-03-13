package br.gov.serpro.ubre.data.var;

/**
 * Interface que determina os métodos de um objeto que tem seu conteúdo
 * manipulado através de Strings. Um método para retornar a String
 * correspondente e um para receber a String e armazenar internamente.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 20/10/2015
 */
public interface Stringable {
	/**
	 * Seta o valor do campo a partir de uma String representativa do tipo do
	 * valor interno.
	 * 
	 * @param string
	 *            A String com o valor que o campo deve receber no formato de
	 *            uma String.
	 */
	void setString(String string);

	/**
	 * Retorna o valor interno como String.
	 * 
	 * @return A String que representa o valor interno ou null.
	 */
	String getString();

}
