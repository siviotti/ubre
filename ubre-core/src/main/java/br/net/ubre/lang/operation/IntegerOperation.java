package br.net.ubre.lang.operation;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Superclasse para todas as operações com inteiros.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 19/10/2015
 */
public abstract class IntegerOperation implements Operation {
	
	public static final Integer INTEGER_ZERO = new Integer(0);
	public static final Integer INTEGER_ONE = new Integer(1);

	public StatementType leftType() {
		return StatementType.INTEGER;
	}

	public StatementType rightType() {
		return StatementType.INTEGER;
	}

	public StatementType resultType() {
		return StatementType.INTEGER;
	}
	
	/**
	 * Retorna o valor de 'result' do Statement (parte) passado como parâmetro,
	 * porém já tratando possíveis valores nulos. Se o result for nulo será
	 * invocado o método <code>neutralResult</code> que é um valor padrão que
	 * não afeta a operação (neutro).
	 * 
	 * @param statement
	 *            O statementdo qual será extraido o result.
	 * @param container
	 *            O container de dados.
	 * @return O valor de result do Statement passado
	 */
	public Integer safeResult(DataContainer container, Statement statement) {
		Integer result = (Integer) statement.result(container);
		return (result != null) ? result : neutralResult();
	}
	
	/**
	 * Valor neutro para ser retornado quando o valor do Statement em questão fo
	 * nulo.
	 * 
	 * @return O valor que substitui o nulo.
	 */
	public abstract Integer neutralResult();


}
