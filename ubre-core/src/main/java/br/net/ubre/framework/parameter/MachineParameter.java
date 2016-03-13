package br.net.ubre.framework.parameter;

import br.net.ubre.data.container.DataContainer;

/**
 * Parâmetro que aponta para uma variável simples no padrão $xxx, onde xxx é o
 * nome da variável.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class MachineParameter extends Parameter{

	public MachineParameter(String source) {
		super(source);
	}

	@Override
	public Object eval(DataContainer container) {
		return null;
	}

	
}
