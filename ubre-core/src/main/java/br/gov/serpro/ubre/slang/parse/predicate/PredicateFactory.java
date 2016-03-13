package br.gov.serpro.ubre.slang.parse.predicate;

import br.gov.serpro.ubre.slang.keyword.Keyword;

/**
 * Fábrica de predicados em função da keyword.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 12/10/2015
 */
public class PredicateFactory {

	public Predicate create(Keyword keyword, String source, char sep) {
		if (source == null || source.isEmpty()) {
			return new EmptyPredicate();
		}
		source = source.trim();
		if (keyword.hasTarget() && !keyword.hasParameter()) {
			return new TargetPredicate(source);
		} else if (!keyword.hasTarget() && keyword.hasParameter()) {
			return new ParameterPredicate(keyword, source);
		}
		return new ComplexPredicate(keyword, source, sep);

	}
}
