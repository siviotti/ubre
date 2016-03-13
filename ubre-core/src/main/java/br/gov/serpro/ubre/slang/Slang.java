package br.gov.serpro.ubre.slang;

import java.util.List;

import br.gov.serpro.ubre.lang.Lang;
import br.gov.serpro.ubre.slang.build.ScriptBuilder;
import br.gov.serpro.ubre.slang.script.Script;

/**
 * Classe de fachada da linguagem de Script do CTD (SLang).
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 09/10/2015
 */
public class Slang {

	private Lang lang;
	private Syntagma syntagma;
	private ScriptBuilder builder;

	public Slang(Lang lang, Syntagma syntagma) {
		super();
		this.lang = lang;
		this.syntagma = syntagma;
		lang.getSyntax().setExternalKeywords(syntagma.getKeywordSet());
		builder = new ScriptBuilder(lang, syntagma);
	}
	
	// ********** API de Script **********

	/**
	 * Retorna um Script compilado a partir de um fonte seperado em linhas.
	 * 
	 * @param sourceLines
	 *            As linhas que compõe o código fonte.
	 * @return A instância de Script.
	 */
	public Script build(List<String> sourceLines) {
		return builder.build(sourceLines);
	}
	
	// GET / SET
	
	public Syntagma getSyntagma() {
		return syntagma;
	}

	public Lang getLang() {
		return lang;
	}

}
