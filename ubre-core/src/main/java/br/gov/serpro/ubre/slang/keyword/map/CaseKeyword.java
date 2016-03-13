package br.gov.serpro.ubre.slang.keyword.map;

import br.gov.serpro.ubre.lang.statement.StatementType;
import br.gov.serpro.ubre.slang.keyword.ControlKeyword;
import br.gov.serpro.ubre.slang.keyword.TargetType;

/**
 * Keyword para case.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 13/10/2015
 */
public class CaseKeyword extends ControlKeyword{

	public CaseKeyword() {
		super("case", TargetType.NONE, StatementType.OBJECT);
	}

}
