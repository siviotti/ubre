package br.gov.serpro.ubre.framework.rule;

/**
 * Fefine os tipos possíveis de regra.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 13/10/2015
 */
public enum RuleType {

	/** Regra cujo corpo (body) é um Script escrito na linguagem Slang */
	SCRIPT(false),
	/**
	 * Regra cujo corpo (body) é uma classe que implementa Executable é é
	 * apontada pela própria regra.
	 */
	STATIC(true),
	/**
	 * Regra cujo corpo (body) é indefinido na regra e será buscado no Classpath
	 * uma classe anotada com RuleBody
	 */
	DYNAMIC(true);

	private boolean code;

	private RuleType(boolean code) {
		this.code = code;
	}

	public boolean isCode() {
		return code;
	}

	public static RuleType get(String typeString) {
		for (RuleType ruleType: values() ){
			if (ruleType.name().equals(typeString)){
				return ruleType;
			}
		}
		return null;
	}

}
