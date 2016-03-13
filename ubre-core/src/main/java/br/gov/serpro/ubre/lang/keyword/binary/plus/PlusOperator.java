package br.gov.serpro.ubre.lang.keyword.binary.plus;

import static br.gov.serpro.ubre.lang.statement.StatementType.DATE;
import static br.gov.serpro.ubre.lang.statement.StatementType.INTEGER;
import static br.gov.serpro.ubre.lang.statement.StatementType.NUMERIC;
import static br.gov.serpro.ubre.lang.statement.StatementType.STRING;
import static br.gov.serpro.ubre.lang.statement.StatementType.TUPLE;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.LangError;
import br.gov.serpro.ubre.lang.LangException;
import br.gov.serpro.ubre.lang.keyword.binary.BinaryKeyword;
import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.operation.arithmetic.date.AddDateOperation;
import br.gov.serpro.ubre.lang.operation.arithmetic.decimal.AddDecimalOperation;
import br.gov.serpro.ubre.lang.operation.arithmetic.integer.AddIntegerOperation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class PlusOperator extends BinaryKeyword {

	private Operation operation;

	public PlusOperator(String token) {
		super(token);

	}

	@Override
	public void link(Statement left, Statement right) {
		if (left.resultType().equals(STRING)) {
			operation = ConcatOperation.INSTANCE;
		} else if (bothTypes(left, right, INTEGER)) {
			operation =  AddIntegerOperation.INSTANCE;
		} else if (left.resultType().equals(DATE)
				&& right.resultType().equals(INTEGER)) {
			operation = new AddDateOperation();
		} else if (NUMERIC.isComparableTo(left.resultType())
				|| NUMERIC.isComparableTo(right.resultType())) {
			operation = AddDecimalOperation.INSTANCE;
		} else {
			throw new LangException(LangError.E31,
					" Uso incorreto do operador +");
		}
		super.link(left, right);
	}

	

	public Statement perform(DataContainer container) {
		return operation.perform(container, left, right);
	}

	public StatementType leftType() {
		return operation.leftType();
	}

	public StatementType rightType() {
		return operation.rightType();
	}

	public StatementType resultType() {
		return operation.resultType();
	}

	@Override
	public int precedence() {
		return 5;
	}

}
