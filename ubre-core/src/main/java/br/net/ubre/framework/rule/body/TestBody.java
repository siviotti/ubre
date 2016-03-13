package br.net.ubre.framework.rule.body;

import br.net.ubre.behave.Executable;
import br.net.ubre.data.container.DataContainer;

public class TestBody implements Executable {

	public void execute(DataContainer container) {
		System.out.println("Teste de Body customizado em código");
	}

}
