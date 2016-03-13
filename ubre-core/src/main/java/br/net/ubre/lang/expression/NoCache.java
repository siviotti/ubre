package br.net.ubre.lang.expression;

import br.net.ubre.lang.compile.ExpCompiler;

/**
 * Cache que não faz cache.Este cache sempre compila as expressões.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 20/05/2015
 * 
 */
public class NoCache extends ExpressionCache {

	public NoCache(ExpCompiler compiler) {
		super(compiler);
	}

	@Override
	public Expression get(String source) {
		return compiler.compile(source);
	}
	
	

}
