package br.net.ubre.slang.keyword.map;

import br.net.ubre.lang.statement.StatementType;
import br.net.ubre.slang.keyword.ControlKeyword;
import br.net.ubre.slang.keyword.TargetType;

/**
 * Opção default de um switch.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 24/10/2015 6:20
 */
public class DefaultKeyword extends ControlKeyword {

	public DefaultKeyword() {
		super("default", TargetType.NONE, StatementType.VOID);
	}

}
