package br.gov.serpro.ubre.slang.command.field;

import static br.gov.serpro.ubre.lang.statement.StatementType.BOOLEAN;
import static br.gov.serpro.ubre.lang.statement.StatementType.VOID;
import static br.gov.serpro.ubre.slang.keyword.TargetType.FIELD;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.data.field.Field;
import br.gov.serpro.ubre.internal.Str;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;
import br.gov.serpro.ubre.slang.keyword.Keyword;
import br.gov.serpro.ubre.slang.keyword.action.CommandKeyword;

/**
 * Comando que torna um campo opcional (required=false).
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 27/10/2015
 */
public class UnrequireCommand extends FieldCommand {

	public UnrequireCommand() {
		super(new CommandKeyword(Str.UNREQUIRE_COMMAND, FIELD, VOID));
	}

	@Override
	public void execute(DataContainer container, String targetToken,
			Statement parameter) {
		Field field = getField(container, targetToken);
		field.setRequired(false);		
	}

}
