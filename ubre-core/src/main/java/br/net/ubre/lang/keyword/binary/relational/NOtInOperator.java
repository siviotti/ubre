package br.net.ubre.lang.keyword.binary.relational;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.literal.BooleanStatement;
import br.net.ubre.lang.statement.Statement;

/**
 * Operador "Não está contido"
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 20/04/2015
 * 
 */
public class NOtInOperator extends InOperator {

	public NOtInOperator(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		return ((BooleanStatement) super.perform(container)).inverse();
	}

}
