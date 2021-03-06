package br.net.ubre.lang.data;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.statement.StatementType;

/**
 * Statement que aponta para um texto com tags de formatação tipo ${token}.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 15/10/2015
 */
public class MutantStatement extends DataStatement {

	public MutantStatement(String token, StatementType dataType) {
		super(token, dataType);
	}

	@Override
	public Object result(DataContainer container) {
		return container.getFormattedValue(token);
	}
	
	

}
