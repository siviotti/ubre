package br.net.ubre.io.txt;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe com metodos utilitarios para manipulação de texto.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 29/03/2011
 * 
 */
public final class Text {

	private Text() {
	}

	/**
	 * Determina se um texto (String) e composto somente por digitos numericos.
	 * Ou seja, na String so podem haver caracteres entre 0 e 9. Qualquer outro
	 * caractere fara o metodo retornar <code>false</code>.
	 * 
	 * @param text
	 *            O texto que deve ser testado.
	 * @return <code>true</code> se so houver caracteres numericos (0-9) ou
	 *         <code>false</code> se houver ao menos um caractere nao numerico.
	 */
	public static boolean isOnlyDigits(String text) {
		if (text == null || text.trim().length() == 0) {
			return false;
		}
		for (int i = 0; i < text.length(); i++) {
			if (!Character.isDigit(text.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Preenche <b>(à Direita)</b> uma String com uma determinada quantidade do
	 * caractere de complemento passado como parâmetro.
	 * 
	 * @param baseText
	 *            O texto a ser compĺetado.
	 * @param ch
	 *            O caractere de complemento.
	 * @param size
	 *            O tamanho total que a String final deve ter.
	 */
	public static String fill(final String baseText, final char ch,
			final int size) {
		String text = baseText;
		if (text == null) {
			text = "";
		}
		StringBuffer sb = new StringBuffer(text);
		for (int i = text.length(); i < size; i++) {
			sb.append("" + ch);
		}
		return sb.toString();
	}

	public static String cut(final String baseText, int size) {
		return baseText.substring(0, size);
	}

	public static String leftCut(final String baseText, int size) {
		return baseText.substring(baseText.length() - size, baseText.length());
	}

	/**
	 * Preenche <b>(à Direita)</b> com espaços uma String com uma determinada
	 * quantidade de espaços vazios até completar o tamanho passado por
	 * parâmetro.
	 * 
	 * @param text
	 *            O texto a ser compĺetado.
	 * @param size
	 *            O tamanho total que a String final deve ter.
	 */
	public static String fill(String text, int size) {
		return fill(text, ' ', size);
	}

	/**
	 * Preenche <b>(à Esquerda)</b> uma String com uma determinada quantidade do
	 * caractere de complemento passado como parâmetro.
	 * 
	 * @param baseText
	 *            O texto a ser compĺetado.
	 * @param ch
	 *            O caractere de complemento.
	 * @param size
	 *            O tamanho total que a String final deve ter.
	 */
	public static String leftFill(final String baseText, final char ch,
			final int size) {
		String text = baseText;
		if (text == null) {
			text = "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = text.length(); i < size; i++) {
			sb.append(ch);
		}
		sb.append(text);
		return sb.toString();
	}

	/**
	 * Preenche <b>(à Esquerda)</b> uma String com uma determinada quantidade do
	 * caractere de complemento passado como parâmetro.
	 * 
	 * @param text
	 *            O texto a ser compĺetado.
	 * @param ch
	 *            O caractere de complemento.
	 * @param size
	 *            O tamanho total que a String final deve ter.
	 */
	public static String leftFill(String text, int size) {
		return leftFill(text, ' ', size);
	}

	/**
	 * Determina se um texto tem "valor vazio". Valor vazio que é quando o texto
	 * é nulo (<code>null</code>), é igual a "" (String vazia) ou é composto
	 * somente por espaços em branco.
	 * 
	 * <pre>
	 * return text == null || text.trim().length() == 0;
	 * </pre>
	 * 
	 * @param text
	 *            A instância de String que contém o texto
	 * @return <code>true</code> se é vazio (null ou espaços) ou
	 *         <code>false</code> se contiver algum caractere diferente de
	 *         espaço.
	 */
	public static boolean isNullOrEmpty(String text) {
		return text == null || text.trim().length() == 0;
	}

	/**
	 * bc = Before Char - Tudo o que vem antes de um determinado caractere.
	 * 
	 * @param source
	 *            O texto origem
	 * @param c
	 *            O caractere separador
	 * @return O texto antes do caractere ou null.
	 */
	public static String bc(final String source, char c) {
		if (source == null) {
			return null;
		}
		int index = source.indexOf(c);
		if (index < 0) {
			return null;
		}
		return source.substring(0, index);
	}

	/**
	 * Extrai a String dentro de um bloco formado por um caractere de abertura e
	 * um de fechamento.
	 * 
	 * @param source
	 *            O texto
	 * @param firstLeft
	 *            O primeiro caractere encontrado de abertura.
	 * @param firstRight
	 *            O primeiro caractere encontrado de fechamento.
	 * @return A String dentro do bloco.
	 */
	public static String block(final String source, char firstLeft,
			char firstRight) {
		if (source == null) {
			return null;
		}
		String temp = ac(source, firstLeft);
		if (temp == null) {
			return null;
		}
		return bc(temp, firstRight);
	}

	/**
	 * ac = After Char - Tudo o que vem depois de um determinado caractere.
	 * 
	 * @param source
	 *            O texto origem
	 * @param c
	 *            O caractere separador
	 * @return O texto depois do caractere ou null.
	 */
	public static String ac(final String source, char c) {
		if (source == null) {
			return null;
		}
		int index = source.indexOf(c);
		if (index < 0) {
			return null;
		}
		return source.substring(index + 1, source.length());
	}

}
