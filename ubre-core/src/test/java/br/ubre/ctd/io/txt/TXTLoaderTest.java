package br.ubre.ctd.io.txt;

import org.junit.Test;

import br.net.ubre.framework.Context;
import br.net.ubre.io.txt.TXTLoader;

public class TXTLoaderTest {

	@Test
	public void test() {
		TXTLoader loader = new TXTLoader();
		Context context = loader.load("context.txt", "UTF-8");
		System.out.print(context.toDebug());
	}

}
