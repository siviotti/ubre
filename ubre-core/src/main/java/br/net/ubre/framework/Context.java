package br.net.ubre.framework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import br.net.ubre.behave.BehaveMap;
import br.net.ubre.behave.Callable;
import br.net.ubre.data.field.FieldFactory;
import br.net.ubre.data.map.DataMap;
import br.net.ubre.data.var.Datex;
import br.net.ubre.data.var.ValueType;
import br.net.ubre.exception.CTDException;
import br.net.ubre.framework.domain.Domain;
import br.net.ubre.framework.process.Event;
import br.net.ubre.framework.process.Process;
import br.net.ubre.framework.rule.Rule;
import br.net.ubre.lang.Lang;
import br.net.ubre.lang.Syntax;
import br.net.ubre.slang.Slang;
import br.net.ubre.slang.Syntagma;
import br.net.ubre.util.Debuggable;
import br.net.ubre.util.FreezableList;

/**
 * Representa um Contexto que agrupa todos os elementos válidos dentro de uma
 * vigência (Lifetime) e a relação entre eles. É a classe concentradora da parte
 * interna (Model) do framework.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 19/03/2015
 */
// @ApplicationScoped
public class Context extends ContextElement implements Debuggable {

	private static final String CONTEXT_NOT_BUILT = "O Contexto ainda não foi compilado e não pode ser congelado. É necessário chamar o método build()";
	private static final String CONTEXT_CALLABLE_ALREADY_EXISTS = "O contexto já tem um Callable (chamável) denominado ";

	// 1 - Constantes
	private ConstMap constants = new ConstMap();
	// 2 - Variáveis
	private List<Variable> variables = new FreezableList<Variable>();
	// 3 - Domínios
	private List<Domain> domains = new FreezableList<Domain>();
	private Map<String, Domain> mapDomains = new HashMap<String, Domain>();
	// 4 - Metadados
	private List<Metadata> metadatas = new FreezableList<Metadata>();
	private Map<String, Metadata> mapMetadatas = new HashMap<String, Metadata>();
	// 5 - Escopos
	private List<Scope> scopes = new FreezableList<Scope>();
	private Map<String, Scope> mapScopes = new HashMap<String, Scope>();
	// 6 - Mensagens
	private List<Message> messages = new FreezableList<Message>();
	private Map<String, Message> mapMessages = new HashMap<String, Message>();
	// 7 - Regras
	private List<Rule> rules = new FreezableList<Rule>();
	private Map<String, Rule> mapRules = new HashMap<String, Rule>();
	// 8 - Processos
	private List<Process> processes = new FreezableList<Process>();
	private Map<String, Process> mapProcesses = new HashMap<String, Process>();

	// Instância da linguagem de expressões
	private Lang lang;
	// Instância da linguagem de Scripts
	private Slang slang;
	// Factories
	private FieldFactory fieldFactory;
	// Controle de build
	private boolean built = false;
	private Map<String, Callable> callables = new HashMap<String, Callable>();
	// Mapa ed dados e seus tipos
	private DataMap dataMap = new DataMap();
	private BehaveMap behaveMap = new BehaveMap();

	/**
	 * Construtor com vigência, pois o contexto sempre deve ter vigência.
	 * 
	 * @param name
	 *            O nome do conntexto (chave única).
	 * @param label
	 *            A descrição do contexto.
	 * @param begin
	 *            Início da vigência.
	 * @param end
	 *            Fim da vigência.
	 * @param syntax
	 *            A sintaxe usada pelo contexto na execução de expressões dentro
	 *            da instância de <code>Lang</code>.
	 */
	public Context(String id, String label, Date begin, Date end,
			Syntax syntax, Syntagma syntagma, FieldFactory fieldFactory) {
		super(id, label, begin, end, "");
		lang = new Lang(syntax, dataMap, true);
		slang = new Slang(lang, syntagma);
		this.fieldFactory = fieldFactory;
		loadInitialElements();
	}

	public Context(String id, String label, Date begin, Date end) {
		this(id, label, begin, end, new Syntax(), new Syntagma(),
				new FieldFactory());
	}

	private void loadInitialElements() {
		addConst(new Constant("CTX_DATE", ValueType.DATE, new Datex()));
	}

	// ****************************
	// ********** ADIÇÃO **********
	// ****************************

	public void addConst(Constant constant) {
		testId(constant, "Constante", constants.values());
		constants.put(constant.getId(), constant);
		dataMap.add(constant, true);
	}

	public void addConst(String id, ValueType valueType, Object value) {
		addConst(new Constant(id, valueType, value));
	}

	public void addVar(Variable variable) {
		testId(variable, "Variável", variables);
		variables.add(variable);
		dataMap.add(variable, false);
	}

	public void addVar(String id, ValueType valueType, Object value) {
		addVar(new Variable(id, valueType, value));
	}

	public void addDomain(Domain domain) {
		testId(domain, "Domínio", domains);
		domains.add(domain);
	}

	public void addMetadata(Metadata metadata) {
		testId(metadata, "Metadado", metadatas);
		metadatas.add(metadata);
		dataMap.add(metadata, false);
	}

	public void addScope(Scope scope) {
		testId(scope, "Escopo", scopes);
		scopes.add(scope);
	}

	public void addMessage(Message message) {
		testId(message, "Mensagem", messages);
		messages.add(message);
		dataMap.add(message, true);
	}

	public void addRule(Rule rule) {
		testId(rule, "Regra", rules);
		addCallable(rule);
		rules.add(rule);
	}

	public void addProcess(Process process) {
		testId(process, "Processo", processes);
		for (Event event : process.getEvents()) {
			addCallable(event);
		}
		processes.add(process);
	}

	private void addCallable(Callable callable) {
		if (callables.containsKey(callable.getToken())) {
			throw new CTDException(CONTEXT_CALLABLE_ALREADY_EXISTS
					+ callable.getToken());
		}
		callables.put(callable.getToken(), callable);
	}

	private void testId(ContextElement element, String elementName,
			Collection<? extends ContextElement> list) {
		if (list.contains(element)) {
			throw new CTDException("Já existe um elemento do tipo "
					+ elementName + " com o ID '" + element.getId()
					+ "' presente no contexto.");
		}
	}

	/**
	 * Informa se o contexto já foi compilado ou não. Quando o contexto é
	 * compilado, regras e processos (sessão dinâmica) são validados e
	 * compilados.
	 * 
	 * @return <code>true</code> se já foi compilado ou <code>false</code> se
	 *         não foi.
	 */
	public boolean isBuilt() {
		return built;
	}

	/**
	 * Executa o processo de compilação de um contexto. A compilação irá
	 * executar a validação e compilação das regras e processos.
	 * 
	 */
	public Context build() {
		testFrozen();
		copyToMap(domains, mapDomains);
		copyToMap(metadatas, mapMetadatas);
		copyToMap(scopes, mapScopes);
		copyToMap(messages, mapMessages);
		copyToMap(rules, mapRules);
		copyToMap(processes, mapProcesses);
		slang.getSyntagma().setBehaveMap(behaveMap);
		for (Callable callable : callables.values()) {
			callable.build(slang);
		}
		built = true;
		return this;
	}

	/**
	 * Fecha o contexto para futuras alterações. Este método é invocado após
	 * todos os elementos de um contexto terem sido inseridos previamente.
	 */
	@Override
	public void freeze() {
		if (!built) {
			throw new CTDException(CONTEXT_NOT_BUILT);
		}
		super.freeze();
		constants.freeze();
		((FreezableList<Variable>) variables).freeze();
		((FreezableList<Domain>) domains).freeze();
		((FreezableList<Metadata>) metadatas).freeze();
		((FreezableList<Message>) messages).freeze();
		((FreezableList<Scope>) scopes).freeze();
		((FreezableList<Rule>) rules).freeze();
		((FreezableList<Process>) processes).freeze();
		dataMap.freeze();
		lang.getSyntax().freeze(); // congela a sintaxe
		slang.getSyntagma().freeze(); // congela o framework
	}

	private void copyToMap(List<? extends ContextElement> list, Map map) {
		for (ContextElement o : list) {
			map.put(o.getId(), o);
		}
	}

	// *********************************
	// ********** RECUPERAÇÃO **********
	// *********************************

	// ********** CONST **********

	/**
	 * Informa se o token representa uma constante interna.
	 * 
	 * @param token
	 *            O token (name da constante) desejado.
	 * @return <code>true</code> se o token representa uma constante
	 *         <code>false</code> se não existir.
	 */
	public boolean hasConst(String token) {
		return constants.containsKey(token);
	}

	public List<Constant> getConstants() {
		return new ArrayList<Constant>(constants.values());
	}

	public Constant getConst(String name) {
		return constants.get(name);
	}

	// ********** VAR **********

	public List<Variable> getVariables() {
		return new ArrayList<Variable>(variables);
	}

	// ********** DOMAIN **********
	public boolean hasDomain(String domainId) {
		return mapDomains.containsKey(domainId);
	}

	public List<Domain> getDomains() {
		return new ArrayList<Domain>(domains);
	}

	public Domain getDomain(String domainId) {
		return mapDomains.get(domainId);
	}

	// ********** METADATA **********

	public boolean hasMetadata(String metadataId) {
		return mapMetadatas.containsKey(metadataId);
	}

	public Metadata getMetadata(String metadataId) {
		return mapMetadatas.get(metadataId);
	}

	public List<Metadata> getMetadatas() {
		return new ArrayList<Metadata>(metadatas);
	}

	// ********** SCOPE **********

	public List<Scope> getScopes() {
		return new ArrayList<Scope>(scopes);
	}

	public Scope getScope(String scopeId) {
		return mapScopes.get(scopeId);
	}

	// ********** MESSAGE **********
	public List<Message> getMessages() {
		return new ArrayList<Message>(messages);
	}

	public boolean hasMessage(String token) {
		return mapMessages.containsKey(token);
	}

	public Message getMessage(String name) {
		return mapMessages.get(name);
	}

	// ********** RULE **********
	/**
	 * @return the domains
	 */
	public List<Rule> getRules() {
		return new ArrayList<Rule>(rules);
	}

	public Rule getRule(String ruleId) {
		return mapRules.get(ruleId);
	}

	// ********** PROCESS **********

	public List<Process> getProcesses() {
		return new ArrayList<Process>(processes);
	}

	public Process getProcess(String id) {
		return mapProcesses.get(id);
	}

	// ********** CALLABLE **********

	public Set<String> getCallableIds() {
		return new TreeSet<String>(callables.keySet());
	}

	public Callable getCallable(String token) {
		return callables.get(token);
	}

	// **************************
	// ********** ETC ***********
	// **************************

	public Lang getLang() {
		return lang;
	}

	public Slang getSlang() {
		return slang;
	}

	// *********** GET / SET **********

	@Override
	public String toDebug() {
		StringBuilder sb = new StringBuilder();
		sb.append("CONTEXT " + getId() + "\n");
		for (Constant constant : constants.values()) {
			sb.append(constant.toDebug());
			sb.append("\n");
		}
		for (Variable variable : variables) {
			sb.append(variable.toDebug());
			sb.append("\n");
		}
		for (Domain domain : domains) {
			sb.append(domain.toDebug());
			sb.append("\n");
		}
		for (Metadata metadata : metadatas) {
			sb.append(metadata.toDebug());
			sb.append("\n");
		}
		for (Scope scope : scopes) {
			sb.append(scope.toDebug());
			sb.append("\n");
		}
		for (Message message : messages) {
			sb.append(message.toDebug());
			sb.append("\n");
		}
		for (Rule rule : rules) {
			sb.append(rule.toDebug());
			sb.append("\n");
		}
		for (Process process : processes) {
			sb.append(process.toDebug());
			sb.append("\n");
		}
		return sb.toString();
	}

	public FieldFactory getFieldFactory() {
		return fieldFactory;
	}

	public DataMap getDataMap() {
		return dataMap;
	}

	public BehaveMap getBehaveMap() {
		return behaveMap;
	}

}
