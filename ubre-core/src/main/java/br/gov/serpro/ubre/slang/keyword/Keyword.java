package br.gov.serpro.ubre.slang.keyword;

import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Representa uma keyword (palavra reservada).
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 09/10/2015
 */
public abstract class Keyword {

	private static final String KEYWORD_TARGET_TYPE_CANNOT_BE_NULL = "tipo do parâmetro não pode ser nulo!;";
	private static final String KEYWORD_TYPE_CANNOT_BE_NULL = "tipo do alvo não pode ser nulo!";
	private static final String KEYWORD_TOKEN_CANNOT_BE_NULL = "token não pode ser nulo!";

	private String token;
	private TargetType targetType;
	private StatementType parameterType;

	public Keyword(String token, TargetType targetType,
			StatementType parameterType) {
		super();
		if (token == null) {
			throw new CTDException(KEYWORD_TOKEN_CANNOT_BE_NULL);
		}
		this.token = token;
		if (targetType == null) {
			throw new CTDException(KEYWORD_TYPE_CANNOT_BE_NULL);
		}
		this.targetType = targetType;
		if (parameterType == null) {
			throw new CTDException(KEYWORD_TARGET_TYPE_CANNOT_BE_NULL);
		}
		this.parameterType = parameterType;
	}

	// ********** API **********

	/**
	 * Informa se a Keyword é de controle de fluxo, repetição, bloco etc. Ou
	 * seja, não é uma ação/instrução.
	 * 
	 * @return <code>true</code> se for de controle ou <code>false</code> se não
	 *         for.
	 */
	public abstract boolean isControl();

	/**
	 * Informa se a Keyword é de ação (comando ou expressão). Ou seja, não é
	 * controle.
	 * 
	 * @return <code>true</code> se for de ação ou <code>false</code> se não
	 *         for.
	 */
	public abstract boolean isAction();

	public boolean hasTarget() {
		return !targetType.equals(TargetType.NONE);
	}

	public boolean hasParameter() {
		return !parameterType.equals(StatementType.VOID);
	}

	// Get / Set
	public String getToken() {
		return token;
	}

	public TargetType getTargetType() {
		return targetType;
	}

	public StatementType getParameterType() {
		return parameterType;
	}

}
