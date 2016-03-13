package br.net.ubre.framework.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.exception.CTDException;
import br.net.ubre.framework.ContextElement;
import br.net.ubre.framework.Scope;
import br.net.ubre.util.Debuggable;

/**
 * Representa um processo de utilização de uma interface com o usuário com seus
 * vários eventos previamente previstos.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class Process extends ContextElement implements Debuggable {

	private static final String EVENT_NOT_FOUND = "Event não encontrado:";
	private Process parent;
	private Map<String, Event> events;
	private int level = 1;

	public Process(String id, String label, Scope scope, Process parent,
			List<Event> events, Date begin, Date end, String tags) {
		super(id, label, begin, end, tags);
		this.parent = parent;
		level = (parent != null) ? parent.getLevel() + 1 : 1;
		this.events = new HashMap<String, Event>();
		for (Event event : events) {
			this.events.put(event.getId(), event);
		}
	}

	public void run(DataContainer container, String... eventId) {
		for (String id : eventId) {
			run(container, id);
		}
	}

	public void run(DataContainer container, String eventId) {
		Event event = events.get(eventId);
		if (event == null) {
			throw new CTDException(EVENT_NOT_FOUND + eventId);
		}
		if (parent != null) {
			// parent.run(eventId, container);
		}
		event.execute(container);
	}

	public String toDebug() {
		StringBuilder sb = new StringBuilder();
		sb.append("  PROCESS " + getId() + " " + getLabel());
		for (Event event : events.values()) {
			sb.append("\n    " + event.toDebug());
		}
		return sb.toString();
	}

	/**
	 * retorna uma cópia da lista de Scripts.
	 * 
	 * @return
	 */
	public List<Event> getEvents() {
		return new ArrayList<Event>(events.values());
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	public Event getEvent(String eventId) {
		return events.get(eventId);
	}
}
