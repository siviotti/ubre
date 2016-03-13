package br.gov.serpro.ubre.io;

import br.gov.serpro.ubre.framework.Context;

/**
 * Classe que efetua a leitura do COntexto.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public interface ContextLoader {
	
	Context load(String file, String encoding);
	
}
