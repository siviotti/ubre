package br.gov.serpro.ubre.io;

import br.gov.serpro.ubre.framework.Context;

/**
 * Interface que define o método de gravação de um contexto.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 26/03/2015
 * 
 */
public interface ContextWriter {

	void write(Context context, String file, String encoding);
}
