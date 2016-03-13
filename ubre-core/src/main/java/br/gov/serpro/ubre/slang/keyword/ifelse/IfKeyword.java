package br.gov.serpro.ubre.slang.keyword.ifelse;

import br.gov.serpro.ubre.lang.statement.StatementType;
import br.gov.serpro.ubre.slang.keyword.ControlKeyword;
import br.gov.serpro.ubre.slang.keyword.TargetType;

/**
 * Keyword para if.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 13/10/2015
 */
public class IfKeyword extends ControlKeyword {

	public IfKeyword() {
		super("if", TargetType.NONE, StatementType.BOOLEAN);
	}

}
