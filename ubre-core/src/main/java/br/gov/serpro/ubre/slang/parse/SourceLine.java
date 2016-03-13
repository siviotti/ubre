package br.gov.serpro.ubre.slang.parse;

import br.gov.serpro.ubre.internal.Const;
import br.gov.serpro.ubre.io.txt.Text;
import br.gov.serpro.ubre.slang.Slang;
import br.gov.serpro.ubre.slang.Syntagma;
import br.gov.serpro.ubre.slang.keyword.Keyword;
import br.gov.serpro.ubre.slang.parse.predicate.Predicate;
import br.gov.serpro.ubre.slang.parse.predicate.PredicateFactory;

/**
 * Representa uma linha de um código fonte que contém um CTDS (CTD Script).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @since 09/10/2015 00:00
 */
public class SourceLine {

	public static final char SEP = Const.SPLIT_CHAR;
	private int number;
	private Keyword keyword;
	private Predicate predicate;

	public SourceLine(Syntagma syntagma, int number, String originalSource) {
		this.number = number;
		String source = extractMetaline(originalSource.trim());
		String predicateSource="";
		if (syntagma.getKeywordSet().contains(source)) {
			keyword = syntagma.getKeyword(source);
		} else {
			String keywordToken = Text.bc(source, SEP);
			keyword = syntagma.getKeyword(keywordToken);
			predicateSource = Text.ac(source, SEP);
		}
		//System.out.println("keyword:" + keyword.getToken());
		// Se primeiro token não é uma keyword explícita
		if (keyword == null) {
			// keyword será a instância de ExpressionKeyword
			keyword = syntagma.getExpressionKeyword();
			// predicate será todo o source (expressão)
			predicateSource = source;
		}
		predicate = new PredicateFactory().create(keyword, predicateSource, SEP);
	}
	
	private String extractMetaline(String originalSource){
		
		return originalSource;
	}

	public String toSource() {
		StringBuilder sb = new StringBuilder();
		sb.append(keyword.getToken());
		sb.append(SEP);
		sb.append(predicate.toSource());
		return sb.toString();
	}

	/**
	 * Valida a linha em função dos elementos internos (target e parameter) uma
	 * vez que a linha já teve sua estrutura validada no construtor.
	 * 
	 * @param context
	 *            O contexto lido para que sejam feitas velidações de regras,
	 *            processos e outros elementos.
	 */
	public void validate(Slang slang) {
		predicate.validate(keyword, slang);
	}

	// GET / SET

	public int getNumber() {
		return number;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public Predicate getPredicate() {
		return predicate;
	}

	@Override
	public String toString() {
		return toSource();
	}
}