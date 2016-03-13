package br.gov.serpro.ubre.slang.condition;

import br.gov.serpro.ubre.data.container.DataContainer;

/**
 * Condição que sempre é verdaderia. Uma regra sem condição tem uma condição
 * verdadeira. Esta classe existe para ganho de performance, pois o método que
 * testa já retorna true.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class TrueCondition implements Condition {

	public boolean isTrue(DataContainer container) {
		return true;
	}

	public String getSource() {
		return "true";
	}



}
