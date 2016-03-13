package br.net.ubre.io.txt.converter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.net.ubre.data.var.ValueType;
import br.net.ubre.exception.CTDException;
import br.net.ubre.framework.Context;
import br.net.ubre.framework.ContextElement;
import br.net.ubre.framework.Metadata;
import br.net.ubre.framework.domain.Domain;
import br.net.ubre.io.txt.Line;
import br.net.ubre.io.txt.LineType;

/**
 * Conveter para Matedata.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 30/03/2015
 * 
 */
public class MetadataConverter extends LineConverter {

	private static final String FIELD_DESCRIPTION = "description";
	private static final String FIELD_TYPE = "type";
	private static final String FIELD_SIZE = "size";
	private static final String FIELD_FORMAT = "format";
	private static final String FIELD_DOMAIN = "domain";
	private static final String FIELD_FRAME = "frame";
	private static final String FIELD_FORM = "form";
	private static final String FIELD_ARRAY = "array";
	private static final String FIELD_PERSISTENT = "persistent";
	private static final String FIELD_TAGS = "tags";

	@Override
	public Metadata asObject(Context context, Line line) {
		Map<String, String> map = getPipeMap(line.getValues());
		String label = map.get(FIELD_LABEL);
		Date begin = parseDate(map.get(FIELD_BEGIN));
		Date end = parseDate(map.get(FIELD_END));
		String tags = map.get(FIELD_TAGS);
		String description = map.get(FIELD_DESCRIPTION);
		String typeStr = map.get(FIELD_TYPE);
		ValueType type = ValueType.get(typeStr);
		if (type == null) {
			throw new CTDException("Tipo de Metadado desconhecido:" + typeStr);
		}
		int size = parseInt(map.get(FIELD_SIZE));
		String format = map.get(FIELD_FORMAT);
		Domain domain = null;
		String domainStr = map.get(FIELD_DOMAIN);
		if (domainStr != null && !domainStr.isEmpty()) {
			domain = getDomain(context, domainStr);
			if (domain == null) {
				//throw new CTDException("Domínio desconhecido:" + domainStr						+ "\n" + line);
			}
		}
		String frame = map.get(FIELD_FRAME);
		String form = map.get(FIELD_FORM);
		boolean array = parseBoolean(map.get(FIELD_ARRAY));
		boolean persistent = parseBoolean(map.get(FIELD_PERSISTENT));
		Metadata metadata = new Metadata(line.getId(), label, description,
				type, size, format, domain, frame, form, array, persistent,
				begin, end, tags);
		return metadata;
	}

	private Domain getDomain(Context context, String domainId) {
		for (Domain domain : context.getDomains()) {
			if (domain.getId().equals(domainId)) {
				return domain;
			}
		}
		return null;
	}

	@Override
	public Line asLine(ContextElement parent, Object object) {
		Metadata metadata = (Metadata) object;
		Map<String, String> map = new HashMap<String, String>();
		map.put(FIELD_LABEL, metadata.getLabel());
		map.put(FIELD_DESCRIPTION, metadata.getDescription());
		map.put(FIELD_TYPE, metadata.getType().name());
		map.put(FIELD_SIZE, asString(metadata.getSize()));
		map.put(FIELD_FORMAT, metadata.getFormat());
		if (metadata.getDomain() != null) {
			map.put(FIELD_DOMAIN, metadata.getDomain().getId());
		} else {
			map.put(FIELD_DOMAIN, "");
		}
		map.put(FIELD_FRAME, metadata.getFrame());
		map.put(FIELD_FORM, metadata.getForm());
		map.put(FIELD_ARRAY, asString(metadata.isArray()));
		map.put(FIELD_PERSISTENT, asString(metadata.isPersistent()));
		map.put(FIELD_BEGIN, asString(metadata.getBegin()));
		map.put(FIELD_END, asString(metadata.getEnd()));
		map.put(FIELD_TAGS, metadata.getTags());
		StringBuilder sb = new StringBuilder();
		setValue(map, FIELD_LABEL, sb);
		setValue(map, FIELD_DESCRIPTION, sb);
		setValue(map, FIELD_TYPE, sb);
		setValue(map, FIELD_SIZE, sb);
		setValue(map, FIELD_FORMAT, sb);
		setValue(map, FIELD_DOMAIN, sb);
		setValue(map, FIELD_FRAME, sb);
		setValue(map, FIELD_FORM, sb);
		setValue(map, FIELD_ARRAY, sb);
		setValue(map, FIELD_PERSISTENT, sb);
		setValue(map, FIELD_BEGIN, sb);
		setValue(map, FIELD_END, sb);
		setLastValue(map, FIELD_TAGS, sb);
		Line line = new Line(LineType.METADATA.getType(), metadata.getId(),
				null, sb.toString());
		return line;
	}

}
