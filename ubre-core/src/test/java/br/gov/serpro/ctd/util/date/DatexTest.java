package br.gov.serpro.ctd.util.date;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import br.gov.serpro.ubre.data.var.Datex;
import br.gov.serpro.ubre.exception.FreezeException;

/**
 * Teste unitário para <code>FormattedDate</code>.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 16/10/2015
 */
public class DatexTest {

	//@Test
	public void testCreate() throws InterruptedException {
		// Datas que não usam Hora
		Date onlyDay = new Datex(true, false);
		Thread.sleep(100); // pausa de 100 milisegundos
		Date onlyDay2 = new Datex(true, false);
		assertEquals(onlyDay.getTime(), onlyDay2.getTime());
		
		// Datas que usam hora
		Date dateTime = new Datex(true, true);
		Thread.sleep(10); // pausa de 10 milisegundos
		Date dateTime2 = new Datex(false, false);
		assertFalse(dateTime.getTime() == dateTime2.getTime());
		
		// Datas somente com Hora
		Date time = new Datex(false, true);
		Thread.sleep(1001); // pausa de mais de 1 segundo
		Date time2 = new Datex(false, true);
		// Os milisegundos não são iguais por causa da pausa
		assertFalse(time.getTime() == time2.getTime());
		// A diferença é de exatamente 1000 milisegundos
		assertTrue(time2.getTime() - time.getTime() == 1000);
		// Todo time termina com 000, pois o formato não tem mili
		assertTrue(time.getTime() % 1000 == 0);
		assertTrue(time2.getTime() % 1000 == 0);
		//System.out.println(time.getTime());
		//System.out.println(time2.getTime());
	}
	
	@Test
	public void testDatexOnlyDate(){
		Datex datex1 = new Datex(2000, 12, 12);
		Datex datex2 = new Datex("12/12/2000");
		assertEquals(datex1, datex2);
	}
	
	//@Test
	public void testCreatePerformance(){
		long t0 = System.currentTimeMillis();
		Date d;
		for (int i=0; i < 10000; i++){
			d = new Date();
		}
		long t1 = System.currentTimeMillis();
		Datex xd;
		for (int i=0; i < 10000; i++){
			xd = new Datex(true, false);
		}
		long t2 = System.currentTimeMillis();
		for (int i=0; i < 10000; i++){
			xd = new Datex();
		}
		long t3 = System.currentTimeMillis();
		for (int i=0; i < 10000; i++){
			xd = new Datex("12/12/2000");
		}
		long t4 = System.currentTimeMillis();
		System.out.println(t1-t0);
		System.out.println(t2-t1);
		System.out.println(t3-t2);
		System.out.println(t4-t3);
	}
	
	@Test
	public void testZero(){
		Datex zero = Datex.ZERO;
		System.out.println(zero.getTime());
		Datex d1= new Datex("01/01/0001 00:00:00");
		System.out.println(d1);
		Datex d2= new Datex("31/12/-0001 00:00:00");
		System.out.println(d2);
	}
	
	@Test
	public void testFormatPerformance(){
		
	}
	
	@Test
	public void testDif(){
		Datex d1 = new Datex("12/12/2000");
		Datex d2 = new Datex("15/12/2000");
		assertEquals(3L, d1.daysTo(d2));
	}

}

