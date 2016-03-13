package br.gov.serpro.ctd.lang.keyword.binary.list;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.lang.Lang;
import br.gov.serpro.ubre.lang.data.literal.IntegerStatement;
import br.gov.serpro.ubre.lang.data.literal.LiteralStatement;
import br.gov.serpro.ubre.lang.data.literal.StringStatement;
import br.gov.serpro.ubre.lang.expression.Expression;
import br.gov.serpro.ubre.lang.keyword.binary.list.CommaOperator;
import br.gov.serpro.ubre.lang.statement.PointerStatement;
import br.gov.serpro.ubre.lang.statement.Statement;

public class CommaOperatorTest {

	public static final Context CONTEXT = new Context("teste",
			"Contexto Teste", new Date(), new Date());

	@Test
	public void test() {
		CommaOperator commaOperator = new CommaOperator(":");
		LiteralStatement tres = new IntegerStatement(3);
		LiteralStatement abc = new StringStatement("abc");
		LiteralStatement efg = new StringStatement("efg");
		commaOperator.link(tres, abc);
		List<Object> list = (List) commaOperator.result(null);
		System.out.println(list);
		assertEquals(3, list.get(0));
		commaOperator.link(commaOperator.perform(null), efg);
		list = (List) commaOperator.result(null);
		System.out.println(list);
	}

	@Test
	public void testListElements() {
		String exp = "(1,2,3,4,5)";
		Lang lang = CONTEXT.getLang();
		Expression expression = lang.compile(exp);
		System.out.println(expression.getRoot().asNode(" "));
		PointerStatement pointer = (PointerStatement) expression.getRoot();
		CommaOperator commaOperator = (CommaOperator) pointer.getTarget();
		List<Statement> list = new ArrayList<Statement>(); 
		commaOperator.loadList(list);
		System.out.println(list);
	}

}
