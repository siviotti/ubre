package br.gov.serpro.ubre.io.txt.converter.rule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.gov.serpro.ubre.behave.Executable;
import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.framework.ContextElement;
import br.gov.serpro.ubre.framework.Scope;
import br.gov.serpro.ubre.framework.rule.Rule;
import br.gov.serpro.ubre.framework.rule.RuleType;
import br.gov.serpro.ubre.io.txt.Line;
import br.gov.serpro.ubre.io.txt.LineType;
import br.gov.serpro.ubre.io.txt.converter.LineConverter;
import br.gov.serpro.ubre.slang.script.Script;

/**
 * Conversor de regras
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 01/04/2015
 * 
 */
public class RuleConverter extends LineConverter {

	private static final String SCOPE = "scope";
	public static final String FIELD_TYPE = "type";

	@Override
	public Rule asObject(Context context, Line line) {
		Map<String, String> map = getPipeMap(line.getValues());
		String label = map.get(FIELD_LABEL);
		Date begin = parseDate(map.get(FIELD_BEGIN));
		Date end = parseDate(map.get(FIELD_END));
		String tags = map.get(FIELD_TAGS);
		String scopeId = map.get(SCOPE);
		String typeString = map.get(FIELD_TYPE);
		RuleType ruleType = RuleType.get(typeString);
		Scope scope = context.getScope(scopeId);
		List<String> sourceLines = new ArrayList<String>();
		for (Line child : line.getChildrenByType(LineType.CODE)) {
			sourceLines.add(child.getValues());
		}
		Rule rule = new Rule(line.getId(), label, begin, end, tags, ruleType,
				scope, sourceLines);
		return rule;
	}

	@Override
	public Line asLine(ContextElement parent, Object object) {
		Rule rule = (Rule) object;
		Map<String, String> map = new HashMap<String, String>();
		map.put(FIELD_LABEL, rule.getLabel());
		map.put(FIELD_BEGIN, asString(rule.getBegin()));
		map.put(FIELD_END, asString(rule.getEnd()));
		map.put(FIELD_TAGS, rule.getTags());
		StringBuilder sb = new StringBuilder();
		setValue(map, FIELD_LABEL, sb);
		setValue(map, FIELD_BEGIN, sb);
		setValue(map, FIELD_END, sb);
		setLastValue(map, FIELD_TAGS, sb);
		Line line = new Line(LineType.RULE.getType(), rule.getId(), null,
				sb.toString());
		return line;
	}

	private void extractChildLines(Rule rule, Line line) {

	}

}
