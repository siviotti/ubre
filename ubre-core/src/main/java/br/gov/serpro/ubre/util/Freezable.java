package br.gov.serpro.ubre.util;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 20/03/2015
 * 
 */
public interface Freezable {

	/**
	 * Método que aciona o processo de congelamento de um objeto. Após ser
	 * congelado, o objeto não pode ser mais alterado.
	 */
	void freeze();

	/**
	 * Informa se o objeto está ou não congelado.
	 * 
	 * @return
	 */
	boolean isFrozen();
}
