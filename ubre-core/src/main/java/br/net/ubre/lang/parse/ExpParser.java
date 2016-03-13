package br.net.ubre.lang.parse;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.net.ubre.data.map.DataMap;
import br.net.ubre.data.var.Datex;
import br.net.ubre.data.var.ValueType;
import br.net.ubre.exception.CTDException;
import br.net.ubre.lang.LangError;
import br.net.ubre.lang.LangException;
import br.net.ubre.lang.Syntax;
import br.net.ubre.lang.data.DataStatement;
import br.net.ubre.lang.data.FieldStatement;
import br.net.ubre.lang.data.VarStatement;
import br.net.ubre.lang.data.literal.DateStatement;
import br.net.ubre.lang.data.literal.DecimalStatement;
import br.net.ubre.lang.data.literal.IntegerStatement;
import br.net.ubre.lang.data.literal.StringStatement;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

/**
 * Classe que faz o parsing de um texto para uma Expressão.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class ExpParser {

	private static final String EPARSER_FORBIDDEN_TOKEN = "Não é permitido usar esta palavra reservada em uma expressão:";

	private Syntax syntax;
	private StringMap stringMap;
	private Splitter splitter;
	private Optimizer optimizer;
	private DataMap dataMap;

	public ExpParser(Syntax syntax, StringMap stringMap, DataMap dataMap) {
		super();
		this.syntax = syntax;
		this.stringMap = stringMap;
		this.dataMap = dataMap;
		splitter = new Splitter(syntax.getOperators(), stringMap);
		optimizer = new Optimizer();
	}

	/**
	 * Transforma uma String com uam expressão em uma lista de Statements
	 * sequenciais da mesma forma que na expressão original, incluindo blocos e
	 * separadores.
	 * 
	 * @param source
	 *            A String com a expressão original.
	 * @return Uma lista de Statements.
	 * @throws ParseException
	 */
	public List<Statement> parse(String source) throws ParseException {
		// Passo 2 - Separar os tokens
		List<String> step2 = splitter.split(source);
		// Passo 3 - Criar os Statemens a partir dos tokens
		List<Statement> statements = new ArrayList<Statement>();
		for (String token : step2) {
			statements.add(createStatement(token));
		}
		// Passo 4 - Otimizar a lista
		return optimizer.optmize(statements);
	}

	public Statement createStatement(String token) throws ParseException {
		// Keywords proibidas (TOKENS DE sCRIPT)
		if (syntax.getExternalKeywords().contains(token)) {
			throw new LangException(LangError.E10, EPARSER_FORBIDDEN_TOKEN
					+ token);
		}
		// Testa se o token é uma Keyword
		if (syntax.isKeyword(token)) {
			return createKeywordStatement(token);
		}
		// Tenta criar um Statement literal
		Statement statement = createLiteralStatement(token);
		if (statement != null) {
			return statement; // É literal
		}
		// Se não for literal tenta criar um Var baseado no DataMap
		// Se não existir no DataMap vai gerar uma exceção
		return createDataStatement(token);
	}

	public Statement createKeywordStatement(String token) {
		if (syntax.isLiteralKeyword(token)) {
			return syntax.getLiteralInstance(token);
		}
		Class<? extends Statement> clazz = syntax.getKeywordClass(token);
		try {
			Constructor<?>[] c = clazz.getConstructors();
			Object[] valores = { token };
			return (Statement) c[0].newInstance(valores);
		} catch (InstantiationException e) {
			throw new CTDException(e);
		} catch (IllegalAccessException e) {
			throw new CTDException(e);
		} catch (IllegalArgumentException e) {
			throw new CTDException(e);
		} catch (InvocationTargetException e) {
			throw new CTDException(e);
		}
	}

	/**
	 * Cria um Statement a partir de um valor literal String, Integer, Decimal,
	 * Date Boolean ou List.
	 * 
	 * @param token
	 *            O token que contém o literal que pode ser parseado para um
	 *            Statement que estende <code>LiteralStatement</code>.
	 * @return A instância de <code>LiteralStatement</code> ou null se não foi
	 *         possível criar o Statement.
	 * @throws ParseException
	 */
	public DataStatement createLiteralStatement(String token)
			throws ParseException {
		// Const
		if (dataMap.getVarPattern().isStringToken(token)) {
			return new StringStatement(stringMap.get(token));
		}
		if (dataMap.getVarPattern().isIntegerToken(token)) {
			return new IntegerStatement(token);
		}
		if (dataMap.getVarPattern().isDecimalToken(token)) {
			return new DecimalStatement(token);
		}
		if (dataMap.getVarPattern().isDateToken(token)) {
			return new DateStatement(getDate(token));
		}
		return null;
	}

	private Datex getDate(String token) {
		return new Datex(token.substring(1, token.length() - 1));
	}

	/**
	 * Cria e retorna um Statement Const, Var ou Field cujo token está presente
	 * no DataMap. Se não estiver no DataMap gerará uma exceção.
	 * 
	 * @param token
	 *            O token presente no DataMap.
	 * @return A instância de VarStatement ou FieldStatement, dependendo do que
	 *         foi definido no dataMap.
	 */
	public DataStatement createDataStatement(String token) {
		ValueType valueType;
		valueType = dataMap.getType(token);
		StatementType statementType = StatementType.getFromValueType(valueType);
		if (dataMap.isField(token)) {
			return new FieldStatement(token, statementType);
		}
		if (dataMap.isSimpleVar(token)) {
			return new VarStatement(token, statementType);
		}
		return new DataStatement(token, statementType);
	}

	// ********** GET / SET **********
	/**
	 * @return the syntax
	 */
	public Syntax getSyntax() {
		return syntax;
	}

	/**
	 * @return the stringMap
	 */
	public StringMap getStringMap() {
		return stringMap;
	}

	public DataMap getDataMap() {
		return dataMap;
	}

	public Splitter getSplitter() {
		return splitter;
	}

}
