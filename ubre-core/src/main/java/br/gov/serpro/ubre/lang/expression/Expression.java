package br.gov.serpro.ubre.lang.expression;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * Representa uma expressão.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class Expression {

	public static final String EXPRESSION_ROOT_MUST_BE_BOOLEAN = "O tipo do elemento raiz (root) deve ser booleano!";

	private String source;
	private Statement root;

	public Expression(String source, Statement root) {
		super();
		this.source = source;
		this.root = root;
	}

	/**
	 * Avalia e resolve a expressão com base na máquina passada por parâmetro
	 * que contém os dados variáveis para serem usados no lugar das variáveis.
	 * 
	 * @param container
	 *            A sessão com a área de memória que contém as variáveis.
	 * @return O resultado da execução da expressão.
	 */
	public Object eval(DataContainer container) {
		return root.result(container);
	}

	/**
	 * Informa se a expressão só trabalha com literais (Statements que
	 * representam valores eplícitos) ou se contém alguma variável cujo valor
	 * não se pode saber em tempo de parsing.
	 * 
	 * @return <code>true</code> se todos os elementos (Statements) da expressão
	 *         forem literais ou <code>false</code> se ao menos um elemento
	 *         (Statement) for uma variável ou campo.
	 */
	public boolean isLiteral() {
		return root.isLiteral();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Expression)) {
			return false;
		}
		Expression e = (Expression) obj;
		return source.equals(e.source);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return source.hashCode();
	}

	// ********** GET / SET **********
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @return the root
	 */
	public Statement getRoot() {
		return root;
	}

}
