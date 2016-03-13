package br.net.ubre.slang.code;

import java.util.Arrays;
import java.util.List;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.exception.CTDException;
import br.net.ubre.lang.LangError;
import br.net.ubre.slang.SLangException;
import br.net.ubre.slang.parse.SourceLine;

/**
 * Representa um bloco de código, ou seja, uma ou várias linhas. Sua função é
 * agrupar outras linhas e não ser uma ação. No entanto, pode ser condicional ou
 * de repetição.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 */
public class CodeBlock extends CodeLine {

	private static final String CODE_BLOCK_CANNOT_CLOSED_BY_TOKEN = "O bloco %s não pode ser fechado com o token %s";

	private static final String CODE_BLOCK_ALREADY_CLOSED = "O bloco '%s'já está fechado!";

	private CodeLine[] codeLines = new CodeLine[0];
	protected boolean closed;

	public CodeBlock(SourceLine sourceLine) {
		super(sourceLine);
	}

	public void execute(DataContainer container) {
		int lineIndex = 0;
		int size = codeLines.length;
		while (lineIndex < size) {
			codeLines[lineIndex].execute(container);
			lineIndex++;
		}
	}

	/**
	 * Método que adiciona uma linha de código ao corpo.
	 * 
	 * @param codeLine
	 *            A linha de código já montada.
	 */
	public void link(CodeLine codeLine) {
		testFrozen();
		if (codeLine== null){
			throw new CTDException("CodeLine null!!!");
		}
		if (closed) {
			String msg = String.format(CODE_BLOCK_ALREADY_CLOSED, getSourceLine().toSource());
			throw new SLangException(LangError.E20, msg);
		}
		CodeLine[] newArray = new CodeLine[codeLines.length + 1];
		for (int i = 0; i < codeLines.length; i++) {
			newArray[i] = codeLines[i];
		}
		newArray[codeLines.length] = codeLine;
		codeLines = newArray;
		tryClose(codeLine);
	}

	protected void tryClose(CodeLine codeLine) {
		if (codeLine instanceof BlockCloser) {
			// System.out.println(" CLOSE " + toString() + " - " +
			// codeLine.toString());
			if (canClose(codeLine)) {
				closed = true;
			} else {
				errorClosingBlock(codeLine);
			}
		}
	}

	protected boolean canClose(CodeLine codeLine) {
		return codeLine instanceof EndLine;
	}

	protected void errorClosingBlock(CodeLine codeLine) {
		String msg = String.format(CODE_BLOCK_CANNOT_CLOSED_BY_TOKEN, getSourceLine().toSource(), codeLine.getToken());
		throw new SLangException(LangError.E20, msg);
	}

	/**
	 * retorna uma cópia da lista.
	 * 
	 * @return
	 */
	public List<CodeLine> getCodeLines() {
		return Arrays.asList(codeLines);
	}

	@Override
	public String toSource(int level) {
		StringBuilder sb = new StringBuilder();
		sb.append(initSpace(level) + getSourceLine().toSource());
		sb.append("\n");
		for (CodeLine line : codeLines) {
			sb.append(line.toSource(level + 1));
		}
		return sb.toString();
	}

	/**
	 * Informa se o bloco foi fechado por uma linha de fechamento.
	 * 
	 * @return
	 */
	public boolean isClosed() {
		return closed;
	}

	public void close() {
		closed = true;
	}

}
