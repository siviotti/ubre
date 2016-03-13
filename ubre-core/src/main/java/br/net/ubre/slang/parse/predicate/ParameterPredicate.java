package br.net.ubre.slang.parse.predicate;

import br.net.ubre.framework.Context;
import br.net.ubre.slang.Slang;
import br.net.ubre.slang.Syntagma;
import br.net.ubre.slang.keyword.Keyword;

/**
 * Predicado que só tem o parâmetro.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 12/10/2015
 */
public class ParameterPredicate extends Predicate {
	
	private String parameterSource;

	public ParameterPredicate(Keyword keyword, String source) {
		super();
		this.parameterSource = source;
	}

	public String toSource() {
		return parameterSource;
	}

	@Override
	public void validate(Keyword keyword, Slang slang) {
		validateParameter(keyword, slang, parameterSource);
	}

	@Override
	public String getTartegToken() {
		return PREDICATE_EMPTY;
	}

	@Override
	public String getParameterSource() {
		return parameterSource;
	}

}
