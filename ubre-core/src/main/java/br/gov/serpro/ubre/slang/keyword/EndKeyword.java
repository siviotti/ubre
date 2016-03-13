package br.gov.serpro.ubre.slang.keyword;

import br.gov.serpro.ubre.lang.statement.StatementType;

/**Keyword para end.
 * @author Douglas Siviotti (073.116.317-69)
 * @version 13/10/2015
 */
public class EndKeyword extends ControlKeyword{

	public EndKeyword() {
		super("end", TargetType.NONE, StatementType.VOID);
	}

}
