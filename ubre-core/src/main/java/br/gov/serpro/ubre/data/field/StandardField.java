package br.gov.serpro.ubre.data.field;

import java.util.ArrayList;
import java.util.List;

import br.gov.serpro.ubre.data.var.StandardVar;
import br.gov.serpro.ubre.data.var.ValueType;
import br.gov.serpro.ubre.data.var.Var;
import br.gov.serpro.ubre.data.var.VarFactory;
import br.gov.serpro.ubre.framework.Metadata;
import br.gov.serpro.ubre.framework.domain.DomainItem;

/**
 * Implementaçao padrão para a interface Field. Se não houver maiores
 * necessidades um campo será instanciado desta classe.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 18/05/2015
 * 
 */
public class StandardField implements Field {

	public static final String CLOSED_FIELD = "Impossível alterar o valor de um Campo fechado:";
	private Metadata metadata;
	private boolean required;
	private boolean enabled;
	private boolean visible;
	private boolean valid;
	private boolean dynamic;
	private boolean closed;
	private String label;
	private String message;
	private List<DomainItem> domainItems;
	private Var var;

	public StandardField(String token, Metadata metadata) {
		var = new VarFactory().create(token, metadata.getType());
		this.metadata = metadata;
		if (metadata.getDomain() != null) {
			domainItems = new ArrayList<DomainItem>(metadata.getDomain()
					.getItems());
		}
	}

	// métodos específicos para as subclasses

	protected void require() {
		required = true;
	}

	// equals, toString e hashcodfe

	/**
	 * critério de igualdade: o metadado é igual e o valor também.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Field)) {
			return false;
		}
		Field field = (Field) obj;
		if (!field.getMetadata().equals(getMetadata())) {
			return false;
		}
		return (getValue() != null) ? getValue().equals(field.getValue())
				: field.getValue() == null;
	}

	@Override
	public int hashCode() {
		return (getValue() != null) ? getValue().hashCode() : 0;
	}

	@Override
	public String toString() {
		return (getValue() != null) ? getValue().toString() : "";
	}

	// GET / SET

	public ValueType getType() {
		return metadata.getType();
	}

	public boolean useDomain() {
		return metadata.getDomain() != null;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @param required
	 *            the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible
	 *            the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the metadata
	 */
	public Metadata getMetadata() {
		return metadata;
	}

	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid
	 *            the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return the dynamic
	 */
	public boolean isDynamic() {
		return dynamic;
	}

	/**
	 * @param dynamic
	 *            the dynamic to set
	 */
	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
	}

	public String getName() {
		return metadata.getId();
	}

	/**
	 * @return the domainItems
	 */
	public List<DomainItem> getItems() {
		return domainItems;
	}

	/**
	 * @param domainItems
	 *            the domainItems to set
	 */
	public void setItems(List<DomainItem> domainItems) {
		this.domainItems = domainItems;
	}

	public boolean isClosed() {
		return closed;
	}

	public void close() {
		this.closed = true;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String getToken() {
		return var.getToken();
	}

	@Override
	public Object getValue() {
		return var.getValue();
	}

	@Override
	public void setValue(Object value) {
		var.setValue(value);
	}

	@Override
	public void setString(String string) {
		var.setString(string);
	}

	@Override
	public String getString() {
		return var.getString();
	}


}
