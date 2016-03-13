package br.net.ubre.slang.command.console;

import static br.net.ubre.lang.statement.StatementType.OBJECT;
import static br.net.ubre.slang.keyword.TargetType.NONE;

import java.io.PrintStream;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.slang.command.GenericCommand;
import br.net.ubre.slang.keyword.Keyword;
import br.net.ubre.slang.keyword.action.CommandKeyword;

/**
 * Comando para imprimir uma mensagem. Usado para debug.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class PrintCommand extends GenericCommand {
	
	private PrintStream out;

	public PrintCommand(PrintStream out) {
		super(new CommandKeyword("print", NONE, OBJECT));
		this.out = out;
	}

	public void execute(DataContainer container, String targetToken,
			Statement  parameter) {
		out.println(parameter.result(container));
	}

}
