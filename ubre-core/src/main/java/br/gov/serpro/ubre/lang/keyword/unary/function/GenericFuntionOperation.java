package br.gov.serpro.ubre.lang.keyword.unary.function;

import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Superclasse genérica para as operações de funções.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 22/10/2015
 */
public abstract class GenericFuntionOperation implements Operation {

	@Override
	public StatementType leftType() {
		return StatementType.VOID; // padrão das funções
	}

	@Override
	public int precedence() {
		return 1; // padrão das funções
	}

}
