package br.net.ubre.lang.keyword.parenthesis;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.exception.CTDException;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 20/04/2015
 * 
 */
public class CloseParenthesisOperator extends ParenthesisOperator {

	private static final String LINK_ERROR = "O operador ) é apenas de marcação de fim.";

	public CloseParenthesisOperator(String token) {
		super(token);
	}

	public StatementType leftType() {
		return StatementType.VOID;
	}

	public StatementType rightType() {
		return StatementType.VOID;
	}

	public Statement perform(DataContainer container) {
		throw new CTDException(LINK_ERROR);
	}
	
	@Override
	public boolean isLiteral() {
		throw new CTDException(LINK_ERROR);
	}
}
