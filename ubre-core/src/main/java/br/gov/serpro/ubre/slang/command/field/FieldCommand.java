package br.gov.serpro.ubre.slang.command.field;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.data.field.Field;
import br.gov.serpro.ubre.slang.command.GenericCommand;
import br.gov.serpro.ubre.slang.keyword.Keyword;


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
