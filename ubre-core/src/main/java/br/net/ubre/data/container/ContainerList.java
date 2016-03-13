package br.net.ubre.data.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import br.net.ubre.exception.CTDException;
import br.net.ubre.framework.Scope;

/**
 * Lista de containers.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 11/06/2015
 * 
 */
public class ContainerList implements List<DataContainer> {

	private static final String SCOPE_CANNOT_BE_NULL = "scope não pode ser null";
	private Scope scope;

	public ContainerList(Scope scope) {
		super();
		if (scope == null) {
			throw new NullPointerException(SCOPE_CANNOT_BE_NULL);
		}
		this.scope = scope;
	}

	private List<DataContainer> list = new ArrayList<DataContainer>();

	private void testScope(Scope scope) {
		if (this.scope.equals(scope)) {
			throw new CTDException("Escopo inválido:" + scope
					+ ". Escopo esperado:" + this.scope);
		}
	}

	public boolean add(DataContainer container) {
		testScope(container.getScope());
		return list.add(container);
	}

	public void add(int index, DataContainer container) {
		testScope(container.getScope());
		list.add(index, container);
	}

	public boolean addAll(Collection<? extends DataContainer> c) {
		return list.addAll(c);
	}

	public boolean addAll(int arg0, Collection<? extends DataContainer> arg1) {
		return list.addAll(arg0, arg1);
	}

	public void clear() {
		list.clear();
	}

	public boolean contains(Object arg0) {
		return list.contains(arg0);
	}

	public boolean containsAll(Collection<?> arg0) {
		return list.containsAll(arg0);
	}

	public DataContainer get(int index) {
		return list.get(index);
	}

	public int indexOf(Object obj) {
		return list.indexOf(obj);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public Iterator<DataContainer> iterator() {
		return list.iterator();
	}

	public int lastIndexOf(Object arg0) {
		return list.lastIndexOf(arg0);
	}

	public ListIterator<DataContainer> listIterator() {
		return list.listIterator();
	}

	public ListIterator<DataContainer> listIterator(int index) {
		return list.listIterator(index);
	}

	public boolean remove(Object obj) {
		return list.remove(obj);
	}

	public DataContainer remove(int index) {
		return list.remove(index);
	}

	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	public DataContainer set(int index, DataContainer element) {
		return list.set(index, element);
	}

	public int size() {
		return list.size();
	}

	public List<DataContainer> subList(int begin, int end) {
		return list.subList(begin, end);
	}

	public Object[] toArray() {
		return list.toArray();
	}

	public <T> T[] toArray(T[] arg0) {
		return list.toArray(arg0);
	}

	/**
	 * @return the scope
	 */
	public Scope getScope() {
		return scope;
	}

}
