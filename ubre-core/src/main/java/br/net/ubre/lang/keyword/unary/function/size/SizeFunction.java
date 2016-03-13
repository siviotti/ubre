package br.net.ubre.lang.keyword.unary.function.size;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.LangError;
import br.net.ubre.lang.LangException;
import br.net.ubre.lang.keyword.unary.function.Function;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Função que calcula tamanho de várias coisas (String, List etc).
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 22/10/2015
 */
public class SizeFunction extends Function {

	private Operation operation;

	public SizeFunction(String token) {
		super(token);
	}

	@Override
	public Statement perform(DataContainer container) {
		return operation.perform(container, left, right);
	}

	@Override
	public StatementType rightType() {
		return StatementType.OBJECT;
	}

	@Override
	public StatementType resultType() {
		return StatementType.INTEGER;
	}

	@Override
	public void link(Statement left, Statement right) {
		if (right.resultType().equals(StatementType.STRING)) {
			operation = StringSizeOperation.INSTANCE;
		} else if (right.resultType().equals(StatementType.TUPLE)) {
			operation = TupleSizeOperation.INSTANCE;
		} else {
			String msg = String.format(FUNCTION_INVALID_TYPE, asToken(), right.resultType().toString());
			throw new LangException(LangError.E31, msg);
		}
		super.link(left, right);
	}

}
