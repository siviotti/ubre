package br.net.ubre.slang.command.field;

import static br.net.ubre.lang.statement.StatementType.BOOLEAN;
import static br.net.ubre.slang.keyword.TargetType.FIELD;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.data.field.Field;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.slang.keyword.action.CommandKeyword;

/**
 * Comando para manipular a propriedade "enabled" de um campo (Field).
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 18/09/2015
 */
public class EnableCommand extends FieldCommand{

	public EnableCommand() {
		super(new CommandKeyword("enable", FIELD, BOOLEAN));
	}

	public void execute(DataContainer container, String targetToken,
			Statement parameter) {
		Field field = getField(container, targetToken);
		field.setEnabled((Boolean)parameter.result(container));
	}

	@Override
	public Class<?> getParameterType() {
		return Boolean.class;
	}
	
	

}
