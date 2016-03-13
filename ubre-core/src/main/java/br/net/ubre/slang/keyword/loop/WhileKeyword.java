package br.net.ubre.slang.keyword.loop;

import br.net.ubre.lang.statement.StatementType;
import br.net.ubre.slang.keyword.ControlKeyword;
import br.net.ubre.slang.keyword.TargetType;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 24/10/2015
 */
public class WhileKeyword extends ControlKeyword{

	public WhileKeyword() {
		super("while", TargetType.NONE, StatementType.BOOLEAN);
	}

}
