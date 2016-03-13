package br.net.ubre.io.txt;

import static org.junit.Assert.*;

import org.junit.Test;

import br.net.ubre.io.txt.Line;

public class LineTest {

	@Test
	public void test() {
		String s = "domain.nj.206=label=Sociedade Limitada";
		Line line = new Line(1, s);

		assertEquals("domain", line.getType());
		assertEquals("nj", line.getId());
		assertEquals("206", line.getItem());
		assertEquals("label=Sociedade Limitada", line.getValues());

		s = "meta.nomefantasia=label=Nome Fantasia;tamanho=10";
		line = new Line(2, s);

		assertEquals("meta", line.getType());
		assertEquals("nomefantasia", line.getId());
		assertEquals(null, line.getItem());
		assertEquals("label=Nome Fantasia;tamanho=10", line.getValues());

		System.out.print("abc" + Character.PARAGRAPH_SEPARATOR + "abc");
	}

}
