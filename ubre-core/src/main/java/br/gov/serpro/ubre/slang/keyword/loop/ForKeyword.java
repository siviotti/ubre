package br.gov.serpro.ubre.slang.keyword.loop;

import br.gov.serpro.ubre.lang.statement.StatementType;
import br.gov.serpro.ubre.slang.keyword.ControlKeyword;
import br.gov.serpro.ubre.slang.keyword.TargetType;

/**
 * Keyword para a repetição com número fixo.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @since 26/10/2015 00:00
 */
public class ForKeyword extends ControlKeyword {

	public ForKeyword() {
		super("for", TargetType.NONE, StatementType.TUPLE);
	}

}
