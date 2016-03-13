package br.gov.serpro.ctd.util;

import static org.junit.Assert.*;

import org.junit.Test;

import br.gov.serpro.ubre.exception.FreezeException;
import br.gov.serpro.ubre.util.GenericFreezable;

/**
 * Teste Unitário de <code>GenericFreezable</code>.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 15/10/2015
 */
public class GenericFreezableTest extends GenericFreezable {

	@Test(expected = FreezeException.class)
	public void test() {
		assertFalse(isFrozen());
		freeze();
		assertTrue(isFrozen());
		// Error:
		testFrozen();
	}

}

