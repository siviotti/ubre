package br.net.ubre.io.txt;

import static br.net.ubre.io.txt.converter.LineConverter.DATE_FORMATTER;
import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import br.net.ubre.framework.Context;
import br.net.ubre.io.txt.Line;
import br.net.ubre.io.txt.converter.ContextConverter;

public class ContextValueConverterTest {

	@Test
	public void test() {
		Calendar begin = new GregorianCalendar(2000, Calendar.DECEMBER, 12);
		Calendar end = new GregorianCalendar(2001, Calendar.DECEMBER, 12);
		String s = "context.ctx001=label=Contexto/ de teste|begin=12/12/2000|end=12/12/2001";
		Line line = new Line(1, s);
		ContextConverter contextConverter = new ContextConverter();
		Context context = contextConverter.asObject(null, line);
		// Testes do unparsing (criaçao do objeto a partir da string)
		assertEquals("ctx001", context.getId());
		assertEquals("Contexto/ de teste", context.getLabel());
		assertEquals(new Date(begin.getTimeInMillis()), context.getBegin());
		assertEquals(new Date(end.getTimeInMillis()), context.getEnd());

		// Objeto OK - fa o parsing
		//context.setLabel("Contexto| de teste");// troca | por /
		line = contextConverter.asLine(null, context);
		System.out.println(s);
		System.out.println(line.toString());
		assertEquals(s, line.toString());
	}

}
