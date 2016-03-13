package br.net.ubre.slang.parse.predicate;

import br.net.ubre.slang.Slang;
import br.net.ubre.slang.keyword.Keyword;

/**
 * Predicado que só tem o alvo.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 12/10/2015
 */
public class TargetPredicate extends Predicate {

	private String targetToken;

	public TargetPredicate(String source) {
		super();
		this.targetToken = source;
	}

	public String toSource() {
		return targetToken;
	}

	public void validate(Keyword keyword, Slang slang) {
		validateTarget(keyword, slang, targetToken);
	}

	@Override
	public String getTartegToken() {
		return targetToken;
	}

	@Override
	public String getParameterSource() {
		return PREDICATE_EMPTY;
	}

}
