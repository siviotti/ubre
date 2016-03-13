package br.gov.serpro.ubre.io.txt.converter;

import java.util.ArrayList;
import java.util.List;

import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.framework.ContextElement;
import br.gov.serpro.ubre.framework.process.Event;
import br.gov.serpro.ubre.framework.process.Process;
import br.gov.serpro.ubre.framework.rule.Rule;
import br.gov.serpro.ubre.io.txt.Line;
import br.gov.serpro.ubre.io.txt.LineType;
import br.gov.serpro.ubre.io.txt.TxtException;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 02/04/2015
 * 
 */
public class EventConverter extends LineConverter {

	@Override
	public Event asObject(Context context, Line line) {
		String id = line.getId() + "_" + line.getItem();
		String rulesStr = line.getValues();
		String[] array = rulesStr.split(Event.SEP_RULE_ID_REGEX);
		List<Rule> rules = new ArrayList<Rule>();
		Rule rule;
		for (String ruleStr : array) {
			rule = getRule(context, ruleStr);
			if (rule == null) {
				throw new TxtException(line.asNumber()
						+ " Regra não encontrada:" + ruleStr);
			}
			rules.add(rule);
		}
		return new Event(id, rules);
	}

	private Rule getRule(Context context, String ruleId) {
		for (Rule rule : context.getRules()) {
			if (rule.getId().equals(ruleId)) {
				return rule;
			}
		}
		return null;
	}

	@Override
	public Line asLine(ContextElement parent, Object object) {
		Event script = (Event) object;
		if (!(parent instanceof Process)) {
			throw new CTDException("Parent deve ser um Process");
		}
		return new Line(LineType.PROCESS.getType(), parent.getId(),
				script.getId(), script.getRuleIds());
	}

}
