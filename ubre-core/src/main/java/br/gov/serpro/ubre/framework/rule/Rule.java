package br.gov.serpro.ubre.framework.rule;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.gov.serpro.ubre.behave.Callable;
import br.gov.serpro.ubre.behave.CallableType;
import br.gov.serpro.ubre.behave.Executable;
import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.framework.ContextElement;
import br.gov.serpro.ubre.framework.Scope;
import br.gov.serpro.ubre.slang.Slang;
import br.gov.serpro.ubre.util.Reflect;

/**
 * Representa uma regra de negócio executada dentro de um processo e associada à
 * uma fase.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015 - inicial estendendo CodeElement
 * @version 08/10/2015 - Body e linhas de código
 * 
 */
public class Rule extends ContextElement implements Callable {

	private static final String RULE_TYPE_MISSING = "Regra sem tipo! Use um destes:type=";
	private static final String RULE_CLASS_MISSING = "Regra de códogo estática sem classe definida:";
	private Scope scope;
	private RuleType type; // O tipo define o tipo de Body
	private Executable body; // SCRIPT - STATIC (Code) - DYNAMIC (Code)
	private List<String> sourceLines;

	public Rule(String id, String label, Date begin, Date end, String tags,
			RuleType type, Scope scope, List<String> sourceLines) {
		super(id, label, begin, end, tags);
		this.scope = scope;
		this.sourceLines = sourceLines;
		this.type = type;
	}

	public boolean isBuilt() {
		return body != null;
	}

	public void build(Slang slang) {
		if (RuleType.SCRIPT.equals(type)) {
			body = slang.build(sourceLines);
		} else if (RuleType.STATIC.equals(type)) {
			if (sourceLines.isEmpty()) {
				throw new CTDException(RULE_CLASS_MISSING + getId());
			}
			String className = sourceLines.get(0);
			body = Reflect.createExecutable(className);
		} else if (RuleType.DYNAMIC.equals(type)) {
			body = Reflect.findRuleBody(getId());
		} else {
			throw new CTDException(RULE_TYPE_MISSING
					+ Arrays.toString(RuleType.values()));
		}
	}

	public void execute(DataContainer container) {
		body.execute(container);
	}

	public String getToken() {
		return getId();
	}

	public Object toDebug() {
		StringBuilder sb = new StringBuilder();
		sb.append("  RULE " + getId() + " " + getLabel());
		// sb.append(body.toDebug());
		return sb.toString();
	}

	public Scope getScope() {
		return scope;
	}

	public CallableType getType() {
		return CallableType.RULE;
	}

}
