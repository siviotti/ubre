package br.gov.serpro.ubre.lang;

import br.gov.serpro.ubre.internal.Str;

/**
 * Lista de erros possíveis da linguagem.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 25/03/2015
 * 
 */
public enum LangError {

	/**
	 * GRUPO Erros de Parsing (erro na estrutura ou escrita dos tokens) - O
	 * compilador não entendeu o que deve ser feito ou não identificou algum
	 * token.
	 */
	E10(Str.LANG_ERROR_10_PARSING),
	/**
	 * GRUPO Erros de Sintaxe (erro na forma/ordem como os tokens se relacionam)
	 * - O compilador não permite que os tokens sejam usados da forma como estão
	 * sendo usados.
	 */
	E20(Str.LANG_ERROR_20_SYNTAX),
	/**
	 * GRUPO Erros de Semântica (erro relacionado ao oconteúdo dos tokens) - O
	 * compilador não permite uma certa combinação de tokens que não faz sentido
	 * (tipos diferentes, por exemplo).
	 */
	E30(Str.LANG_ERROR_30_SEMANTIC),
	/**
	 * [Erro de Semântica] - Erro semântico para tentativa de usar tokens de
	 * tipos imcompatíveis (atribuição, aritmética, comparação etc).
	 */
	E31(Str.LANG_ERROR_31_TYPES),
	/**
	 * GRUPO Erros de execução (erro durante a execução de uma expressão) - O
	 * compilador não pega este tipo de erro. Ele ocorre durante a execução de
	 * uma expressão/script já devidamente parseado e compilado.
	 */
	E40(Str.LANG_ERROR_40_RUNTIME),
	/** ArrayIndexOutOfBounds */
	E41(Str.LANG_ERROR_41_INDEX),
	/** Erros de referência de campos/variáveis que são nulos */
	E42(Str.LANG_ERROR_42_NULL);

	private String description;
	private String label;

	private LangError(String description) {
		this.description = description;
		label = "[" + name() + " " + description + "] ";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return description;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

}
