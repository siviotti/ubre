package br.gov.serpro.ctd.lang.keyword.binary.dash;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import br.gov.serpro.ubre.data.var.Datex;
import br.gov.serpro.ubre.lang.data.literal.DateStatement;
import br.gov.serpro.ubre.lang.data.literal.IntegerStatement;
import br.gov.serpro.ubre.lang.data.literal.LiteralStatement;
import br.gov.serpro.ubre.lang.keyword.binary.dash.DashOperator;
import br.gov.serpro.ubre.lang.statement.StatementType;

public class DashOperatorTest {

	@Test
	public void test1() {

		// Date quatroAbriu2015 = new Date();
		Calendar c = Calendar.getInstance();

		c.set(Calendar.SECOND, 01);
		c.set(Calendar.MINUTE, 02);
		c.set(Calendar.HOUR_OF_DAY, 03);
		c.set(Calendar.DAY_OF_MONTH, 04);
		c.set(Calendar.MONTH, 05);
		c.set(Calendar.YEAR, 15);

		LiteralStatement tres = new IntegerStatement(3);
		LiteralStatement seis = new IntegerStatement(6);
		DashOperator dashOp = new DashOperator("-");

		dashOp.defineOperation(seis.perform(null), tres.perform(null));
		dashOp.link(seis.perform(null), tres.perform(null));
		Integer result = (Integer) dashOp.result(null);
		System.out.println(result);
		assertEquals("3", result.toString());

		dashOp.link(tres.perform(null), seis.perform(null));
		result = (Integer) dashOp.result(null);
		System.out.println(result);
		assertEquals("-3", result.toString());

	}

	@Test
	public void test2() {

		// Date quatroAbriu2015 = new Date();
		Calendar c = Calendar.getInstance();

		c.set(Calendar.SECOND, 01);
		c.set(Calendar.MINUTE, 02);
		c.set(Calendar.HOUR_OF_DAY, 03);
		c.set(Calendar.DAY_OF_MONTH, 04);
		c.set(Calendar.MONTH, 05);// vira 6
		c.set(Calendar.YEAR, 2015);

		DateStatement date = new DateStatement(new Datex(c.getTimeInMillis()));
		DashOperator dashOp = new DashOperator("-");
		LiteralStatement um = new IntegerStatement(1);
		dashOp.defineOperation(date, um.perform(null));
		dashOp.link(date, um.perform(null));
		Datex result = (Datex) dashOp.result(null);
		System.out.println(result);
		assertEquals("03/06/2015 03:02:01", result.toString());

	}

}
