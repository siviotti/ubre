package br.gov.serpro.ubre.slang.keyword.action;

import br.gov.serpro.ubre.lang.statement.StatementType;
import br.gov.serpro.ubre.slang.keyword.TargetType;

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
