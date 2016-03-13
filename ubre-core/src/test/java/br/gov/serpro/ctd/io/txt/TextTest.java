package br.gov.serpro.ctd.io.txt;

import static org.junit.Assert.*;

import org.junit.Test;

import br.gov.serpro.ubre.io.txt.Text;

public class TextTest {

	@Test
	public void testBc() {
		String s = "abc=def";
		String bc = Text.bc(s, '=');
		System.out.println(bc);
		assertEquals("abc", bc);
	}

	@Test
	public void testAc() {
		String s = "abc=def";
		String ac = Text.ac(s, '=');
		System.out.println(ac);
		assertEquals("def", ac);
	}
	
	@Test
	public void testSplit(){
		String s = "label=abc|descripton=def|teste=hhfh|campo2=irufjn|campo3=jjdiuei|xyz=kkdjk";
		String[] array;
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++){
			array = s.split("\\|");
		}
		long t2 = System.currentTimeMillis();
		System.out.println("Tempo de Split do Java:" + (t2-t1));
	}

}
