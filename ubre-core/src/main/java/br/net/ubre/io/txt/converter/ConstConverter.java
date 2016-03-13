package br.net.ubre.io.txt.converter;

import java.util.Date;
import java.util.Map;

import br.net.ubre.data.var.ValueType;
import br.net.ubre.framework.Constant;
import br.net.ubre.framework.Context;
import br.net.ubre.framework.ContextElement;
import br.net.ubre.io.txt.Line;
import br.net.ubre.io.txt.LineType;
import br.net.ubre.lang.expression.Expression;

/**
 * Converter para Constant.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 30/03/2015
 * 
 */
public class ConstConverter extends LineConverter {

	@Override
	public Constant asObject(Context context, Line line) {
		Map<String, String> map = getPipeMap(line.getValues());
		Date begin = parseDate(map.get(FIELD_BEGIN));
		Date end = parseDate(map.get(FIELD_END));
		String tags = map.get(FIELD_TAGS);
		// A constante tem um tipo explícito e o valor é atômico
		if (line.getItem() != null && !line.getItem().isEmpty()) {
			ValueType valueType = ValueType.get(line.getItem());
			Object valueObject = valueType.getConverter().asObject(line.getValues());
			return new Constant(line.getId(), line.getValues(), valueType, valueObject, begin, end, tags);
		}
		// A constante não tem tipo definido e é uma expressão a ser resolvida
		Expression expression = context.getLang().getCompiler().compile(line.getValues());
		return new Constant(line.getId(), line.getValues(), expression, context.getDataMap(), begin, end, tags);
	}

	@Override
	public Line asLine(ContextElement parent, Object object) {
		Constant constant = (Constant) object;
		return new Line(LineType.CONSTANT.getType(), constant.getId(), constant.getType().name(), constant.getString());
	}

}
