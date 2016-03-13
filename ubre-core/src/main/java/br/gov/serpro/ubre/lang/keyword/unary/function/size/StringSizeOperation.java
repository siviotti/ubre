package br.gov.serpro.ubre.lang.keyword.unary.function.size;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.IntegerStatement;
import br.gov.serpro.ubre.lang.data.literal.IntegerZeroStatement;
import br.gov.serpro.ubre.lang.keyword.unary.function.GenericFuntionOperation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Determina o tamanho de uma String.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 22/10/2015
 */
public class StringSizeOperation extends GenericFuntionOperation {
	
	public static final StringSizeOperation INSTANCE = new StringSizeOperation();
	
	private StringSizeOperation() {
	}

	@Override
	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		String rightResult = (String) right.result(container);
		if (rightResult == null) {
			return IntegerZeroStatement.INSTANCE;
		}
		return new IntegerStatement(rightResult.length());
	}

	@Override
	public StatementType rightType() {
		return StatementType.STRING;
	}

	@Override
	public StatementType resultType() {
		return StatementType.INTEGER;
	}

}
