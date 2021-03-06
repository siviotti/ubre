package br.net.ubre.lang.data.property.field;

import br.net.ubre.internal.Str;
import br.net.ubre.lang.data.property.PropertyStatement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 27/10/2015
 */
public class RequiredStatement extends PropertyStatement {

	public static final RequiredStatement INSTANCE = new RequiredStatement();

	private RequiredStatement() {
		super(Str.REQUIRED_PROPERTY, StatementType.STRING);
	}

}
