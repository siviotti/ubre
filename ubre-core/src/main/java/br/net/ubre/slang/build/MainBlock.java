package br.net.ubre.slang.build;

import br.net.ubre.slang.Syntagma;
import br.net.ubre.slang.code.CodeBlock;
import br.net.ubre.slang.parse.SourceLine;

/**
 * Bloco usado pelo compilador para agregar as linhas de código enquanto ainda
 * não foram passadas para o Script. O MainBlock é o Script ainda não compilado.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 23/10/2015
 */
public class MainBlock extends CodeBlock {

	public MainBlock(Syntagma syntagma) {
		super(new SourceLine(syntagma, 0, "'main'"));

	}

}
