package br.net.ubre.lang.result;

import java.math.BigDecimal;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.data.var.Datex;
import br.net.ubre.lang.statement.Statement;

/**
 * Interface que define os métodos para obtenção de "result" de forma segura
 * quanto a ocorrência de valores nulos (null). Se um valor é nulo, é retornado
 * um valor default para cada tipo de dado.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 28/10/2015
 */
public interface Result {

	/**
	 * Retorna um Integer "seguro". Se o valor interno for null é retornado a
	 * instância de <code>IntegerZeroStatement</code> que contém um zero
	 * interno.
	 * 
	 * @param container
	 *            O container
	 * @param statement
	 *            O elemento que deve fornecer um "result"
	 * @return O valor de "result" do elemento (Statement) ou o valor "safe" se
	 *         for nulo.
	 */
	Integer safeInteger(DataContainer container, Statement statement);

	/**
	 * Retorna um Integer "NÂO seguro". Não faz o teste de <code>null</code>
	 * antes de retornar.
	 * 
	 * @param container
	 *            O container
	 * @param statement
	 *            O elemento que deve fornecer um "result"
	 * @return O valor de "result" do elemento (Statement) ou o valor "safe" se
	 *         for nulo.
	 */
	Integer getInteger(DataContainer container, Statement statement);

	/**
	 * Retorna um BigDecimal "seguro". Se o valor interno for null é retornado a
	 * instância de <code>DecimalZeroStatement</code> que contém um zero
	 * interno.
	 * 
	 * @param container
	 *            O container
	 * @param statement
	 *            O elemento que deve fornecer um "result"
	 * @return O valor de "result" do elemento (Statement) ou o valor "safe" se
	 *         for nulo.
	 */
	BigDecimal safeDecimal(DataContainer container, Statement statement);

	/**
	 * Retorna um BigDecimal "NÂO seguro". 
	 * 
	 * @param container
	 *            O container
	 * @param statement
	 *            O elemento que deve fornecer um "result"
	 * @return O valor de "result" do elemento (Statement) ou o valor "safe" se
	 *         for nulo.
	 */
	BigDecimal getDecimal(DataContainer container, Statement statement);

	/**
	 * Retorna um Boolean "seguro". Se o valor interno for null é retornado a
	 * instância de <code>FalseStatement</code> que contém um 'false' interno.
	 * 
	 * @param container
	 *            O container
	 * @param statement
	 *            O elemento que deve fornecer um "result"
	 * @return O valor de "result" do elemento (Statement) ou o valor "safe" se
	 *         for nulo.
	 */
	Boolean safeBoolean(DataContainer container, Statement statement);

	/**
	 * Retorna um Boolean "NÂO seguro". Não faz o teste de <code>null</code>
	 * antes de retornar.
	 * 
	 * @param container
	 *            O container
	 * @param statement
	 *            O elemento que deve fornecer um "result"
	 * @return O valor de "result" do elemento (Statement) ou o valor "safe" se
	 *         for nulo.
	 */
	Boolean getBoolean(DataContainer container, Statement statement);

	/**
	 * Retorna um Date "seguro". Se o valor interno for null é retornado a
	 * instância de <code>DateZeroStatement</code> que contém um zero interno.
	 * 
	 * @param container
	 *            O container
	 * @param statement
	 *            O elemento que deve fornecer um "result"
	 * @return O valor de "result" do elemento (Statement) ou o valor "safe" se
	 *         for nulo.
	 */
	Datex safeDate(DataContainer container, Statement statement);

	/**
	 * Retorna um Date (Datex) "NÂO seguro". Não faz o teste de
	 * <code>null</code> antes de retornar.
	 * 
	 * @param container
	 *            O container
	 * @param statement
	 *            O elemento que deve fornecer um "result"
	 * @return O valor de "result" do elemento (Statement) ou o valor "safe" se
	 *         for nulo.
	 */
	Datex getDate(DataContainer container, Statement statement);

	/**
	 * Retorna uma String "segura". Se o valor interno for null é retornado a
	 * instância de <code>DateZeroStatement</code> que contém um zero interno.
	 * 
	 * @param container
	 *            O container
	 * @param statement
	 *            O elemento que deve fornecer um "result"
	 * @return O valor de "result" do elemento (Statement) ou o valor "safe" se
	 *         for nulo.
	 */
	String safeString(DataContainer container, Statement statement);

	/**
	 * Retorna um String "NÂO seguro". Se o valor interno for null é retornado a
	 * instância de <code>DateZeroStatement</code> que contém um zero interno.
	 * 
	 * @param container
	 *            O container
	 * @param statement
	 *            O elemento que deve fornecer um "result"
	 * @return O valor de "result" do elemento (Statement) ou o valor "safe" se
	 *         for nulo.
	 */
	String getString(DataContainer container, Statement statement);

}
