package br.net.ubre.slang.code.action;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.slang.command.Command;

/**
 * Ação associada a um comando.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class SimpleCommandAction extends Action {

	protected Command command;
	protected String targetToken;

	public SimpleCommandAction(Command command, String targetToken) {
		this.command = command;
		this.targetToken = targetToken;
	}

	public void execute(DataContainer container) {
		command.execute(container, targetToken, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return toSource();
	}

	/**
	 * @return the command
	 */
	public Command getCommand() {
		return command;
	}

	public String getTargetToken() {
		return targetToken;
	}

	@Override
	public String toSource() {
		return command.getKeyword().getToken() + ' ' + targetToken;
	}

}
