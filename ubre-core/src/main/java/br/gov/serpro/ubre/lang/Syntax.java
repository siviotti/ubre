package br.gov.serpro.ubre.lang;

import static br.gov.serpro.ubre.lang.LangError.E20;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.gov.serpro.ubre.exception.FreezeException;
import br.gov.serpro.ubre.internal.Const;
import br.gov.serpro.ubre.internal.Str;
import br.gov.serpro.ubre.lang.data.literal.DecimalZeroStatement;
import br.gov.serpro.ubre.lang.data.literal.FalseStatement;
import br.gov.serpro.ubre.lang.data.literal.IntegerZeroStatement;
import br.gov.serpro.ubre.lang.data.literal.NullStatement;
import br.gov.serpro.ubre.lang.data.literal.StringEmptyStatement;
import br.gov.serpro.ubre.lang.data.literal.TrueStatement;
import br.gov.serpro.ubre.lang.keyword.binary.arithmetic.DivideOperator;
import br.gov.serpro.ubre.lang.keyword.binary.arithmetic.ModOperator;
import br.gov.serpro.ubre.lang.keyword.binary.arithmetic.MultiplyOperator;
import br.gov.serpro.ubre.lang.keyword.binary.arithmetic.PowOperator;
import br.gov.serpro.ubre.lang.keyword.binary.assigment.AssigmentOperator;
import br.gov.serpro.ubre.lang.keyword.binary.compare.BetweenOperator;
import br.gov.serpro.ubre.lang.keyword.binary.compare.GreaterEqualOperator;
import br.gov.serpro.ubre.lang.keyword.binary.compare.GreaterOperator;
import br.gov.serpro.ubre.lang.keyword.binary.compare.LessEqualOperator;
import br.gov.serpro.ubre.lang.keyword.binary.compare.LessOperator;
import br.gov.serpro.ubre.lang.keyword.binary.compare.NotBetweenOperator;
import br.gov.serpro.ubre.lang.keyword.binary.dash.DashOperator;
import br.gov.serpro.ubre.lang.keyword.binary.dot.DotOperator;
import br.gov.serpro.ubre.lang.keyword.binary.dot.Property;
import br.gov.serpro.ubre.lang.keyword.binary.dot.date.DayOperation;
import br.gov.serpro.ubre.lang.keyword.binary.list.ColonOperator;
import br.gov.serpro.ubre.lang.keyword.binary.list.CommaOperator;
import br.gov.serpro.ubre.lang.keyword.binary.logical.AndOperator;
import br.gov.serpro.ubre.lang.keyword.binary.logical.OrOperator;
import br.gov.serpro.ubre.lang.keyword.binary.plus.PlusOperator;
import br.gov.serpro.ubre.lang.keyword.binary.relational.EqualsOperator;
import br.gov.serpro.ubre.lang.keyword.binary.relational.InOperator;
import br.gov.serpro.ubre.lang.keyword.binary.relational.NOtInOperator;
import br.gov.serpro.ubre.lang.keyword.binary.relational.NotEqualsOperator;
import br.gov.serpro.ubre.lang.keyword.bracket.CloseBracketOperator;
import br.gov.serpro.ubre.lang.keyword.bracket.OpenBracketOperator;
import br.gov.serpro.ubre.lang.keyword.conditional.ConditionalOperator;
import br.gov.serpro.ubre.lang.keyword.parenthesis.CloseParenthesisOperator;
import br.gov.serpro.ubre.lang.keyword.parenthesis.OpenParenthesisOperator;
import br.gov.serpro.ubre.lang.keyword.unary.NotOperator;
import br.gov.serpro.ubre.lang.keyword.unary.function.BoolFunction;
import br.gov.serpro.ubre.lang.keyword.unary.function.DecFunction;
import br.gov.serpro.ubre.lang.keyword.unary.function.IntFunction;
import br.gov.serpro.ubre.lang.keyword.unary.function.LowerFunction;
import br.gov.serpro.ubre.lang.keyword.unary.function.StrFunction;
import br.gov.serpro.ubre.lang.keyword.unary.function.UpperFunction;
import br.gov.serpro.ubre.lang.keyword.unary.function.date.AgeFunction;
import br.gov.serpro.ubre.lang.keyword.unary.function.date.DayFunction;
import br.gov.serpro.ubre.lang.keyword.unary.function.size.SizeFunction;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.util.GenericFreezable;

/**
 * Classe que define a sintaxe da linguagem de expressões embarcada a partir de
 * constantes e métodos de padroniozação.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class Syntax extends GenericFreezable {

	private static final String SYNTAX_LITERAL_NOT_FOUND = "Literal não encontrado:";

	private Map<Character, Character> blocks = new HashMap<Character, Character>();
	private Map<String, Class<? extends Statement>> keywords = new HashMap<String, Class<? extends Statement>>();
	private Set<String> externalKeywords = new HashSet<String>();
	private Set<String> operators = new HashSet<String>();
	private Map<String, Statement> literals = new HashMap<String, Statement>();
	private Set<String> functions = new HashSet<String>();

	private boolean frozen = false;

	public Syntax() {
		// Literals
		putLiteral(Str.TRUE_LITERAL, TrueStatement.INSTANCE);
		putLiteral(Str.FALSE_LITERAL, FalseStatement.INSTANCE);
		putLiteral(Str.NULL_LITERAL, NullStatement.INSTANCE);
		putLiteral(Str.INTEGER_ZERO_LITERAL, IntegerZeroStatement.INSTANCE);
		putLiteral(Str.DECIMAL_ZERO_LITERAL, DecimalZeroStatement.INSTANCE);
		putLiteral(Str.STRING_EMPTY_LITERAL, StringEmptyStatement.INSTANCE);
		// Field Prop
		putLiteral(Property.REQUIRED.token(), Property.REQUIRED.statement());

		// Operators
		// Precedence 0
		putOperator(Const.DOT_OPERATOR, DotOperator.class);
		// Precedence 1
		putOperator(Const.OPEN_BRACKET_OPERATOR, OpenBracketOperator.class);
		putOperator(Const.CLOSE_BRACKET_OPERATOR, CloseBracketOperator.class);
		// Precedence 2
		putOperator(Const.NOT_OPERATOR, NotOperator.class);
		// Precedence 3
		putOperator(Const.POW_OPERATOR, PowOperator.class);
		// Precedence 4
		putOperator(Const.MULTIPLY_OPERATOR, MultiplyOperator.class);
		putOperator(Const.DIVIDE_OPERATOR, DivideOperator.class);
		putOperator(Const.MOD_OPERATOR, ModOperator.class);
		// Precedence 5
		putOperator(Const.PLUS_OPERATOR, PlusOperator.class);
		putOperator(Const.DASH_OPERATOR, DashOperator.class);
		// Precedence 7
		putOperator(Const.GREATER_OPERATOR, GreaterOperator.class);
		putOperator(Const.LESS_OPERATOR, LessOperator.class);
		putOperator(Const.GREATER_EQUAL_OPERATOR, GreaterEqualOperator.class);
		putOperator(Const.LESS_EQUAL_OPERATOR, LessEqualOperator.class);
		putOperator(Const.BETWEEN_OPERATOR, BetweenOperator.class);
		putOperator(Const.NOT_BETWEEN_OPERATOR, NotBetweenOperator.class);
		// Precedence 8
		putOperator(Const.EQUAL_OPERATOR, EqualsOperator.class);
		putOperator(Const.NOT_EQUAL_OPERATOR, NotEqualsOperator.class);
		putOperator(Const.IN_OPERATOR, InOperator.class);
		putOperator(Const.NOT_IN_OPERATOR, NOtInOperator.class);
		// Precedence 12
		putOperator(Const.AND_OPERATOR, AndOperator.class);
		// Precedence 13
		putOperator(Const.OR_OPERATOR, OrOperator.class);
		// Precedence 14
		putOperator(Const.CONDITIONAL_OPERATOR, ConditionalOperator.class);
		// Precedence 15
		putOperator(Const.ASSIGNMENT_OPERATOR, AssigmentOperator.class);
		// Precedence 16
		putOperator(Const.COMMA_OPERATOR, CommaOperator.class);
		putOperator(Const.COLON_OPERATOR, ColonOperator.class);
		// Precedence 17
		putOperator(Const.OPEN_PARENTHESIS_OPERATOR,
				OpenParenthesisOperator.class);
		putOperator(Const.CLOSE_PARENTHESIS_OPERATOR,
				CloseParenthesisOperator.class);

		// Functions (Precedence 1)
		putFunction(Str.STR_FUNCTION, StrFunction.class);
		putFunction(Str.UPPER_FUNCTION, UpperFunction.class);
		putFunction(Str.LOWER_FUNCTION, LowerFunction.class);
		putFunction(Str.SIZE_FUNCTION, SizeFunction.class);
		putFunction(Str.INT_FUNCTION, IntFunction.class);
		putFunction(Str.DEC_FUNCTION, DecFunction.class);
		putFunction(Str.BOOL_FUNCTION, BoolFunction.class);
		// Date Functions
		putFunction(Str.AGE_FUNCTION, AgeFunction.class);
		putFunction(Str.DAY_FUNCTION, DayFunction.class);
		

	}

	// Métodos de construção da Sintaxe (visíveis para subclasses)

	protected final void putLiteral(String token, Statement statement) {
		testFrozen();
		putKeyword(token, statement.getClass());
		literals.put(token, statement);
	}

	protected final void putFunction(String token,
			Class<? extends Statement> keyword) {
		testFrozen();
		putKeyword(token, keyword);
		functions.add(token);
	}

	protected final void putKeyword(String token,
			Class<? extends Statement> keyword) {
		testFrozen();
		keywords.put(token, keyword);
	}

	protected final void putOperator(String token,
			Class<? extends Statement> keyword) {
		if (frozen) {
			throw new FreezeException(getClass());
		}
		if (operators.contains(token)) {
			throw new LangException(E20, "Operador já existe na sintaxe:"
					+ token);
		}
		putKeyword(token, keyword);
		operators.add(token);
	}

	// ********** API **********

	public Class<? extends Statement> getKeywordClass(String token) {
		return keywords.get(token);
	}

	/**
	 * @return the blocks
	 */
	public Map<Character, Character> getBlocks() {
		return new HashMap<Character, Character>(blocks);
	}

	public boolean isKeyword(String token) {
		return keywords.containsKey(token);
	}

	/**
	 * @return the operators
	 */
	public Set<String> getOperators() {
		return new HashSet<String>(operators);
	}

	public boolean isLiteralKeyword(String token) {
		return literals.containsKey(token);
	}

	public Statement getLiteralInstance(String token) {
		if (!isLiteralKeyword(token)) {
			throw new LangException(E20, SYNTAX_LITERAL_NOT_FOUND + token);
		}
		return literals.get(token);
	}

	// ********** GET / SET **********

	public Set<String> getExternalKeywords() {
		return externalKeywords;
	}

	public void setExternalKeywords(Set<String> externalKeywords) {
		testFrozen();
		this.externalKeywords = externalKeywords;
	}

}