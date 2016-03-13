package br.ubre.ctd.slang.script;

import java.util.ArrayList;
import java.util.List;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.data.container.StandardDataContainer;
import br.net.ubre.data.map.DataMap;
import br.net.ubre.data.var.ValueType;
import br.net.ubre.framework.Variable;
import br.net.ubre.lang.Lang;
import br.net.ubre.lang.Syntax;
import br.net.ubre.slang.Syntagma;
import br.net.ubre.slang.build.ScriptBuilder;
import br.net.ubre.slang.script.Script;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 25/10/2015
 */
public class ScriptPerformanceTest {
	
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
	public void script() {
		List<String> source = new ArrayList<String>();
		source.add("x = 7");
		source.add("y = 0");
		source.add("if x > 10");
		source.add("y = 1");
		source.add("elseif  x < 5");
		source.add("y = 2");
		source.add("else");
		source.add("y = 3");
		source.add("end");
		Script script = build(source);
		DataContainer container = createContainer();

		int COUNT = 1000000;
		long t0 = System.currentTimeMillis();
		for (int i = 0; i < COUNT; i++) {
			script.execute(container);
		}
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < COUNT; i++) {
			int x = 7;
			int y = 0;
			if (x > 10) {
				y = 1;
			} else if (x < 5) {
				y = 2;
			} else {
				y = 3;
			}
		}
		long t2 = System.currentTimeMillis();
		System.out.println("ubre:" + (t1 - t0));
		System.out.println("java:" + (t2 - t1));
	}
	
	//@Test
	public void testWhile(){
		List<String> source = new ArrayList<String>();
		source.add("x = 0");
		source.add("while (x < 1000000)");
		source.add("x = x + 1");
		source.add("end");
		Script script = build(source);
		DataContainer container = createContainer();
		
		long t0= System.currentTimeMillis();
		script.execute(container);
		long t1 = System.currentTimeMillis();
		
		int x = 0;
		while (x < 10000){
			x = x + 1;
			script = build(source);
		}
		long t2 = System.currentTimeMillis();
		System.out.println("ubre:" + (t1 - t0));
		System.out.println("java:" + (t2 - t1));		
	}

}
