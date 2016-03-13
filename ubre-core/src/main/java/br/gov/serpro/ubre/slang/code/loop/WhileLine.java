package br.gov.serpro.ubre.slang.code.loop;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.slang.code.CodeBlock;
import br.gov.serpro.ubre.slang.condition.Condition;
import br.gov.serpro.ubre.slang.parse.SourceLine;

/**
 * Linha de repetiçao while.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 24/10/2015
 *
 */
public class WhileLine extends CodeBlock {

	private Condition condition;

	public WhileLine(SourceLine sourceLine, Condition condition) {
		super(sourceLine);
		this.condition = condition;
	}

	@Override
	public void execute(DataContainer container) {
		while (condition.isTrue(container)) {
			super.execute(container);
		}
	}

}
