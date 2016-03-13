package br.gov.serpro.ubre.data.container;

import java.util.List;
import java.util.Map;

import br.gov.serpro.ubre.data.field.Field;
import br.gov.serpro.ubre.data.field.FieldFactory;
import br.gov.serpro.ubre.data.map.DataMap;
import br.gov.serpro.ubre.data.var.StandardVar;
import br.gov.serpro.ubre.data.var.ValueType;
import br.gov.serpro.ubre.data.var.Var;
import br.gov.serpro.ubre.data.var.VarFactory;
import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.framework.Metadata;
import br.gov.serpro.ubre.framework.Scope;
import br.gov.serpro.ubre.util.FreezableMap;

/**
 * Implementação genérica de DataContainer.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 19/05/2015
 * 
 */
public class StandardDataContainer implements DataContainer {


	private final String id;
	private Scope scope;
	private DataMap dataMap;
	protected FreezableMap<String, Field> fields = new FreezableMap<String, Field>();
	protected FreezableMap<String, Var> vars = new FreezableMap<String, Var>();
	private DataContainer parent;

	public StandardDataContainer(String id, DataMap dataMap) {
		this(id, dataMap, null, null, null);
	}

	public StandardDataContainer(String id, DataMap dataMap, Scope scope,
			FieldFactory fieldFactory, DataContainer parent) {
		super();
		if (id == null) {
			throw new CTDException(DATA_CONTAINER_ID_CANNOT_BE_NULL);
		}
		this.id = id;
		if (dataMap == null) {
			throw new CTDException(DATA_CONTAINER_DATAMAP_CANNOT_BE_NULL);
		}
		this.dataMap = dataMap;
		this.scope = scope;
		this.parent = parent;
		createFields(fieldFactory);
		createVars();
	}

	private void createVars() {
		VarFactory varFactory = new VarFactory();
		List<String> varTokens;
		if (scope != null) {
			varTokens = scope.getVarTokens();
		} else {
			varTokens = dataMap.getVarTokens();
		}

		StandardVar var;
		for (String varToken : varTokens) {
			var = varFactory.create(varToken, dataMap.getType(varToken));
			var.setValue(dataMap.getValue(varToken));
			vars.put(varToken, var);
		}
	}

	private void createFields(FieldFactory fieldFactory) {
		List<Metadata> metadatas;
		if (scope != null) {
			metadatas = scope.getMetadatas();
		} else {
			metadatas = dataMap.getMetadatas();
		}
		Field field;
		for (Metadata metadata : metadatas) {
			field = fieldFactory.create(metadata);
			fields.put(field.getToken(), field);
		}
		fields.freeze();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public ValueType createVar(String token, ValueType valueType) {
		StandardVar value = new VarFactory().create(token, valueType);
		vars.put(token, value);
		return value.getType();
	}

	@Override
	public boolean contains(String token) {
		return dataMap.isConstant(token) || fields.containsKey(token)
				|| vars.containsKey(token)
				|| (parent != null && parent.contains(token));
	}

	@Override
	public boolean isNull(String token) {
		return getValue(token) == null;
	}

	@Override
	public Object getValue(String token) {
		// 1 - Constantes
		if (dataMap.isConstant(token)) {
			return dataMap.getValue(token);
		}
		// Prioridade 2 - campos
		if (fields.containsKey(token)) {
			return fields.get(token).getValue();
		}
		// Prioridade 3 - Variáveis
		if (vars.containsKey(token)) {
			return vars.get(token).getValue();
		}
		// Prioridade 4 - Parent
		if (parent != null && parent.contains(token)) {
			return parent.getValue(token);
		}
		throw new CTDException(DATA_CONTAINER_VAR_NOT_FOUND + token);
	}

	@Override
	public String getFormattedValue(String token) {
		String original = (String) getValue(token);
		return null;
	}

	@Override
	public ValueType getType(String token) {
		// 1 - Constantes
		if (dataMap.contains(token)) {
			return dataMap.getType(token);
		}
		// 2 - Campos
		if (fields.containsKey(token)) {
			return fields.get(token).getType();
		}
		// 3 - Variáveis da Sessão CTD
		if (vars.containsKey(token)) {
			return vars.get(token).getType();
		}
		// 4 - Parent
		if (parent != null && parent.contains(token)) {
			return parent.getType(token);
		}
		throw new CTDException(DATA_CONTAINER_VAR_NOT_FOUND + token);
	}

	@Override
	public void setValue(String token, Object data) {
		// 1 - Constante - somente leitura
		if (dataMap.isConstant(token)) {
			throw new CTDException(token + DATA_CONTAINER_CONST_IS_READ_ONLY);
		}
		// 2 - Campos
		if (fields.containsKey(token)) {
			fields.get(token).setValue(data);
			return;
		}
		// 3 - Variáveis da Sessão CTD
		if (vars.containsKey(token)) {
			vars.get(token).setValue(data);
			return;
		}
		// 4 - Parent
		if (parent != null && parent.contains(token)) {
			parent.setValue(token, data);
		}
		throw new CTDException(DATA_CONTAINER_VAR_NOT_FOUND + token);
	}

	// ********** Var **********
	public boolean isVar(String token) {
		return vars.containsKey(token);
	}

	public Map<String, Var> getVars() {
		return vars;
	}
	
	@Override
	public Var getVar(String token) {
		System.out.println(vars.values());
		if (!vars.containsKey(token)){
			throw new CTDException(DATA_CONTAINER_VAR_NOT_FOUND + token);
		}
		return vars.get(token);
	}


	// ********** Field **********

	@Override
	public boolean isField(String token) {
		return fields.containsKey(token);
	}

	@Override
	public Map<String, Field> getFields() {
		return fields;
	}

	@Override
	public Field getField(String fieldName) {
		Field field = fields.get(fieldName);
		if (field == null) {
			throw new CTDException(DATA_CONTAINER_FIELD_NOT_FOUND + fieldName);
		}
		return field;
	}

	// ********** ETC **********

	/**
	 * @return the scope
	 */
	public Scope getScope() {
		return scope;
	}

	public DataMap getDataMap() {
		return dataMap;
	}


}
