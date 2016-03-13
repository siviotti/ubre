package br.gov.serpro.ubre.data.var;


/**
 * INterface que define os métodos de identificação e tipagem de um dado dentro
 * de um container.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 14/10/2015
 */
public interface Var extends Stringable{

	/**
	 * O token que identifica um dado em um Container.
	 * 
	 * @return A String do token.
	 */
	String getToken();

	/**
	 * Informa o tipo do campo.
	 * 
	 * @return O tipo do campo.
	 */
	ValueType getType();

	/**
	 * Retorna o valor do campo.
	 * 
	 * @return o valor do campo como ele é armazenado internamente.
	 */
	Object getValue();

	/**
	 * Seta o valor do campo da forma como é armazeando internamente. Recebe o
	 * valor do campo como instância de uma classe associada ao tipo.
	 * 
	 * @param value
	 *            the value to set
	 */
	void setValue(Object value);


}
