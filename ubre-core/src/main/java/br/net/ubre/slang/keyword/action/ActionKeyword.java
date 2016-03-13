package br.net.ubre.slang.keyword.action;

import br.net.ubre.lang.statement.StatementType;
import br.net.ubre.slang.keyword.Keyword;
import br.net.ubre.slang.keyword.TargetType;

/**
 * Keyword para linhas que de fato executam alguma ação (comandos e expressões).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 21/10/2015
 */

public class ActionKeyword extends Keyword {

	public ActionKeyword(String token, TargetType targetType, StatementType parameterType) {
		super(token, targetType, parameterType);
	}

	@Override
	public boolean isControl() {
		return false;
	}

	@Override
	public boolean isAction() {
		return true;
	}

}
