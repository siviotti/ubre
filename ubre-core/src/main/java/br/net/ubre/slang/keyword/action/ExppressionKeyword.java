package br.net.ubre.slang.keyword.action;

import br.net.ubre.lang.statement.StatementType;
import br.net.ubre.slang.keyword.TargetType;

/**
 * Keyword para exp.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 13/10/2015
 */
public class ExppressionKeyword extends ActionKeyword{

	public ExppressionKeyword() {
		super("", TargetType.NONE, StatementType.OBJECT);
	}

}
