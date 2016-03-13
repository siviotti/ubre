package br.net.ubre.lang.parse;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import br.net.ubre.data.var.ValueType;
import br.net.ubre.framework.Context;
import br.net.ubre.lang.parse.ExpParser;
import br.net.ubre.lang.statement.Statement;

public class ParserTest {

	public static void debugStatementList(List<Statement> list) {
		for (Statement statement : list) {
			System.out.println("statement " + statement + " \t("
					+ statement.getClass().getSimpleName() + ")");
		}
	}

	@Test
	public void test() throws ParseException {
		Context context = new Context("id", "context", null, null);
		context.addVar("s", ValueType.TEXT, null);
		ExpParser parser = new ExpParser(context.getLang().getSyntax(), context
				.getLang().getStringMap(), context.getDataMap());
		String s1 = "s = 'teste de String' + ('segunda string' + 'teste de String')";
		List<Statement> list = parser.parse(s1);
		debugStatementList(list);

		String s2 = "2 + 3 * 4 > 20 && 5 / 2 == 4 / 2 || 7 - 6 != 5 % 2";
		list = parser.parse(s2);
		debugStatementList(list);
	}
}
