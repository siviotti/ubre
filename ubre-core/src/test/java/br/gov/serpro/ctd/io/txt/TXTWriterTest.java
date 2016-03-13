package br.gov.serpro.ctd.io.txt;

import org.junit.Test;

import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.io.ContextWriter;
import br.gov.serpro.ubre.io.txt.TXTLoader;
import br.gov.serpro.ubre.io.txt.TXTWriter;

/**
 * Teste unitário de gravação do contexto em um arquivo txt.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 18/09/2015
 */
public class TXTWriterTest {

	@Test
	public void test() {
		TXTLoader loader = new TXTLoader();
		Context context = loader.load("context.txt", "UTF-8");
		ContextWriter writer = new TXTWriter();
		String home = System.getenv("HOME");
		System.out.println(home);
		writer.write(context, home + "/TXTWriterTest.txt", "UTF-8");
	}
}
