package br.net.ubre.slang.command.etc;

import static br.net.ubre.lang.statement.StatementType.VOID;
import static br.net.ubre.slang.keyword.TargetType.CALLABLE;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.slang.Syntagma;
import br.net.ubre.slang.command.GenericCommand;
import br.net.ubre.slang.keyword.action.CommandKeyword;

/**
 * Chama uma regra e a executa. A regra vai fazer a condição e executar a as
 * ações "then" se for verdadeira ou as ações "else" se for falsa. Se houver
 * necessidade de executar apenas as ações de uma regra se rodar a condição
 * utilize o comando perform.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 29/05/2015
 * 
 */
public class CallCommand extends GenericCommand {

	private Syntagma syntagma;

	public CallCommand(Syntagma syntagma) {
		super(new CommandKeyword("call", CALLABLE, VOID));
		this.syntagma = syntagma;
	}

	public void execute(DataContainer container, String targetToken, Statement parameter) {
		syntagma.getBehaveMap().get(targetToken).execute(container);
	}
}
