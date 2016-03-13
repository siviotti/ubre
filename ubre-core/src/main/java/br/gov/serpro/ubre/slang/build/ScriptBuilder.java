package br.gov.serpro.ubre.slang.build;

import java.util.List;

import br.gov.serpro.ubre.lang.Lang;
import br.gov.serpro.ubre.lang.LangError;
import br.gov.serpro.ubre.slang.SLangException;
import br.gov.serpro.ubre.slang.Syntagma;
import br.gov.serpro.ubre.slang.code.CodeBlock;
import br.gov.serpro.ubre.slang.code.CodeLine;
import br.gov.serpro.ubre.slang.code.ifelse.ElseIfLine;
import br.gov.serpro.ubre.slang.code.ifelse.ElseLine;
import br.gov.serpro.ubre.slang.code.ifelse.ElseParent;
import br.gov.serpro.ubre.slang.code.map.KeyBlock;
import br.gov.serpro.ubre.slang.code.map.MapBlock;
import br.gov.serpro.ubre.slang.parse.ScriptParser;
import br.gov.serpro.ubre.slang.script.Script;

/**
 * Compilador de Script.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 12/10/2015
 */
public class ScriptBuilder {

	public static final String SBUILD_INTERRUPTED = "O processo de build parou inesperadamente na linha %d:%s";

	public static final String SBUILD_BLOCK_NOT_CLOSED = "Bloco não terminado corretamente: ";

	public static final String SBUILD_CANNOT_USE_ELSE = "Não é possível usar um 'else' após um bloco sem condição. Somente é possível usar 'else' após 'if' ou 'elseif'.";

	public static final String SBUILD_BLOCK_IS_EMPTY = "Não é permitido um bloco vazio:";

	private ScriptParser parser;

	public ScriptBuilder(Lang lang, Syntagma syntagma) {
		super();
		parser = new ScriptParser(lang, syntagma);
	}

	/**
	 * Retorna uma instância de <code>Script</code> a partir de um fonte em
	 * texto.
	 * 
	 * @param sourceLines
	 *            O código fonte onde cada linha é um elemento do LIst.
	 * @return A instância de Script.
	 */
	public Script build(List<String> sourceLines) {
		// Passo 1 - Parsing
		List<CodeLine> plainLines = parser.parse(sourceLines);
		// Passo 2 - Indenting
		MainBlock block = new MainBlock(getSyntagma());
		int lines = linkBlock(block, plainLines, 0);
		if (lines < plainLines.size()) {
			String msg = String.format(SBUILD_INTERRUPTED, lines, plainLines.get(lines));
			throw new SLangException(LangError.E30, msg);
		}
		Script script = new Script(block.getCodeLines());
		return script;
	}

	private int linkBlock(CodeBlock block, List<CodeLine> plainLines, int index) {
		int i = index;
		CodeLine line;
		while (i < plainLines.size()) {
			line = plainLines.get(i);
			if (line instanceof KeyBlock) {
				break;
			}

			link(block, line);
			i++;

			if (line instanceof MapBlock) {
				i = linkMapBlock((MapBlock) line, plainLines, i);
			} else if (line instanceof CodeBlock) {
				i = linkBlock((CodeBlock) line, plainLines, i);
			}

			if (block.isClosed()) {
				break;
			}
		}
		validateBlock(block);
		return i;
	}

	private void validateBlock(CodeBlock block) {
		if (!block.isClosed()) {
			if (!(block instanceof MainBlock) && !(block instanceof KeyBlock)) {
				throw new SLangException(LangError.E20, SBUILD_BLOCK_NOT_CLOSED + block.toString());
			}
		}
		if (block.getCodeLines().isEmpty()) {
			throw new SLangException(LangError.E20, SBUILD_BLOCK_IS_EMPTY + block.toString());
		}
	}

	private int linkMapBlock(MapBlock mapBlock, List<CodeLine> plainLines, int index) {
		int i = index;
		CodeLine line;
		while (i < plainLines.size()) {
			line = plainLines.get(i);
			if (line instanceof KeyBlock) {
				link(mapBlock, line);
				i++;
				i = linkBlock((KeyBlock) line, plainLines, i);
				if (((KeyBlock) line).isClosed()){
					mapBlock.close();
				} else {
					((KeyBlock) line).close();
				}
			} else {
				if (mapBlock.isClosed()) {
					break;
				} else {
					throw new SLangException(LangError.E20, "Switch só aceita case:" + line.toString());
				}
			}
		}
		return i;
	}

	private void link(CodeBlock block, CodeLine line) {
		//System.out.println("LINK " + line + " @ " + block);
		if (line instanceof ElseLine || line instanceof ElseIfLine) {
			linkElse(block, line);
		} else {
			block.link(line);
		}
	}

	/**
	 * Adiciona um 'else' a um bloco.
	 * 
	 * @param block
	 * @param elseLine
	 */
	private void linkElse(CodeBlock block, CodeLine elseLine) {
		if (block instanceof ElseParent) {
			((ElseParent) block).linkElse(elseLine);
		} else {
			throw new SLangException(LangError.E20, SBUILD_CANNOT_USE_ELSE);
		}
	}

	// ********** GET / SET **********
	public Syntagma getSyntagma() {
		return parser.getSyntagma();
	}

}
