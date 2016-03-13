package br.net.ubre.slang.keyword;

import br.net.ubre.lang.statement.StatementType;

/**
 * Keyword para as palavras de início de linha de controle.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 12 de out de 2015
 */
public abstract class ControlKeyword extends Keyword {

	public ControlKeyword(String token, TargetType targetType, StatementType parameterType) {
		super(token, targetType, parameterType);
	}

	@Override
	public boolean isControl() {
		return true;
	}
	
	@Override
	public boolean isAction() {
		return false;
	}


}
