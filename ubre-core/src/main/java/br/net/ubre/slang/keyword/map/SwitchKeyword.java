package br.net.ubre.slang.keyword.map;

import br.net.ubre.lang.statement.StatementType;
import br.net.ubre.slang.keyword.ControlKeyword;
import br.net.ubre.slang.keyword.TargetType;

/**
 * Keyword para switch.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 13/10/2015
 */
public class SwitchKeyword extends ControlKeyword{

	public SwitchKeyword() {
		super("switch", TargetType.VAR_OR_FIELD, StatementType.VOID);
	}

}
