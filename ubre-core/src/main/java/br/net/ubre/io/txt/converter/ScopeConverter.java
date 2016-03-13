package br.net.ubre.io.txt.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.net.ubre.exception.CTDException;
import br.net.ubre.framework.Context;
import br.net.ubre.framework.ContextElement;
import br.net.ubre.framework.Metadata;
import br.net.ubre.framework.Scope;
import br.net.ubre.io.txt.Line;
import br.net.ubre.io.txt.LineType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 27/05/2015
 * 
 */
public class ScopeConverter extends LineConverter {

	@Override
	public Object asObject(Context context, Line line) {
		Map<String, String> map = getPipeMap(line.getValues());
		String label = map.get(FIELD_LABEL);
		Date begin = parseDate(map.get(FIELD_BEGIN));
		Date end = parseDate(map.get(FIELD_END));
		String tags = map.get(FIELD_TAGS);
		String parentId = map.get(FIELD_PARENT);
		List<Metadata> metas = new ArrayList<Metadata>();
		List<Scope> scopes = new ArrayList<Scope>();
		List<String> varTokens = new ArrayList<String>();
		if (parentId != null) {
			Scope parentScope = getParentScope(context, parentId);
			metas.addAll(parentScope.getMetadatas());
			varTokens.addAll(parentScope.getVarTokens());
			scopes.addAll(parentScope.getScopes());
		}
		metas.addAll(getMetadatas(context, line));
		varTokens.addAll(getVarTokens(context, line));
		scopes.addAll(getScopes(context, line));
		Scope scope = new Scope(line.getId(), label, metas, varTokens, scopes,
				begin, end, tags);
		return scope;
	}

	private Scope getParentScope(Context context, String parentId) {
		for (Scope scope : context.getScopes()) {
			if (scope.getId().equals(parentId)) {
				return scope;
			}
		}
		throw new CTDException("Escopo parent não encontrado:" + parentId);
	}

	private List<Scope> getScopes(Context context, Line line) {
		List<Scope> scopes = new ArrayList<Scope>();
		Map<String, Scope> scopeMap = new HashMap<String, Scope>();
		for (Scope scope : context.getScopes()) {
			scopeMap.put(scope.getId(), scope);
		}
		Line scopeLine = line.getFirstChildByType(LineType.SCOPE_LISTS);
		if (scopeLine.isValuesEmpty()) {
			return scopes;
		}
		Scope scope;
		String[] arrayScope = getPipeArray(scopeLine.getValues());
		for (String scopeId : arrayScope) {
			scope = scopeMap.get(scopeId);
			if (scope == null) {
				throw new CTDException("Escopo não encontrado:" + scopeId
						+ "\n" + scopeLine);
			}
			scopes.add(scope);
		}
		return scopes;
	}

	private List<String> getVarTokens(Context context, Line line) {
		Line varTokensLine = line.getFirstChildByType(LineType.SCOPE_VARIABLES);
		if (varTokensLine != null) {
			List<String> varTokens = new ArrayList<String>();
			String tokens = varTokensLine.getValues();
			if (tokens != null) {
				String[] array = getPipeArray(tokens);
				for (String token : array) {
					varTokens.add(token);
				}
			}
			return varTokens;
		}
		return context.getDataMap().getVarTokens();
	}

	private List<Metadata> getMetadatas(Context context, Line line) {
		Map<String, Metadata> metaMap = new HashMap<String, Metadata>();
		for (Metadata metadata : context.getMetadatas()) {
			metaMap.put(metadata.getId(), metadata);
		}
		Line metasLine = line.getFirstChildByType(LineType.SCOPE_FIELDS);
		if (metasLine == null) {
			throw new CTDException("[Scope] Linha de metadados não encontrada.");
		}
		String[] arrayMeta = getPipeArray(metasLine.getValues());
		Metadata metadata;
		List<Metadata> metas = new ArrayList<Metadata>();
		for (String metaId : arrayMeta) {
			metadata = metaMap.get(metaId);
			if (metadata == null) {
				throw new CTDException("Metadado não encontrado:" + metaId
						+ "\n" + metasLine);
			}
			metas.add(metadata);
		}
		return metas;
	}

	@Override
	public Line asLine(ContextElement parent, Object object) {
		Scope scope = (Scope) object;
		Map<String, String> map = new HashMap<String, String>();
		map.put(FIELD_LABEL, scope.getLabel());
		map.put(FIELD_BEGIN, asString(scope.getBegin()));
		map.put(FIELD_END, asString(scope.getEnd()));
		map.put(FIELD_TAGS, scope.getTags());
		StringBuilder sb = new StringBuilder();
		setValue(map, FIELD_LABEL, sb);
		setValue(map, FIELD_BEGIN, sb);
		setValue(map, FIELD_END, sb);
		setLastValue(map, FIELD_TAGS, sb);
		Line line = new Line(LineType.SCOPE.getType(), scope.getId(), null,
				sb.toString());
		line.addChild(getChildMetas(scope));
		return line;
	}

	private Line getChildMetas(Scope scope) {
		StringBuilder sb = new StringBuilder();
		for (Metadata metadata : scope.getMetadatas()) {
			sb.append(metadata.getId());
			sb.append(SEP_VALUE);
		}
		sb.deleteCharAt(sb.length() - 1); // tira o último pipe
		Line childMetas = new Line(LineType.SCOPE.getType(), scope.getId(),
				LineType.SCOPE_FIELDS.getItem(), sb.toString());
		return childMetas;
	}

}
