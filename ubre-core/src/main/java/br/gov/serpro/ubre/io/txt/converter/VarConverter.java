package br.gov.serpro.ubre.io.txt.converter;

import java.util.Date;
import java.util.Map;

import br.gov.serpro.ubre.data.var.ValueType;
import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.framework.ContextElement;
import br.gov.serpro.ubre.framework.Variable;
import br.gov.serpro.ubre.io.txt.Line;
import br.gov.serpro.ubre.io.txt.LineType;
import br.gov.serpro.ubre.lang.expression.Expression;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 20/05/2015
 * 
 */
public class VarConverter extends LineConverter {

	@Override
	public Variable asObject(Context context, Line line) {
		Map<String, String> map = getPipeMap(line.getValues());
		Date begin = parseDate(map.get(FIELD_BEGIN));
		Date end = parseDate(map.get(FIELD_END));
		String tags = map.get(FIELD_TAGS);
		// A constante tem um tipo explícito e o valor é atômico
		if (line.getItem() != null && !line.getItem().isEmpty()) {
			ValueType valueType = ValueType.get(line.getItem());
			Object valueObject = valueType.getConverter().asObject(
					line.getValues());
			return new Variable(line.getId(), line.getValues(), valueType,
					valueObject, begin, end, tags);
		}
		// A constante não tem tipo definido e é uma expressão a ser resolvida
		Expression expression = context.getLang().getCompiler()
				.compile(line.getValues());
		return new Variable(line.getId(), line.getValues(), expression,
				context.getDataMap(), begin, end, tags);
	}

	@Override
	public Line asLine(ContextElement parent, Object object) {
		Variable var = (Variable) object;
		return new Line(LineType.VARIABLE.getType(), var.getId(), var.getType()
				.name(), var.getString());
	}

}
