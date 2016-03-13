package br.gov.serpro.ubre.framework.domain;

import java.util.Comparator;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public enum DomainSortType {

	ID(new DomainIdComparator()), DESCRIPTION(new DomainLabelComparator());

	private Comparator<DomainItem> comparator;

	private DomainSortType(Comparator<DomainItem> comparator) {
		this.comparator = comparator;
	}

	/**
	 * @return the comparator
	 */
	public Comparator<DomainItem> getComparator() {
		return comparator;
	}
}
