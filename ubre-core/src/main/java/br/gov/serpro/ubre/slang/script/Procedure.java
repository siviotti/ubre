package br.gov.serpro.ubre.slang.script;

import java.util.List;

import br.gov.serpro.ubre.slang.code.CodeLine;

/**
 * Uma procedure é um Script com um nome que pode ser invocado dentro de uma
 * instância da linguagem.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 09/10/2015
 */
public class Procedure extends Script {
	

	private final String name;
	
	public Procedure(String name, List<CodeLine> codeLines) {
		super(codeLines);
		this.name = name;
	}


	// Equals , hashcode e toString
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	// Get / Set

	public String getName() {
		return name;
	}
	
	
	
	

}
