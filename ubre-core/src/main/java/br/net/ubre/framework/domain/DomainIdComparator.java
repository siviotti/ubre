package br.net.ubre.framework.domain;

import java.util.Comparator;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/03/2015
 * 
 */
public class DomainIdComparator implements Comparator<DomainItem> {

	public int compare(DomainItem item1, DomainItem item2) {
		return item1.getId().compareTo(item2.getId());
	}

}
