package br.net.ubre.lang.keyword.binary.dot.field;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.keyword.binary.dot.PropertyOperation;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 29/10/2015
 */
public abstract class FieldOperation extends PropertyOperation{

	@Override
	public StatementType leftType() {
		return null;
	}

	@Override
	public StatementType rightType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatementType resultType() {
		// TODO Auto-generated method stub
		return null;
	}

}
