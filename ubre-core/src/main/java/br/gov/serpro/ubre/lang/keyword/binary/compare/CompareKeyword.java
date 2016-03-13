package br.gov.serpro.ubre.lang.keyword.binary.compare;

import static br.gov.serpro.ubre.lang.statement.StatementType.DATE;
import static br.gov.serpro.ubre.lang.statement.StatementType.INTEGER;
import static br.gov.serpro.ubre.lang.statement.StatementType.NUMERIC;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.LangError;
import br.gov.serpro.ubre.lang.LangException;
import br.gov.serpro.ubre.lang.keyword.binary.BinaryKeyword;
import br.gov.serpro.ubre.lang.keyword.binary.compare.comparation.Comparation;
import br.gov.serpro.ubre.lang.keyword.binary.compare.comparation.DateComparation;
import br.gov.serpro.ubre.lang.keyword.binary.compare.comparation.IntegerComparation;
import br.gov.serpro.ubre.lang.keyword.binary.compare.comparation.NumericComparation;
import br.gov.serpro.ubre.lang.keyword.binary.compare.comparation.StringComparation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Operador genéricos dos operadores de comparação (maior, menor, maior igual,
 * menor igual, Entre, Fora). Esta classe já decide quel o tipo de comparação
 * deverá ser feita (inteiro, numérico, data ou String).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public abstract class CompareKeyword extends BinaryKeyword {

	protected Comparation comparation;
	
	public Statement perform(DataContainer container) {
		return null;
	}

	public CompareKeyword(String token) {
		super(token);
	}

	public StatementType leftType() {
		return StatementType.OBJECT;
	}

	public StatementType rightType() {
		return StatementType.OBJECT;
	}

	public StatementType resultType() {
		return StatementType.BOOLEAN;
	}

	@Override
	public int precedence() {
		return 7;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.gov.serpro.ctd.lang.keyword.binary.BinaryKeyword#link(br.gov.serpro
	 * .ctd.lang.Statement, br.gov.serpro.ctd.lang.Statement)
	 */
	@Override
	public void link(Statement left, Statement right) {
		super.link(left, right);
		checkCompare(left, right);
		if (left.resultType().equals(INTEGER)
				&& right.resultType().equals(INTEGER)) {
			comparation = new IntegerComparation();
		} else if (NUMERIC.isComparableTo(left.resultType())) {
			comparation = new NumericComparation();
		} else if (left.resultType().equals(DATE)) {
			comparation = new DateComparation();
		} else {
			comparation = new StringComparation();
		}

	}

	protected void checkCompare(Statement left, Statement right) {
		if (!left.resultType().isComparableTo(right.resultType())) {
			throw new LangException(LangError.E31,
					"Tipos imcompatíveis para uma comparação:"
							+ left.resultType() + " e " + right.resultType());
		}
	}

}
