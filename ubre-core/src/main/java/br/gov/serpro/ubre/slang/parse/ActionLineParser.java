package br.gov.serpro.ubre.slang.parse;

import br.gov.serpro.ubre.lang.Lang;
import br.gov.serpro.ubre.lang.LangError;
import br.gov.serpro.ubre.lang.expression.Expression;
import br.gov.serpro.ubre.slang.SLangException;
import br.gov.serpro.ubre.slang.Syntagma;
import br.gov.serpro.ubre.slang.code.action.Action;
import br.gov.serpro.ubre.slang.code.action.ActionLine;
import br.gov.serpro.ubre.slang.code.action.ExpressionAction;
import br.gov.serpro.ubre.slang.code.action.ParameterCommandAction;
import br.gov.serpro.ubre.slang.code.action.SimpleCommandAction;
import br.gov.serpro.ubre.slang.command.Command;
import br.gov.serpro.ubre.slang.keyword.Keyword;
import br.gov.serpro.ubre.slang.keyword.action.CommandKeyword;
import br.gov.serpro.ubre.slang.keyword.action.ExppressionKeyword;

/**
 * Parser para ActionLine.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @version 08/10/2015
 * @see ActionLine
 */
public class ActionLineParser extends CodeLineParser {

	@Override
	public ActionLine parse(Lang lang, Syntagma framework, SourceLine sourceLine) {
		Action action = null;
		Keyword keyword = sourceLine.getKeyword();
		if (keyword instanceof ExppressionKeyword) {
			action = createExpessionAction(sourceLine, lang);
		} else if (keyword instanceof CommandKeyword) {
			action = createCommandAction(sourceLine, lang, framework);
		} else {
			throw new SLangException(LangError.E10, LINE_PARSER_UNKNOWN_KEYWORD
					+ keyword);
		}
		return new ActionLine(sourceLine, action);
	}

	private Action createCommandAction(SourceLine sourceLine, Lang lang,
			Syntagma syntagma) {
		Command command = syntagma.getCommand(sourceLine.getKeyword()
				.getToken());
		String targetToken = sourceLine.getPredicate().getTartegToken();
		if (sourceLine.getKeyword().hasParameter()) {
			String source = sourceLine.getPredicate().getParameterSource();
			Expression expression;
			if (source == null || source.trim().isEmpty()) {
				expression = lang.compile("null");
			} else {
				expression = lang.compile(source);
			}
			return new ParameterCommandAction(command, targetToken, expression);
		} else {
			return new SimpleCommandAction(command, targetToken);
		}
	}

	private Action createExpessionAction(SourceLine sourceLine, Lang lang) {
		String source = sourceLine.getPredicate().getParameterSource();
		Expression expression = lang.compile(source);
		return new ExpressionAction(expression);
	}

}
