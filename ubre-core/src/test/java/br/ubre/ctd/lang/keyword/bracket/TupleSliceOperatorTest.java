package br.ubre.ctd.lang.keyword.bracket;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.net.ubre.lang.data.TupleStatement;
import br.net.ubre.lang.data.literal.IntegerStatement;
import br.net.ubre.lang.data.literal.LiteralStatement;
import br.net.ubre.lang.data.literal.StringStatement;
import br.net.ubre.lang.keyword.binary.list.ColonOperator;
import br.net.ubre.lang.keyword.bracket.OpenBracketOperator;
import br.net.ubre.lang.statement.Statement;

public class TupleSliceOperatorTest {

	public static TupleStatement createTuple() {
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
		StringStatement sa = new StringStatement("a");
		StringStatement sb = new StringStatement("b");
		StringStatement sc = new StringStatement("c");
		StringStatement sd = new StringStatement("d");
		StringStatement se = new StringStatement("e");
		StringStatement sf = new StringStatement("f");
		StringStatement sg = new StringStatement("g");
		StringStatement sh = new StringStatement("h");
		StringStatement si = new StringStatement("i");
		StringStatement sj = new StringStatement("j");
		StringStatement sk = new StringStatement("k");
		StringStatement sl = new StringStatement("l");
		StringStatement sm = new StringStatement("m");
		StringStatement sn = new StringStatement("n");
		StringStatement so = new StringStatement("o");
		StringStatement sp = new StringStatement("p");
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
		list.add(sa);
		list.add(sb);
		list.add(sc);
		list.add(sd);
		list.add(se);
		list.add(sf);
		list.add(sg);
		list.add(sh);
		list.add(si);
		list.add(sj);
		list.add(sk);
		list.add(sl);
		list.add(sm);
		list.add(sn);
		list.add(so);
		list.add(sp);
		return new TupleStatement(list);
	}

	//@Test
	public void test1() {
		LiteralStatement tuple = createTuple();

		OpenBracketOperator bracketOperator = new OpenBracketOperator("[.]");
		ColonOperator colonOperator = new ColonOperator(":");
		LiteralStatement um = new IntegerStatement(0);
		LiteralStatement dois = new IntegerStatement(2);
		colonOperator.link(um, dois);
		bracketOperator.link(tuple, colonOperator.perform(null));
		List result = (List) bracketOperator.result(null);
		System.out.println(result);
		assertEquals("[0, 1]", result.toString());

	}

	//@Test
	public void test2() {

		LiteralStatement tuple = createTuple();
		OpenBracketOperator bracketOperator = new OpenBracketOperator("[.]");
		ColonOperator colonOperator = new ColonOperator(":");
		LiteralStatement um = new IntegerStatement(0);
		LiteralStatement dois = new IntegerStatement(0);
		colonOperator.link(um, dois);
		bracketOperator.link(tuple, colonOperator.perform(null));
		List result = (List) bracketOperator.result(null);
		System.out.println(result);
		assertEquals(
				"[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p]",
				result.toString());

	}

	//@Test
	public void test3() {
		LiteralStatement tuple = createTuple();
		OpenBracketOperator bracketOperator = new OpenBracketOperator("[.]");
		ColonOperator colonOperator = new ColonOperator(":");
		LiteralStatement um = new IntegerStatement(0);
		LiteralStatement dois = new IntegerStatement(-1);
		colonOperator.link(um, dois);
		bracketOperator.link(tuple, colonOperator.perform(null));
		List result = (List) bracketOperator.result(null);
		System.out.println(result);
		assertEquals(
				"[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o]",
				result.toString());

	}
}
