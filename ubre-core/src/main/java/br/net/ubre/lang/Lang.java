package br.net.ubre.lang;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.data.map.DataMap;
import br.net.ubre.lang.compile.ExpCompiler;
import br.net.ubre.lang.expression.Expression;
import br.net.ubre.lang.expression.ExpressionCache;
import br.net.ubre.lang.expression.NoCache;
import br.net.ubre.lang.parse.StringMap;

/**
 * Classe de fachada da linguagem de expressão do CTD.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 14/05/2015
 * 
 */
public class Lang {

	private Syntax syntax;
	private StringMap stringMap;
	private ExpCompiler compiler;
	private ExpressionCache cache;

	public Lang(Syntax syntax, DataMap dataMap, boolean useCache) {
		stringMap = new StringMap("" + dataMap.getVarPattern().getStringTag());
		compiler = new ExpCompiler(syntax, stringMap, dataMap);
		if (useCache) {
			cache = new ExpressionCache(compiler);
		} else {
			cache = new NoCache(compiler);
		}
		this.syntax = syntax;
	}

	// ********** API de Expression **********

	/**
	 * Obtém uma instância já cacheada de Expression ou compila e retorna caso
	 * ela não esteja no cache.
	 * 
	 * @param source
	 *            O fonte da expressão.
	 * @return A instância de Expression.
	 */
	public Expression getOrCompile(String source) {
		return cache.get(source);
	}

	/**
	 * Obtém uma instância de Expression através de uma compilação compulsória
	 * (nũnca usa cache).
	 * 
	 * @param source
	 *            O fonte da expressão.
	 * @return A instância de Expression.
	 */
	public Expression compile(String source) {
		return compiler.compile(source);
	}

	/**
	 * valia uma expressão e retorna o resultado.
	 * 
	 * @param container
	 *            O container de dados.
	 * @param source
	 *            O código da expressão.
	 * @return O resultado da expressão.
	 */
	public Object eval(DataContainer container, String source) {
		Expression expression = getOrCompile(source);
		return expression.eval(container);
	}

	// ********** GET / SET **********
	/**
	 * @return the syntax
	 */
	public Syntax getSyntax() {
		return syntax;
	}

	/**
	 * @return the stringMap
	 */
	public StringMap getStringMap() {
		return stringMap;
	}

	/**
	 * @return the compiler
	 */
	public ExpCompiler getCompiler() {
		return compiler;
	}

	/**
	 * @return the cache
	 */
	public ExpressionCache getCache() {
		return cache;
	}

	public DataMap getDataMap() {
		return compiler.getParser().getDataMap();
	}
}
