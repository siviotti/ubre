package br.gov.serpro.ubre.slang.command.field;

import static br.gov.serpro.ubre.lang.statement.StatementType.BOOLEAN;
import static br.gov.serpro.ubre.slang.keyword.TargetType.FIELD;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.data.field.Field;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.slang.keyword.action.CommandKeyword;

/**
 * Comando para manipular a propriedade "valid" de um campo (Field).
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 18/09/2015
 */
public class CheckCommand extends FieldCommand {

	public CheckCommand() {
		super(new CommandKeyword("enable", FIELD, BOOLEAN));
	}

	public void execute(DataContainer container, String targetToken, Statement parameter) {
		Field field = getField(container, targetToken);
		field.setValid((Boolean) parameter.result(container) );
	}

	@Override
	public Class<?> getParameterType() {
		return Boolean.class;
	}

}
