package br.gov.serpro.ubre.slang.parse.predicate;

import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.slang.Slang;
import br.gov.serpro.ubre.slang.Syntagma;
import br.gov.serpro.ubre.slang.keyword.Keyword;

/**
 * Predicado para comandos sem nenhum parâmetro ou alvo.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 12/10/2015
 */
public class EmptyPredicate extends Predicate {

	public EmptyPredicate() {
		super();
	}

	public String toSource() {
		return "";
	}

	@Override
	public void validate(Keyword keyword, Slang slang) {
		// nada
	}

	@Override
	public String getTartegToken() {
		return PREDICATE_EMPTY;
	}

	@Override
	public String getParameterSource() {
		return PREDICATE_EMPTY;
	}

}
