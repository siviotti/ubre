package br.gov.serpro.ubre.util;

import java.util.ArrayList;
import java.util.Collection;

import br.gov.serpro.ubre.exception.FreezeException;
import br.gov.serpro.ubre.framework.ContextElement;

/**
 * Lista imutável a partir do momento em que é bloqueada na sua construção. Os
 * itens devem ser passados no construtor e nunca mais são aletrados. Não podem
 * ser removidos nem adicionados novos itens.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 20/03/2015
 * 
 * @param <E>
 */
public class FreezableList<E extends ContextElement> extends ArrayList<E>
		implements Freezable {

	private static final String FROZEN_LIST = "LISTA CONGELADA!!!!";

	private static final long serialVersionUID = 1L;

	private boolean frozen;

	public void freeze() {
		frozen = true;
		for(ContextElement e: this){
			e.freeze();
		}
	}
	
	public boolean isFrozen() {
		return frozen;
	}


	private void testFrozen() {
		if (frozen) {
			throw new FreezeException(getClass());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	@Override
	public boolean add(E e) {
		testFrozen();
		return super.add(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		testFrozen();
		super.add(index, element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		testFrozen();
		return super.addAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		testFrozen();
		return super.addAll(index, c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#clear()
	 */
	@Override
	public void clear() {
		testFrozen();
		super.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#remove(int)
	 */
	@Override
	public E remove(int index) {
		testFrozen();
		return super.remove(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		testFrozen();
		return super.remove(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		testFrozen();
		return super.removeAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#removeRange(int, int)
	 */
	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		testFrozen();
		super.removeRange(fromIndex, toIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		testFrozen();
		return super.set(index, element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#trimToSize()
	 */
	@Override
	public void trimToSize() {
		testFrozen();
		super.trimToSize();
	}


}
