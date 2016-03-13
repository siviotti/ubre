package br.net.ubre.lang.keyword.binary.dot;

import br.net.ubre.internal.Str;
import br.net.ubre.lang.data.property.field.RequiredStatement;
import br.net.ubre.lang.keyword.binary.dot.date.DayOperation;
import br.net.ubre.lang.keyword.binary.dot.field.RequireOperation;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.statement.Statement;

/**
 * Lista de properties aplicaveis a objetos complexos.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 27/10/2015
 */
public enum Property {

	// Field
	/** Obrigatoriedade do campo */
	REQUIRED(RequiredStatement.INSTANCE.asToken(), RequiredStatement.INSTANCE,
			RequireOperation.INSTANCE),

	;

	public static Property get(String name) {
		for (Property property : values()) {
			if (property.token.equals(name)) {
				return property;
			}
		}
		return null;
	}

	public static Property get(Statement statement) {
		for (Property property : values()) {
			if (property.statement.equals(statement)) {
				return property;
			}
		}
		return null;
	}

	private String token;
	private Statement statement;
	private Operation operation;

	private Property(String name, Statement statement, Operation operation) {
		this.token = name;
		this.statement = statement;
		this.operation = operation;
	}

	public String token() {
		return token;
	}

	public Statement statement() {
		return statement;
	}

	public Operation operation() {
		return operation;
	}

}
