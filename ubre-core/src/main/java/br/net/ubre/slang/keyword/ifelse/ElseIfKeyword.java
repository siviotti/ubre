package br.net.ubre.slang.keyword.ifelse;

import br.net.ubre.lang.statement.StatementType;
import br.net.ubre.slang.keyword.ControlKeyword;
import br.net.ubre.slang.keyword.TargetType;

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
