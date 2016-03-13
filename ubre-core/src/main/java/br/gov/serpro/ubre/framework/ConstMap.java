package br.gov.serpro.ubre.framework;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.gov.serpro.ubre.exception.FreezeException;
import br.gov.serpro.ubre.util.Freezable;

/**
 * Mapa de constantes que pode ser congelado.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class ConstMap implements Map<String, Constant>, Freezable {

	private Map<String, Constant> constants = new HashMap<String, Constant>();

	private boolean frozen;

	public void freeze() {
		frozen = true;
	}

	public boolean isFrozen() {
		return frozen;
	}

	private void testFrozen() {
		if (frozen) {
			throw new FreezeException(getClass());
		}
	}

	/**
	 * Adiciona uma constante
	 * 
	 * @param constant
	 *            A constante a ser adicionada.
	 */
	public void add(Constant constant) {
		put(constant.getId(), constant);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.HashMap#clear()
	 */
	public void clear() {
		testFrozen();
		constants.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	public Constant put(String key, Constant value) {
		testFrozen();
		return constants.put(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.HashMap#putAll(java.util.Map)
	 */
	public void putAll(Map<? extends String, ? extends Constant> m) {
		testFrozen();
		constants.putAll(m);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.HashMap#remove(java.lang.Object)
	 */
	public Constant remove(Object key) {
		testFrozen();
		return constants.remove(key);
	}

	// Repassa para Hashmap

	public boolean containsKey(Object key) {
		return constants.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return constants.containsValue(value);
	}

	public Set<java.util.Map.Entry<String, Constant>> entrySet() {
		return constants.entrySet();
	}

	public Constant get(Object key) {
		return constants.get(key);
	}

	public boolean isEmpty() {
		return constants.isEmpty();
	}

	public Set<String> keySet() {
		return constants.keySet();
	}

	public int size() {
		return constants.size();
	}

	public Collection<Constant> values() {
		return constants.values();
	}

}
