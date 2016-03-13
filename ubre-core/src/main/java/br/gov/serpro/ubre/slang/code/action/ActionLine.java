package br.gov.serpro.ubre.slang.code.action;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.slang.code.CodeLine;
import br.gov.serpro.ubre.slang.parse.SourceLine;

/**
 * Linha compilada que executa uma ação individual.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @version 08/10/2015
 */
public class ActionLine extends CodeLine {

	private Action action;

	public ActionLine(SourceLine sourceLine, Action action) {
		super(sourceLine);
		this.action = action;
	}

	public void execute(DataContainer container) {
		action.execute(container);
	}

	@Override
	public String toSource(int level) {
		return initSpace(level) + action.toSource() + "\n";
	}

	@Override
	public boolean isExpression() {
		return action instanceof ExpressionAction;
	}

	
}
