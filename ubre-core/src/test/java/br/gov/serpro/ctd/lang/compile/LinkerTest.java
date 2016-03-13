package br.gov.serpro.ctd.lang.compile;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import br.gov.serpro.ctd.lang.parse.ParserTest;
import br.gov.serpro.ubre.data.map.DataMap;
import br.gov.serpro.ubre.data.map.VarPattern;
import br.gov.serpro.ubre.lang.Syntax;
import br.gov.serpro.ubre.lang.compile.Linker;
import br.gov.serpro.ubre.lang.keyword.binary.logical.OrOperator;
import br.gov.serpro.ubre.lang.parse.ExpParser;
import br.gov.serpro.ubre.lang.parse.StringMap;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 21/05/2015
 * 
 */
public class LinkerTest {

	@Test
	public void test() throws ParseException {
		// Não use o operador -, pois ele precisa definir operação antes de link
		String source = "(2 + 3) * 4 > 20 && 5 / 2 == 4 / 2 || 7 + 6 != 5 % 2";
		Syntax syntax = new Syntax();
		ExpParser parser = new ExpParser(syntax, new StringMap("" + new VarPattern().getFieldTag()), new DataMap());
		List<Statement> list = parser.parse(source);
		assertEquals(25, list.size()); // 25 membros
		ParserTest.debugStatementList(list);
		Linker linker = new Linker();
		Statement root = linker.link(list);
		System.out.println(root.asNode("  "));
		// asserts
		assertEquals(OrOperator.class, root.getClass());

	}
}
