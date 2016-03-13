package br.net.ubre.lang.keyword.conditional;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.PairStatement;
import br.net.ubre.lang.keyword.KeywordStatement;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Operador ternário condicional.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 20/04/2015
 * 
 */
public class ConditionalOperator extends KeywordStatement {

	public ConditionalOperator(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		if ((Boolean) left.result(container)) {
			return ((PairStatement) right).getLeft().perform(container);
		}
		return ((PairStatement) right).getRight().perform(container);
	}

	public StatementType leftType() {
		return StatementType.BOOLEAN;
	}

	public StatementType rightType() {
		return StatementType.PAIR;
	}

	public StatementType resultType() {
		return StatementType.OBJECT;
	}

	@Override
	public int precedence() {
		return 14;
	}

	@Override
	public boolean isLiteral() {
		return left.isLiteral()
				&& ((PairStatement) right).getLeft().isLiteral()
				&& ((PairStatement) right).getRight().isLiteral();
	}
}
