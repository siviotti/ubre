package br.net.ubre.data.map;

import java.math.BigDecimal;
import java.text.DateFormat;

import br.net.ubre.internal.Const;

/**
 * Classe com os padrões de construção e parsing de tokens e valores de dados.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 15/10/2015
 */
public class VarPattern {

	// Msg

	public static final String TOKEN_PATTERN_MSG_INVALID_BEGIN = "Variáveis devem iniciar com ";

	public static final String TOKEN_PATTERN_MSG_NULL_OR_EMPTY = "Variável com nome nulo ou vazio.";

	public static final String TOKEN_PATTERN_MSG_FISRT_CHAR = "O primeiro caractere de uma variável deve ser uma letra (a-zA-Z) ou sublinhado (_)";

	public static final String TOKEN_PATTERN_MSG_INVALID_CHAR = "A partir do segundo caractere, uma variável só aceita letras (a-zA-Z), números (0-9) ou sublinhado (_)";

	public static final String TOKEN_PATTERN_MSG_MAX_SIZE = "Variável com tamanho acima do limite";

	public static final String TOKEN_PATTERN_MSG_MIN_SIZE = "Variável com tamanho abaixo do limite";

	/**
	 * Valida um token comum (Const, Var, Id) e retornauma String se houver
	 * problema.
	 * 
	 * @param token
	 *            O token a ser validado.
	 * @return <code>null</code> se o token for válido ou <code>String</code>
	 *         com a mensagem de erro se for inválido.
	 */
	public String validateVarToken(String token) {
		if (token == null || token.trim().isEmpty()) {
			return TOKEN_PATTERN_MSG_NULL_OR_EMPTY;
		}
		if (token.length() > maxSize()) {
			return TOKEN_PATTERN_MSG_MAX_SIZE;
		}
		if (token.length() < minSize()) {
			return TOKEN_PATTERN_MSG_MIN_SIZE;
		}
		Character c = token.charAt(0); // primeiro caractere
		if (!Character.isLetter(c) && c != '_') {
			return TOKEN_PATTERN_MSG_FISRT_CHAR;
		}
		for (int i = 1; i < token.length(); i++) {
			c = token.charAt(i);
			if (!Character.isLetter(c) && !Character.isDigit(c) && c != '_') {
				return TOKEN_PATTERN_MSG_INVALID_CHAR;
			}
		}
		return null;
	}

	/**
	 * Valida um token de Field ($abc) e retorna uma String se houver problema.
	 * 
	 * @param token
	 *            O token a ser validado.
	 * @return <code>null</code> se o token for válido ou <code>String</code>
	 *         com a mensagem de erro se for inválido.
	 */
	public String validateFieldToken(String token) {
		if (token == null || token.trim().isEmpty()) {
			return TOKEN_PATTERN_MSG_NULL_OR_EMPTY;
		}
		if (token.charAt(0) != getFieldTag()) {
			return TOKEN_PATTERN_MSG_INVALID_BEGIN + getFieldTag();
		}
		return validateVarToken(token.substring(1));
	}

	/**
	 * Determina se o token é um nome de Field válido.
	 * 
	 * @param token
	 *            o token.
	 * @return
	 */
	public boolean isFieldToken(String token) {
		return validateFieldToken(token) == null;
	}

	/**
	 * etermina se um token é uma String. Uma String durante o parsing é sempre
	 * representada por $ seguido de um número, pois foi removido no primeiro
	 * passo do parser.
	 * 
	 * @param token
	 *            O token cru de uma expressão sendo parseada.
	 * @return
	 */
	public boolean isStringToken(String token) {
		if (token == null || token.length() < 2 || token.charAt(0) != Const.FIELD_TAG) {
			return false;
		}
		for (int i = 1; i < token.length(); i++) {
			if (!Character.isDigit(token.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determina se uma palavra representa um valor do tipo decimal. Se a
	 * palavra puder da origem a um BigDecimal então é um numérico válido.
	 * 
	 * @param token
	 *            A palavra presente na expressão.
	 * @return <code>true</code> se form um valor numérico válido ou
	 *         <code>false</code> se não for.
	 */
	public boolean isDecimalToken(String token) {
		if (token == null) {
			return false;
		}
		Character c;
		int dots=0;
		for (int i = 0; i < token.length(); i++) {
			c = token.charAt(i);
			if (c == Const.DECIMAL_SEPARATOR){
				dots++;
				if (dots > 1){
					return false;
				}
				continue;
			}
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determina se uma palavra representa um valor do tipo Integer. Se a
	 * palavra puder da origem a um Long então é um numérico válido.
	 * 
	 * @param token
	 *            A palavra presente na expressão.
	 * @return <code>true</code> se form um valor numérico válido ou
	 *         <code>false</code> se não for.
	 */
	public boolean isIntegerToken(String token) {
		if (token == null) {
			return false;
		}
		Character c;
		for (int i = 0; i < token.length(); i++) {
			c = token.charAt(i);
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determina se uma palavra representa um valor do tipo date.
	 * 
	 * @param token
	 *            A palavra presente na expressão.
	 * @return <code>true</code> se form um valor Date válido ou
	 *         <code>false</code> se não for.
	 */
	public boolean isDateToken(String token) {
		if (token == null || token.trim().isEmpty() || token.length() < 3) {
			return false;
		}
		// { e }
		return token.charAt(0) == Const.DATE_BLOCK[0] && token.charAt(token.length() - 1) == Const.DATE_BLOCK[1];
	}

	/**
	 * Tamanho máximo que um token (Const, Var, Field e ID) pode ter.
	 * 
	 * @return O tamanho máximo.
	 */
	protected int maxSize() {
		return Const.VAR_TOKEN_MAX_SIZE;
	}

	/**
	 * * Tamanho máximo que um token (Const, Var, Field e ID) pode ter.
	 * 
	 * @return O tamanho mínimo.
	 */
	protected int minSize() {
		return Const.VAR_TOKEN_MIN_SIZE;
	}

	/**
	 * Caracter de início do token que o identifica como campo (diferencia de
	 * Var)..
	 * 
	 * @return O caractere que vem antes dos nomes de campos no DataMap.
	 */
	public char getFieldTag() {
		return Const.FIELD_TAG;
	}

	public boolean isField(String token) {
		return token != null && token.startsWith("" + getFieldTag());
	}

	public char getStringTag() {
		return Const.STRING_TAG;
	}

}
