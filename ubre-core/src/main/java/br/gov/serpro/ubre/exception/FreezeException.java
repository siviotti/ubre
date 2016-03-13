package br.gov.serpro.ubre.exception;

/**
 * Exceção específica para violação de congelamento de elementos de contexto
 * congeláveis ou outros tipos de elementos imutáveis.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class FreezeException extends CTDException {

	private static final long serialVersionUID = 1L;
	private static final String FREEZABLE_EXCEPTION_MESSAGE = "O objeto da classe %s está congelado e não pode ser alterado. O código fonte está fazendo um acesso inválido e deve ser verificado.";

	public FreezeException() {
		super();
	}

	public FreezeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FreezeException(String message) {
		super(message);
	}

	public FreezeException(Throwable cause) {
		super(cause);
	}

	public FreezeException(Class<?> clazz) {
		this(String.format(FREEZABLE_EXCEPTION_MESSAGE, clazz.getCanonicalName()));
	}


}
