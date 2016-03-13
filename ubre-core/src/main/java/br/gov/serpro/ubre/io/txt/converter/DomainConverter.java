package br.gov.serpro.ubre.io.txt.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.framework.ContextElement;
import br.gov.serpro.ubre.framework.domain.Domain;
import br.gov.serpro.ubre.framework.domain.DomainItem;
import br.gov.serpro.ubre.framework.domain.DomainMetadata;
import br.gov.serpro.ubre.io.txt.Line;
import br.gov.serpro.ubre.io.txt.LineType;
import br.gov.serpro.ubre.io.txt.TxtException;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 31/03/2015
 * 
 */
public class DomainConverter extends LineConverter {

	private static final String META_PATTERN = "domain.DOMAIN-ID.meta=field1=BOOLEAN|field2=TEXT(20)|field3=INTEGER";

	@Override
	public Domain asObject(Context context, Line line) {
		Map<String, String> map = getPipeMap(line.getValues());
		String label = map.get(FIELD_LABEL);
		Date begin = parseDate(map.get(FIELD_BEGIN));
		Date end = parseDate(map.get(FIELD_END));
		String tags = map.get(FIELD_TAGS);
		List<DomainMetadata> metas = getMetas(context, line);
		List<DomainItem> items = getItems(context, line);
		Domain domain = new Domain(line.getId(), label, metas, begin, end, tags);
		for (DomainItem domainItem : items) {
			domain.addItem(domainItem);
		}
		return domain;
	}

	private List<DomainMetadata> getMetas(Context context, Line line) {
		Line metaLine = line.getFirstChildByType(LineType.DOMAIN_META);
		if (metaLine == null) {
			throw new TxtException("O domínio '" + line.getId()
					+ "' não tem um metadado definido. Use uma linha no padrão " + META_PATTERN);
		}
		return (List<DomainMetadata>) metaLine.asElement(context);
	}

	private List<DomainItem> getItems(Context context, Line line) {
		List<DomainItem> items = new ArrayList<DomainItem>();
		if (!line.hasChild()) {
			return items;
		}
		DomainItem item;
		for (Line childLine : line.getChildren()) {
			if (childLine.checkType(LineType.DOMAIN_ITEM)) {
				item = (DomainItem) childLine.asElement(context);
				items.add(item);
			}
		}
		return items;
	}

	@Override
	public Line asLine(ContextElement parent, Object object) {
		Domain domain = (Domain) object;
		Map<String, String> map = new HashMap<String, String>();
		map.put(FIELD_LABEL, domain.getLabel());
		map.put(FIELD_BEGIN, asString(domain.getBegin()));
		map.put(FIELD_END, asString(domain.getEnd()));
		map.put(FIELD_TAGS, domain.getTags());
		StringBuilder sb = new StringBuilder();
		setValue(map, FIELD_LABEL, sb);
		setValue(map, FIELD_BEGIN, sb);
		setValue(map, FIELD_END, sb);
		setLastValue(map, FIELD_TAGS, sb);
		Line line = new Line(LineType.DOMAIN.getType(), domain.getId(), null, sb.toString());

		return line;
	}

}
