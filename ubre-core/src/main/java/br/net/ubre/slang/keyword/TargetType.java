package br.net.ubre.slang.keyword;

import static br.net.ubre.slang.keyword.TargetGroup.BEHAVE;
import static br.net.ubre.slang.keyword.TargetGroup.DATA;

/**
 * Tipos possíveis de alvo. Um alvo sempre entre na linha como uma String que o
 * identifica. Se o conteúdo pode não ser uma String, então não há alvo e sim um
 * parâmetro.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 09/10/2015
 */
public enum TargetType {

	/** Nenhum target */
	NONE(null),
	// Targets de Alvos que estão no DataMap
	/** Somante Constantes presente no DataMap */
	CONST(DATA),
	/** Somante Var presente no DataMap */
	SIMPLE_VAR(DATA),
	/** Somente Field presente no DataMap */
	FIELD(DATA),
	/** Var ou Field presente no DataMap */
	VAR_OR_FIELD(DATA),
	/**
	 * GENÉRICO: Var, Field, Literal, Const ou Message (TypedData) presente no
	 * DataMap
	 */
	ANY_DATA(DATA),
	// Targets de Alvos que estão no BehaveMap
	/** Procedure */
	PROCEDURE(BEHAVE),
	/** Regra - pode fazer referência à procedure */
	RULE(BEHAVE),
	/** Evento de um Processo - pode fazer referência à procedure e às Regras */
	EVENT(BEHAVE),
	/** GENÉRICO: Todos os de comportamento: Procedure, Rule e Event */
	CALLABLE(BEHAVE), ;

	private TargetGroup group;

	private TargetType(TargetGroup level) {
		this.group = level;
	}

	public boolean isData() {
		return TargetGroup.DATA.equals(group);
	}

	public boolean isBehave() {
		return TargetGroup.BEHAVE.equals(group);
	}

	public TargetGroup getGroup() {
		return group;
	}

}
