package br.gov.serpro.ubre.slang.command.console;

import static br.gov.serpro.ubre.lang.statement.StatementType.OBJECT;

import java.io.InputStream;
import java.util.Scanner;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.data.var.Var;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.slang.command.GenericCommand;
import br.gov.serpro.ubre.slang.keyword.TargetType;
import br.gov.serpro.ubre.slang.keyword.action.CommandKeyword;

/**
 * Comando ler um dado do teclado.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 22/102015
 * 
 */
public class InputCommand extends GenericCommand {

	private Scanner scanner;

	public InputCommand(InputStream in) {
		super(new CommandKeyword("input", TargetType.SIMPLE_VAR, OBJECT));
		scanner = new Scanner(in);
	}

	@Override
	public void execute(DataContainer container, String targetToken, Statement parameter) {
		String string = scanner.nextLine();
		Var var = container.getVar(targetToken);
		var.setString(string);
	}

}
