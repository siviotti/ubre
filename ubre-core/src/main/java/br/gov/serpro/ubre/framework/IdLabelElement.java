package br.gov.serpro.ubre.framework;

import br.gov.serpro.ubre.util.GenericFreezable;

/**
 * Representa um elemento que contém um ID e um Label.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/06/2015
 * 
 */
public class IdLabelElement extends GenericFreezable implements IdLabel {

	private String id;
	private String label;

	public IdLabelElement(String id, String label) {
		super();
		this.id = id;
		this.label = label;
		if (id == null) {
			throw new NullPointerException("'id' must not be null!");
		}
		if (label == null) {
			throw new NullPointerException("'label' must not be null em " + id);
		}
	}

	// ********** equals, toString e hascode **********
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!obj.getClass().equals(getClass())) {
			return false;
		}
		return id.equals(((IdLabelElement) obj).id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id;
	}

	// ********** GET / SET **********

	/**
	 * @return the name
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the name to set
	 */
	public void setId(String id) {
		testFrozen();
		this.id = id;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		testFrozen();
		this.label = label;
	}

	public String asIdLabel() {
		return id + " " + label;
	}

}
