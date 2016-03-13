package br.net.ubre.lang.data;

import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Interface de marcação dos onjetos que tem o cpnteúdo complexo (Datas, Campos
 * etc).
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 27/10/2015
 */
public interface ComplexData extends Statement{
	
	boolean hasProperty(String propertyName);
	
	StatementType getType(String propertyName);

}
