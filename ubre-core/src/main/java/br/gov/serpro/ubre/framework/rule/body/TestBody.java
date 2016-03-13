package br.gov.serpro.ubre.framework.rule.body;

import br.gov.serpro.ubre.behave.Executable;
import br.gov.serpro.ubre.data.container.DataContainer;

public class TestBody implements Executable {

	public void execute(DataContainer container) {
		System.out.println("Teste de Body customizado em código");
	}

}
