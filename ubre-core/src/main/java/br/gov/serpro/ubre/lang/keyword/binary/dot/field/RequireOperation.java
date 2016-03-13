package br.gov.serpro.ubre.lang.keyword.binary.dot.field;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.data.field.Field;
import br.gov.serpro.ubre.lang.data.literal.FalseStatement;
import br.gov.serpro.ubre.lang.data.literal.TrueStatement;
import br.gov.serpro.ubre.lang.keyword.binary.dot.PropertyOperation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Propriedade required.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 27/10/2015
 */
public class RequireOperation extends PropertyOperation {
	
	public static final RequireOperation INSTANCE = new RequireOperation();

	@Override
	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		Field field = container.getField(left.asToken());
		return (field.isRequired()) ? TrueStatement.INSTANCE
				: FalseStatement.INSTANCE;
	}


	@Override
	public StatementType resultType() {
		return StatementType.BOOLEAN;
	}

}
