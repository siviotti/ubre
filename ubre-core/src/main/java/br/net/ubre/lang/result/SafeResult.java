package br.net.ubre.lang.result;

import java.math.BigDecimal;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.data.var.Datex;
import br.net.ubre.internal.Const;
import br.net.ubre.lang.statement.Statement;

/**
 * Implementsção de <code>Result</code> onde os métodos "safe" fazem verificação
 * de nulidae (==null) e retornam um valor padrão que o substitui.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 28/10/2015
 */
public class SafeResult implements Result {
	
	public static final Result INSTANCE = new SafeResult();

	@Override
	public Integer safeInteger(DataContainer container, Statement statement) {
		Integer result = (Integer) statement.result(container);
		return (result != null) ? result : Const.INTEGER_DEFAULT_RESULT;
	}

	@Override
	public Integer getInteger(DataContainer container, Statement statement) {
		return (Integer) statement.result(container);
	}

	@Override
	public BigDecimal safeDecimal(DataContainer container, Statement statement) {
		BigDecimal result = (BigDecimal) statement.result(container);
		return (result != null) ? result : Const.DECIMAL_DEFAULT_RESULT;
	}

	@Override
	public BigDecimal getDecimal(DataContainer container, Statement statement) {
		return (BigDecimal) statement.result(container);
	}

	@Override
	public Boolean safeBoolean(DataContainer container, Statement statement) {
		Boolean result = (Boolean) statement.result(container);
		return (result != null) ? result : Const.BOOLEAN_DEFAULT_RESULT;
	}

	@Override
	public Boolean getBoolean(DataContainer container, Statement statement) {
		return (Boolean) statement.result(container);
	}

	@Override
	public Datex safeDate(DataContainer container, Statement statement) {
		Datex result = (Datex) statement.result(container);
		return (result != null) ? result : Datex.ZERO;
	}

	@Override
	public Datex getDate(DataContainer container, Statement statement) {
		return (Datex) statement.result(container);
	}

	@Override
	public String safeString(DataContainer container, Statement statement) {
		String result = (String) statement.result(container);
		return (result != null) ? result : Const.STRING_DEFAULT_RESULT;
	}

	@Override
	public String getString(DataContainer container, Statement statement) {
		return (String) statement.result(container);
	}

}
