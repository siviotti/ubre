package br.net.ubre.lang;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.data.container.StandardDataContainer;
import br.net.ubre.data.var.Datex;
import br.net.ubre.data.var.ValueType;
import br.net.ubre.framework.Context;
import br.net.ubre.framework.Variable;
import br.net.ubre.lang.expression.Expression;

/**
 * Teste para expressões que usam variáveis, constantes e mensagens. Testes com
 * expressões literais estão na classe <code>ExpressionTest</code>.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 23/10/2015 ExpressionTest
 * @see ExpressionTest
 */
public class ExpressionVarTest {

	public static final Context CONTEXT = new Context("teste",
			"Contexto Teste", new Date(), new Date());
	static {
		CONTEXT.addVar(new Variable("x", ValueType.INTEGER, 0));
		CONTEXT.addVar(new Variable("y", ValueType.INTEGER, 0));
		CONTEXT.addVar(new Variable("z", ValueType.INTEGER, 0));
		CONTEXT.addVar(new Variable("b", ValueType.BOOLEAN, false));
		CONTEXT.addVar(new Variable("d1", ValueType.DECIMAL, 0.0));
		CONTEXT.addVar(new Variable("d2", ValueType.DECIMAL, 0.0));
		CONTEXT.addVar(new Variable("d3", ValueType.DECIMAL, 0.0));
		CONTEXT.addVar(new Variable("dt", ValueType.DATE, new Datex()));
		CONTEXT.addVar(new Variable("s", ValueType.INTEGER, ""));
	}

	private DataContainer container;

	private Object eval(String exp) {
		System.out.println("\n*****************************************");
		System.out.println(exp);
		System.out.println("*****************************************");
		Expression expression = CONTEXT.getLang().compile(exp);
		System.out.println(CONTEXT.getLang().getCompiler().getStatementList());
		System.out.println(expression.getRoot().asNode("   "));

		Object obj = expression.eval(container);
		if (obj != null) {
			System.out.println("result=" + obj + " ("
					+ obj.getClass().getSimpleName() + ")");
		} else {
			System.out.println("result=" + obj + " (null)");
		}
		assertFalse(expression.isLiteral());
		return obj;
	}
	
	@Test(expected=AssertionError.class)
	public void testLiteral(){
		eval ("1 + 1");
	}

	@Test
	public void testCalc() {
		container = new StandardDataContainer("calc", CONTEXT.getDataMap());
		
		// INT
		eval("x=8");
		eval("y=2");
		assertEquals(10, eval("x + y"));
		assertEquals(6, eval("x - y"));
		assertEquals(16, eval("x * y"));
		assertEquals(4, eval("x / y"));
		assertEquals(64, eval("x ** y"));
		
		eval("y=null");
		assertEquals(8, eval("x + y"));
		assertEquals(8, eval("x + y"));
		assertEquals(8, eval("x - y"));
		assertEquals(8, eval("x * y"));
		assertEquals(8, eval("x / y"));
		assertEquals(8, eval("x ** y"));
		
		//DEC
		eval("d1=8.0");
		eval("d2=2.0");
		assertEquals(new BigDecimal(10.0), eval("d1 + d2"));
		assertEquals(new BigDecimal(6.0), eval("d1 - d2"));
		assertEquals(new BigDecimal(16.0), eval("d1 * d2"));
		assertEquals(new BigDecimal(4.0), eval("d1 / d2"));
		assertEquals(new BigDecimal(64.0), eval("d1 ** d2"));
		
		eval("d2=null");
		assertEquals(new BigDecimal(8.0), eval("d1 + d2"));
		assertEquals(new BigDecimal(8.0), eval("d1 + d2"));
		assertEquals(new BigDecimal(8.0), eval("d1 - d2"));
		assertEquals(new BigDecimal(8.0), eval("d1 * d2"));
		assertEquals(new BigDecimal(8.0), eval("d1 / d2"));
		assertEquals(new BigDecimal(8.0), eval("d1 ** d2"));
		

		container= null;
	}

}
