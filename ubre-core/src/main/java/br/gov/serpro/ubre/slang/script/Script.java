package br.gov.serpro.ubre.slang.script;

import java.util.List;

import br.gov.serpro.ubre.behave.Executable;
import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.slang.code.CodeLine;

/**
 * Um script é um conjunto de linhas de código usando ou não a sintaxe expandida
 * (if/else/switch etc) e os comandos de Script. O código de um script tem uma
 * instrução por linha, ou seja, a quebra de linha determina o fim da instrução.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 09/10/2015
 */
public class Script implements Executable {

	private List<CodeLine> codeLines;

	public Script(List<CodeLine> codeLines) {
		super();
		this.codeLines = codeLines;
	}

	public void execute(DataContainer container) {
		for (CodeLine codeLine : codeLines) {
			try {
				codeLine.execute(container);
			} catch (CTDException e) {
				throw new ScriptException(codeLine, e);
			}
		}
	}

	/**
	 * Retorna o código fonte em forma de lista onde cada elemento da lista é
	 * uma linha do código.
	 * 
	 * @return A lista de linhas.
	 */
	public String toSource() {
		StringBuilder sb = new StringBuilder();
		for (CodeLine line: codeLines){
			sb.append(line.toSource(0));
		}		
		return sb.toString();
	}

	public boolean isExpression() {
		return codeLines.size()==1 && codeLines.get(0).isExpression();
	}


}
