package br.gov.serpro.ubre.lang.parse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.internal.Const;

/**
 * Classe que separa os tokens em função de espaços ou de blocos com
 * delimitadores. O método split retorna os tokens textuais de uma expressão.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 29/03/2013
 * 
 */
public class Splitter {

	private Map<Character, Character> blocks = new HashMap<Character, Character>();
	private Set<String> operators;
	private List<Character> firstChars = new ArrayList<Character>();
	private StringMap stringMap;
	private StringExtractor stringExtractor = new StringExtractor();

	public Splitter(Set<String> operators, StringMap stringMap) {
		super();
		this.stringMap = stringMap;
		blocks.put(Const.DATE_BLOCK[0], Const.DATE_BLOCK[1]);
		this.operators = operators;
		for (String op : operators) {
			firstChars.add(op.charAt(0));
			if (op.length() > 2) {
				throw new CTDException(
						"Operadores devem ter somente 2 caracteres. Operador inválido:"
								+ op);
			}
		}
	}

	public List<String> split(String source) throws ParseException {
		List<String> parts = new ArrayList<String>();
		String s = stringExtractor.extract(source, stringMap);
		StringBuilder token = new StringBuilder();
		char c;
		char endChar;
		int endPos = 0;
		String op;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (isComment(c, i, source)) {
				break;
			}
			if (c == Const.SPLIT_CHAR) {
				addToken(parts, token.toString());
				token = new StringBuilder();
			} else if (c == Const.OPEN_PARENTHESIS_CHAR
					|| c == Const.CLOSE_PARENTHESIS_CHAR
					|| c == Const.OPEN_BRACKET_CHAR
					|| c == Const.CLOSE_BRACKET_CHAR) {
				addToken(parts, token.toString());
				addToken(parts, "" + c);
				token = new StringBuilder();
			} else if (isOperatorFirstChar(c)) {
				addToken(parts, token.toString());
				op = "" + c;
				if (i < s.length()) {
					op = op + s.charAt(i + 1);
				}
				if (operators.contains(op)) {
					addToken(parts, op);
					i++;
				} else {
					addToken(parts, "" + c);
				}
				token = new StringBuilder();
			} else if (isBeginBlockChar(c)) {
				endChar = getEndChar(c, i);
				addToken(parts, token.toString());
				endPos = s.indexOf(endChar, i);
				addToken(parts, s.substring(i, endPos + 1));
				i = endPos;
				token = new StringBuilder();
			} else {
				token.append(c);
			}
		}
		addToken(parts, token.toString());
		debug(parts);
		return new ArrayList<String>(parts);
	}

	private boolean isComment(char c, int i, String source) {
		if (Const.COMMENT_TAG.charAt(0) != c) {// inicia com /
			return false;
		}
		if (i + 1 < source.length()) {
			return source.charAt(i + 1) == Const.COMMENT_TAG.charAt(1);
		}
		return false;
	}

	private char getEndChar(char c, int i) throws ParseException {
		Character endChar = blocks.get(c);
		if (endChar != null) {
			return endChar;
		}
		throw new ParseException("Caractere de fechamento impossível:" + c, i);
	}

	private boolean isBeginBlockChar(char c) {
		return blocks.keySet().contains(c);
	}

	private boolean isOperatorFirstChar(char c) {
		return firstChars.contains(c);
	}

	protected void debug(List<String> parts2) {
		// Template Method para visualizar o reusltado nos testes
	}

	private void addToken(List<String> parts, String token) {
		if (token != null && !token.isEmpty()) {
			parts.add(token);
		}
	}

}
