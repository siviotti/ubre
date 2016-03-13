package br.net.ubre.exception;

/**
 * Exceção que é disparada dentro das classes de fachada (API) do CTD.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 14/10/2015
 */
public class CTDApiException extends CTDException {

	private static final long serialVersionUID = 1L;

	public CTDApiException() {
		super();
	}

	public CTDApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public CTDApiException(String message) {
		super(message);
	}

	public CTDApiException(Throwable cause) {
		super(cause);
	}

}
