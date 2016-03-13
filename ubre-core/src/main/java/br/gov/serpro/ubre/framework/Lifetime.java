package br.gov.serpro.ubre.framework;

import java.util.Date;

/**
 * Representa uma vigência (tempo de vida) de um item de um contexto, próprio
 * contexto e de uma instrução de Script.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 26/05/2015
 * 
 */
public class Lifetime {

	private Date begin;
	private Date end;
	
	public Lifetime(){
		this(new Date(), null);
	}

	public Lifetime(Date begin) {
		this(begin, null);
	}

	public Lifetime(Date begin, Date end) {
		super();
		if (begin == null) {
			throw new NullPointerException("'begin' cannot be null");
		}
		this.begin = begin;
		this.end = end;
	}

	/**
	 * Indica se a vigência está fechada, ou seja, se tem data de fim.
	 * 
	 * @return
	 */
	public boolean hasEnd() {
		return end != null;
	}

	public boolean in(Date date) {
		if (date == null) {
			return false;
		}
		return !begin.after(date) && (hasEnd()) ? !end.before(date) : true;
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
		this.end = end;
	}

}
