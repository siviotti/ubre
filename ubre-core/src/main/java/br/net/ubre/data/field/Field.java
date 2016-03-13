package br.net.ubre.data.field;

import java.util.List;

import br.net.ubre.data.var.Var;
import br.net.ubre.framework.Metadata;
import br.net.ubre.framework.domain.DomainItem;

/**
 * Interface que define os métodos do comportamento de um campo. Esta interface
 * define os métodos da parte editável de um campo (valor, obrigatório,
 * habilitado, visível, mensagem, dinâmico e domínio). A parte fixa é definida
 * no metadado. Há ainda "getType()" que é fixo, mas está presente para
 * simplificar o acesso ao tipo do campo.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public interface Field extends Var{


	
	/**
	 * Informa se o campo usa ou não um domínio fixo.
	 * 
	 * @return <code>true</code> se usa domínio ou <code>fals</code> se não usa.
	 */
	boolean useDomain();

	/**
	 * Informa se o campo é obrigatório (required).
	 * 
	 * @return the required
	 */
	boolean isRequired();

	/**
	 * Determina se o campo deve ser obrigatório ou não (required).
	 * 
	 * @param required
	 *            the required to set
	 */
	void setRequired(boolean required);

	/**
	 * Informa se o campo está habilitado (enabled) ou não.
	 * 
	 * @return the enabled
	 */
	boolean isEnabled();

	/**
	 * Determina se o campo deve estar habilitado (enabled) ou não.
	 * 
	 * @param enabled
	 *            the enabled to set
	 */
	void setEnabled(boolean enabled);

	/**
	 * @return the visible
	 */
	boolean isVisible();

	/**
	 * @param visible
	 *            the visible to set
	 */
	void setVisible(boolean visible);

	/**
	 * @return the message
	 */
	String getMessage();

	/**
	 * @param message
	 *            the message to set
	 */
	void setMessage(String message);

	/**
	 * Retorna o rótulo do campo.
	 * 
	 * @return O rótulo.
	 */
	String getLabel();

	/**
	 * Determina o rótulo do campo.
	 * 
	 * @param label
	 *            O rótulo que deve ser usado no campo.
	 */
	void setLabel(String label);

	/**
	 * @return the metadata
	 */
	Metadata getMetadata();

	/**
	 * @return the valid
	 */
	boolean isValid();

	/**
	 * @param valid
	 *            the valid to set
	 */
	void setValid(boolean valid);

	/**
	 * @return the dynamic
	 */
	boolean isDynamic();

	/**
	 * @param dynamic
	 *            the dynamic to set
	 */
	void setDynamic(boolean dynamic);

	/**
	 * Informa se o campo está fechado e não poderá receber novos valores.
	 * 
	 * @return <code>true</code> se está fechado ou <code>false</code> se não
	 *         está.
	 */
	boolean isClosed();

	/**
	 * "Fecha" o campo impedindo futuras edições de seu valor. Uma vez fechado
	 * um campo não pode ser reaberto.
	 */
	void close();

	/**
	 * @return
	 */
	String getName();

	/**
	 * @return the domainItems
	 */
	List<DomainItem> getItems();

	/**
	 * @param domainItems
	 *            the domainItems to set
	 */
	void setItems(List<DomainItem> domainItems);

}
