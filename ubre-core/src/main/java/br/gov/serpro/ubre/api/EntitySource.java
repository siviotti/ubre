package br.gov.serpro.ubre.api;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.framework.Scope;

/**
 * @author douglas
 * 
 * @param <T>
 *            O tipo da entidade
 */
public abstract class EntitySource<T> {

	private T entity;
	private Scope scope;

	public EntitySource(T entity, Scope scope) {
		super();
		this.entity = entity;
		if (scope == null) {
			throw new CTDException("main scope não pode ser nulo");
		}
		this.scope = scope;
	}

	/**
	 * Cria um DataContainer que representa a instância da entidade origem.
	 */
	public abstract void refreshContainer(DataContainer container);

	public abstract void refreshEntity(DataContainer container);

	/**
	 * Retorna o escopo principal.
	 * 
	 * @return
	 */
	public Scope getScope() {
		return scope;
	}

}
