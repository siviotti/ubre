package br.gov.serpro.ubre.slang.parse;

import br.gov.serpro.ubre.lang.Lang;
import br.gov.serpro.ubre.lang.LangError;
import br.gov.serpro.ubre.lang.expression.Expression;
import br.gov.serpro.ubre.slang.SLangException;
import br.gov.serpro.ubre.slang.Syntagma;
import br.gov.serpro.ubre.slang.code.CodeLine;
import br.gov.serpro.ubre.slang.code.EndLine;
import br.gov.serpro.ubre.slang.code.ifelse.ElseIfLine;
import br.gov.serpro.ubre.slang.code.ifelse.ElseLine;
import br.gov.serpro.ubre.slang.code.ifelse.IfLine;
import br.gov.serpro.ubre.slang.code.loop.WhileLine;
import br.gov.serpro.ubre.slang.code.map.CaseLine;
import br.gov.serpro.ubre.slang.code.map.DefaultLine;
import br.gov.serpro.ubre.slang.code.map.SwitchLine;
import br.gov.serpro.ubre.slang.condition.Condition;
import br.gov.serpro.ubre.slang.condition.ConditionFactory;
import br.gov.serpro.ubre.slang.keyword.EndKeyword;
import br.gov.serpro.ubre.slang.keyword.Keyword;
import br.gov.serpro.ubre.slang.keyword.ifelse.ElseIfKeyword;
import br.gov.serpro.ubre.slang.keyword.ifelse.ElseKeyword;
import br.gov.serpro.ubre.slang.keyword.ifelse.IfKeyword;
import br.gov.serpro.ubre.slang.keyword.loop.WhileKeyword;
import br.gov.serpro.ubre.slang.keyword.map.CaseKeyword;
import br.gov.serpro.ubre.slang.keyword.map.DefaultKeyword;
import br.gov.serpro.ubre.slang.keyword.map.SwitchKeyword;

/**
 * Parser para as linhas de Controle.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 14/10/2015
 */
public class ControlLineParser extends CodeLineParser {

	private ConditionFactory factory = new ConditionFactory();

	@Override
	public CodeLine parse(Lang lang, Syntagma syntagma, SourceLine sourceLine) {
		Keyword keyword = sourceLine.getKeyword();
		if (keyword instanceof IfKeyword) {
			Condition condition = factory.create(getPar(sourceLine), lang);
			return new IfLine(sourceLine, condition);
		}
		if (keyword instanceof ElseIfKeyword) {
			Condition condition = factory.create(getPar(sourceLine), lang);
			return new ElseIfLine(sourceLine, condition);
		}
		if (keyword instanceof EndKeyword) {
			return new EndLine(sourceLine);
		}
		if (keyword instanceof ElseKeyword) {
			return new ElseLine(sourceLine);
		}
		if (keyword instanceof SwitchKeyword){
			String target = getTarget(sourceLine);
			return new SwitchLine(sourceLine, target);
		}
		if (keyword instanceof CaseKeyword){
			Expression expression = lang.compile(getPar(sourceLine));
			return new CaseLine(sourceLine, expression);
		}
		if (keyword instanceof DefaultKeyword){
			return new DefaultLine(sourceLine);
		}
		if (keyword instanceof WhileKeyword){
			Condition condition = factory.create(getPar(sourceLine), lang);
			return new WhileLine(sourceLine, condition);
		}
		throw new SLangException(LangError.E10, "Keyword desconhecida:"+keyword.getToken());
	}


	private String getTarget(SourceLine sourceLine) {
		return sourceLine.getPredicate().getTartegToken();
	}


	private String getPar(SourceLine sourceLine) {
		return sourceLine.getPredicate().getParameterSource();
	}

}
