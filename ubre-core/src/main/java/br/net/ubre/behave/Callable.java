package br.net.ubre.behave;

/**
 * Interface que define os métodos de um objeto executável, compilável e que
 * também pode ser invocado a partir de seu token.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 13/10/2015
 */
public interface Callable extends Executable, Buildable {

	/**
	 * Token (palavra) que identifica unicamente um elemento dentro de um
	 * contexto de possíveis chamadas.
	 * 
	 * @return A String que representa o identificador de chamada.
	 */
	String getToken();

	/**
	 * Retorna o tipo do Callable.
	 * 
	 * @return O tipo de <code>CallableType</code>.
	 */
	CallableType getType();
}
