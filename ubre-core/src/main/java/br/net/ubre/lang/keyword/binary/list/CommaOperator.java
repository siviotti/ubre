package br.net.ubre.lang.keyword.binary.list;

import java.util.ArrayList;
import java.util.List;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.data.TupleStatement;
import br.net.ubre.lang.keyword.binary.BinaryKeyword;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 26/03/2015
 * 
 */
public class CommaOperator extends BinaryKeyword {

	public CommaOperator(String token) {
		super(token);
	}

	public Statement perform(DataContainer container) {
		TupleStatement tuple;
		if (left.resultType().equals(StatementType.TUPLE)) {
			tuple = (TupleStatement) left.perform(container);
			tuple.add(right);
		} else {
			List<Statement> list = new ArrayList<Statement>();
			list.add(left);
			list.add(right);
			tuple = new TupleStatement(list);
		}
		return tuple;

	}

	public StatementType leftType() {
		return StatementType.OBJECT;
	}

	public StatementType rightType() {
		return StatementType.OBJECT;
	}

	public StatementType resultType() {
		return StatementType.TUPLE;
	}

	@Override
	public int precedence() {
		return 16;
	}

	/**
	 * Retorna os elementos separados pela vírgula no caso de mais de dois
	 * elementos. Quando há mais de dois elementos a vírgula linc um à esquerda
	 * e uma outra vírgula com os outros elementos à direita num esquema de
	 * árvore binária. Este método percorre toda a árvore e monta a lista que
	 * foi definida originalmente na sintaxe.
	 * 
	 * <P>
	 * (a,b,c,d,e) torna-se:
	 * 
	 * <pre>
	 *         ,
	 *       a   ,
	 *         b   ,
	 *           c   ,
	 *             d  e
	 * </pre>
	 * 
	 * @return Uma lista com os elementos separados pela vírgula inclusive os
	 *         separados por vírgulas aninhadas na árvore binária.
	 */
	public void loadList(List<Statement> list) {
		if (left instanceof CommaOperator){
			((CommaOperator)left).loadList(list);
		} else {
			list.add(left);
		}
		list.add(right);
	}

	@Override
	public String asToken() {
		return "'"+super.asToken() + "'";
	}

}
