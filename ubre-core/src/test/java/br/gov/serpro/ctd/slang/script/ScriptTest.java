package br.gov.serpro.ctd.slang.script;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.data.container.StandardDataContainer;
import br.gov.serpro.ubre.data.map.DataMap;
import br.gov.serpro.ubre.data.var.ValueType;
import br.gov.serpro.ubre.framework.Variable;
import br.gov.serpro.ubre.lang.Lang;
import br.gov.serpro.ubre.lang.Syntax;
import br.gov.serpro.ubre.slang.Syntagma;
import br.gov.serpro.ubre.slang.build.ScriptBuilder;
import br.gov.serpro.ubre.slang.parse.ScriptParser;
import br.gov.serpro.ubre.slang.script.Script;
import br.gov.serpro.ubre.util.FileUtil;

/**
 * Teste Unitário para <code>Script</code>.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 23/10/2015
 */
public class ScriptTest {

	FileUtil fileUtil = new FileUtil();

	private DataMap createDataMap() {
		DataMap dataMap = new DataMap();
		dataMap.add(new Variable("x", ValueType.INTEGER, 0), false);
		dataMap.add(new Variable("y", ValueType.INTEGER, 0), false);
		dataMap.add(new Variable("z", ValueType.INTEGER, 0), false);
		return dataMap;
	}

	private Script build(List<String> sourceLines) {
		DataMap dataMap = createDataMap();
		Lang lang = new Lang(new Syntax(), dataMap, true);
		Syntagma syntagma = new Syntagma();
		ScriptBuilder builder = new ScriptBuilder(lang, syntagma);
		return builder.build(sourceLines);
	}

	private DataContainer createContainer() {
		return new StandardDataContainer("id", createDataMap());
	}

	//@Test
	public void testBuild() {
		List<String> source = new ArrayList<String>();
		source.add("// Script com todos os tipos de linha");
		source.add("print 'Executando Script de Teste...'");
		source.add("x = 11");
		source.add("y = 0");
		source.add("z= 0");		
		source.add("if x > 10");

		source.add("switch x");
		source.add("case 11");
		source.add("z = x * 2");
		source.add("case 12");
		source.add("z = x / 2");
		source.add("print 'x=12'");
		source.add("default");
		source.add("z = 13");
		source.add("end");

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
		source.add("case 1,2");
		source.add("print 'y=1'");
		source.add("default");
		source.add("print 'y=3'");
		source.add("end");
		source.add("while (y < 5)");
		source.add("y = y +1");
		source.add("print 'aumentando y para ' + y");
		source.add("end");
		Script script = build(source);
		System.out.println(script.toSource());
		script.execute(createContainer());
	}
	
	@Test
	public void testSimpleBlock(){
		List<String> source = new ArrayList<String>();
		source.add("print 'SCRIPT TEST - Simple Block'");
		source.add("x = 8");
		source.add("y = 2");
		source.add("print x + y");		
		source.add("print x - y");
		Script script = build(source);
		DataContainer container = createContainer();
		script.execute(container);
		assertTrue(container.getValue("x").equals(8));
		assertTrue(container.getValue("y").equals(2));
	}

	@Test
	public void testSimpleIf(){
		DataContainer container = createContainer();
		container.setValue("x", 8);
		List<String> source = new ArrayList<String>();
		source.add("print 'SCRIPT TEST - Simple IF'");
		source.add("if x % 2 == 0");
		source.add("    print 'x é par'");
		source.add("end");
		source.add("x = x + 1");
		Script script = build(source);
		script.execute(container);
		assertTrue(container.getValue("x").equals(9));
		script.execute(container);
		assertTrue(container.getValue("x").equals(10));
	}
	
	@Test
	public void testSimpleIfElse(){
		DataContainer container = createContainer();
		container.setValue("x", 5);
		List<String> source = new ArrayList<String>();
		source.add("print 'SCRIPT TEST - Simple IF-ELSE'");
		source.add("if x % 2 == 0");
		source.add("    print 'x é par'");
		source.add("    y = 2");
		source.add("else");
		source.add("    print 'x é ímpar'");
		source.add("    y = 3");
		source.add("end");
		source.add("print 'level 1'");
		Script script = build(source);
		script.execute(container);
		assertTrue(container.getValue("x").equals(5));
		assertTrue(container.getValue("y").equals(3));
	}
	
	@Test
	public void testSimpleIfElseIfElse(){
		DataContainer container = createContainer();
		container.setValue("y", 0);
		List<String> source = new ArrayList<String>();
		source.add("print 'SCRIPT TEST - Simple IF-ELSEIF-ELSE'");
		source.add("if x > 10");
		source.add("    print 'BIG'");
		source.add("    y = 1");
		source.add("elseif x < 5");
		source.add("    print 'SMALL'");
		source.add("    y = 2");
		source.add("else");
		source.add("    print x");
		source.add("    y = 3");
		source.add("end");
		source.add("print 'level 1'");
		Script script = build(source);
		container.setValue("x", 15);
		script.execute(container);
		assertTrue(container.getValue("y").equals(1));
		container.setValue("x", 2);
		script.execute(container);
		assertTrue(container.getValue("y").equals(2));
		container.setValue("x", 7);
		script.execute(container);
		assertTrue(container.getValue("y").equals(3));
	}
	
	
}


