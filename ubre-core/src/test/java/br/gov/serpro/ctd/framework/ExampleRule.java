package br.gov.serpro.ctd.framework;

import br.gov.serpro.ubre.behave.Executable;
import br.gov.serpro.ubre.data.container.DataContainer;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 */
public class ExampleRule implements Executable{

	public void execute(DataContainer container) {
		System.out.println("*** Exemplo de regra de código!! ***");
	}

}
