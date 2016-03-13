package br.net.ubre.slang.command.field;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.data.field.Field;
import br.net.ubre.slang.command.GenericCommand;
import br.net.ubre.slang.keyword.Keyword;


/**
 * Classe genérica para os comandos que manipulam ou usam campos (Field).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @since 18/09/2015
 */
public abstract class FieldCommand extends GenericCommand {

	public FieldCommand(Keyword keyword) {
		super(keyword);
	}

	
	protected Field getField(DataContainer container, String targetToken) {
		return container.getField(targetToken);
	}


}
