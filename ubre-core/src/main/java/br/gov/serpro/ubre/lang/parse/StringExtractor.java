package br.gov.serpro.ubre.lang.parse;

import java.text.ParseException;

import br.gov.serpro.ubre.internal.Const;

/**
 * Classe que extrai as String de uma expressão e as coloca na tabela de Strings
 * substituindo as String originais por uma variável do tipo $x onde x é a
 * posição da String na tabela.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 16/04/2015
 * 
 */
public class StringExtractor {

	private String inserted = new String("");

	/**
	 * Extrai as String de uma expressão ou script e as coloca na tabela de
	 * Strings, em seguida as substitui por $x onde x é a posição da String na
	 * tabela.
	 * 
	 * @param source
	 *            A expressão ou script original.
	 * @param stringMap
	 *            A tabela de Strings.
	 * @return A nova expressão.
	 * @throws ParseException
	 */
	public String extract(String source, StringMap stringMap)
			throws ParseException {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < source.length(); i++) {
			if (source.charAt(i) == Const.STRING_CHAR1
					|| source.charAt(i) == Const.STRING_CHAR2) {
				i = extractString(stringMap, source, i, source.charAt(i));
				sb.append(inserted);
			} else {
				sb.append(source.charAt(i));
			}
		}
		debug(sb);
		return sb.toString();
	}

	protected void debug(StringBuilder sb) {
		// Template Method para visualizar o reusltado nos testes
	}

	public int extractString(StringMap stringMap, String expression, int begin,
			char quotes) throws ParseException {
		if (begin >= expression.length()) {
			throw new ParseException("Índice inicial inválido:" + begin + "\n"
					+ expression, 0);
		}
		if (expression.charAt(begin) != quotes) {
			throw new ParseException(
					"Caractere de início não é uma aspa dupla nem simples:"
							+ expression.charAt(begin) + " diferente de "
							+ quotes + "\n" + expression, begin);
		}

		StringBuilder sb = new StringBuilder("");
		int index = begin + 1;
		while (true) {
			if (index > expression.length() - 1) {
				throw new ParseException("String não terminada corretamente."
						+ sb.toString() + "\n" + expression, begin);
			}
			if (expression.charAt(index) == quotes) {
				inserted = stringMap.add(sb.toString());
				break;
			} else {
				sb.append(expression.charAt(index));
			}
			index++;
		}
		return index;
	}

}
