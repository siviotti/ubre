package br.net.ubre.slang.parse.predicate;

import static br.net.ubre.lang.LangError.E10;
import static br.net.ubre.lang.LangError.E20;

import br.net.ubre.behave.BehaveMap;
import br.net.ubre.data.map.DataMap;
import br.net.ubre.lang.expression.Expression;
import br.net.ubre.slang.SLangException;
import br.net.ubre.slang.Slang;
import br.net.ubre.slang.keyword.Keyword;
import br.net.ubre.slang.keyword.TargetType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 12/10/2015
 */
public abstract class Predicate {

	private static final String PREDICATE_WRONG_TARGET_TYPE = "Tipo de alvo inválido: :%s não pode ser usado após %s. O alvo deve ser do tipo %s";
	private static final String PREDICATE_DATA_TARGET_NOT_FOUND = "Alvo do tipo 'Dado' não encontrado no DataMap:";
	private static final String PREDICATE_PROCEDURE_NOT_FOUND = "Procedure não encontrada:";
	private static final String PREDICATE_RULE_NOT_FOUND = "Regra não encontrada:";
	private static final String PREDICATE_EVENT_NOT_FOUND = "Evento (de processo) não encontrado:";
	protected static final String PREDICATE_EMPTY = "";

	public abstract String toSource();

	public abstract String getTartegToken();

	public abstract String getParameterSource();

	public abstract void validate(Keyword keyword, Slang slang);

	// ******************************
	// TARGET
	// ******************************

	protected void validateTarget(Keyword keyword, Slang slang,
			String targetToken) {
		if (keyword.getTargetType().isBehave()) {
			validateBehaveTarget(keyword, slang, targetToken);
		} else if (keyword.getTargetType().isData()) {
			validateDataTarget(keyword, slang, targetToken);
		}
	}

	protected void validateBehaveTarget(Keyword keyword, Slang slang,
			String targetToken) {
		BehaveMap behaveMap = slang.getSyntagma().getBehaveMap();
		if (keyword.getTargetType().equals(TargetType.PROCEDURE)) {
			if (!behaveMap.contains(targetToken)) {
				throw new SLangException(E10, PREDICATE_PROCEDURE_NOT_FOUND
						+ targetToken);
			}
		} else if (keyword.getTargetType().equals(TargetType.RULE)) {
			if (!behaveMap.contains(targetToken)) {
				throw new SLangException(E10, PREDICATE_RULE_NOT_FOUND
						+ targetToken);
			}
		} else if (keyword.getTargetType().equals(TargetType.EVENT)) {
			if (!behaveMap.contains(targetToken)) {
				throw new SLangException(E10, PREDICATE_EVENT_NOT_FOUND
						+ targetToken);
			}
		}
	}

	protected void validateDataTarget(Keyword keyword, Slang slang,
			String targetToken) {
		TargetType targetType = keyword.getTargetType();
		DataMap dataMap = slang.getLang().getDataMap();
		if (!dataMap.contains(targetToken)) {
			throw new SLangException(E10, PREDICATE_DATA_TARGET_NOT_FOUND
					+ targetToken);
		}

		switch (targetType) {
		case SIMPLE_VAR:
			validateSimpleVar(dataMap, keyword, targetToken);
		case FIELD:
			validateField(dataMap, keyword, targetToken);
		case VAR_OR_FIELD:
			validateVarOrField(dataMap, keyword, targetToken);
		}

	}

	private void validateVarOrField(DataMap dataMap, Keyword keyword,
			String targetToken) {
		if (!dataMap.isVariable(targetToken)) {
			String msg = String.format(PREDICATE_WRONG_TARGET_TYPE,
					targetToken, keyword.getToken(),
					TargetType.VAR_OR_FIELD.name());
			throw new SLangException(E20, msg);
		}
	}

	private void validateField(DataMap dataMap, Keyword keyword,
			String targetToken) {
		if (!dataMap.isField(targetToken)) {
			String msg = String.format(PREDICATE_WRONG_TARGET_TYPE,
					targetToken, keyword.getToken(), TargetType.FIELD.name());
			throw new SLangException(E20, msg);
		}
	}

	private void validateSimpleVar(DataMap dataMap, Keyword keyword,
			String targetToken) {
		if (!dataMap.isSimpleVar(targetToken)) {
			String msg = String.format(PREDICATE_WRONG_TARGET_TYPE,
					targetToken, keyword.getToken(),
					TargetType.SIMPLE_VAR.name());
			throw new SLangException(E20, msg);
		}
	}

	// ******************************
	// PARAMETER
	// ******************************

	protected void validateParameter(Keyword keyword, Slang slang,
			String parameterSource) {
		Expression expression = slang.getLang().compile(parameterSource);

	}
}
