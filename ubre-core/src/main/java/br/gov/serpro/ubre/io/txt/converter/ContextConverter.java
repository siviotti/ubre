package br.gov.serpro.ubre.io.txt.converter;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import br.gov.serpro.ubre.data.field.FieldFactory;
import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.framework.ContextElement;
import br.gov.serpro.ubre.io.txt.Line;
import br.gov.serpro.ubre.io.txt.LineType;
import br.gov.serpro.ubre.io.txt.TxtException;
import br.gov.serpro.ubre.lang.Syntax;
import br.gov.serpro.ubre.slang.Syntagma;
import br.gov.serpro.ubre.util.Reflect;

/**
 * Parser para Context.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 27/03/2015
 * 
 */
public class ContextConverter extends LineConverter {

	private static final String PROP_SYNTAX = "syntax";
	private static final String PROP_SYNTAGMA = "syntagma";
	private static final String PROP_FIELD_FACTORY = "fieldFactory";
	private static final Logger LOGGER = Logger
			.getLogger(ContextConverter.class.getSimpleName());

	@Override
	public Context asObject(final Context context, Line line) {
		System.out.println(line.getChildren());
		Map<String, String> map = getPipeMap(line.getValues());
		String label = map.get(FIELD_LABEL);
		Date begin = null;
		Date end = null;
		try {
			begin = DATE_FORMATTER.parse(map.get(FIELD_BEGIN));
			end = DATE_FORMATTER.parse(map.get(FIELD_END));
		} catch (ParseException e) {
			throw new TxtException(e);
		}
		// syntax
		String syntaxClassName = line.getValuesByLastItem(PROP_SYNTAX);
		Syntax syntax = createSyntax(syntaxClassName);
		// Framework
		String syntagmaClassName = line.getValuesByLastItem(PROP_SYNTAGMA);
		Syntagma syntagma = createSyntagma(syntagmaClassName);
		// FieldFactory
		String fieldFactoryClassName = line
				.getValuesByLastItem(PROP_FIELD_FACTORY);
		FieldFactory fieldFactory = createFieldFactory(fieldFactoryClassName);
		// Context
		Context ctx = new Context(line.getId(), label, begin, end, syntax,
				syntagma, fieldFactory);
		return ctx;
	}

	@Override
	public Line asLine(ContextElement parent, Object object) {
		Context context = (Context) object;
		Map<String, String> map = new HashMap<String, String>();
		map.put(FIELD_LABEL, context.getLabel());
		map.put(FIELD_BEGIN, DATE_FORMATTER.format(context.getBegin()));
		map.put(FIELD_END, DATE_FORMATTER.format(context.getEnd()));

		StringBuilder sb = new StringBuilder();
		setValue(map, FIELD_LABEL, sb);
		setValue(map, FIELD_BEGIN, sb);
		setLastValue(map, FIELD_END, sb);
		// Linha do Contexto
		Line line = new Line(LineType.CONTEXT.getType(), context.getId(), null,
				sb.toString());
		addPropLine(line, PROP_SYNTAX, context.getSlang().getLang().getSyntax()
				.getClass().getCanonicalName());
		addPropLine(line, PROP_SYNTAGMA, context.getSlang().getSyntagma()
				.getClass().getCanonicalName());
		addPropLine(line, PROP_FIELD_FACTORY, context.getFieldFactory()
				.getClass().getCanonicalName());
		return line;
	}

	private void addPropLine(Line contextLine, String propName, String objectStr) {
		Line propLine = new Line(contextLine.getType(), contextLine.getId(),
				propName, objectStr);
		contextLine.addChild(propLine);
	}

	private Syntax createSyntax(String className) {
		if (className == null || className.trim().isEmpty()) {
			return new Syntax();
		}
		Class<?> clazz = Reflect.classForName(className);
		LOGGER.info("Customização de Syntax:" + clazz);
		Reflect.testAssignableFrom(Syntax.class, clazz);
		return (Syntax) Reflect.newInstance(clazz);
	}

	private Syntagma createSyntagma(String className) {
		if (className == null || className.trim().isEmpty()) {
			return new Syntagma();
		}
		Class<?> clazz = Reflect.classForName(className);
		LOGGER.info("Customização de Syntagma:" + clazz);
		Reflect.testAssignableFrom(Syntagma.class, clazz);
		return (Syntagma) Reflect.newInstance(clazz);
	}

	private FieldFactory createFieldFactory(String className) {
		if (className == null || className.trim().isEmpty()) {
			return new FieldFactory();
		}
		Class<?> clazz = Reflect.classForName(className);
		LOGGER.info("Customização de FieldFactory:" + clazz);
		Reflect.testAssignableFrom(FieldFactory.class, clazz);
		return (FieldFactory) Reflect.newInstance(clazz);
	}

}
