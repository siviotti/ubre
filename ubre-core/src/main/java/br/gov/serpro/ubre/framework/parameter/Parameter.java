package br.gov.serpro.ubre.framework.parameter;

import br.gov.serpro.ubre.data.container.DataContainer;

/**
 * Representa um parâmetro.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public abstract class Parameter {

	private String source;

	public Parameter(String source) {
		super();
		this.source = source;
	}
	
	public abstract Object eval(DataContainer container);

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

}
