package br.gov.serpro.ubre.lang.operation;

import java.math.BigDecimal;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Operação expecializada decimal.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 19/10/2015
 */
public abstract class DecimalOperation implements Operation {

	public static final BigDecimal DECIMAL_ZERO = new BigDecimal("0.0");
	public static final BigDecimal DECIMAL_ONE = new BigDecimal("1.0");

	public StatementType leftType() {
		return StatementType.NUMERIC;
	}

	public StatementType rightType() {
		return StatementType.NUMERIC;
	}

	public StatementType resultType() {
		return StatementType.DECIMAL;
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
	public Number safeResult(DataContainer container, Statement statement) {
		Number result = (Number) statement.result(container);
		return (result != null) ? result : neutralResult();
	}

	/**
	 * Valor neutro para ser retornado quando o valor do Statement em questão fo
	 * nulo.
	 * 
	 * @return O valor que substitui o nulo.
	 */
	public abstract Number neutralResult();

}
