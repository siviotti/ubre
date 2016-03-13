package br.net.ubre.framework.rule.body;

import br.net.ubre.behave.Executable;
import br.net.ubre.data.container.DataContainer;
import br.net.ubre.framework.rule.RuleBody;

@RuleBody(ruleId="R004")
public class TestReflectBody implements Executable{

	public void execute(DataContainer container) {
		System.out.println("Primeira regra de código!!!!!!!");
		
	}

}
