package br.net.ubre.slang.command.etc;

import static br.net.ubre.lang.statement.StatementType.OBJECT;
import static br.net.ubre.slang.keyword.TargetType.SIMPLE_VAR;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.slang.command.GenericCommand;
import br.net.ubre.slang.keyword.action.CommandKeyword;

/**
 * Comando que serve para setar o valor de um campo ou variável (var statement).
 * CAso a variável não esteja no Container ela será criada se existir no
 * DataMap.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 11 de out de 2015
 */
public class PutCommand extends GenericCommand {

	public PutCommand() {
		super(new CommandKeyword("put", SIMPLE_VAR, OBJECT));
	}

	public void execute(DataContainer container, String targetToken,
			Statement parameter) {
		if (!container.contains(targetToken)) {
			container.createVar(targetToken,
					container.getDataMap().getType(targetToken));
		}
		container.setValue(targetToken, parameter.result(container));
	}

}
