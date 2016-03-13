package br.net.ubre.slang.code.map;

import br.net.ubre.lang.expression.Expression;
import br.net.ubre.slang.parse.SourceLine;

/**
 * Representa um caso de um Switch e é composto por uma chave (usada pelo
 * Switch)
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 */
public class CaseLine extends KeyBlock {

	public CaseLine(SourceLine sourceLine, Expression expression) {
		super(sourceLine, eval(expression));
	}

	@Override
	public boolean isDefault() {
		return false;
	}


}
