package br.ubre.ctd.slang.parse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.net.ubre.data.map.DataMap;
import br.net.ubre.data.var.ValueType;
import br.net.ubre.framework.Variable;
import br.net.ubre.lang.Lang;
import br.net.ubre.lang.Syntax;
import br.net.ubre.slang.Syntagma;
import br.net.ubre.slang.code.CodeLine;
import br.net.ubre.slang.code.EndLine;
import br.net.ubre.slang.code.action.ActionLine;
import br.net.ubre.slang.code.ifelse.ElseIfLine;
import br.net.ubre.slang.code.ifelse.ElseLine;
import br.net.ubre.slang.code.ifelse.IfLine;
import br.net.ubre.slang.code.map.CaseLine;
import br.net.ubre.slang.code.map.SwitchLine;
import br.net.ubre.slang.parse.ScriptParser;

public class ScriptParserTest {

	private ScriptParser create() {
		DataMap dataMap = new DataMap();
		dataMap.add(new Variable("x", ValueType.INTEGER, 0), false);
		dataMap.add(new Variable("y", ValueType.INTEGER, 0), false);
		Lang lang = new Lang(new Syntax(), dataMap, true);
		Syntagma syntagma = new Syntagma();
		return new ScriptParser(lang, syntagma);
	}

	@Test
	public void testParseOneLine() {
		ScriptParser parser = create();
		List<String> source = new ArrayList<String>();
		source.add("// Comentário não conta");
		source.add("print 'script test'");
		List<CodeLine> lines = parser.parse(source);
		assertTrue(lines.size() == 1);// Gerou uma linha de código
		assertTrue(lines.get(0) instanceof CodeLine);
		assertTrue(lines.get(0) instanceof ActionLine);// linha de ação
														// (Comando)
		ActionLine actionLine = (ActionLine) lines.get(0);
		assertFalse(actionLine.isExpression()); // é action
	}

	@Test
	public void testAllLInes() {
		ScriptParser parser = create();
		List<String> source = new ArrayList<String>();
		source.add("// Script com todos os tipos de linha");
		source.add("print 'ScriptParserTest'");
		source.add("x = 3");
		source.add("y = 0");
		source.add("if x > 10");
		source.add("print 'Maior que 10'");
		source.add("y = 1");
		source.add("elseif  x < 5");
		source.add("print 'Menor que 5'");
		source.add("y = 2");
		source.add("else");
		source.add("print 'X entre 6 e 9'");
		source.add("y = 3");
		source.add("end");
		source.add("print 'resultado de y='+y");
		source.add("switch y");
		source.add("case 1");
		source.add("print 'y=1'");
		source.add("case 2");
		source.add("print 'y=2'");
		source.add("end");
		List<CodeLine> lines = parser.parse(source);
		assertTrue(lines.size() == 20);// Ignora o comentário
		// Testa a classe das linhas após Parser
		for (CodeLine line : lines) {
			System.out.println(line.getClass().getSimpleName() + ":"
					+ line.toString());
		}
		assertTrue(lines.get(0) instanceof ActionLine);
		assertTrue(lines.get(1) instanceof ActionLine);
		assertTrue(lines.get(2) instanceof ActionLine);
		assertTrue(lines.get(3) instanceof IfLine);
		assertTrue(lines.get(4) instanceof ActionLine);
		assertTrue(lines.get(5) instanceof ActionLine);
		assertTrue(lines.get(6) instanceof ElseIfLine);
		assertTrue(lines.get(7) instanceof ActionLine);
		assertTrue(lines.get(8) instanceof ActionLine);
		assertTrue(lines.get(9) instanceof ElseLine);
		assertTrue(lines.get(10) instanceof ActionLine);
		assertTrue(lines.get(11) instanceof ActionLine);
		assertTrue(lines.get(12) instanceof EndLine);
		assertTrue(lines.get(13) instanceof ActionLine);
		assertTrue(lines.get(14) instanceof SwitchLine);
		assertTrue(lines.get(15) instanceof CaseLine);
		assertTrue(lines.get(16) instanceof ActionLine);
		assertTrue(lines.get(17) instanceof CaseLine);
		assertTrue(lines.get(18) instanceof ActionLine);
		assertTrue(lines.get(19) instanceof EndLine);

	}

}
