package br.net.ubre.lang.keyword.binary.assigment;

import static br.net.ubre.lang.LangError.E30;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.exception.CTDException;
import br.net.ubre.lang.LangError;
import br.net.ubre.lang.LangException;
import br.net.ubre.lang.data.FieldStatement;
import br.net.ubre.lang.data.VarStatement;
import br.net.ubre.lang.data.literal.NullStatement;
import br.net.ubre.lang.keyword.binary.BinaryKeyword;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 20/04/2015
 * 
 */
public class AssigmentOperator extends BinaryKeyword {

	private static final String ASSINGMENT_IS_NOT_VAR = " não é uma variável e não pode ter seu valor alterado";
	private static final String ASSIGMENT_FIELD_NOT_ALOWED = "Não é permitido fazer atribuições de valor para campos (tipo '$xpto = 123') usando o operador '='. Campo:";

	public AssigmentOperator(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		container.setValue(left.asToken(), right.result(container));
		return left;
	}

	public StatementType leftType() {
		return StatementType.OBJECT;
	}

	public StatementType rightType() {
		return StatementType.OBJECT;
	}

	public StatementType resultType() {
		return StatementType.OBJECT;
	}

	@Override
	public int precedence() {
		return 15;
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
		if (left instanceof FieldStatement) {
			throw new LangException(LangError.E20, left.asToken()
					+ ASSIGMENT_FIELD_NOT_ALOWED + left.asToken());
		}
		if (!isVar(left)) {
			throw new CTDException(left.asToken() + ASSINGMENT_IS_NOT_VAR);
		}
		this.left = left;
		this.right = right;
		if (!isNullAssingment() && !isSameTypes()) {
			throw new LangException(LangError.E31, left.resultType() + " = "
					+ right.resultType());
		}
	}

	private boolean isVar(Statement left) {
		return left instanceof VarStatement;
	}

	private boolean isNullAssingment() {
		return right instanceof NullStatement;
	}

}
