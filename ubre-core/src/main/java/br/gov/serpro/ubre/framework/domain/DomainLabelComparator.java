package br.gov.serpro.ubre.framework.domain;

import java.util.Comparator;

/**
 * Comparador que usa as descrições.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public class DomainLabelComparator implements Comparator<DomainItem> {

	public int compare(DomainItem item1, DomainItem item2) {
		return item1.getLabel().compareTo(item2.getLabel());
	}

}
