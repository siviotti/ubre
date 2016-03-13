package br.net.ubre.slang.code;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.slang.parse.SourceLine;

/**
 * Representa uma linha de fechamento de bloco.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 09/10/2015
 */
public class EndLine extends CodeLine implements BlockCloser {
	

	public EndLine(SourceLine sourceLine) {
		super(sourceLine);
	}

	public void execute(DataContainer container) {

	}
	@Override
	public String toSource(int level) {
		return initSpace(level-1) + "end\n";
	}

	
}
