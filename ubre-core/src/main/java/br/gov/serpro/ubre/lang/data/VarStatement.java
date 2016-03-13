package br.gov.serpro.ubre.lang.data;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Representa um token que está presente no DataMap como variável (não
 * representa um Campo [Field]). Quando for invocado result é de lá que virá o
 * valor resultante.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 09/10/2015
 */
public class VarStatement extends DataStatement {

	public VarStatement(String token, StatementType dataType) {
		super(token, dataType);
	}

	@Override
	public Object result(DataContainer container) {
		//busca direto do mapa de variáveis
		return container.getVars().get(token).getValue();
	}

	
}
