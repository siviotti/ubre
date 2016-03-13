package br.gov.serpro.ubre.framework.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.gov.serpro.ubre.data.var.ValueType;
import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.framework.ContextElement;
import br.gov.serpro.ubre.util.Debuggable;

/**
 * Representa um tipo de domínio.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class Domain extends ContextElement implements Debuggable {

	private List<DomainMetadata> metas;
	private Map<String, DomainMetadata> mapMetas;
	private List<DomainItem> items;
	private Map<Object, DomainItem> mapItems = new TreeMap<Object, DomainItem>();
	private boolean integerId;

	public Domain(String name, String label, List<DomainMetadata> metas, Date begin, Date end, String tags) {
		super(name, label, begin, end, tags);
		this.mapMetas = new HashMap<String, DomainMetadata>();
		this.metas = new ArrayList<DomainMetadata>();
		if (metas != null) {
			this.metas.addAll(metas);
			for (DomainMetadata meta : metas) {
				this.mapMetas.put(meta.getName(), meta);
			}
		}
		this.items = new ArrayList<DomainItem>();
	}

	// Métodos controlados para edição da lista de itens
	
	public void clear(){
		testFrozen();
		items.clear();
		mapItems.clear();
	}
	
	public void addItem(DomainItem item) {
		testFrozen();
		items.add(item);
		mapItems.put(item.getId(), item);
	}

	/**
	 * Retorna um novo ArrayList com os itens de domínio.
	 * 
	 * @return the itens Os itens internos em uma cópia do ArrayList.
	 */
	public List<DomainItem> getItems() {
		return new ArrayList<DomainItem>(items);
	}

	public List<DomainMetadata> getMetadatas() {
		return new ArrayList<DomainMetadata>(metas);
	}

	/**
	 * retorna um elemento de domínio a partir de seu ID.
	 * 
	 * @param id
	 *            O ID.
	 * @return O elemento ou null.
	 */
	public DomainItem getById(Object id) {
		return mapItems.get(id);
	}

	public String toDebug() {
		StringBuilder sb = new StringBuilder();
		sb.append("  DOMAIN " + getId() + " " + getLabel() + " meta:" + mapMetas.values());
		for (DomainItem item : items) {
			sb.append("\n    " + item.toDebug());
		}
		return sb.toString();
	}

	public boolean fieldExists(String name) {
		return mapMetas.containsKey(name);
	}

	public ValueType fieldType(String name) {
		if (!mapMetas.containsKey(name)) {
			throw new CTDException("Atributo '" + name + "' não encontrado no domínio '" + getId() + "'.");
		}
		return mapMetas.get(name).getType();
	}

	/**
	 * @return the integerId
	 */
	public boolean isIntegerId() {
		return integerId;
	}

	/**
	 * @param integerId the integerId to set
	 */
	public void setIntegerId(boolean integerId) {
		testFrozen();
		this.integerId = integerId;
	}

}
