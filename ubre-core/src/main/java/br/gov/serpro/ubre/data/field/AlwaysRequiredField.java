package br.gov.serpro.ubre.data.field;

import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.framework.Metadata;

/**
 * Campo sempre obrigatório. Este campo não permite que a propriedade "required"
 * seja alterada. O método <code>setRequired()</code>simplesmente não faz nada.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 06/10/2015
 */
public class AlwaysRequiredField extends StandardField {

	private static final String CANNOT_CHANGE_OB = "Um campo 'Sempre Obrigatório' não pode ter sua obrigatoriedade alterada:";

	public AlwaysRequiredField(String token, Metadata metadata) {
		super(token, metadata);
		require();// torna o campo obrigatório e não muda mais
	}

	/*
	 * Método aterrado. Gera um erro ao tentar trocar sua obrigatoriedade.
	 * 
	 * @see br.gov.serpro.ctd.data.GenericField#setRequired(boolean)
	 */
	@Override
	public void setRequired(boolean required) {
		throw new CTDException(CANNOT_CHANGE_OB + getMetadata().getId());
	}

}
