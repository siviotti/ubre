package br.gov.serpro.ubre.slang.condition;

import br.gov.serpro.ubre.lang.Lang;
import br.gov.serpro.ubre.lang.LangError;
import br.gov.serpro.ubre.lang.expression.BooleanExpression;
import br.gov.serpro.ubre.lang.expression.Expression;
import br.gov.serpro.ubre.slang.SLangException;

/**
 * Fábrica de condições.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class ConditionFactory {

	private static final String NOT_BOOLEAN_CONDITION = "A condição não é booleana:";

	/**
	 * Cria uma condição a partir
	 * 
	 * @param source
	 *            O valor do parâmetro.
	 * @param lang
	 *            A instância de Lang que vai fazer a possíbel compilação do
	 *            source em uma expressão.
	 * @return A instância de <code>Condition</code>
	 */
	public Condition create(String source, Lang lang) {
		if (source == null || source.trim().length() == 0) {
			return new TrueCondition();
		}
		Expression expression = lang.getOrCompile(source);
		if (!(expression instanceof BooleanExpression)) {
			throw new SLangException(LangError.E10, NOT_BOOLEAN_CONDITION
					+ source);
		}

		return new ExpressionCondition((BooleanExpression) expression);
	}
}
