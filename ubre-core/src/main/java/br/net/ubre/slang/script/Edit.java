package br.net.ubre.slang.script;

import java.util.ArrayList;
import java.util.List;

/**
 * Editor de Script para testes e concatenação simples.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 22/10/2015
 */
public class Edit {

	private List<String> lines = new ArrayList<String>();
	
	public void in(String line){
		lines.add(line);
	}

	public List<String> getLines() {
		return lines;
	}


}
