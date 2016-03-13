package br.net.ubre.data.var;

import br.net.ubre.data.converter.BooleanConverter;
import br.net.ubre.data.converter.Converter;
import br.net.ubre.data.converter.DateConverter;
import br.net.ubre.data.converter.DecimalConverter;
import br.net.ubre.data.converter.IntegerConverter;
import br.net.ubre.data.converter.ListConverter;
import br.net.ubre.data.converter.StringConverter;

/**
 * Tipos de dados para os metadados.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 19/03/2015
 * 
 */
public enum ValueType {

	/** Dados alfanuméricos */
	TEXT(new StringConverter()),
	/** Dados numéricos inteiros */
	INTEGER(new IntegerConverter()),
	/** Dados numéricos fracionados */
	DECIMAL(new DecimalConverter()),
	/** Dados do tipo booleano (sim/não) */
	BOOLEAN(new BooleanConverter()),
	/** Dados do tipo data, data-hora ou hora */
	DATE(new DateConverter()),
	/** Dados do tipo booleano (sim/não) */
	LIST(new ListConverter());

	private Converter converter;

	private ValueType(Converter converter) {
		this.converter = converter;
	}

	/**
	 * Retorna a instância apartir do nome do tipo.
	 * 
	 * @param type
	 *            O nome do tipo correspondente aos itens do Enum.
	 * @return A instância correspondente ao nome.
	 */
	public static ValueType get(String type) {
		for (ValueType valueType : values()) {
			if (valueType.name().equals(type)) {
				return valueType;
			}
		}
		throw new RuntimeException("Tipo desconhecido:" + type);
	}

	/**
	 * @return the converter
	 */
	public Converter getConverter() {
		return converter;
	}

}
