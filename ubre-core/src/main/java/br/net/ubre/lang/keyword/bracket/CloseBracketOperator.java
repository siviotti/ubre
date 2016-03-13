package br.net.ubre.lang.keyword.bracket;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.exception.CTDException;
import br.net.ubre.lang.keyword.KeywordStatement;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Classe para definir o fechamento de colchetes.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 27/04/2015
 * 
 */
public class CloseBracketOperator extends KeywordStatement {

	private static final String LINK_ERROR = "Erro de montagem de árvore. O colchete fechado é apenas para delimitação final do bloco";

	public CloseBracketOperator(String token) {
		super("]");
	}

	public Statement perform(DataContainer container) {
		throw new CTDException(LINK_ERROR);
	}

	public StatementType leftType() {
		throw new CTDException(LINK_ERROR);
	}

	public StatementType rightType() {
		throw new CTDException(LINK_ERROR);
	}

	public StatementType resultType() {
		return StatementType.VOID;
	}

	@Override
	public int precedence() {
		throw new CTDException(LINK_ERROR);
	}

	@Override
	public boolean isLiteral() {
		throw new CTDException(LINK_ERROR);
	}

}
