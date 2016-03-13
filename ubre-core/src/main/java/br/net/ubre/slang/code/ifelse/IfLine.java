package br.net.ubre.slang.code.ifelse;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.slang.code.CodeBlock;
import br.net.ubre.slang.code.CodeLine;
import br.net.ubre.slang.condition.Condition;
import br.net.ubre.slang.parse.SourceLine;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 */
public class IfLine extends CodeBlock implements ElseParent {

	private Condition condition;
	protected CodeLine elseLine;

	public IfLine(SourceLine sourceLine, Condition condition) {
		super(sourceLine);
		this.condition = condition;
	}

	public void execute(DataContainer container) {
		if (condition.isTrue(container)) {
			super.execute(container);
		} else if (elseLine != null) {
			elseLine.execute(container);
		}
	}

	public CodeLine getElseLine() {
		return elseLine;
	}

	public void linkElse(CodeLine elseLine) {
		testFrozen();
		this.elseLine = elseLine;
		tryClose(elseLine);
	}

	@Override
	protected boolean canClose(CodeLine codeLine) {
		return super.canClose(codeLine) || codeLine instanceof ElseLine || codeLine instanceof ElseIfLine;
	}

	@Override
	public String toSource(int level) {
		StringBuilder sb = new StringBuilder();
		sb.append(initSpace(level) + getSourceLine().toSource());
		sb.append("\n");
		for (CodeLine line : getCodeLines()) {
			sb.append(line.toSource(level + 1));
		}
		if (elseLine != null) {
			sb.append(elseLine.toSource(level + 1));
		}
		return sb.toString();
	}

}
