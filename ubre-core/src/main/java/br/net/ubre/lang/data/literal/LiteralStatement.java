package br.net.ubre.lang.data.literal;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.exception.CTDException;
import br.net.ubre.lang.data.DataStatement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Representa um Statement cujo token já representa o valor(Literal) e não será
 * preciso buscar no DataMap.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public abstract class LiteralStatement extends DataStatement {

	public static final String NULL = "null";

	private static final String INVALID_TYPE = "Uma constante deve ter um tipo específico. Não é permitido usar tipos agrupadores como:";
	protected Object value;

	public LiteralStatement(String token, StatementType dataType, Object value) {
		super(token, dataType);
		if (!dataType.isGroup()) {
			throw new CTDException(INVALID_TYPE + dataType.name());
		}
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.gov.serpro.ctd.lang.data.DataStatement#getValue(br.gov.serpro.ctd.
	 * lang.CTDMachine)
	 */
	@Override
	public Object result(DataContainer container) {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.gov.serpro.ctd.lang.data.DataStatement#defineType(br.gov.serpro.ctd
	 * .lang.StatementType)
	 */
	@Override
	public void defineType(StatementType type) {
		throw new CTDException("Constantes não podem trocar de tipo");
	}

	@Override
	public boolean isLiteral() {
		return true;
	}

	
}
