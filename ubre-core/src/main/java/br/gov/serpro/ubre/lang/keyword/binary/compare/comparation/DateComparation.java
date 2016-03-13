package br.gov.serpro.ubre.lang.keyword.binary.compare.comparation;

import java.util.Date;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public class DateComparation implements Comparation {

	public int compare(Object obj1, Object obj2) {
		return ((Date) obj1).compareTo((Date) obj2);
	}

}
