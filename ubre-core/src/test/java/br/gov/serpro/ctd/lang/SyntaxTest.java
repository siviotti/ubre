package br.gov.serpro.ctd.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.gov.serpro.ubre.lang.Syntax;
import br.gov.serpro.ubre.lang.data.literal.FalseStatement;
import br.gov.serpro.ubre.lang.data.literal.NullStatement;
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

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 16/04/2015
 * 
 */
public class SyntaxTest {

	@Test
	public void testBasic() {
		Syntax syntax = new Syntax();
		// freeze
		assertTrue(!syntax.isFrozen());
		syntax.freeze();
		assertTrue(syntax.isFrozen());
	}

	@Test
	public void testKeyWords() {
		// *** Literals ***
		assertEquals(keyword("true"), TrueStatement.class);
		assertEquals(keyword("false"), FalseStatement.class);
		assertEquals(keyword("null"), NullStatement.class);
		// *** Operators ***
		// Precedence 1
		assertEquals(keyword("("), OpenParenthesisOperator.class);
		assertEquals(keyword(")"), CloseParenthesisOperator.class);
		// assertEquals(keyword("."), DotOperator.class);
		assertEquals(keyword("["), OpenBracketOperator.class);
		assertEquals(keyword("]"), CloseBracketOperator.class);
		// Precedence 2
		assertEquals(keyword("!"), NotOperator.class);
		// Precedence 3
		assertEquals(keyword("**"), PowOperator.class);
		// Precedence 4
		assertEquals(keyword("*"), MultiplyOperator.class);
		assertEquals(keyword("/"), DivideOperator.class);
		assertEquals(keyword("%"), ModOperator.class);
		// Precedence 5
		assertEquals(keyword("+"), PlusOperator.class);
		assertEquals(keyword("-"), DashOperator.class);
		// Precedence 7
		assertEquals(keyword(">"), GreaterOperator.class);
		assertEquals(keyword("<"), LessOperator.class);
		assertEquals(keyword(">="), GreaterEqualOperator.class);
		assertEquals(keyword("<="), LessEqualOperator.class);
		assertEquals(keyword("><"), BetweenOperator.class);
		assertEquals(keyword("<>"), NotBetweenOperator.class);
		// Precedence 8
		assertEquals(keyword("=="), EqualsOperator.class);
		assertEquals(keyword("!="), NotEqualsOperator.class);
		assertEquals(keyword("@"), InOperator.class);
		assertEquals(keyword("!@"), NOtInOperator.class);
		// Precedence 12
		assertEquals(keyword("&&"), AndOperator.class);
		// Precedence 13
		assertEquals(keyword("||"), OrOperator.class);
		// Precedence 14
		assertEquals(keyword("?"), ConditionalOperator.class);
		// Precedence 15
		assertEquals(keyword("="), AssigmentOperator.class);
		// Precedence 16
		assertEquals(keyword(","), CommaOperator.class);
		assertEquals(keyword(":"), ColonOperator.class);
		// *** Functions ***

	}

	private Object keyword(String token) {
		return new Syntax().getKeywordClass(token);
	}

	

	@Test
	public void test() {
		System.out.println(Character.isDigit('-'));
	}

}
