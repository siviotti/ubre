package br.gov.serpro.ubre.lang.data;

import java.util.HashMap;
import java.util.Map;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.internal.Str;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * É uma variável especialidaza para tokens que representam campos (Field).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 19/05/2015
 * 
 */
public class FieldStatement extends VarStatement implements ComplexData {

	private static final Map<String, StatementType> propDefs = new HashMap<String, StatementType>();

	static {
		propDefs.put(Str.REQUIRED_PROPERTY, StatementType.BOOLEAN);
	}

	public FieldStatement(String token, StatementType dataType) {
		super(token, dataType);
	}

	@Override
	public Object result(DataContainer container) {
		// BUsca direto do mapa de campos
		return container.getField(token).getValue();
	}

	@Override
	public boolean hasProperty(String propertyName) {
		return propDefs.containsKey(propertyName);
	}

	@Override
	public StatementType getType(String propertyName) {
		return propDefs.get(propertyName);
	}

}
