package br.net.ubre.framework;

import java.util.Date;

/**
 * Superclasse para os elementos internos a um contexto.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 20/03/2015
 * 
 */
public class ContextElement extends IdLabelElement {

	private Date begin;

	private Date end;

	private String tags;

	public ContextElement(String id, String label, Date begin,
			Date end, String tags) {
		super(id, label);
		this.begin = begin;
		this.end = end;
		this.tags = tags;
	}



	// ********** GET / SET **********
	/**
	 * @return the begin
	 */
	public Date getBegin() {
		return begin;
	}

	/**
	 * @param begin
	 *            the begin to set
	 */
	public void setBegin(Date begin) {
		testFrozen();
		this.begin = begin;
	}

	/**
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(Date end) {
		testFrozen();
		this.end = end;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * Determina se o elemento tem internamente a tag passada por parâmetro.
	 * 
	 * @param tag
	 *            A tag buscada.
	 * @return <code>true</code> se a tag está presente no elemento ou
	 *         <code>false</code> se não está.
	 */
	public boolean hasTag(String tag) {
		return tags != null && tags.contains(tag);
	}

}
