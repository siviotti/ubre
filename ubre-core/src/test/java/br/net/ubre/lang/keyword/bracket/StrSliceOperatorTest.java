package br.net.ubre.lang.keyword.bracket;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.net.ubre.lang.data.literal.IntegerStatement;
import br.net.ubre.lang.data.literal.LiteralStatement;
import br.net.ubre.lang.data.literal.StringStatement;
import br.net.ubre.lang.keyword.binary.list.ColonOperator;
import br.net.ubre.lang.keyword.bracket.OpenBracketOperator;

public class StrSliceOperatorTest {
	
	@Test
	public void test1() {
		OpenBracketOperator bracketOperator= new OpenBracketOperator("[.]");
		ColonOperator colonOperator = new ColonOperator(":");
		LiteralStatement um = new IntegerStatement(0);
		LiteralStatement dois = new IntegerStatement(2);
		LiteralStatement str = new StringStatement("0123456789abcdefghijklmnop");
		colonOperator.link(um, dois);
		bracketOperator.link(str,colonOperator.perform(null));
		String result = (String) bracketOperator.result(null);
		System.out.println(result);
		assertEquals("01", result);
	}
	
	@Test
	public void test2() {
		OpenBracketOperator bracketOperator= new OpenBracketOperator("[.]");
		ColonOperator colonOperator = new ColonOperator(":");
		LiteralStatement um = new IntegerStatement(0);
		LiteralStatement dois = new IntegerStatement(-1);
		LiteralStatement str = new StringStatement("0123456789abcdefghijklmnop");
		colonOperator.link(um, dois);
		bracketOperator.link(str,colonOperator.perform(null));
		String result = (String) bracketOperator.result(null);
		System.out.println(result);
		assertEquals("0123456789abcdefghijklmno", result);
	}
	@Test
	public void test3() {
		OpenBracketOperator bracketOperator= new OpenBracketOperator("[.]");
		ColonOperator colonOperator = new ColonOperator(":");
		LiteralStatement um = new IntegerStatement(0);
		LiteralStatement dois = new IntegerStatement(0);
		LiteralStatement str = new StringStatement("0123456789abcdefghijklmnop");
		colonOperator.link(um, dois);
		bracketOperator.link(str,colonOperator.perform(null));
		String result = (String) bracketOperator.result(null);
		System.out.println(result);
		assertEquals("0123456789abcdefghijklmnop", result);
	}

}
