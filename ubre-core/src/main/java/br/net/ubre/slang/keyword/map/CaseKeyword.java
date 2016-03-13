package br.net.ubre.slang.keyword.map;

import br.net.ubre.lang.statement.StatementType;
import br.net.ubre.slang.keyword.ControlKeyword;
import br.net.ubre.slang.keyword.TargetType;

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
