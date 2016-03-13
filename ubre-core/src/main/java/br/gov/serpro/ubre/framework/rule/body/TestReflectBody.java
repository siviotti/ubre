package br.gov.serpro.ubre.framework.rule.body;

import br.gov.serpro.ubre.behave.Executable;
import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.framework.rule.RuleBody;

@RuleBody(ruleId="R004")
public class TestReflectBody implements Executable{

	public void execute(DataContainer container) {
		System.out.println("Primeira regra de código!!!!!!!");
		
	}

}
