package br.net.ubre.slang.command.field;

import static br.net.ubre.lang.statement.StatementType.BOOLEAN;
import static br.net.ubre.lang.statement.StatementType.VOID;
import static br.net.ubre.slang.keyword.TargetType.FIELD;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.data.field.Field;
import br.net.ubre.internal.Str;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;
import br.net.ubre.slang.keyword.action.CommandKeyword;

/**
 * Comando que torna um campo obrigatório (required=true).
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 27/10/2015
 */
public class RequireCommand extends FieldCommand {

	public RequireCommand() {
		super(new CommandKeyword(Str.REQUIRE_COMMAND, FIELD, VOID));
	}

	public void execute(DataContainer container, String targetToken,
			Statement parameter) {
		Field field = getField(container, targetToken);
		field.setRequired(true);
	}

}
