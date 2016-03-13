package br.net.ubre.lang.compile;

import java.util.List;

import br.net.ubre.lang.Syntax;
import br.net.ubre.lang.keyword.KeywordStatement;
import br.net.ubre.lang.keyword.MultiOperationKeyword;
import br.net.ubre.lang.statement.Statement;

/**
 * Classe que faz a anlálise e verificaçao sintática e semântica de uma
 * expressão. Esta classe faz a funçao de um pré compilador ou parser de segundo
 * nível.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 22/04/2015
 * 
 */
public class Checker {

	private Syntax syntax;

	public Checker(Syntax syntax) {
		this.syntax = syntax;
	}

	/**
	 * Verifica uma expressão já desmembrada em Statements.
	 * 
	 * @param list
	 *            A lista de Statements de uma expressão.
	 */
	public void check(List<Statement> list) {
		KeywordStatement keyword;
		Statement left;
		Statement right;
		for (int i = 0; i < list.size(); i++) {
			if (!(list.get(i) instanceof KeywordStatement)) {
				continue;
			}
			keyword = (KeywordStatement) list.get(i);
			left = (i > 0) ? list.get(i - 1) : null;
			right = (i < list.size() - 1) ? right = list.get(i + 1) : null;
			// Operador traço pode ser "menos" ou "negativo"
			checkMultiOperation(keyword, left, right);
		}
	}

	private void checkMultiOperation(Statement keyword, Statement left,
			Statement right) {
		if (keyword instanceof MultiOperationKeyword) {
			((MultiOperationKeyword) keyword).defineOperation(left, right);
		}
	}
}
