package br.net.ubre.lang.data;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.exception.CTDException;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Statement que representa um valor variável.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public class DataStatement implements Statement {

	private static final String DATA_CANNOT_LINK = "Impossível fazer link de um token do tipo 'DataStatement'";
	protected String token;
	protected StatementType dataType;

	public DataStatement(String token, StatementType dataType) {
		super();
		this.token = token;
		this.dataType = dataType;

	}

	public Statement perform(DataContainer container) {
		return this;
	}

	public String asToken() {
		return token;
	}

	public Object result(DataContainer container) {
		// Busca no DataMap de forma genérica (const, var ou field)
		return container.getValue(token);
	}

	public StatementType resultType() {
		return dataType;
	}

	public StatementType leftType() {
		return StatementType.VOID;
	}

	public StatementType rightType() {
		return StatementType.VOID;
	}

	public void link(Statement left, Statement right) {
		throw new CTDException(DATA_CANNOT_LINK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return token;
	}

	public String asNode(String space) {
		return space + "─── " + token + " " + resultType();
	}

	public void defineType(StatementType type) {
		dataType = type;
	}

	@Override
	public boolean isLiteral() {
		return false;
	}

}
