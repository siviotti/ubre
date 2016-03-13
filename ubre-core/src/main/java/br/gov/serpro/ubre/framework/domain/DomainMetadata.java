package br.gov.serpro.ubre.framework.domain;

import br.gov.serpro.ubre.data.var.ValueType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 02/04/2015
 *
 */
public class DomainMetadata {

	private String name;
	private ValueType type;
	private int size;
	
	public DomainMetadata(String name, ValueType type, int size) {
		super();
		this.name = name;
		this.type = type;
		this.size = size;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public ValueType getType() {
		return type;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (size > 0){
			return name + ":" + type.name() + "(" + size + ")";
		}
		return name + ":" + type.name();
	}
	
	
}
