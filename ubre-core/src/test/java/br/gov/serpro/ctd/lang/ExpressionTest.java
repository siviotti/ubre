package br.gov.serpro.ctd.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.data.container.StandardDataContainer;
import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.lang.expression.Expression;

/**
 * Teste Unitário para <code>Expression</code>. Todas as expressões deste teste
 * são literais. testes com variáveis e testes de valores nulos estão na classe
 * <code>ExpressionVarTest</code>.
 * 
 * @author JV
 * @since (revisado 23/10/2015)
 * @see ExpressionVarTest
 */
public class ExpressionTest {

	public static final Context CONTEXT = new Context("teste",
			"Contexto Teste", new Date(), new Date());

	private Object eval(String exp) {
		System.out.println("\n*****************************************");
		System.out.println(exp);
		System.out.println("*****************************************");
		Expression expression = CONTEXT.getLang().compile(exp);
		System.out.println(CONTEXT.getLang().getCompiler().getStatementList());
		System.out.println(expression.getRoot().asNode("   "));
		DataContainer container = new StandardDataContainer("id",
				CONTEXT.getDataMap());
		Object obj = expression.eval(container);
		if (obj != null) {
			System.out.println("result=" + obj + " ("
					+ obj.getClass().getSimpleName() + ")");
		} else {
			System.out.println("result=" + obj + " (null)");
		}
		// Esta classe de teste só trabalha com expressões literais
		assertTrue(expression.isLiteral());
		return obj;
	}

	@Test
	public void testLiterals() {
		assertEquals(true, eval("true"));
		assertEquals(false, eval("false"));
		assertEquals(null, eval("null"));
		assertEquals(false, eval("!true"));
		assertEquals(true, eval("!false"));
		assertEquals("a", eval("'a'"));
		assertEquals(1, eval("1"));
		assertEquals(1023, eval("1023"));
		assertEquals(new BigDecimal("1.44"), eval("1.44"));
		assertEquals(new BigDecimal("1.04"), eval("1.04"));

		try {
			assertEquals(null, eval("!null"));
			fail();
		} catch (CTDException e) {

		}
	}

	@Test
	public void testCalc() {
		// Cálculos básicos- Integer:Integer
		assertEquals(5, eval("2 + 3 // Comment")); // soma
		assertEquals(-1, eval("2 - 3")); // subtração
		assertEquals(4, eval("2 * 2")); // multiplicação
		assertEquals(1, eval("2 / 2")); // divisão
		assertEquals(2, eval("10 / 4")); // divisão INT
		assertNotSame(new BigDecimal("2.5"), eval("10 / 4")); // divisão DEC
		assertEquals(new BigDecimal("2.5"), eval("10.0 / 4")); // divisão DEC
		assertEquals(new BigDecimal("2.5"), eval("10 / 4.0")); // divisão DEC
		assertEquals(0, eval("2 % 2")); // módulo
		assertEquals(8, eval("2 ** 3")); // potência

		// Cálculos básicos- Decimal:Decimal
		assertEquals(new BigDecimal(5.0), eval("2.0 + 3.0")); // soma
		assertEquals(new BigDecimal(-2.0), eval("1.5 - 3.5")); // subtração
		assertEquals(new BigDecimal(4.0), eval("2.0 * 2.0")); // multiplicação
		assertEquals(new BigDecimal(1.0), eval("2.0 / 2.0")); // divisão
		assertEquals(new BigDecimal(8.0), eval("2.0 ** 3")); // potência
		assertEquals(new BigDecimal(15.625), eval("2.5 ** 3")); // potência

		// Operador de negativo
		assertEquals(new BigDecimal(0.0), eval("2.0 + -2.0"));
		assertEquals(0, eval("2 + - 2"));
		assertEquals(-4, eval("2 * - 2"));
		assertEquals(-1, eval("2 / - 2"));
		assertEquals(-1, eval("2 / - 2"));
		assertEquals(5, eval("3+(7 - 5)"));
		assertEquals(5, eval("(2 + 2)+(2-1)"));
		assertEquals(-5, eval("-5"));
		assertEquals(new BigDecimal("-5.2"), eval("-5.2"));
		assertEquals(-5, eval("-(2+3)"));
		System.out.println("teste big" + new BigDecimal(2.1));

		// **//assertEquals(new BigDecimal("-5.2"), eval("-(2.1+3.1)"));

	}

	@Test
	public void testCompare() {
		// Inteiros
		assertEquals(true, eval("2 + 3 > 2 + 2")); // maior
		assertEquals(false, eval("2 + 3 < 2 + 2")); // menor
		assertEquals(true, eval("2 + 3 >= 2 + 3")); // maior ou igual
		assertEquals(true, eval("2 + 3 <= 2 + 3")); // menor ou igual
		// Decimais
		assertEquals(true, eval("2.0 + 3 > 2 + 2.1")); // maior
		assertEquals(false, eval("2.0 + 3 < 2 + 2.1")); // menor
		assertEquals(true, eval("2.1 + 3 >= 2 + 3.1")); // maior ou igual
		assertEquals(true, eval("2.0 + 3 <= 2 + 3.1")); // menor ou igual
	}

	@Test
	public void testCompareAt() {
		assertEquals(true, eval("1 @ (1,2,3)"));
		assertEquals(true, eval("4 !@ (1,2,3)"));
		assertEquals(true, eval("2 @ (1,2,3)"));
		assertEquals(true, eval("3 @ (1,2,3)"));
		assertEquals(false, eval("4 @ (1,2,3)"));
		assertEquals(true, eval("2 @ (1,1+1,3)"));
		// assertEquals(true, eval("2 @ (1,x=2,3)"));
		// assertEquals(true, eval("2 @ (1,x=1+1,3)"));
		assertEquals(true, eval("3 @ (1,2,3,4,5)"));
		assertEquals(true, eval("-3 @ (1,2,-3,4,5)"));
		assertEquals(true, eval("-3-1 @ (1,2,3,-4,5)"));
		assertEquals(true, eval("-3+4 @ (1,2,3,-4,5)"));
		assertEquals(true, eval("-3+8 @ (1,2,3,-4,5)"));
		assertEquals(true, eval("-3+1 @ (1,-2,3,4,5)"));
		assertEquals(true, eval("-10 @ (1,-2-8,3,4,5)"));
		assertEquals(false, eval("3+5 @ (1,2,3,4,5)"));
		assertEquals(true, eval("2+1 @ (1,2,3,4,5)"));
		assertEquals(true, eval("2+1+1 @ (1,2,3,4,5)"));
		assertEquals(true, eval("3.3 @ (1.1,2.2,3.3,4.4,5.5)"));
		assertEquals(false, eval("3.4 @ (1.1,2.2,3.3,4.4,5.5)"));
		assertEquals(true, eval("3 @ (1.1,2.2,3,4.4,5.5)"));
		assertEquals(false, eval("3.0 @ (1.1,2.2,3,4.4,5.5)"));
		assertEquals(false, eval("3 @ (1.1,2.2,3.0,4.4,5.5)"));
		assertEquals(false, eval("3.0+1 @ (1.1,2.2,3.0,4.4,5.5)"));
		assertEquals(false, eval("3.0-1 @ (1.1,2.2,3.0,4.4,5.5,2.0)"));
		/**/// assertEquals(true, eval("3.0*2.0 @ (1.1,2.2,3.0,4.4,5.5,6.0)"));
		/**/// assertEquals(true,
				// eval("12.0/2.0 @ (1.1,2.2,3.0,4.4,5.5,6.0,6)"));
		assertEquals(true, eval("(2+1)+1 @ (1,2,3,4,5)"));
		/**/// assertEquals(true, eval("2*2+1 @ (1,2,3,4,5,6)"));
		assertEquals(true, eval(" \"abc\" @ (\"abc\",\"acd\",\"ade\",\"aef\")"));
		assertEquals(true, eval(" \"abc\" @ (\"abc\",\"acd\",\"ade\",\"aef\")"));
		assertEquals(false, eval(" \"abc\" @ (\"acd\",\"ade\",\"aef\")"));
		assertEquals(false, eval(" \"ab\"  @ (\"abc\",\"ade\",\"aef\")"));
		assertEquals(true, eval(" \"ab\"+\"c\"  @ (\"abc\",\"ade\",\"aef\")"));
		assertEquals(true, eval(" \"abc\" @ (\"ab\"+\"c\",\"ade\",\"aef\")"));

	}

	@Test
	public void trueTable() {
		assertEquals(false, eval("false && false"));
		assertEquals(false, eval("false && true"));
		assertEquals(false, eval("true && false"));
		assertEquals(true, eval("true && true"));
		assertEquals(false, eval("false || false"));
		assertEquals(true, eval("false || true"));
		assertEquals(true, eval("true || false"));
		assertEquals(true, eval("true || true"));
	}

	@Test
	public void data1() {
		System.out.println(eval("1,2"));
		System.out.println(eval("(1+2)"));
		System.out.println(eval("{12/12/2012}"));
		System.out.println(eval("{12:09:00}"));
		//System.out.println(eval("{12:09}[0]")); // morreu!
		System.out.println(eval("(1,2)[1]"));
		System.out.println("** " + eval("{12/12/2012}[3]"));
	}

	@Test
	public void index() {

		assertEquals(2, eval("(2,4,8,16,32.5,65)[0]"));
		assertEquals(4, eval("(2,4,8,16,32.5,65)[1]"));
		assertEquals(16, eval("(2,4,8,16,32.5,65)[3]"));
		assertEquals(8, eval("(2,4,8,16,32.5,65)[1+1]"));
		assertEquals(new BigDecimal("32.5"), eval("(2,4,8,16,32.5,65)[4]"));
		assertEquals(65, eval("(2,4,8,16,32.5,65)[5]"));
		assertEquals(65, eval("(2,4,8,16,32.5,65)[-1]"));
		assertEquals(new BigDecimal("32.5"), eval("(2,4,8,16,32.5,65)[-2]"));

		assertEquals("a", eval("\"abcdef\"[0]"));
		assertEquals("b", eval("\"abcdef\"[1]"));
		assertEquals("f", eval("\"abcdef\"[5]"));
		assertEquals("f", eval("\"abcdef\"[-1]"));
		assertEquals("e", eval("\"abcdef\"[-2]"));

		assertEquals(10, eval("day({10/11/2012})"));
		// assertEquals(11, eval("{10/11/2012}[4]"));
		// assertEquals(2012, eval("{10/11/2012}[5]"));
	}

	@Test
	public void functions() {
		assertEquals("abc a", eval("lower('ABC a')"));
		assertEquals("ABC A", eval("upper('abc A')"));
		assertEquals("123", eval("str(123)"));
		assertEquals("12.3", eval("str(12.3)"));
		assertEquals("false", eval("str(false)"));
		assertEquals("true", eval("str(true)"));
		assertEquals("false", eval("str(true && false)"));
		assertEquals(4, eval("int('4')"));
		assertEquals(new BigDecimal("4.1"), eval("dec('4.1')"));
		assertEquals(new BigDecimal("4.00"), eval("dec('4.00')"));
		assertEquals(new BigDecimal("4"), eval("dec('4')"));
		assertEquals(4, eval("size((1,2,3,4))"));
		assertEquals(4, eval("size('1234')"));
		assertEquals(true, eval("bool('true')"));
		assertEquals(false, eval("bool('false')"));

	}

	@Test
	public void slice() {
		assertEquals("bcde", eval("\"abcdef\"[1:-1]"));
		assertEquals("ab", eval("\"abcdef\"[0:2]"));
		assertEquals("[4, 8]", eval("(2,4,8,16,32.5,65)[1:3]").toString());
	}

	@Test
	public void testLogical() {
		// eval("1,2,(1+2),4,5");
		// assertEquals("bcde", eval("\"abcdef\"[1:-1]"));
	}

	@Test(expected = br.gov.serpro.ubre.lang.LangException.class)
	public void ModErr() {
		System.out.println(eval("1.6 % 0.8")); // módulo
	}
	
	@Test
	public void powErr() {
		System.out.println(eval("9 ** 0.5 // raiz quadrada")); // pow
		assertEquals(new BigDecimal("3"), eval("9 ** 0.5"));
	}

}
