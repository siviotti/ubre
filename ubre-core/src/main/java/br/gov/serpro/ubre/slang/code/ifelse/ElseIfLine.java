package br.gov.serpro.ubre.slang.code.ifelse;

import br.gov.serpro.ubre.slang.code.BlockCloser;
import br.gov.serpro.ubre.slang.condition.Condition;
import br.gov.serpro.ubre.slang.parse.SourceLine;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 */
public class ElseIfLine extends IfLine implements BlockCloser, ElseParent{

	public ElseIfLine(SourceLine sourceLine,  Condition condition) {
		super(sourceLine, condition);
	}

	@Override
	public String toSource(int level) {
		return super.toSource(level-1);
	}

	
}
