package br.gov.serpro.ctd.framework;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.gov.serpro.ubre.data.var.ValueType;
import br.gov.serpro.ubre.framework.Constant;
import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.framework.Message;
import br.gov.serpro.ubre.framework.Metadata;
import br.gov.serpro.ubre.framework.Scope;
import br.gov.serpro.ubre.framework.domain.Domain;
import br.gov.serpro.ubre.framework.domain.DomainItem;
import br.gov.serpro.ubre.framework.process.Event;
import br.gov.serpro.ubre.framework.process.Process;
import br.gov.serpro.ubre.framework.rule.Rule;
import br.gov.serpro.ubre.framework.rule.RuleType;

public class ContextTest {

	static Date begin = new Date();
	static Date end = new Date();

	@Test
	public void test() {

		Context context = createMock("C001", "Contexto Teste", begin, end);
		// Testes
		assertEquals("C001", context.getId());
		assertEquals("Contexto Teste", context.getLabel());
		assertEquals(begin, context.getBegin());
		assertEquals(end, context.getEnd());
		Domain d1 = context.getDomain("D1");
		assertEquals("D1", d1.getId());

	}

	public static Context createMock(String name, String label, Date begin,
			Date end) {
		Context context = new Context("C001", "Contexto Teste", begin, end);
		addConstants(context);
		addDomains(context);
		addMetadatas(context);
		addScopes(context);
		addMessages(context);
		addRules(context);
		addProcesses(context);
		context.build();
		context.freeze();
		return context;
	}

	private static void addScopes(Context context) {
		Scope scope = new Scope("scope1", "Escopo 1", context.getMetadatas(),
				new ArrayList<String>(), new ArrayList<Scope>(), null, null, null);
	}

	private static void addProcesses(Context context) {
		Scope scope = context.getScope("scope1");
		Event event = new Event("script", context.getRules());
		List<Event> eventos = new ArrayList<Event>();
		eventos.add(event);
		context.addProcess(new Process("P001", "Processo 1", scope, null,
				eventos, null, null, null));
	}

	private static void addRules(Context context) {
		Scope scope = context.getScope("scope1");
		List<String> sourceLines = new ArrayList<String>();
		sourceLines.add("print 'construindo regras na mão'");
		context.addRule(new Rule("RNG001", "Regra teste 001", null, null, null,
				RuleType.SCRIPT, scope, sourceLines));
		context.addRule(new Rule("RNG002", "Regra teste 002 - XPTO", null,
				null, null, RuleType.SCRIPT, scope, sourceLines));
		context.addRule(new Rule("RNG003", "Regra teste 003 - ABCD", null,
				null, null, RuleType.SCRIPT, scope, sourceLines));
	}

	private static void addMessages(Context context) {
		context.addMessage(new Message("MSG001", "Mensagem 1 de teste", null,
				null, null, null));
		context.addMessage(new Message("MSG002", "Tamanho máximo = $MAX_SIZE",
				null, null, null, null));
	}

	public static void addConstants(Context context) {
		context.addConst(new Constant("CONST1", ValueType.TEXT, "Value Const1"));
		context.addConst(new Constant("MAX_SIZE", ValueType.INTEGER, 100));
		context.addConst(new Constant("BOOL", ValueType.BOOLEAN, true));
	}

	public static void addMetadatas(Context context) {
		List<Metadata> items = new ArrayList<Metadata>();
		items.add(new Metadata("field1", "Field 1", "Field 1 description",
				ValueType.TEXT, 10, null, null, "MainFrame", "form1", false,
				true, null, null, null));
		items.add(new Metadata("field2", "Field 2", "Field 2 description",
				ValueType.DATE, 10, "dd/MM/yyyy", null, "MainFrame", "form1",
				false, true, null, null, null));
		items.add(new Metadata("field3", "Field 3 d1",
				"Field 3 description (D1)", ValueType.INTEGER, 8, null, null,
				"MainFrame", "form1", false, true, null, null, null));

	}

	public static void addDomains(Context context) {
		List<DomainItem> items = new ArrayList<DomainItem>();
		items.add(new DomainItem("1", "D1 Item 1", begin, end, null, null));
		items.add(new DomainItem("2", "D1 Item 2", begin, end, null, null));
		items.add(new DomainItem("3", "D1 Item 3", begin, end, null, null));
		items.add(new DomainItem("4", "D1 Item 4", begin, end, null, null));
		items.add(new DomainItem("5", "D1 Item 5", begin, end, null, null));
		Domain d1 = new Domain("D1", "Domínio 1", null, null, null, null);
		for (DomainItem domainItem : items) {
			d1.addItem(domainItem);
		}
		context.addDomain(d1);

		items = new ArrayList<DomainItem>();
		items.add(new DomainItem("1", "D2 Item 1", begin, end, null, null));
		items.add(new DomainItem("12", "D2 Item 2", begin, end, null, null));
		items.add(new DomainItem("13", "D2 Item 3", begin, end, null, null));
		items.add(new DomainItem("14", "D2 Item 4", begin, end, null, null));
		items.add(new DomainItem("15", "D2 Item 5", begin, end, null, null));
		Domain d2 = new Domain("D2", "Domínio 2", null, null, null, null);
		for (DomainItem domainItem : items) {
			d2.addItem(domainItem);
		}
		context.addDomain(d2);

	}
}
