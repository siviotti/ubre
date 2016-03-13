package br.net.ubre.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Mapa congelável.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 29/05/2015
 *
 * 
 */
public class FreezableMap<K, V> extends GenericFreezable implements Map<K, V>{
	
	private Map<K, V> internalMap = new HashMap<K, V>();

	public int size() {
		return internalMap.size();
	}

	public boolean isEmpty() {
		return internalMap.isEmpty();
	}

	public boolean containsKey(Object key) {
		return internalMap.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return internalMap.containsValue(value);
	}

	public V get(Object key) {
		return internalMap.get(key);
	}

	public V put(K key, V value) {
		testFrozen();
		return internalMap.put(key, value);
	}

	public V remove(Object key) {
		testFrozen();
		return internalMap.remove(key);
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		testFrozen();
		internalMap.putAll(m);
	}

	public void clear() {
		testFrozen();
		internalMap.clear();
	}

	public Set<K> keySet() {
		return new HashSet<K>(internalMap.keySet());
	}

	public Collection<V> values() {
		return new HashSet<V>(internalMap.values());
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return new HashSet<java.util.Map.Entry<K, V>>(internalMap.entrySet());
	}

}
