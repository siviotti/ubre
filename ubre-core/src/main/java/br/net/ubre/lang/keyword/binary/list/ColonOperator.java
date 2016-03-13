package br.net.ubre.lang.keyword.binary.list;

import static br.net.ubre.lang.LangError.E31;
import static br.net.ubre.lang.statement.StatementType.DATE;
import static br.net.ubre.lang.statement.StatementType.INTEGER;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.LangException;
import br.net.ubre.lang.keyword.binary.BinaryKeyword;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Operador de par (range de dois elementos inteiros ou data).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 27/03/2015
 * 
 */
public class ColonOperator extends BinaryKeyword {

	private static final String COLON_PAIR_INTEGER_OR_DATE = "Um par só pode ser formado por dois inteiros ou duas datas";
	private Operation operation;

	public ColonOperator(String token) {
		super(token);
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
		return StatementType.PAIR;
	}

	@Override
	public int precedence() {
		return 16;
	}

	@Override
	public void link(Statement left, Statement right) {
		if (left.resultType().equals(INTEGER)
				&& right.resultType().equals(INTEGER)) {
			operation = new IntegerPairOperation();
		} else if (left.resultType().equals(DATE)
				&& right.resultType().equals(DATE)) {
			operation = new DatePairOperation();
		} else {
			throw new LangException(E31, COLON_PAIR_INTEGER_OR_DATE);
		}
		super.link(left, right);
	}

}
