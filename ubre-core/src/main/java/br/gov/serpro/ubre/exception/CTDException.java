package br.gov.serpro.ubre.exception;

/**
 * Exceção genérica do Framework CTD.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class CTDException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CTDException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CTDException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CTDException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CTDException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
