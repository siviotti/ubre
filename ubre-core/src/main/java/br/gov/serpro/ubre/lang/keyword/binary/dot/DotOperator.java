package br.gov.serpro.ubre.lang.keyword.binary.dot;

import javax.swing.text.StyleContext.SmallAttributeSet;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.LangError;
import br.gov.serpro.ubre.lang.LangException;
import br.gov.serpro.ubre.lang.data.ComplexData;
import br.gov.serpro.ubre.lang.data.property.PropertyStatement;
import br.gov.serpro.ubre.lang.keyword.KeywordStatement;
import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 27/10/2015
 */
public class DotOperator extends KeywordStatement {

	private static final String DOT_PROPERTY_NOT_FOUND = "Propriedade não encontrada:";
	private static final String DOT_PROPERTY_NOT_FOUND_IN_OBJECT = "A propriedade %s não foi encontrada no objeto %s";
	private static final String DOT_OBJECT_HAS_NO_PROPERTIES = "O objeto %s não aceita propriedades (.)";
	private static final String DOT_USE_INCORRET = "Uso incorreto do operador '.':";
	private Operation operation;

	public DotOperator(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		return operation.perform(container, left, right);
	}

	public StatementType leftType() {
		return operation.leftType();
	}

	public StatementType rightType() {
		return operation.rightType();
	}

	public StatementType resultType() {
		return operation.resultType();
	}

	@Override
	public int precedence() {
		return 0;
	}

	@Override
	public boolean isLiteral() {
		// Nunca é literal, pois aponta para um atributo de um objeto
		return false;
	}

	@Override
	public void link(Statement left, Statement right) {
		if (right instanceof PropertyStatement) {
			operation = getPropertyOperation(left, right);
		} else {
			throw new LangException(LangError.E20, DOT_USE_INCORRET
					+ left.asToken() + "." + right.asToken());
		}

		super.link(left, right);
	}

	private Operation getPropertyOperation(Statement left, Statement right) {
		if (!(left instanceof ComplexData)) {
			String msg = String.format(DOT_OBJECT_HAS_NO_PROPERTIES,
					left.asToken());
			throw new LangException(LangError.E20, msg);
		}
		String propertyName = right.asToken();
		ComplexData complexData = (ComplexData) left;
		if (!complexData.hasProperty(propertyName)) {
			String msg = String.format(DOT_PROPERTY_NOT_FOUND_IN_OBJECT,
					left.asToken(), right.asToken());
			throw new LangException(LangError.E20, msg);
		}
		Property property = Property.get(right);
		if (property == null) {
			throw new LangException(LangError.E20, DOT_PROPERTY_NOT_FOUND
					+ right.asToken());
		}
		return property.operation();
	}

}
