package br.net.ubre.slang.code.ifelse;

import br.net.ubre.slang.code.CodeLine;

/**
 * Interface que define os métodos da linhas que utilizam 'else', ou seja, que
 * tem um alternativo.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/10/2015
 *
 */
public interface ElseParent {
	
	public CodeLine getElseLine();

	public void linkElse(CodeLine elseLine);

}
