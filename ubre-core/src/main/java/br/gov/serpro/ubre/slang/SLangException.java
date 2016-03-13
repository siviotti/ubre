package br.gov.serpro.ubre.slang;

import br.gov.serpro.ubre.lang.LangError;
import br.gov.serpro.ubre.lang.LangException;

/**
 * Exceção específica para erros <B>NO USO</B> da Linguagem de Script (SLANG).
 * Erros de configuração da sintaxe, por exemplo, devem usar outra exceção.
 * 
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 09/10/2015
 */
public class SLangException extends LangException {

	private static final long serialVersionUID = 1L;

	public SLangException(LangError error, String message, Throwable cause) {
		super(error, message, cause);
	}

	public SLangException(LangError error, Throwable cause) {
		super(error, cause);
	}

	public SLangException(LangError error, String message) {
		super(error, message);
	}
}
