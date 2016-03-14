package br.net.ubre.slang.command.etc;

import static br.net.ubre.slang.keyword.TargetType.NONE;

import java.util.logging.Logger;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;
import br.net.ubre.slang.command.GenericCommand;
import br.net.ubre.slang.keyword.action.CommandKeyword;

/**Comando que só executado quando o DataContainer está com "Mode Info" habilitado.
 * @author douglas
 *
 */
public class InfoCommand extends GenericCommand{
	
	
	public InfoCommand() {
		super(new CommandKeyword("info", NONE, StatementType.OBJECT));
	}


	@Override
	public void execute(DataContainer container, String targetToken, Statement parameter) {
		Logger logger = Logger.getLogger(container.getId());
		logger.info(parameter.perform(container).toString());
	}

}
