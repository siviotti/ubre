package br.gov.serpro.ubre.lang.compile;

import static br.gov.serpro.ubre.lang.LangError.E10;
import static br.gov.serpro.ubre.lang.statement.StatementType.BOOLEAN;

import java.text.ParseException;
import java.util.List;

import br.gov.serpro.ubre.data.map.DataMap;
import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.lang.LangException;
import br.gov.serpro.ubre.lang.Syntax;
import br.gov.serpro.ubre.lang.expression.BooleanExpression;
import br.gov.serpro.ubre.lang.expression.Expression;
import br.gov.serpro.ubre.lang.keyword.parenthesis.ParenthesisOperator;
import br.gov.serpro.ubre.lang.parse.ExpParser;
import br.gov.serpro.ubre.lang.parse.StringMap;
import br.gov.serpro.ubre.lang.statement.Statement;

/**
 * Compilador de expressões para árvobres binárias de execução.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 * @see ExpParser
 * @see Linker
 */
public class ExpCompiler {

	private static final String LINKING = "[ECompiler][linking]";
	private static final String CHECKING = "[ECompiler][checking]";
	private static final String PARSING = "[ECompiler][parsing]";
	private ExpParser parser;
	private Checker checker;
	private Linker linker;
	private List<Statement> statementList;

	public ExpCompiler(Syntax syntax, StringMap stringMap, DataMap dataMap) {
		super();
		parser = new ExpParser(syntax, stringMap, dataMap);
		checker = new Checker(syntax);
		linker = new Linker();
	}

	/**
	 * Comipla uma expressão textual para uma instância de
	 * <code>Expression</code>.
	 * 
	 * @param source
	 *            O código fonte da expressão a ser compilada.
	 * @return A instância de expressão compilada.
	 */
	public Expression compile(String source) {
		// Passo 1 (Parsing) Transforma texto em objetos do tipo Statement
		try {
			statementList = parser.parse(source);
		} catch (ParseException e) {
			System.err.println(PARSING + source);
			throw new LangException(E10, e);
		} catch (CTDException ctde) {
			System.err.println(PARSING + source);
			throw new LangException(E10, ctde);
		}
		// Passo 2 (Checking) Valida gramática e semântica
		try {
			checker.check(statementList);
		} catch (CTDException ctde) {
			System.err.println(CHECKING + statementList + debugList());
			throw ctde;
		}
		// Passo 3 (Linking) Liga os elementos em uma árvore binária
		try {
			Statement root = linker.link(statementList);
			if (root.isLiteral()){
				root.result(null);// não precisa de DataContainer
			}
			if (root.resultType().equals(BOOLEAN)) {
				return new BooleanExpression(source, root);
			} else {
				return new Expression(source, root);
			}
		} catch (CTDException ctde) {
			System.err.println(LINKING + statementList + debugList());
			throw ctde;
		}
	}

	protected String debugList() {
		StringBuilder sb = new StringBuilder();
		for (Statement statement : statementList) {
			sb.append("\n");
			sb.append(statement.asToken());
			sb.append(" (");
			sb.append(statement.getClass().getSimpleName());
			sb.append(":");
			if (statement instanceof ParenthesisOperator) {
				sb.append("Parenthesis");
			} else {
				try {
					sb.append(statement.resultType());
				} catch (Exception e) {
					sb.append("<EMPTY>");
				}
			}
			sb.append(")");

		}
		return sb.toString();
	}

	// ********** GET / SET **********

	/**
	 * @return the parser
	 */
	public ExpParser getParser() {
		return parser;
	}

	/**
	 * @param parser
	 *            the parser to set
	 */
	public void setParser(ExpParser parser) {
		this.parser = parser;
	}

	/**
	 * A última ista de Statements usada no processo de compilação.
	 * 
	 * @return the list
	 */
	public List<Statement> getStatementList() {
		return statementList;
	}

}
