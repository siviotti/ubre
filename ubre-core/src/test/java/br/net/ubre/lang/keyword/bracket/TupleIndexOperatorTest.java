package br.net.ubre.lang.keyword.bracket;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.net.ubre.lang.data.TupleStatement;
import br.net.ubre.lang.data.literal.IntegerStatement;
import br.net.ubre.lang.data.literal.LiteralStatement;
import br.net.ubre.lang.keyword.bracket.OpenBracketOperator;
import br.net.ubre.lang.statement.Statement;

public class TupleIndexOperatorTest {
	
	public static TupleStatement createTuple(){
		IntegerStatement s0 = new IntegerStatement(0);
		IntegerStatement s1 = new IntegerStatement(1);
		IntegerStatement s2 = new IntegerStatement(2);
		IntegerStatement s3 = new IntegerStatement(3);
		IntegerStatement s4 = new IntegerStatement(4);
		IntegerStatement s5 = new IntegerStatement(5);
		IntegerStatement s6 = new IntegerStatement(6);
		IntegerStatement s7 = new IntegerStatement(7);
		IntegerStatement s8 = new IntegerStatement(8);
		IntegerStatement s9 = new IntegerStatement(9);
		List<Statement> list = new ArrayList<Statement>();
		list.add(s0);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
		list.add(s6);
		list.add(s7);
		list.add(s8);
		list.add(s9);
		return new TupleStatement(list);
	}

	@Test
	public void test1() {

		LiteralStatement tuple = createTuple();
		OpenBracketOperator bracketOperator = new OpenBracketOperator("[.]");
		LiteralStatement zero = new IntegerStatement(0);

		bracketOperator.link(tuple, zero.perform(null));
		Integer result = (Integer) bracketOperator.result(null);
		System.out.println(result);
		assertEquals("0", result.toString());

	}

	@Test
	public void test2() {

		LiteralStatement tuple = createTuple();
		OpenBracketOperator bracketOperator = new OpenBracketOperator("[.]");
		LiteralStatement nine = new IntegerStatement(9);

		bracketOperator.link(tuple, nine.perform(null));
		Integer result = (Integer) bracketOperator.result(null);
		System.out.println(result);
		assertEquals("9", result.toString());

	}

	@Test
	public void test3() {
		LiteralStatement tuple = createTuple();
		OpenBracketOperator bracketOperator = new OpenBracketOperator("[.]");
		LiteralStatement lessOne = new IntegerStatement(-1);

		bracketOperator.link(tuple, lessOne.perform(null));
		Integer result = (Integer) bracketOperator.result(null);
		System.out.println(result);
		assertEquals("9", result.toString());

	}

}
