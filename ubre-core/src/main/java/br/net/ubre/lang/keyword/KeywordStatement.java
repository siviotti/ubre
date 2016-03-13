package br.net.ubre.lang.keyword;

import static br.net.ubre.lang.statement.StatementType.VOID;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.exception.CTDException;
import br.net.ubre.lang.LangError;
import br.net.ubre.lang.LangException;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.result.Result;
import br.net.ubre.lang.statement.PointerStatement;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Representa uma palavra reservada. As palavras reservadas utilizam o método
 * perform para produzir algum efeito ao contrário dos valores que já armazenam
 * algo.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public abstract class KeywordStatement implements Statement {

	public static final String LEFT = "esquerda";
	public static final String RIGHT = "direita";

	protected String token;
	protected Statement left;
	protected Statement right;
	protected Result safeResult;

	public abstract int precedence();

	public KeywordStatement(String token) {
		super();
		this.token = token;
	}

	public String asToken() {
		return token;
	}

	public Object result(DataContainer container) {
		return perform(container).result(container);
	}

	protected void checkType(String side, StatementType selfType,
			StatementType sideType) {
		if (!selfType.isComparableTo(sideType)
				&& !sideType.isComparableTo(selfType)) {
			StringBuilder sb = new StringBuilder();
			sb.append("O token ");
			sb.append(asToken());
			sb.append(" não aceita o tipo ");
			sb.append(sideType);
			sb.append(" à ");
			sb.append(side);
			sb.append(". Somente estes tipos podem ser utilizados:");
			sb.append(selfType.getMatchTypes());
			throw new LangException(LangError.E31, sb.toString());
		}
	}

	/**
	 * Linca o elemento a outros possíves dois elementos. Valida o Statement
	 * após o parsing. Avalia, por exemplo, se os elementos à esquerda e direita
	 * são de tipos válidos e se os requisitos mínimos estão sendo atendidos.
	 */
	public void link(Statement left, Statement right) {
		this.left = left;
		if (left != null && leftType().equals(StatementType.VOID)) {
			throw new CTDException(token + " não aceita membros à esquerda:"
					+ left);
		}
		this.right = right;
		if (right != null && rightType().equals(StatementType.VOID)) {
			throw new CTDException(token + " não aceita membros à direita:"
					+ right);
		}
	}

	/**
	 * Informe se os tipos do elemento da esquerda e o da direita são iguais.
	 * 
	 * @return <code>true</code> se os tipos são iguais ou <code>false</code> se
	 *         não são.
	 */
	protected boolean isSameTypes() {
		return left.resultType().equals(right.resultType());
	}

	/**
	 * Informa se ambos os tipos dos elementos lateirais (esquerda e direita)
	 * são de um determinado tipo passado por parâmetro.
	 * 
	 * @param type
	 *            O tipo que deve ser testado.
	 * @return <code>true</code> se ambos os tipos são iguais ao tipo passado ou
	 *         <code>false</code> se um dos tipos não for igual.
	 */
	protected boolean bothTypes(Statement leftTry, Statement rightTry,
			StatementType type) {
		return leftTry.resultType().equals(type)
				&& rightTry.resultType().equals(type);
	}

	/**
	 * Testa os dois valores para veficar se um deles é <code>null</code>.
	 * 
	 * @param leftResult
	 *            O valor do left.
	 * @param rightResult
	 *            O valor do right.
	 * @return <code>true</code> se um deles for nulo ou <code>false</code> se
	 *         nenhum deles for nulo.
	 */
	protected boolean isNull(Object leftResult, Object rightResult) {
		return leftResult == null || rightResult == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return asToken();
	}

	/**
	 * Determina se a palavra reservada usa um tatement à esquerda.
	 * 
	 * @return
	 */
	public boolean useLeftStatement() {
		return !leftType().equals(VOID);
	}

	public boolean useRightStatement() {
		return !rightType().equals(VOID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.gov.serpro.ctd.lang.Statement#asNode()
	 */
	public String asNode(String space) {
		// String leftNode = (left != null) ? left.asNode(space + ".") : "";
		// String rightNode = (right != null) ? right.asNode(space + ".") : "";
		// return leftNode + "\n" + space + asToken() + "\n" + rightNode;
		String type = resultType().name();
		if (space.startsWith(PointerStatement.POINTER_PREFIX)) {
			type = type + "**";
			space = space.substring(PointerStatement.POINTER_PREFIX.length());
		}
		String curve = null;
		if (space.endsWith("└")) {
			curve = "└";
		} else if (space.endsWith("┌")) {
			curve = "┌";
		} else {
			curve = "─";
		}
		String leftPrefix = " ";
		String rightPrefix = " ";
		space = space.substring(0, space.length() - 1);
		String s = "";
		if (left != null) {
			s = s + left.asNode(space + leftPrefix + "    " + "┌") + "\n";
		}
		s = s + space + curve + "─── " + asToken() + " " + type + "\n";
		if (right != null) {
			s = s + right.asNode(space + rightPrefix + "    " + "└");
		}
		return s;
	}

}
