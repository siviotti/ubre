package br.net.ubre.lang.operation.arithmetic;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 19/10/2015
 */
public class DivisionByZeroException extends ArithmeticException {

	private static final long serialVersionUID = 1L;

	public DivisionByZeroException() {
		super();
	}

	public DivisionByZeroException(String s) {
		super(s);

	}

}
