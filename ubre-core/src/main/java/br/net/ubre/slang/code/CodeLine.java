package br.net.ubre.slang.code;

import br.net.ubre.behave.Executable;
import br.net.ubre.slang.parse.SourceLine;
import br.net.ubre.util.Freezable;
import br.net.ubre.util.GenericFreezable;

/**
 * Representa uma linha compilada e executável do código de um script (ignorando
 * comantários). Uma linha de código pode ser um if, elseif, else, action, code,
 * switch ou qualquer outro tipo de instrução de execução. Toda linha de código
 * é um objeto executável.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 * @see Executable
 * @see GenericFreezable
 */
public abstract class CodeLine extends GenericFreezable implements Executable {

	private SourceLine sourceLine;

	public CodeLine(SourceLine sourceLine) {
		super();
		this.sourceLine = sourceLine;
	}

	/**
	 * Retorna a linha no fomato Texto.
	 * 
	 * @param level
	 *            O nível de identação (0 = main block, 1 = primeiro nível etc)
	 * @return A String que representa a linha de código em TXT.
	 */
	public abstract String toSource(int level);

	/**
	 * Retorna o espaço inicial (em caracteres de espaço) antes da instrução.
	 * * @param level Fator de multiplicação de identação (0 = main block, 1 =
	 * primeiro nível etc)
	 * 
	 * @return Os espaços iniciais.
	 */
	public String initSpace(int level) {
	    StringBuilder sb = new StringBuilder();
		for (int i=0; i < level ; i++){
			sb.append("    ");
		}
		return sb.toString();
	}

	/**
	 * Retorna o token do início da linha.
	 * 
	 * @return O token.
	 */
	public String getToken() {
		return sourceLine.getKeyword().getToken();
	}

	/**
	 * O objeto que representa o código que deu origem a esta linha.
	 * 
	 * @return
	 */
	public SourceLine getSourceLine() {
		return sourceLine;
	}

	public boolean isExpression() {
		return false;
	}

	@Override
	public String toString() {
		return (sourceLine != null) ? sourceLine.toString() : "INIT BLOCK";
	}

}
