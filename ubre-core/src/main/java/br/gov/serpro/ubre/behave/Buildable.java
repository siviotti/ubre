package br.gov.serpro.ubre.behave;

import br.gov.serpro.ubre.slang.Slang;

/**
 * Interface que define os métodos de um objeto capaz de ser construído
 * (compilado).
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 13/10/2015
 */
public interface Buildable {

	/**
	 * Informa se o objeto já foi construído (compilado).
	 * 
	 * @return <code>true</code> se já foi compilado ou <code>false</code> se
	 *         ainda não foi.
	 */
	boolean isBuilt();

	/**
	 * Compila o objeto com o auxílio de uma instância de SLang (engine da
	 * linguagem de Script).
	 * 
	 * @param slang
	 *            A instância de SLang que ajudará no processo de compilação.
	 */
	void build(Slang slang);

}
