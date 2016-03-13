package br.net.ubre.data.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.net.ubre.data.var.InternalVar;
import br.net.ubre.data.var.ValueType;
import br.net.ubre.data.var.Var;
import br.net.ubre.data.var.VarFactory;
import br.net.ubre.exception.CTDException;
import br.net.ubre.framework.Metadata;
import br.net.ubre.util.GenericFreezable;

/**
 * MAPA DE DADOS com todos os possíveis dados e seus tokens presentes em um
 * DataContainer. Uma variável, constante, mensagem ou campo só pode existir em
 * um DataContainer se estiverem mapeadas em um DataMap.Esta classe é usada para
 * fazer parsing de expressões e linhas de script.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 14/10/2015
 */
public class DataMap extends GenericFreezable {

	public static final String DATAMAP_FIELD_CANNOT_BE_CONST = "Campo não pode ser constante:";
	public static final String DATAMAP_DUPLICATE_TOKEN = "Token já mapeado (duplicado) anteriormente:";
	public static final String DATAMAP_INVALID_TOKEN_CAUSE = ". Motivo:";
	public static final String DATAMAP_INVALID_TOKEN = "Token inválido:";
	public static final String DATAMAP_UNKNOWN_TOKEN = "Termo (token) desconhecido:'";
	public static final String DATAMAP_IN_LIST = "' na lista ";

	// Mapa geral de tokens
	private Map<String, Var> vars = new HashMap<String, Var>();
	// Constantes e Mensagens
	private List<String> constants = new ArrayList<String>();
	// Variáveis e Campos
	private List<String> variables = new ArrayList<String>();
	// Somente variáveis
	private List<String> varTokens = new ArrayList<String>();
	// Somente campos
	private List<String> fieldTokens = new ArrayList<String>();
	private List<Metadata> metadatas = new ArrayList<Metadata>();
	// Lista de elementos mutantes (que usam format com ${xxx}
	private List<String> mutants = new ArrayList<String>();
	private VarPattern varPattern = new VarPattern();
	private VarFactory varFactory = new VarFactory();

	public void add(Var var, boolean isConstant) {
		testFrozen();
		String token = var.getToken();
		if (var instanceof Metadata) {
			token = varPattern.getFieldTag() + token;
			metadatas.add((Metadata) var);
		}
		validateToken(var, token, isConstant);
		InternalVar internalVar = varFactory.createInternal(var.getToken(),
				var.getType(), var.getValue());
		vars.put(token, internalVar);
		if (isConstant) {
			constants.add(token);
		} else {
			variables.add(token);
			if (varPattern.isField(token)) {
				fieldTokens.add(token);
			} else {
				varTokens.add(token);
			}
		}
	}

	private void validateToken(Var data, String token, boolean isConstant) {
		String msg = null;
		if (varPattern.isField(token)) {
			if (isConstant) {
				throw new CTDException(DATAMAP_FIELD_CANNOT_BE_CONST + token);
			}
			msg = varPattern.validateFieldToken(token);
		} else {
			msg = varPattern.validateVarToken(token);
		}
		if (msg != null) {
			throw new CTDException(DATAMAP_INVALID_TOKEN + data.getToken()
					+ DATAMAP_INVALID_TOKEN_CAUSE + msg);

		}
		if (vars.containsKey(token)) {
			throw new CTDException(DATAMAP_DUPLICATE_TOKEN + data.getToken());
		}
	}

	/**
	 * Informa se o DataMap contén um certo Dado a partir de seu token.
	 * 
	 * @param token
	 *            O token procurado.
	 * @return <code>true</code> se o Mapa contém ou <code>false</code> se não
	 *         contém.
	 */
	public boolean contains(String token) {
		return vars.containsKey(token);
	}

	/**
	 * Obtém o tipo a partir de um token.
	 * 
	 * @param token
	 *            O token identificador.
	 * @return O tipo referente ao token informado.
	 */
	public ValueType getType(String token) {
		if (!vars.containsKey(token)) {
			throw new CTDException(DATAMAP_UNKNOWN_TOKEN + token);
		}
		return vars.get(token).getType();
	}

	/**
	 * Obtém o valor a partir de um token.
	 * 
	 * @param token
	 *            O token identificador.
	 * @return O valor referente ao token informado.
	 */
	public Object getValue(String token) {
		if (!vars.containsKey(token)) {
			throw new CTDException(DATAMAP_UNKNOWN_TOKEN + token);
		}
		return vars.get(token).getValue();
	}

	/**
	 * Conjunto com todos os tokens presentes no DapaMap (cópia).
	 * 
	 * @return Um Set com oso tokens.
	 */
	public List<String> getTokens() {
		return new ArrayList<String>(vars.keySet());
	}

	/**
	 * Conjunto com todos os tokens de CONSTANTES presentes no DapaMap (cópia).
	 * 
	 * @return Um Set com oso tokens.
	 */
	public List<String> getConstantTokens() {
		return new ArrayList<String>(constants);
	}

	/**
	 * Conjunto com todos os tokens de VARIÁVEIS presentes no DapaMap (cópia).
	 * 
	 * @return Um Set com oso tokens.
	 */
	public List<String> getVariableTokenSet() {
		return new ArrayList<String>(variables);
	}

	public boolean isConstant(String token) {
		return constants.contains(token);
	}

	public boolean isVariable(String token) {
		return variables.contains(token);
	}

	public boolean isSimpleVar(String token) {
		return varTokens.contains(token);
	}

	public boolean isField(String token) {
		return fieldTokens.contains(token);
	}

	public boolean isMutant(String token) {
		return mutants.contains(token);
	}

	/**
	 * Lista de tokens das variáveis que não são campos.
	 * 
	 * @return
	 */
	public List<String> getVarTokens() {
		return new ArrayList<String>(varTokens);
	}

	/**
	 * Lista de tokens dos campos.
	 * 
	 * @return
	 */
	public List<String> getFieldTokens() {
		return new ArrayList<String>(fieldTokens);
	}

	public String toDebug() {
		StringBuilder sb = new StringBuilder();
		sb.append("*** Datamap ***");
		for (String token : vars.keySet()) {
			sb.append("\n");
			sb.append(token);
			sb.append(":");
			sb.append(getType(token));
			sb.append("=");
			sb.append(getValue(token));
			Object value = getValue(token);
			if (value != null) {
				sb.append(" " + value.getClass());
			}
		}
		return sb.toString();
	}

	public List<Metadata> getMetadatas() {
		return new ArrayList<Metadata>(metadatas);
	}

	public VarPattern getVarPattern() {
		return varPattern;
	}

	public void setVarPattern(VarPattern tokenPattern) {
		testFrozen();
		this.varPattern = tokenPattern;
	}

}
