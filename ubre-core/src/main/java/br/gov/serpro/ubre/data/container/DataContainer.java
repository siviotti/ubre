package br.gov.serpro.ubre.data.container;

import java.util.Map;

import br.gov.serpro.ubre.data.field.Field;
import br.gov.serpro.ubre.data.map.DataMap;
import br.gov.serpro.ubre.data.var.ValueType;
import br.gov.serpro.ubre.data.var.Var;
import br.gov.serpro.ubre.framework.Scope;

/**
 * Interface que define os comportamentos necessários para um container de dados
 * (variáveis).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 15/05/2015
 * 
 */
public interface DataContainer {

	static final String DATA_CONTAINER_DATAMAP_CANNOT_BE_NULL = "DataMap não pode ser nulo!";
	static final String DATA_CONTAINER_ID_CANNOT_BE_NULL = "Id do Container não pode ser nulo!";
	static final String DATA_CONTAINER_VAR_NOT_FOUND = "Variável não encontrada:";
	static final String DATA_CONTAINER_FIELD_NOT_FOUND = "Campo não encontrado:";
	static final String DATA_CONTAINER_FIELD_CANNOT_CHANGE_TYPE = "Campos não podem trocar seu tipo:";
	static final String DATA_CONTAINER_CONST_IS_READ_ONLY = " é uma constante não pode ser alterada";

	/**
	 * Identificador da área de dados.
	 * 
	 * @return
	 */
	String getId();

	/**
	 * Cria uma variável na Sessão.
	 * 
	 * @param token
	 *            O nome da variável.
	 * @param object
	 *            O valor inicial da variável.
	 * @return
	 */
	ValueType createVar(String token, ValueType valueType);

	/**
	 * Determina se existe variável com o nome passado como parâmetro.
	 * 
	 * @param token
	 *            O nome da variável que se deseja procurar.
	 * @return
	 */
	boolean contains(String token);

	boolean isNull(String token);

	/**
	 * Retorna um valor a partir de um token.
	 * 
	 * @param token
	 *            A palavra que representa um dado na área de memória de uma
	 *            máquina.
	 * @return O valor a partir do token.
	 */
	Object getValue(String token);

	/**
	 * Retorna uma String formatada onde as tags do tipo ${token} foram
	 * substituídas pelos seus respectivos valores presentes no Container.
	 * 
	 * @param token
	 *            O token.
	 * @return O valor formatado.
	 */
	String getFormattedValue(String token);

	/**
	 * Retorna o tipo da variável a partir do token.
	 * 
	 * @param token
	 *            O token que identifica a variável.
	 * @return O tipo ou um ero de variável não encontrada.
	 */
	ValueType getType(String token);

	/**
	 * Seta o valor de uma variável. Se for uma constante será disparada uma
	 * exceção.
	 * 
	 * @param token
	 *            O token da variável que receberá o valor.
	 * @param value
	 *            O valor da variável.
	 */
	void setValue(String token, Object value);

	// ********** API de Var **********

	/**
	 * Determina se o token representa uma Var.
	 * 
	 * @param token
	 *            O token
	 * @return
	 */
	boolean isVar(String token);

	/**
	 * Retorna o mapa interno de variáveis. CUIDADO: este método retorna a
	 * instância interna e não uma cópia.
	 * 
	 * @return A instância interna de Map que contém as variáveis.
	 */
	Map<String, Var> getVars();

	/**
	 * Retorna uma Var a partir de seu token. Gera uma exceao se o token não for
	 * encontrado.
	 * 
	 * @param token
	 *            O identificador da Var
	 * @return A Var.
	 */
	Var getVar(String token);

	// ********** API de Field **********

	/**
	 * Determina se o token representa um campo.
	 * 
	 * @param token
	 *            O token
	 * @return
	 */
	boolean isField(String token);

	/**
	 * Retorna a instância de Field a partir do fieldname (ID).Recomendado
	 * testar se o campo existe antes através de <code>isField()</code>.
	 * 
	 * @param fieldName
	 *            O nome/ID do campo
	 * @return A instância do campo ou uma exceção se não for encontrado.
	 */
	Field getField(String fieldName);

	/**
	 * Retorna o mapa interno de campos. CUIDADO: este método retorna a
	 * instância interna e não uma cópia.
	 * 
	 * @return A instância interna de Map que contém os campos.
	 */
	Map<String, Field> getFields();

	/**
	 * Retorna o escopo do container.
	 * 
	 * @return
	 */
	Scope getScope();

	/**
	 * retorna o DataMap usado.
	 * 
	 * @return
	 */
	DataMap getDataMap();

}
