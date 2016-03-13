package br.gov.serpro.ubre.lang.parse;

import static br.gov.serpro.ubre.lang.statement.StatementType.INTEGER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.gov.serpro.ubre.lang.data.literal.DecimalStatement;
import br.gov.serpro.ubre.lang.data.literal.LiteralStatement;
import br.gov.serpro.ubre.lang.keyword.KeywordStatement;
import br.gov.serpro.ubre.lang.keyword.binary.dot.DotOperator;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

/**
 * Classe que analisa a lista de elementos parseados fazer uma otimização. O
 * Optmizer é um paser de segundo momento.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 07/05/2015
 * 
 */
public class Optimizer {

	private static final String OPTIMIZER_INCORRET_USE_OF_DOT = "Uso incorreto do operador '.':";
	private static final String OPTIMIZER_PROPERTY_NOT_FOUND = "Propriedade desconhecida:";

	public List<Statement> optmize(List<Statement> list) {
		List<Statement> newList = new ArrayList<Statement>();
		KeywordStatement keyword;
		Statement left;
		Statement right;
		for (int i = 0; i < list.size(); i++) {
			newList.add(list.get(i));
			if (!(list.get(i) instanceof KeywordStatement)) {
				continue;
			}
			keyword = (KeywordStatement) list.get(i);
			left = (i > 0) ? list.get(i - 1) : null;
			right = (i < list.size() - 1) ? right = list.get(i + 1) : null;
			// left.right
			if (isDecimalDot(keyword, left, right)) {
				createDecimalDot(newList, keyword, left, right);
				i++; // pula right
				continue;
			}
		}
		return newList;
	}

	private void optimizeDot(List<Statement> newList, KeywordStatement keyword,
			Statement left, Statement right) {
		// TODO Auto-generated method stub

	}

	private void createDecimalDot(List<Statement> newList,
			KeywordStatement keyword, Statement left, Statement right) {
		newList.remove(newList.size() - 1); // remove .
		newList.remove(newList.size() - 1); // remove left
		Statement newDot = new DecimalStatement(left.asToken()
				+ keyword.asToken() + right.asToken());
		newList.add(newDot);
	}

	/*
	 * private void createPropertyDot(List<Statement> newList, KeywordStatement
	 * keyword, Statement left, Statement right) { newList.remove(newList.size()
	 * - 1); // remove . newList.remove(newList.size() - 1); // remove left
	 * Property property = Property.get(right.asToken()); if (property == null)
	 * { throw new LangException(E10, OPTIMIZER_PROPERTY_NOT_FOUND +
	 * right.asToken()); } FieldPropertyStatement newDot = new
	 * FieldPropertyStatement( left.asToken(), property); newList.add(newDot); }
	 * private boolean isPropertyDot(KeywordStatement keyword, Statement left,
	 * Statement right) { return keyword instanceof DotOperator && right
	 * instanceof FieldPropertyStatement; }
	 */
	private boolean isDecimalDot(KeywordStatement keyword, Statement left,
			Statement right) {
		return keyword instanceof DotOperator
				&& left instanceof LiteralStatement
				&& right instanceof LiteralStatement
				&& left.resultType().equals(INTEGER)
				&& right.resultType().equals(INTEGER);
	}
}
