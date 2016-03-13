package br.net.ubre.io.txt;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import br.net.ubre.framework.Constant;
import br.net.ubre.framework.Context;
import br.net.ubre.io.txt.Line;
import br.net.ubre.io.txt.converter.ConstConverter;
import br.net.ubre.io.txt.converter.ContextConverter;

public class ConstValueConverterTest {

	@Test
	public void test() {
		// Contexto
		String s1 = "context.ctx001=label=Contexto de teste|begin=12/12/2000|end=12/12/2001";
		Line lineContext = new Line(1, s1);
		ContextConverter contextConverter = new ContextConverter();
		Context context = contextConverter.asObject(null, lineContext);
		// Const
		Calendar cal= new GregorianCalendar(2000, Calendar.DECEMBER, 12);
		Date start = new Date(cal.getTimeInMillis());
		String s2 = "const.START.DATE=12/12/2000";
		Line lineConst = new Line(2, s2);
		ConstConverter converter = new ConstConverter();
		Constant constant = converter.asObject(context, lineConst);
		// Teste
		System.out.println(start);
		System.out.println(constant.getValue());
		assertEquals(start, constant.getValue());
	}

}
