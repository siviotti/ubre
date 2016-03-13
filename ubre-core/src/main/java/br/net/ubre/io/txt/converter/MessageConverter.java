package br.net.ubre.io.txt.converter;

import java.util.Date;
import java.util.Map;

import br.net.ubre.data.var.ValueType;
import br.net.ubre.framework.Context;
import br.net.ubre.framework.ContextElement;
import br.net.ubre.framework.Message;
import br.net.ubre.io.txt.Line;
import br.net.ubre.io.txt.LineType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 09/04/2015
 * 
 */
public class MessageConverter extends LineConverter {

	@Override
	public Message asObject(Context context, Line line) {
		Map<String, String> map = getPipeMap(line.getValues());
		Date begin = parseDate(map.get(FIELD_BEGIN));
		Date end = parseDate(map.get(FIELD_END));
		String tags = map.get(FIELD_TAGS);
		// A constante tem um tipo explícito e o valor é atômico
		ValueType valueType = ValueType.TEXT;
		Object valueObject = valueType.getConverter()
				.asObject(line.getValues());
		return new Message(line.getId(), line.getValues(), valueObject, begin,
				end, tags);
	}

	@Override
	public Line asLine(ContextElement parent, Object object) {
		Message message = (Message) object;
		return new Line(LineType.MESSAGE.getType(), message.getId(), null,
				message.getLabel());
	}

}
