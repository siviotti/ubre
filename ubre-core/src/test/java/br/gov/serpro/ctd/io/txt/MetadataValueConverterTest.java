package br.gov.serpro.ctd.io.txt;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.gov.serpro.ubre.data.var.ValueType;
import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.framework.Metadata;
import br.gov.serpro.ubre.io.txt.Line;
import br.gov.serpro.ubre.io.txt.converter.ContextConverter;
import br.gov.serpro.ubre.io.txt.converter.MetadataConverter;

public class MetadataValueConverterTest {

	@Test
	public void test() {
		// Contexto
		String s1 = "context.ctx001=label=Contexto de teste|begin=12/12/2000|end=12/12/2001";
		Line lineContext = new Line(1, s1);
		ContextConverter contextConverter = new ContextConverter();
		Context context = contextConverter.asObject(null, lineContext);
		// Metadata
		// Linha -> Objeto
		String s = "meta.nomeFantasia=label=Nome Fantasia|description=Nome Fantasia da empresa|type=TEXT|size=200|format=|domain=|frame=solicitacao|form=identificacao|array=true|persistent=true|begin=|end=|tags=";
		MetadataConverter converter = new MetadataConverter();
		Line line = new Line(1, s);
		Metadata metadata = converter.asObject(context, line);
		assertEquals("Nome Fantasia", metadata.getLabel());
		assertEquals("Nome Fantasia da empresa", metadata.getDescription());
		assertEquals(ValueType.TEXT, metadata.getType());
		assertEquals(200, metadata.getSize());
		assertEquals(null, metadata.getFormat());
		assertEquals(null, metadata.getDomain());
		assertEquals("solicitacao", metadata.getFrame());
		assertEquals("identificacao", metadata.getForm());
		assertEquals(true, metadata.isArray());
		assertEquals(true, metadata.isPersistent());
		assertEquals(null, metadata.getTags());
		// Performance
		long t1 = System.currentTimeMillis();
		for (int i=0; i < 1000; i++){
			line = new Line(1, s);
			metadata = converter.asObject(context, line);			
		}
		long t2 = System.currentTimeMillis();
		System.out.println("Tempo de 1000:"+(t2-t1));
		// Objeto -> Linha
		Line line2 = converter.asLine(null, metadata);
		System.out.println( s);
		System.out.println(line2.toString());
		assertEquals(line, line2);
	}

}
