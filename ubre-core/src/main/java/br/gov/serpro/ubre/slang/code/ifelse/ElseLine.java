package br.gov.serpro.ubre.slang.code.ifelse;

import br.gov.serpro.ubre.slang.code.BlockCloser;
import br.gov.serpro.ubre.slang.code.CodeBlock;
import br.gov.serpro.ubre.slang.parse.SourceLine;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 */
public class ElseLine extends CodeBlock implements BlockCloser{

	public ElseLine(SourceLine sourceLine) {
		super(sourceLine);
	}
	
	@Override
	public String toSource(int level) {
		return super.toSource(level-1);
	}

}
