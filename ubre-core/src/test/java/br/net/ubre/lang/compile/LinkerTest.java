package br.net.ubre.lang.compile;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import br.net.ubre.data.map.DataMap;
import br.net.ubre.data.map.VarPattern;
import br.net.ubre.lang.Syntax;
import br.net.ubre.lang.compile.Linker;
import br.net.ubre.lang.keyword.binary.logical.OrOperator;
import br.net.ubre.lang.parse.ExpParser;
import br.net.ubre.lang.parse.ParserTest;
import br.net.ubre.lang.parse.StringMap;
import br.net.ubre.lang.statement.Statement;

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
