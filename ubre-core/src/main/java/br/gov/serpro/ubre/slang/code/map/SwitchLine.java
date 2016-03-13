package br.gov.serpro.ubre.slang.code.map;

import br.gov.serpro.ubre.slang.parse.SourceLine;

/**
 * Representa um switch/case usando linhas de código.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 */
public class SwitchLine extends MapBlock {


	public SwitchLine(SourceLine sourceLine, String target) {
		super(sourceLine, target);
	}


}
