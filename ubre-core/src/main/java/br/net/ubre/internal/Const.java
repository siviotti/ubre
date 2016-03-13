package br.net.ubre.internal;

import java.math.BigDecimal;

/**
 * Classe que centraliza as constantes internas do Framework e das linguagens de
 * Expressão e Script. Esta classe não contém mensagens nem constantes que
 * possam ter que ser traduzidas! Este tipo de constantes está em
 * <code>Str</code>. Aqui ficam constantes de configuração e comportamento do
 * framework.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 26/10/2015
 */
public class Const {

	// ******************************
	// SPLIT
	// ******************************

	// Caracteres especiais das linguagens para separação de tokens
	public static final char SPLIT_CHAR = ' ';
	public static final char STRING_CHAR1 = '"';
	public static final char STRING_CHAR2 = '\'';
	public static final char OPEN_PARENTHESIS_CHAR = '(';
	public static final char CLOSE_PARENTHESIS_CHAR = ')';
	public static final char OPEN_BRACKET_CHAR = '[';
	public static final char CLOSE_BRACKET_CHAR = ']';
	public static final char OPEN_BRACE_CHAR = '{';
	public static final char CLOSE_BRACE_CHAR = '}';
	public static final char PIPE_CHAR = '|';
	public static final char AT_CHAR = '@';
	public static final char SEMICOLON_CHAR = ';';
	public static final String COMMENT_TAG = "//";
	public static final char DECIMAL_SEPARATOR = '.';

	// Dates {12/12/2000} || {12/12/2000 12:12:12}
	public static final char[] DATE_BLOCK = { OPEN_BRACE_CHAR, CLOSE_BRACE_CHAR };

	// ******************************
	// OPERATOR
	// ******************************

	// Precedence 0
	public static final String DOT_OPERATOR = ".";
	// Precedence 1
	public static final String OPEN_BRACKET_OPERATOR = "" + OPEN_BRACKET_CHAR;
	public static final String CLOSE_BRACKET_OPERATOR = "" + CLOSE_BRACKET_CHAR;
	// Precedence 2
	public static final String NOT_OPERATOR = "!";
	// Precedence 3
	public static final String POW_OPERATOR = "**";
	// Precedence 4
	public static final String MULTIPLY_OPERATOR = "*";
	public static final String DIVIDE_OPERATOR = "/";
	public static final String MOD_OPERATOR = "%";
	// Precedence 5
	public static final String PLUS_OPERATOR = "+"; // Positive Precedence = 2
	public static final String DASH_OPERATOR = "-"; // Negate Precedence = 2
	// Precedence 7
	public static final String GREATER_OPERATOR = ">";
	public static final String LESS_OPERATOR = "<";
	public static final String GREATER_EQUAL_OPERATOR = ">=";
	public static final String LESS_EQUAL_OPERATOR = "<=";
	public static final String BETWEEN_OPERATOR = "><";
	public static final String NOT_BETWEEN_OPERATOR = "<>";
	// Precedence 8
	public static final String EQUAL_OPERATOR = "==";
	public static final String NOT_EQUAL_OPERATOR = "!=";
	public static final String IN_OPERATOR = "@";
	public static final String NOT_IN_OPERATOR = "!@";
	// Precedence 12
	public static final String AND_OPERATOR = "&&";
	// Precedence 13
	public static final String OR_OPERATOR = "||";
	// Precedence 14
	public static final String CONDITIONAL_OPERATOR = "?";
	// Precedence 15
	public static final String ASSIGNMENT_OPERATOR = "=";
	// Precedence 16
	public static final String COMMA_OPERATOR = ",";
	public static final String COLON_OPERATOR = ":";
	// Precedence 17
	public static final String OPEN_PARENTHESIS_OPERATOR = ""
			+ OPEN_PARENTHESIS_CHAR;
	public static final String CLOSE_PARENTHESIS_OPERATOR = ""
			+ CLOSE_PARENTHESIS_CHAR;

	// ******************************
	// VAR
	// ******************************

	public static final char FIELD_TAG = '$';
	public static final char STRING_TAG = '$';
	public static final int VAR_TOKEN_MAX_SIZE = 50;
	public static final int VAR_TOKEN_MIN_SIZE = 1;

	// ******************************
	// DEFAULT
	// ******************************

	public static final Integer INTEGER_DEFAULT_RESULT = new Integer(0);
	public static final BigDecimal DECIMAL_DEFAULT_RESULT = BigDecimal.ZERO;
	// public static final Datex DATEX_DEFAULT_RESULT = new Integer(0);
	public static final Boolean BOOLEAN_DEFAULT_RESULT = Boolean.FALSE;
	public static final String STRING_DEFAULT_RESULT = "";

}
