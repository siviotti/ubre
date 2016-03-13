package br.net.ubre.framework;

import java.util.Date;

import br.net.ubre.data.var.ValueType;
import br.net.ubre.data.var.Var;
import br.net.ubre.framework.domain.Domain;
import br.net.ubre.util.Debuggable;

/**
 * Representa um metadado que dará origem a um campo.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 19/03/2015
 * 
 */
public class Metadata extends ContextElement implements Var, Debuggable {

	private String description; // descricao
	private ValueType type; // o tipo - tipo
	private int size; // o tamanho -
	private String format; // o formato, quando for o caso
	private Domain domain; // domínio do campo, caso haja
	private String frame; // dá origem à entity
	private String form; // dá origem ao form
	private boolean array; // se o campo e ocorrente (multivalorado)
	private boolean persistent; // se o campo é gravado na base (físico)
	private Object value; //valor default

	public Metadata(ValueType type) {
		super(type.name(), type.name(), null, null, null);
		this.type = type;
		size = -1;
	}

	private Metadata(String id, ValueType valueType, int size, boolean array) {
		super(id, "auto_meta_" + id, null, null, null);
		this.type = valueType;
		this.size = size;
		this.array = array;
	}

	public Metadata(String id, String label, String description,
			ValueType type, int size, String format, Domain domain,
			String frame, String form, boolean array, boolean persistent,
			Date begin, Date end, String tags) {
		super(id, label, begin, end, tags);
		this.description = description;
		this.type = type;
		this.size = size;
		this.format = format;
		this.domain = domain;
		this.frame = frame;
		this.form = form;
		this.array = array;
		this.persistent = persistent;
	}
	
	// ********** Implementação de Var **********
	
	/**
	 * @return the type
	 */
	public ValueType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ValueType type) {
		testFrozen();
		this.type = type;
	}

	public void setString(String string) {
		
	}

	public String getString() {
		return null;
	}

	public String getToken() {
		return getId();
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		testFrozen();
		this.value = value;
	}

	// ********** GET / SET **********

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		testFrozen();
		this.description = description;
	}


	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		testFrozen();
		this.size = size;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		testFrozen();
		this.format = format;
	}

	/**
	 * @return the domain
	 */
	public Domain getDomain() {
		return domain;
	}

	/**
	 * @param domain
	 *            the domain to set
	 */
	public void setDomain(Domain domain) {
		testFrozen();
		this.domain = domain;
	}

	/**
	 * @return the frame
	 */
	public String getFrame() {
		return frame;
	}

	/**
	 * @param frame
	 *            the frame to set
	 */
	public void setFrame(String frame) {
		testFrozen();
		this.frame = frame;
	}

	/**
	 * @return the form
	 */
	public String getForm() {
		return form;
	}

	/**
	 * @param form
	 *            the form to set
	 */
	public void setForm(String form) {
		testFrozen();
		this.form = form;
	}

	/**
	 * @return the array
	 */
	public boolean isArray() {
		return array;
	}

	/**
	 * @param array
	 *            the array to set
	 */
	public void setArray(boolean array) {
		testFrozen();
		this.array = array;
	}

	/**
	 * @return the persistent
	 */
	public boolean isPersistent() {
		return persistent;
	}

	/**
	 * @param persistent
	 *            the persistent to set
	 */
	public void setPersistent(boolean persistent) {
		testFrozen();
		this.persistent = persistent;
	}

	public String toDebug() {
		return "  META " + getId() + " " + getLabel() + " type:" + type;
	}



}
