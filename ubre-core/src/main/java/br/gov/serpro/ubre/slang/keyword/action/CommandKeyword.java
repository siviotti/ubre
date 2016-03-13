package br.gov.serpro.ubre.slang.keyword.action;

import br.gov.serpro.ubre.lang.statement.StatementType;
import br.gov.serpro.ubre.slang.keyword.TargetType;

/**
 * Keyword para comandos de início de linha.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 12/10/2015
 */
public class CommandKeyword extends ActionKeyword {

	public CommandKeyword(String token, TargetType targetType, StatementType parameterType) {
		super(token, targetType, parameterType);
	}

}
