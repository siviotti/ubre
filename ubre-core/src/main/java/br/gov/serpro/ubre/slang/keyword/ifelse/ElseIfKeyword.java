package br.gov.serpro.ubre.slang.keyword.ifelse;

import br.gov.serpro.ubre.lang.statement.StatementType;
import br.gov.serpro.ubre.slang.keyword.ControlKeyword;
import br.gov.serpro.ubre.slang.keyword.TargetType;

/**
 * Keyword para elseif.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 13/10/2015
 */
public class ElseIfKeyword extends ControlKeyword {

	public ElseIfKeyword() {
		super("elseif", TargetType.NONE, StatementType.BOOLEAN);
	}

}
