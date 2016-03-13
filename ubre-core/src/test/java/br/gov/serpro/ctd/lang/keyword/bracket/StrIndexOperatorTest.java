package br.gov.serpro.ctd.lang.keyword.bracket;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.gov.serpro.ubre.lang.data.literal.IntegerStatement;
import br.gov.serpro.ubre.lang.data.literal.LiteralStatement;
import br.gov.serpro.ubre.lang.data.literal.StringStatement;
import br.gov.serpro.ubre.lang.keyword.bracket.OpenBracketOperator;

public class StrIndexOperatorTest {

	@Test
	public void test1() {
		OpenBracketOperator bracketOperator = new OpenBracketOperator("[.]");
		LiteralStatement um = new IntegerStatement(0);
		LiteralStatement str = new StringStatement("0123456789");
		bracketOperator.link(str, um);
		String result = (String) bracketOperator.result(null);
		System.out.println(result);
		assertEquals("0", result);
	}

	@Test
	public void test2() {
		OpenBracketOperator bracketOperator = new OpenBracketOperator("[.]");
		LiteralStatement um = new IntegerStatement(9);
		LiteralStatement str = new StringStatement("0123456789");
		bracketOperator.link(str, um);
		String result = (String) bracketOperator.result(null);
		System.out.println(result);
		assertEquals("9", result);
	}

	@Test
	public void test3() {
		OpenBracketOperator bracketOperator = new OpenBracketOperator("[.]");
		LiteralStatement um = new IntegerStatement(-1);
		LiteralStatement str = new StringStatement("0123456789");
		bracketOperator.link(str, um);
		String result = (String) bracketOperator.result(null);
		System.out.println(result);
		assertEquals("9", result);
	}

}
