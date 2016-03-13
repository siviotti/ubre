package br.net.ubre.framework;

import br.net.ubre.behave.Executable;
import br.net.ubre.data.container.DataContainer;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 */
public class ExampleRule implements Executable{

	public void execute(DataContainer container) {
		System.out.println("*** Exemplo de regra de código!! ***");
	}

}
