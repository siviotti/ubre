package br.gov.serpro.ubre.lang.keyword.unary.function.size;

import java.util.List;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.data.literal.IntegerStatement;
import br.gov.serpro.ubre.lang.data.literal.IntegerZeroStatement;
import br.gov.serpro.ubre.lang.data.literal.LiteralStatement;
import br.gov.serpro.ubre.lang.keyword.unary.function.GenericFuntionOperation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Operaçao para calcular o tamanho de uma Tupla (Lista)
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 22/10/2015
 */
public class TupleSizeOperation extends GenericFuntionOperation {

	public static final TupleSizeOperation INSTANCE = new TupleSizeOperation();

	private TupleSizeOperation() {
	}

	@Override
	public Statement perform(DataContainer container, Statement left,
			Statement right) {
		List rightResult = (List) right.result(container);
		if (rightResult == null || rightResult.isEmpty()) {
			return IntegerZeroStatement.INSTANCE;
		}
		return new IntegerStatement(rightResult.size());
	}

	@Override
	public StatementType rightType() {
		return StatementType.TUPLE;
	}

	@Override
	public StatementType resultType() {
		return StatementType.INTEGER;
	}

}
