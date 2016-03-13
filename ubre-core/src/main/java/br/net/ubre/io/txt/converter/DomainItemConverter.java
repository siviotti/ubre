package br.net.ubre.io.txt.converter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.net.ubre.framework.Context;
import br.net.ubre.framework.ContextElement;
import br.net.ubre.framework.domain.DomainItem;
import br.net.ubre.io.txt.Line;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 31/03/2015
 * 
 */
public class DomainItemConverter extends LineConverter {

	private static final String FIELD_ID_TYPE = "idType";

	@Override
	public DomainItem asObject(Context context, Line line) {
		Map<String, String> map = getPipeMap(line.getValues());
		String label = map.get(FIELD_LABEL);
		Date begin = parseDate(map.get(FIELD_BEGIN));
		Date end = parseDate(map.get(FIELD_END));
		String tags = map.get(FIELD_TAGS);
		String idTypeStr = map.get(FIELD_ID_TYPE);
		DomainItem item = new DomainItem(line.getItem(), label, begin, end, tags, getObjectMap(map));
		return item;
	}

	@Override
	public Line asLine(ContextElement parent, Object object) {

		return null;
	}

	private Map<String, Object> getObjectMap(Map<String, String> mapStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String s : mapStr.values()) {
			map.put(s, mapStr.get(s));
		}
		return map;
	}

}
