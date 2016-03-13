package br.net.ubre.util;

import br.net.ubre.exception.FreezeException;

/**
 * Implementação genérica da interface <code>Freezable</code>.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 15/10/2015
 * @see Freezable
 */
public abstract class GenericFreezable implements Freezable {

	private boolean frozen = false;

	public void freeze() {
		frozen = true;
	}

	public boolean isFrozen() {
		return frozen;
	}

	protected void testFrozen() {
		if (frozen) {
			throw new FreezeException(getClass());
		}
	}

}
