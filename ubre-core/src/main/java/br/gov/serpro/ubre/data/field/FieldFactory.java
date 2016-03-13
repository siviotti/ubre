package br.gov.serpro.ubre.data.field;

import br.gov.serpro.ubre.data.map.VarPattern;
import br.gov.serpro.ubre.framework.Metadata;

/**
 * Fábrica de campos (Field).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 11/06/2015
 * 
 */
public class FieldFactory {

	private VarPattern varPattern = new VarPattern();

	public FieldFactory() {
		super();
	}

	public Field create(Metadata metadata) {
		String token = varPattern.getFieldTag() + metadata.getId();
		Field field = new StandardField(token, metadata);
		return field;
	}
}
