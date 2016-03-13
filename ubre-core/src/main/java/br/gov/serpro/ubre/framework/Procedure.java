package br.gov.serpro.ubre.framework;

import java.util.Date;

/**
 * @author Douglas Siviotti (073.116.317-69).
 * @since 09/10/2015
 */
public class Procedure extends ContextElement{

	public Procedure(String id, String label, Date begin, Date end, String tags) {
		super(id, label, begin, end, tags);

	}

}
