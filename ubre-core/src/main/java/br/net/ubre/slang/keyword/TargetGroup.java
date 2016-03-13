package br.net.ubre.slang.keyword;

/**
 * O nível do alvo determina se é um alvo capaz de ser convertido para um
 * Statement (Data, Var e Field) ou é um elemento do COntexto (Regra, Processo,
 * Procedure etc).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 12/10/2015
 */
public enum TargetGroup {

	/**
	 * Alvo presente no DataMap (Const, Var, Field e Message).
	 */
	DATA,
	/**
	 * Alvo presente no BehaveMap (Procedure, Rule e Event).
	 */
	BEHAVE;
}
