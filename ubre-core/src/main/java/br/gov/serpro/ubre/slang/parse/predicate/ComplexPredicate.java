package br.gov.serpro.ubre.slang.parse.predicate;

import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.io.txt.Text;
import br.gov.serpro.ubre.slang.Slang;
import br.gov.serpro.ubre.slang.Syntagma;
import br.gov.serpro.ubre.slang.keyword.Keyword;

/**
 * Predicado com target e parâmetro e outros possíveis formatos.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 12 de out de 2015
 */
public class ComplexPredicate extends Predicate {

	private String targetToken;
	private String parameterSource;
	private char sep;

	public ComplexPredicate(Keyword keyword, String source, char sep) {
		super();
		this.sep = sep;
		targetToken = Text.bc(source, sep);
		parameterSource = Text.ac(source, sep);
	}

	public String toSource() {
		return targetToken + sep + parameterSource;
	}

	@Override
	public void validate(Keyword keyword, Slang slang) {
		validateTarget(keyword, slang, targetToken);
		validateParameter(keyword, slang, parameterSource);
				
	}

	@Override
	public String getTartegToken() {
		return targetToken;
	}

	@Override
	public String getParameterSource() {
		return parameterSource;
	}

}
