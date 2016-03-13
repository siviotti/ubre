package br.net.ubre.framework.process;

import java.util.ArrayList;
import java.util.List;

import br.net.ubre.behave.Callable;
import br.net.ubre.behave.CallableType;
import br.net.ubre.data.container.DataContainer;
import br.net.ubre.framework.IdLabelElement;
import br.net.ubre.framework.rule.Rule;
import br.net.ubre.slang.Slang;
import br.net.ubre.util.Debuggable;

/**
 * Representa a sequência de execução de regras dentro de um processo associado
 * à uma fase.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class Event extends IdLabelElement implements Callable, Debuggable {

	private static final String SUB_CONTAINER_ID = "$$";
	public static final char SEP_RULE_ID = '|';
	public static final String SEP_RULE_ID_REGEX = "\\" + SEP_RULE_ID;

	private List<Rule> rules;

	public Event(String id, List<Rule> rules) {
		super(id, "");
		this.rules = new ArrayList<Rule>(rules);
	}

	public void execute(DataContainer container) {
		for (Rule rule : rules) {
			executeRule(container, rule);
		}
	}

	public boolean isBuilt() {
		// TODO Auto-generated method stub
		return false;
	}

	public void build(Slang slang) {
		// TODO Auto-generated method stub

	}

	public String getToken() {
		return getId();
	}

	public CallableType getType() {
		return CallableType.EVENT;
	}

	private void executeRule(DataContainer container, Rule rule) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return asIdLabel();
	}

	public String getRuleIds() {
		StringBuilder sb = new StringBuilder();
		for (Rule rule : rules) {
			sb.append(rule.getId());
			sb.append(SEP_RULE_ID);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public String toDebug() {
		return "EVENT " + toString();
	}

}
