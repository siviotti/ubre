package br.ubre.ctd.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.net.ubre.data.var.ValueType;
import br.net.ubre.exception.FreezeException;
import br.net.ubre.framework.ConstMap;
import br.net.ubre.framework.Constant;
import br.net.ubre.framework.Context;
import br.net.ubre.lang.expression.Expression;

/**
 * Teste Unitário de <code>ConstMap</code>.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 15/10/2015
 */
public class ConstMapTest {

	@Test
	public void test() {
		Context context = new Context("testConstMap", "label", null, null);
		ConstMap constMap = new ConstMap();
		assertTrue(constMap instanceof Map);

		Constant constAbc = new Constant("abc", ValueType.TEXT, "Abc");
		Constant constNum = new Constant("NUM", ValueType.INTEGER, 3);
		constMap.put("abc", constAbc); // igual ao add
		constMap.add(constNum); // igual ao put

		assertTrue(constMap.containsKey("abc"));
		assertTrue(constMap.containsKey("NUM"));

		// Freeze
		constMap.freeze();
		try {
			Constant constBool = new Constant("BOOL", ValueType.BOOLEAN, true);
			constMap.add(constBool);
			fail();
		} catch (FreezeException fe) {
			fe.printStackTrace();
			assertTrue(fe.getMessage().contains(
					ConstMap.class.getCanonicalName()));
		} catch (Exception e) {
			fail(); // tem que gerar FreezException
		}
	}

	private Constant mockConstant(String id, String value) {
		Context context = new Context("testConstMap", "label", null, null);
		Expression expression = context.getLang().compile("'Abc'");
		Constant constMock = new Constant(id, ValueType.TEXT, value);
		return constMock;
	}

	@Test(expected = FreezeException.class)
	public void testFreezAdd() {
		ConstMap constMap = new ConstMap();
		constMap.freeze();
		Constant constant = mockConstant("abc", "Abc");
		constMap.add(constant);
	}

	@Test(expected = FreezeException.class)
	public void testFreezPut() {
		ConstMap constMap = new ConstMap();
		constMap.freeze();
		Constant constant = mockConstant("abc", "Abc");
		constMap.put("abc", constant);
	}

	@Test(expected = FreezeException.class)
	public void testFreezClear() {
		ConstMap constMap = new ConstMap();
		Constant constant = mockConstant("abc", "Abc");
		constMap.add(constant);
		assertEquals(1, constMap.size());
		constMap.freeze();
		constMap.clear();
	}

	@Test(expected = FreezeException.class)
	public void testFreezPutAll() {
		ConstMap constMap = new ConstMap();
		Constant constant = mockConstant("abc1", "Abc");
		Constant constant2 = mockConstant("abc2", "Abc");
		Map<String, Constant> map = new HashMap<String, Constant>();
		map.put("abc1", constant);
		map.put("abc2", constant);
		constMap.putAll(map);
		assertEquals(2, constMap.size());
		constMap.freeze();
		constMap.putAll(map);
	}

	@Test(expected = FreezeException.class)
	public void testFreezRemove() {
		ConstMap constMap = new ConstMap();
		Constant constant = mockConstant("abc", "Abc");
		constMap.add(constant);
		constMap.remove("abc");
		assertEquals(0, constMap.size());
		Constant constant2 = mockConstant("abc2", "Abc");
		constMap.add(constant2);
		assertEquals(1, constMap.size());
		constMap.freeze();
		constMap.remove("abc2");
	}

}
