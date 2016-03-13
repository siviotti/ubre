package br.gov.serpro.ubre.behave;

import java.util.HashMap;
import java.util.Map;

import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.util.GenericFreezable;

/**
 * MAPA DE COMPORTAMENTOS. Classe que contém as instância de objetos do tipo
 * <code>Callable</code> que podem ser invocados a partir de seu token
 * identificador que deve ser único.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 15/10/2015
 */
public class BehaveMap extends GenericFreezable {

	public static final String BEHAVEMAP_ALREADY_EXISTS = "Callable já existe no mapa:";
	
	private Map<String, Callable> callables = new HashMap<String, Callable>();

	/**
	 * Adiciona um callable ao Mapa. Gera erro se o mesmo elemento (token) for
	 * passado uma segunda vez.
	 * 
	 * @param callable
	 *            O Callable a ser adicionado.
	 */
	public void add(Callable callable) {
		testFrozen();
		String token = callable.getToken();
		if (callables.containsKey(token)) {
			throw new CTDException(BEHAVEMAP_ALREADY_EXISTS + token);
		}
		callables.put(token, callable);
	}

	/**
	 * Informa se o Mapa já contém um Callable através do seu token.
	 * 
	 * @param token
	 *            O token.
	 * @return <code>true</code> se o Mapa contém ou <code>false</code> se não
	 *         contém.
	 */
	public boolean contains(String token) {
		return callables.containsKey(token);
	}

	/**
	 * Retorna a instância de <code>Caççable</code> a partir de seu token. Se
	 * não encontrar gera um erro.
	 * 
	 * @param token
	 *            O token.
	 * @return A instância de <code>Callable</code> ou um erro.
	 */
	public Callable get(String token) {
		if (callables.containsKey(token)) {
			return callables.get(token);
		}
		throw new CTDException("Callable não encontrado:" + token);
	}

	/**
	 * Retorna o tipo a partir do token.
	 * 
	 * @param token
	 *            O token.
	 * @return O tipo.
	 */
	public CallableType getType(String token) {
		return get(token).getType();
	}
}
