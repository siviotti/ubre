package br.gov.serpro.ubre.framework.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.framework.ContextElement;
import br.gov.serpro.ubre.util.Debuggable;

/**
 * Representa um item de domínio.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class DomainItem extends ContextElement implements Comparable<DomainItem>, Debuggable {

	private static final String MUST_IMPLEMENT_COMPARATOR = "O ID de um item de domínio deve ser implementar Comparable. Exemplo: String, Integer etc.";

	Domain domain;
	private Map<String, Object> attributes = new HashMap<String, Object>();

	public DomainItem(String id, String label, Date begin, Date end, String tags, Map<String, Object> attributes) {
		super(id, label, begin, end, tags);
		if (id == null) {
			throw new NullPointerException("'id' não pode ser nulo");
		}
		if (!(id instanceof Comparable<?>)) {
			throw new CTDException(MUST_IMPLEMENT_COMPARATOR);
		}
		if (attributes != null) {
			this.attributes.putAll(attributes);
		}
	}

	public Object getIdAsObject() {
		if (domain.isIntegerId()) {
			return Integer.parseInt(getId());
		}
		return getId();
	}

	/**
	 * @return the domain
	 */
	public Domain getDomain() {
		return domain;
	}

	// Métodos para atender possívei conexão com JSF

	/**
	 * Deve ser associado à tag 'itemValue'.
	 * 
	 * @return
	 */
	public Object getValue() {
		return getIdAsObject();
	}

	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	public String toDebug() {
		return "ITEM " + getId() + " " + getLabel();
	}

	@Override
	public void setId(String id) {
		super.setId(id);

	}

	public int compareTo(DomainItem obj) {
		return getId().compareTo(obj.getId());
	}

}
