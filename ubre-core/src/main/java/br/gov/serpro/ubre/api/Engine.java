package br.gov.serpro.ubre.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.data.container.StandardDataContainer;
import br.gov.serpro.ubre.exception.CTDApiException;
import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.framework.Scope;

/**
 * Classe de fachada do Framework. Uma Engine representa uma instância do motor
 * de regras e atributos para uma sessão de usuário (Escopo de Sessão). Uma
 * Engine pode criar vários DataContainers, mas sempre para uma mesma sessão.
 * Dentro dessa instância está o <code>Context</code> com o conteúdo que vale
 * para todas as sessões (escopo de Aplicação).
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 14/10/2015
 */
// @SessionScoped
public class Engine {

	public static final String ENGINE_CONTEXT_NOT_FROZEN = "O contexto não está congelado! É necessário congelar o contexto (chamar freeze()) antes de criar uma Engine.";

	public static final String ENGINE_PARENT_NOT_FOUND = "Container pai não encontrado:";

	public static final String ENGINE_SCOPE_NOT_FOUND = "Escopo não encontrado:";

	public static final String ENGINE_ID_ALREADY_USED = "Já existe um Container com este id:";

	private String id;
	private Context context;
	private Map<String, DataContainer> containers = new HashMap<String, DataContainer>();

	public Engine(String id, Context context) {
		super();
		if (!context.isFrozen()) {
			throw new CTDApiException(ENGINE_CONTEXT_NOT_FROZEN);
		}
		this.context = context;
		this.id = id;
	}

	public DataContainer createContainer(String id, String scopeId) {
		return createContainer(id, scopeId, null);
	}

	/**
	 * Cria um DataContainer a partir de um determinado escopo.
	 * 
	 * @param id
	 *            O identificador do container dentro da Engine.
	 * @param scopeId
	 *            O identificador do escopo que definirá quais campos serão
	 *            criados. <B>Use <code>null</code> para nenhum criar um
	 *            container com todos os campos.</B>
	 * @param parentId
	 *            Um possível Containerparent. <B>Use <code>null</code> para
	 *            nenhum.</B>
	 * @return Uma instância de <code>DataContainer</code> devidamente montado
	 *         com os campos do escopo definido.
	 */
	public DataContainer createContainer(String id, String scopeId,
			String parentId) {
		if (containers.containsKey(id)) {
			throw new CTDApiException(ENGINE_ID_ALREADY_USED + id);
		}
		Scope scope = null;
		if (scopeId != null) {
			scope = context.getScope(scopeId);
			if (scope == null) {
				throw new CTDApiException(ENGINE_SCOPE_NOT_FOUND + scopeId);
			}
		}
		DataContainer parent = null;
		if (parentId != null) {
			parent = containers.get(parentId);
			if (parent == null) {
				throw new CTDApiException(ENGINE_PARENT_NOT_FOUND + parentId);
			}
		}
		DataContainer container = new StandardDataContainer(id,
				context.getDataMap(), scope, context.getFieldFactory(), parent);
		return container;
	}

	/**
	 * Avalia e resolve uma expressão com base em um Container.
	 * 
	 * @param container
	 *            O container com com os dados utilizados na execução.
	 * @param expression
	 *            A expressão a ser avaliada e resolvida.
	 * @return O possível resultado da expressão.
	 */
	public Object eval(DataContainer container, String expression) {
		return context.getLang().eval(container, expression);
	}

	/**
	 * Compila e executa um Script contido em Strings agrupadas e ordenadas em
	 * um ArrayLIst.
	 * 
	 * @param container
	 *            O Container com os dados sobre o qual o Script deve atuar.
	 * @param script
	 *            As linhas de um Script que será compilado e executado.
	 */
	public void run(DataContainer container, List<String> script) {
		context.getSlang().build(script).execute(container);
	}

	/**
	 * Chama e executa um objeto "Callable" [executável] (Rule, Event Procedure)
	 * pelo seu ID.
	 * 
	 * @param container
	 *            O Container com os dados sobre os quais o executável irá
	 *            atuar.
	 * @param callableId
	 *            O ID do objeto a ser chamado.
	 */
	public void call(DataContainer container, String callableId) {
		context.getBehaveMap().get(callableId).execute(container);
	}

	// GET / SET

	public String getId() {
		return id;
	}

	public Context getContext() {
		return context;
	}

}
