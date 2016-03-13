package br.net.ubre.lang.keyword.binary.compare.comparation;

/**
 * Comparaçao entre inteiros.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 15/10/2015
 */
public class IntegerComparation implements Comparation {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.gov.serpro.ctd.lang.keyword.binary.compare.Comparation#compare(java
	 * .lang.Object, java.lang.Object)
	 */
	public int compare(Object obj1, Object obj2) {
		return ((Integer) obj1).compareTo((Integer) obj2);
	}

}
