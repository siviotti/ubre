package br.gov.serpro.ubre.lang.keyword.binary.compare.comparation;


/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class NumericComparation implements Comparation {

	public int compare(Object obj1, Object obj2) {
		Number n1 = (Number) obj1;
		Number n2 = (Number) obj2;
		if (n1.doubleValue() > n2.doubleValue()){
			return 1;
		}
		if (n1.doubleValue() < n2.doubleValue()){
			return -1;
		}
		return 0;
	}

}
