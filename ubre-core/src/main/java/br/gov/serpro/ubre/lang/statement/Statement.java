package br.gov.serpro.ubre.lang.statement;

import br.gov.serpro.ubre.data.container.DataContainer;

/**
 * Classe que representa o menor membro de uma expressão. Palavras reservadas,
 * variáveis, constantes, funções etc.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public interface Statement {

	/**
	 * Executa a ação associada a um Statement.
	 * 
	 * @param container
	 *            A máquina virtual com a área de memória a ser utilizada.
	 * @return O Statement resultante da realização da ação ou a referência para
	 *         o ŕóprio Statement no caso de ser um valor.
	 */
	public Statement perform(DataContainer container);

	/**
	 * Retorna a texto original do Statement na expressão para fins de debug.
	 * 
	 * @return O texto do token original.
	 */
	public String asToken();

	/**
	 * Retorna o Statement como nó de uma árvore binária de execução.
	 * 
	 * @return o nó da árvore em um formato pai.pai.filho.
	 */
	public String asNode(String space);

	/**
	 * Retorna o valor associado ao Statement.
	 * 
	 * @param container
	 *            A máquina virtual com a área de memória a ser utilizada.
	 * @return O objeto resultante é um valor armazenado, no caso de valores
	 *         (variáveis e constantes), ou o resultado de uma execução do
	 *         método perform, no caso de operadores e fuções.
	 */
	public Object result(DataContainer container);

	/**
	 * Tipo aceito para o Statement a esquerda.
	 * 
	 * @return O tipo de dado do Statement a esquerda.
	 */
	public StatementType leftType();

	/**
	 * Tipo aceito para o Statement à direita.
	 * 
	 * @return O tipo de dado do Statement à direita.
	 */
	public StatementType rightType();

	/**
	 * Tipo de dado do resultado retornado.
	 * 
	 * @return O tipo de dado do resultado.
	 */
	public StatementType resultType();

	/**
	 * Informa se o Statement é um Literal, ou seja, se o seu valor não depende
	 * de <code>DataContainer</code> (variável). Este tipo de Statement é
	 * oriundo de um token literal. No caso de operadores, esta decisão é
	 * repassada para os Statements lincados.
	 * 
	 * @return <code>true</code> se o Statement é um literal ou
	 *         <code>false</code> se não é.
	 */
	public boolean isLiteral();

}
