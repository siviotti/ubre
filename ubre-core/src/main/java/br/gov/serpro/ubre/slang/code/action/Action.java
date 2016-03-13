package br.gov.serpro.ubre.slang.code.action;

import br.gov.serpro.ubre.behave.Executable;

/**
 * Ação genérica. É uma instrução executável.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 11/10/2015
 */
public abstract class Action implements Executable {

	public abstract String toSource();

}
