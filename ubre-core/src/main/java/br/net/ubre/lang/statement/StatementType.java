package br.net.ubre.lang.statement;

import java.util.HashSet;
import java.util.Set;

import br.net.ubre.data.var.ValueType;

/**
 * Tipos de dados para os metadados.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 19/03/2015
 * 
 */
public enum StatementType {

	// TIPOS INDIVIDUAIS
	/** Ausência de tipos */
	VOID(null),
	/** Tipo do literal null */
	NULL(null),
	/** Dados alfanuméricos */
	STRING(ValueType.TEXT),
	/** Dados inteiros */
	INTEGER(ValueType.INTEGER),
	/** Dados decimais */
	DECIMAL(ValueType.DECIMAL),
	/** Dados do tipo booleano (sim/não) */
	BOOLEAN(ValueType.BOOLEAN),
	/** Dados do tipo data, data-hora ou hora */
	DATE(ValueType.DATE),
	/** Lista */
	TUPLE(ValueType.LIST),
	/** Dupla - tupla com dois elementos do mesmo tipo */
	PAIR(ValueType.LIST),
	// GRUPOS
	/** Dados numéricos */
	NUMERIC(null, INTEGER, DECIMAL),
	/** Aritiméticos */
	ARITHMETIC(null, INTEGER, DECIMAL, DATE),
	/** tODOS OS TIPOS */
	OBJECT(null, STRING, INTEGER, DECIMAL, NUMERIC, BOOLEAN, DATE, TUPLE, PAIR);

	private ValueType valueType;
	private Set<StatementType> matchTypes = new HashSet<StatementType>();

	private StatementType(ValueType valueType, StatementType... matchTypes) {
		this.valueType = valueType;
		this.matchTypes.add(this);
		for (StatementType type : matchTypes) {
			this.matchTypes.add(type);
		}
	}

	/**
	 * Retorna a instância apartir do nome do tipo.
	 * 
	 * @param type
	 *            O nome do tipo correspondente aos itens do Enum.
	 * @return A instância correspondente ao nome.
	 */
	public static StatementType get(String type) {
		for (StatementType statementType : values()) {
			if (statementType.name().equals(type)) {
				return statementType;
			}
		}
		throw new RuntimeException("Tipo de valor inválido:" + type);
	}
	
	public static StatementType getFromValueType(ValueType valueType) {
		for (StatementType statementType : values()) {
			if (valueType.equals(statementType.valueType)) {
				return statementType;
			}
		}
		throw new RuntimeException("Tipo de valor inválido:" + valueType);
	}


	public boolean isComparableTo(StatementType type) {
		return matchTypes.contains(type);
	}

	/**
	 * @return the matchTypes
	 */
	public Set<StatementType> getMatchTypes() {
		return matchTypes;
	}

	/**
	 * @return the valueType
	 */
	public ValueType getValueType() {
		return valueType;
	}
	
	public boolean hasValueType(){
		return valueType != null;
	}

	public boolean isGroup() {
		return valueType == null || this != VOID;
	}


}
