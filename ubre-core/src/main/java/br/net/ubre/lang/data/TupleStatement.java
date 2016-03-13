package br.net.ubre.lang.data;

import java.util.ArrayList;
import java.util.List;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.internal.Const;
import br.net.ubre.lang.data.literal.LiteralStatement;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Statement específico pata tupla.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 27/03/2015
 * 
 */
public class TupleStatement extends LiteralStatement {

	private static String asString(List<Statement> list) {
		StringBuilder sb = new StringBuilder();
		sb.append(Const.OPEN_PARENTHESIS_CHAR);
		for (Statement statement : list) {
			sb.append(statement.asToken());
			sb.append(Const.COMMA_OPERATOR);
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(Const.CLOSE_PARENTHESIS_CHAR);
		return sb.toString();
	}

	public TupleStatement(List<Statement> list) {
		super(asString(list), StatementType.TUPLE, list);
	}

	public Statement get(int index) {
		return (Statement) ((List<Statement>) value).get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.gov.serpro.ctd.lang.data.ConstStatement#result(br.gov.serpro.ctd.api
	 * .CTDSession)
	 */
	@Override
	public Object result(DataContainer container) {
		List list = new ArrayList();
		for (Statement obj : (List<Statement>) value) {
			list.add(obj.result(container));
		}
		return list;
	}

	public void add(Statement statement) {
		((List<Statement>) value).add(statement);
	}

	public Integer size() {
		return ((List<Statement>) value).size();
	}

	public List<Statement> subList(int p1, int p2) {
		return ((List<Statement>) value).subList(p1, p2);
	}

}
