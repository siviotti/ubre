package br.gov.serpro.ubre.slang.command.field;

import static br.gov.serpro.ubre.slang.keyword.TargetType.FIELD;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.data.field.Field;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;
import br.gov.serpro.ubre.slang.keyword.action.CommandKeyword;

/**
 * Comando que seta o valor de um campo.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 11/10/2015
 */
public class SetvalCommand extends FieldCommand {

	public SetvalCommand() {
		super(new CommandKeyword("setval", FIELD, StatementType.OBJECT));
	}

	public void execute(DataContainer container, String targetToken, Statement parameter) {
		Field field = container.getField(targetToken);
		field.setValue(parameter.result(container));
	}

}
