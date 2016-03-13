package br.gov.serpro.ubre.lang.expression;

import java.util.HashMap;
import java.util.Map;

import br.gov.serpro.ubre.lang.compile.ExpCompiler;

/**
 * Cache de expressões compiladas.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/04/2015
 * 
 */
public class ExpressionCache {

	protected ExpCompiler compiler;

	private Map<String, Expression> expressions = new HashMap<String, Expression>();

	public ExpressionCache(ExpCompiler compiler) {
		super();
		this.compiler = compiler;
	}

	public Expression get(String source) {
		if (!expressions.containsKey(source)) {
			Expression expression = compiler.compile(source);
			expressions.put(source, expression);
			return expression;
		}
		return expressions.get(source);
	}

	/**
	 * @return the compiler
	 */
	public ExpCompiler getCompiler() {
		return compiler;
	}

	/**
	 * @return the expressions
	 */
	public Map<String, Expression> getCachedExpressions() {
		return expressions;
	}

}
