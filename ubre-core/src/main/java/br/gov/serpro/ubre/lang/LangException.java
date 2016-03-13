package br.gov.serpro.ubre.lang;

import br.gov.serpro.ubre.exception.CTDException;

/**
 * Exceção específica para erros <B>NO USO</B> da Linguagem de Expressões
 * (LANG). Erros de configuração da sintaxe, por exemplo, devem usar outra
 * exceção.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class LangException extends CTDException {

	private static final long serialVersionUID = 1L;

	public LangException(LangError error, String message, Throwable cause) {
		super(error.getLabel() + message, cause);
	}

	public LangException(LangError error, Throwable cause) {
		super(error.getLabel() + cause.getMessage(), cause);
	}

	public LangException(LangError error, String message) {
		super(error.getLabel() + message);
	}

}
