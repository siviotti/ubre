package br.net.ubre.internal;

/**
 * Constantes String passíveis de tradução e mensagens.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 29/10/2015
 */
public class Str {

	// ******************************
	// LITERAL
	// ******************************

	public static final String TRUE_LITERAL = "true";
	public static final String FALSE_LITERAL = "false";
	public static final String NULL_LITERAL = "null";
	public static final String INTEGER_ZERO_LITERAL = "0";
	public static final String DECIMAL_ZERO_LITERAL = "0.0";
	public static final String STRING_EMPTY_LITERAL = "''";
	
	// ******************************
	// FUNCTION (precedence 1)
	// ******************************

	// Convertion
	public static final String STR_FUNCTION = "str";
	public static final String UPPER_FUNCTION = "upper";
	public static final String LOWER_FUNCTION = "lower";
	public static final String SIZE_FUNCTION = "size";
	public static final String INT_FUNCTION = "int";
	public static final String DEC_FUNCTION = "dec";
	public static final String BOOL_FUNCTION = "bool";
	// Date
	public static final String AGE_FUNCTION = "age";
	public static final String DAY_FUNCTION = "day";
	
	
	// ******************************
	// PROPERTIES
	// ******************************
	
	// Field
	public static final String REQUIRED_PROPERTY = "required";
	


	// ******************************
	// COMMANDS
	// ******************************

	public static final String REQUIRE_COMMAND = "require";
	public static final String UNREQUIRE_COMMAND = "unrequire";
	

	// ******************************
	// LANG_ERROR
	// ******************************

	public static final String LANG_ERROR_10_PARSING = "Erro de Parsing";
	public static final String LANG_ERROR_20_SYNTAX = "Erro de Sintaxe";
	public static final String LANG_ERROR_30_SEMANTIC = "Erro de Semântica";
	public static final String LANG_ERROR_31_TYPES = "Tipo Incompatível";
	public static final String LANG_ERROR_40_RUNTIME = "Erro de Execução";
	public static final String LANG_ERROR_41_INDEX = "Índice Fora da Faixa";
	public static final String LANG_ERROR_42_NULL = "Referência Nula";


}
