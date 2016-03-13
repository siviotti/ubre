package br.net.ubre.lang.statement;

import br.net.ubre.data.container.DataContainer;

/**
 * Este elemento não é um Statement real. Ele é um ponteiro para outro elemento.
 * result, perform e asNode invocao os mesmos métodos no elemento apontado.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 27/04/2015
 * 
 */
public class PointerStatement implements Statement {

	public static final String POINTER_PREFIX = "Pointer";

	private Statement target;

	public PointerStatement(Statement target) {
		super();
		this.target = target;
	}

	public Statement perform(DataContainer container) {
		return target.perform(container);
	}

	public String asToken() {
		return "(" + target.asToken() + ")";
	}

	public String asNode(String space) {
		return target.asNode(POINTER_PREFIX + space);
	}

	public Object result(DataContainer container) {
		return target.result(container);
	}

	public StatementType leftType() {
		return target.leftType();
	}

	public StatementType rightType() {
		return target.rightType();
	}

	public StatementType resultType() {
		return target.resultType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return asToken();
	}

	public Statement getTarget() {
		return target;
	}

	@Override
	public boolean isLiteral() {
		return target.isLiteral();
	}

}
