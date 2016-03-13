package br.net.ubre.io.txt.converter;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.net.ubre.framework.Context;
import br.net.ubre.framework.ContextElement;
import br.net.ubre.framework.Scope;
import br.net.ubre.framework.process.Event;
import br.net.ubre.framework.process.Process;
import br.net.ubre.io.txt.Line;
import br.net.ubre.io.txt.LineType;
import br.net.ubre.io.txt.TxtException;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 01/04/2015
 * 
 */
public class ProcessConverter extends LineConverter {

	public static final String FIELD_PARENT = "parent";
	private static final String FIELD_SCOPE = "scope";

	@Override
	public br.net.ubre.framework.process.Process asObject(Context context,
			Line line) {
		Map<String, String> map = getPipeMap(line.getValues());
		String label = map.get(FIELD_LABEL);
		Date begin = parseDate(map.get(FIELD_BEGIN));
		Date end = parseDate(map.get(FIELD_END));
		String tags = map.get(FIELD_TAGS);
		String parentId = map.get(FIELD_PARENT);
		br.net.ubre.framework.process.Process parent = null;
		if (parentId != null) { // um parent foi indicado
			parent = context.getProcess(parentId);
			validateParent(parent, parentId, line);
		}
		String scopeId = map.get(FIELD_SCOPE);
		Scope scope = context.getScope(scopeId);
		List<Event> scripts = getEvents(context, line);
		br.net.ubre.framework.process.Process process = new br.net.ubre.framework.process.Process(
				line.getId(), label, scope, parent, scripts, begin,
				end, tags);
		return process;
	}

	private List<Event> getEvents(Context context, Line line) {
		List<Event> events = new ArrayList<Event>();
		for (Line childLine : line.getChildren()) {
			if (childLine.getLineType().equals(LineType.EVENT)) {
				events.add(getEvent(context, childLine));
			}
		}
		return events;
	}

	private Event getEvent(Context context, Line childLine) {
		return (Event) childLine.asElement(context);
	}

	@Override
	public Line asLine(ContextElement parent, Object object) {
		br.net.ubre.framework.process.Process process = (Process) object;
		Map<String, String> map = new HashMap<String, String>();
		map.put(FIELD_LABEL, process.getLabel());
		map.put(FIELD_BEGIN, asString(process.getBegin()));
		map.put(FIELD_END, asString(process.getEnd()));
		map.put(FIELD_TAGS, process.getTags());
		StringBuilder sb = new StringBuilder();
		setValue(map, FIELD_LABEL, sb);
		setValue(map, FIELD_BEGIN, sb);
		setValue(map, FIELD_END, sb);
		setLastValue(map, FIELD_TAGS, sb);
		Line line = new Line(LineType.PROCESS.getType(), process.getId(), null,
				sb.toString());
		extractChildLines(process, line);
		return line;
	}

	private void extractChildLines(Process process, Line line) {
		for (Event event : process.getEvents()) {
			line.addChild(LineType.EVENT.getConverter()
					.asLine(process, event));
		}
	}

	private void validateParent(br.net.ubre.framework.process.Process parent,
			String parentId, Line line) {
		if (parent == null) {
			throw new TxtException("Processo parent  '" + parentId
					+ "' (declarado anteriormente) não encontrado na linha:"
					+ line.toString());
		}

	}

}
