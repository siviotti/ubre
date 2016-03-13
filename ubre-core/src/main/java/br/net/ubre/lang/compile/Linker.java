package br.net.ubre.lang.compile;

import java.util.ArrayList;
import java.util.List;

import br.net.ubre.exception.CTDException;
import br.net.ubre.lang.keyword.KeywordStatement;
import br.net.ubre.lang.keyword.bracket.CloseBracketOperator;
import br.net.ubre.lang.keyword.bracket.OpenBracketOperator;
import br.net.ubre.lang.keyword.parenthesis.CloseParenthesisOperator;
import br.net.ubre.lang.keyword.parenthesis.OpenParenthesisOperator;
import br.net.ubre.lang.parse.ExpParser;
import br.net.ubre.lang.statement.PointerStatement;
import br.net.ubre.lang.statement.Statement;

/**
 * Classe que liga os Statements em função de sua precedência e posição em uma
 * expressão. O linker monta a árvore binária de execução.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 17/04/2015
 * 
 * @see ExpParser
 */
public class Linker {

	private static final String EXTRA_CLOSE = "Parêntetes fechando sem ter aberto no Statement:";
	private static final String OPEN_DIF_CLOSE = "Parênteses abertos não correspondem aos fechados:";

	/**
	 * Monta a árvore binária de e execução de uma expressão a partir de seus
	 * Statements já previamente parseados.
	 * 
	 * @param list
	 *            A lista de Statements na ordem exata em que aparecem na
	 *            expressão. Esta lista deve vr de um Parser que transforma o
	 *            texto cru em lista de instancia de Stetements.
	 * @return O Statement "root" da árvore binária. A execuçao inicia por ele.
	 */
	public Statement link(List<Statement> originalList) {
		if (originalList == null || originalList.isEmpty()) {
			throw new CTDException("[Linker] nenhum token encontrado");
		}
		List<Statement> list = new ArrayList<Statement>(originalList);
		List<Statement> temp = extractSub(list);
		temp = extractBracket(temp);
		KeywordStatement keyword = null;
		for (int precedence = 0; precedence < 18; precedence++) {
			for (int index = 0; index < temp.size(); index++) {
				if (temp.get(index) instanceof KeywordStatement) {
					keyword = (KeywordStatement) temp.get(index);
					if (keyword.precedence() == precedence) {
						index = linkStatements(keyword, temp, index);
					}
				}
			}
		}
		return (keyword != null) ? keyword : temp.get(0);
	}

	private List<Statement> extractBracket(List<Statement> originalList) {
		if (originalList.get(0) instanceof OpenBracketOperator) {
			throw new CTDException(
					"Uma expressão não pode iniciar com colchete: [ \n"
							+ originalList);
		}
		return extractBlock(originalList, OpenBracketOperator.class,
				CloseBracketOperator.class);
	}

	private List<Statement> extractSub(List<Statement> originalList) {
		return extractBlock(originalList, OpenParenthesisOperator.class,
				CloseParenthesisOperator.class);
	}

	private List<Statement> extractBlock(List<Statement> originalList,
			Class<?> openClass, Class<?> closeClass) {
		List<Statement> temp = new ArrayList<Statement>();
		int i = 0;
		while (i < originalList.size()) {
			temp.add(originalList.get(i));
			if (openClass.equals(originalList.get(i).getClass())) {
				i = createPointer(i, originalList, temp, openClass, closeClass);
			} else if (closeClass.equals(originalList.get(i).getClass())) {
				throw new CTDException(EXTRA_CLOSE + i + " " + originalList);
			}
			i++;
		}
		return temp;
	}

	private int createPointer(int openIndex, List<Statement> list,
			List<Statement> temp, Class<?> openClass, Class<?> closeClass) {
		int index = openIndex;
		List<Statement> sub = new ArrayList<Statement>();
		int open = 1;
		int close = 0;
		while (open > close) {
			index++;
			if (index > list.size() - 1) {
				throw new CTDException(OPEN_DIF_CLOSE + open + "/" + close);
			}
			if (closeClass.equals(list.get(index).getClass())) {
				close++;
			} else if (openClass.equals(list.get(index).getClass())) {
				open++;
			}
			sub.add(list.get(index));
		}
		sub.remove(sub.size() - 1); // remove o elemento de fechamento ) ou ]
		Statement root = link(sub);
		if (openClass.equals(OpenParenthesisOperator.class)) {
			temp.remove(temp.size() - 1);
			temp.add(new PointerStatement(root));
		} else if (openClass.equals(OpenBracketOperator.class)) {
			Statement left = temp.get(openIndex - 1);
			((KeywordStatement) temp.get(openIndex)).link(left, root);
		} else {
			throw new CTDException("Operador inválido antes de bloco:"
					+ temp.get(openIndex));
		}
		return index;
	}

	private int linkStatements(KeywordStatement keyword, List<Statement> temp,
			int index) {
		int next = index + 1;
		Statement left = null;
		Statement right = null;
		if (index > 0) {
			left = temp.get(index - 1);
		}
		if (index < temp.size() - 1) {
			right = temp.get(index + 1);
		}
		// link do elemento central com os laterais
		keyword.link(left, right);
		// eliminação dos laterais
		if (left != null && keyword.useLeftStatement()) {
			temp.remove(index - 1);
			next--;
		}
		if (right != null && keyword.useRightStatement()) {
			temp.remove(next);
			next--;
		}
		return next;
	}

}
