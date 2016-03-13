package br.net.ubre.slang.keyword.ifelse;

import br.net.ubre.lang.statement.StatementType;
import br.net.ubre.slang.keyword.ControlKeyword;
import br.net.ubre.slang.keyword.TargetType;

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
