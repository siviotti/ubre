package br.gov.serpro.ubre.lang.keyword.binary.compare.comparation;


/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 *
 */
public class StringComparation implements Comparation{

	public int compare(Object obj1, Object obj2) {
		return ((String) obj1).compareTo((String) obj2);
	}

}
