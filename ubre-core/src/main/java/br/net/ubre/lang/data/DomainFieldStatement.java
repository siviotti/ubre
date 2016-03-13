package br.net.ubre.lang.data;

import br.net.ubre.lang.statement.StatementType;

/**
 * Statement especializado em campos que iutilizam domínios.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 19/05/2015
 * 
 */

public class DomainFieldStatement extends FieldStatement{

	public DomainFieldStatement(String token, StatementType dataType) {
		super(token, dataType);
	}

}
