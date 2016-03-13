package br.gov.serpro.ctd.slang.build;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.gov.serpro.ubre.data.container.StandardDataContainer;
import br.gov.serpro.ubre.data.map.DataMap;
import br.gov.serpro.ubre.data.var.ValueType;
import br.gov.serpro.ubre.framework.Variable;
import br.gov.serpro.ubre.lang.Lang;
import br.gov.serpro.ubre.lang.Syntax;
import br.gov.serpro.ubre.slang.Syntagma;
import br.gov.serpro.ubre.slang.build.ScriptBuilder;
import br.gov.serpro.ubre.slang.script.Script;

/**
 * Teste Unitário de <code></code>
 * 
 * @author douglas
 *
 */
public class ScriptBuilderTest {

	@Test
	public void test() {
		Syntax syntax = new Syntax();
		DataMap dataMap = new DataMap();
		dataMap.add(new Variable("x", ValueType.INTEGER, "0"), false);
		Lang lang = new Lang(syntax, dataMap, true);
		ScriptBuilder builder = new ScriptBuilder(lang, new Syntagma());

		List<String> source = new ArrayList<String>();
		source.add("//Script de Teste");
		source.add("print 'abc'");
		source.add("print 2 > 3");
		source.add("x = 2");
		source.add("if 3 > 2");
		source.add("print 't:3 > 2'");
		source.add("    if x == 1");
		source.add("      print 'x é 1'");
		source.add("    elseif x == 2");
		source.add("      print 'x é 2'");
		source.add("    else");
		source.add("      print 'x é outro número'");
		source.add("    end");
		source.add("print 'dentro do primeiro if'");
		source.add("end");
		source.add("print 'fora do if'");
		source.add("2 + 2");
		source.add("print 'x='+x");

		Script script = builder.build(source);
		// System.out.println(script.toSource());
		script.execute(new StandardDataContainer("id", dataMap));

	}

}
