package br.net.ubre.data.field;

import br.net.ubre.exception.CTDException;
import br.net.ubre.framework.Metadata;

/**
 * Campo que apresenta o comportamento de fechar a entrada para seu valor. Ele
 * usa a propriedade <code>closed</code> para testar se o valor pode ser
 * alterado ou não. Os campos "primitivos", ou seja, sempre obrigatórios devem
 * usar esta classe de campo.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 06/10/2015
 */
public class ClosableField extends StandardField {

	public ClosableField(String token, Metadata metadata) {
		super(token, metadata);
	}

	/**
	 * Recebe o valor do campo como instância de uma classe associada ao tipo.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value) {
		if (isClosed()) {
			throw new CTDException(CLOSED_FIELD + getMetadata().getId());
		}
		super.setValue(value);
	}

}
