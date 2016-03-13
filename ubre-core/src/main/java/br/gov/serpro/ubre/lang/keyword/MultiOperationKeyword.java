package br.gov.serpro.ubre.lang.keyword;

import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * Interface que define o método para definição de operação quando o operador é
 * multi operação como o +, por exemplo. A operação é definida em função dos
 * elementos que estão ao lado e a combinação de Statements.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 27/04/2015
 * 
 */
public interface MultiOperationKeyword {

	/**
	 * Define a operação em função dos elementos imediatamente à esquerda e à
	 * direita para alguns operadores que executam mais de uma operação. Este
	 * método existe para os operadores que precisam definir a operação antes da
	 * chamada do método "link" que passa os membros laterais de uma Keyword. O
	 * operador Dash, por exemplo, presisa decidir se é um menos ou um negativo.
	 * Isso ocorre porque as precedências destas operações é diferente.
	 * 
	 * @param left
	 *            O elemento à esquerda.
	 * @param right
	 *            O elemento à direita.
	 */
	void defineOperation(Statement left, Statement right);
}
