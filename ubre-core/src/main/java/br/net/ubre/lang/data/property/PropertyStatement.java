package br.net.ubre.lang.data.property;

import br.net.ubre.lang.data.literal.LiteralStatement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 27/10/2015
 */
public class PropertyStatement extends LiteralStatement{

	public PropertyStatement(String token, StatementType dataType) {
		super(token, dataType, null);
	}

}
