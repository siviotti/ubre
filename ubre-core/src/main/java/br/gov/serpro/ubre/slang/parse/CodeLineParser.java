package br.gov.serpro.ubre.slang.parse;

import br.gov.serpro.ubre.lang.Lang;
import br.gov.serpro.ubre.slang.Syntagma;
import br.gov.serpro.ubre.slang.code.CodeLine;
import br.gov.serpro.ubre.slang.keyword.Keyword;

/**
 * Parser genérico que faz o parsing de um tipo de linha específico.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @version 08/10/2015
 */
public abstract class CodeLineParser {

	protected static final String LINE_PARSER_UNKNOWN_KEYWORD = "A Keyword '%s' é desconhecida pelo parser '%s'";

	public abstract CodeLine parse(Lang lang, Syntagma framework, SourceLine sourceLine);

	protected String msgUnknownKeyword(Keyword keyword, Class<?> parserClass) {
		return String.format(LINE_PARSER_UNKNOWN_KEYWORD, keyword.getToken(), parserClass.getSimpleName());
	}

}
