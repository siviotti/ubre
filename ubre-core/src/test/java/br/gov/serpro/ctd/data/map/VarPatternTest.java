package br.gov.serpro.ctd.data.map;

import static br.gov.serpro.ubre.data.map.VarPattern.TOKEN_PATTERN_MSG_FISRT_CHAR;
import static br.gov.serpro.ubre.data.map.VarPattern.TOKEN_PATTERN_MSG_INVALID_BEGIN;
import static br.gov.serpro.ubre.data.map.VarPattern.TOKEN_PATTERN_MSG_INVALID_CHAR;
import static br.gov.serpro.ubre.data.map.VarPattern.TOKEN_PATTERN_MSG_MAX_SIZE;
import static br.gov.serpro.ubre.data.map.VarPattern.TOKEN_PATTERN_MSG_NULL_OR_EMPTY;
import static org.junit.Assert.*;

import org.junit.Test;

import br.gov.serpro.ubre.data.map.VarPattern;

/**
 * Teste unitário para TokenPattern.
 * 
 * @author Marty McFly
 * @version 21/10/2015 Back To The Future
 *
 */
public class VarPatternTest {

	/**
	 * Teste para as possíveis variáveis válidas e inválidas.
	 */
	@Test
	public void testVarName() {
		// Valid
		VarPattern pattern = new VarPattern();
		String var1 = "abc";
		assertNull(pattern.validateVarToken(var1));
		String var2 = "Abc123";
		assertNull(pattern.validateVarToken(var2));
		String var3 = "_abc";
		assertNull(pattern.validateVarToken(var3));
		String var4 = "abc_xpto";
		assertNull(pattern.validateVarToken(var4));
		String var5 = "_A_1_3";
		assertNull(pattern.validateVarToken(var5));
		String var6 = "123";
		// Invalid
		assertEquals(pattern.validateVarToken(var6), TOKEN_PATTERN_MSG_FISRT_CHAR);
		String var7 = "ab-c";
		assertEquals(pattern.validateVarToken(var7), TOKEN_PATTERN_MSG_INVALID_CHAR);
		String var8 = "acgdjugytracgdjugytracgdjugytracgdjugytracgdjugyczxczxczxtr";
		assertEquals(pattern.validateVarToken(var8), TOKEN_PATTERN_MSG_MAX_SIZE);
		String var9 = "abc def";
	}

	@Test
	public void testFieldName() {
		VarPattern pattern = new VarPattern();
		// Valid
		assertNull(pattern.validateFieldToken("$abc"));
		assertNull(pattern.validateFieldToken("$Abc"));
		assertNull(pattern.validateFieldToken("$_abc"));
		assertNull(pattern.validateFieldToken("$abc_xpto"));
		// Invalid
		assertEquals(pattern.validateFieldToken("abc"), TOKEN_PATTERN_MSG_INVALID_BEGIN + pattern.getFieldTag());
		assertEquals(pattern.validateFieldToken(null), TOKEN_PATTERN_MSG_NULL_OR_EMPTY);
	}

	@Test
	public void testTypes() {
		VarPattern pattern = new VarPattern();
		// token data com data válida
		assertTrue(pattern.isDateToken("{12/12/2000}"));
		// token data cm data inválida
		assertTrue(pattern.isDateToken("{99/99/2000}"));
	}
	
	@Test
	public void testIsDecimal(){
		VarPattern pattern = new VarPattern();
		// True
		assertTrue(pattern.isDecimalToken("1.2"));
		assertTrue(pattern.isDecimalToken("1"));
		assertTrue(pattern.isDecimalToken("0.2"));
		assertTrue(pattern.isDecimalToken(".25"));
		// False
		assertFalse(pattern.isDecimalToken("1. 2"));
		assertFalse(pattern.isDecimalToken("1 .2"));
		assertFalse(pattern.isDecimalToken("1.2.3"));
		assertFalse(pattern.isDecimalToken("1.a2"));
		assertFalse(pattern.isDecimalToken("+1.2"));
		assertFalse(pattern.isDecimalToken("-1.2"));

	}

	@Test
	public void testIsInteger(){
		VarPattern pattern = new VarPattern();
		// True
		assertTrue(pattern.isIntegerToken("12"));
		assertTrue(pattern.isIntegerToken("0"));
		assertTrue(pattern.isIntegerToken("000"));
		assertTrue(pattern.isIntegerToken("1234567890"));
		//False
		assertFalse(pattern.isIntegerToken("1.2"));
		assertFalse(pattern.isIntegerToken("-12")); // - é operador
		assertFalse(pattern.isIntegerToken("+12")); // + é operador
		assertFalse(pattern.isIntegerToken("1a1"));
	}

}


