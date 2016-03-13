package br.gov.serpro.ubre.shell;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.io.txt.Text;
import br.gov.serpro.ubre.lang.Lang;
import br.gov.serpro.ubre.lang.expression.Expression;
import br.gov.serpro.ubre.shell.command.DebugCmd;
import br.gov.serpro.ubre.shell.command.DumpCmd;
import br.gov.serpro.ubre.shell.command.HelpCmd;
import br.gov.serpro.ubre.shell.command.ImportCmd;
import br.gov.serpro.ubre.shell.command.ScriptCmd;
import br.gov.serpro.ubre.shell.command.ShellCommand;
import br.gov.serpro.ubre.slang.Slang;
import br.gov.serpro.ubre.slang.keyword.EndKeyword;
import br.gov.serpro.ubre.slang.script.Script;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/04/2015
 * 
 */
public class Shell {

	private static final String COMMAND_NOT_FOUND = "Comando de shell não encontrado:";

	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

	public static final char SHELL_COMMAND_CHAR = '#';

	private DataContainer container;
	private Map<String, ShellCommand> commands = new HashMap<String, ShellCommand>();
	private boolean active = true;
	private Lang lang;
	private Slang slang;

	private boolean debug;
	private boolean script;
	private List<String> buffer = new ArrayList<String>();

	public Shell(Lang lang, Slang slang, DataContainer container) {
		super();
		this.lang = lang;
		this.slang = slang;
		this.container = container;
		addCommand(new ShellCommand() {

			public String token() {
				return "exit";
			}

			public String help() {
				return "exit";
			}

			public String execute(DataContainer container, String... par) {
				active = false;
				String id = container.getId();
				return "Desligando Shell da sessão '" + id + "'";
			}

		});
		addCommand(new DumpCmd());
		addCommand(new HelpCmd(this));
		addCommand(new ImportCmd(this));
		addCommand(new DebugCmd(this));
		addCommand(new ScriptCmd(this));
	}

	public void addCommand(ShellCommand command) {
		commands.put(command.token(), command);
	}

	public String execute(String commandLine) {
		if (commandLine == null || commandLine.trim().isEmpty()) {
			return null;
		}
		if (commandLine.charAt(0) == SHELL_COMMAND_CHAR) {
			return executeShellCommand(commandLine.substring(1));
		} else if (script) {
			if (commandLine.equals(new EndKeyword().getToken())) {
				String result = executeScript(buffer);
				buffer.clear();
				return result;
			} else {
				buffer.add(commandLine);
			}
			return null;
		} else {
			return executeScriptLine(commandLine);
		}
	}

	private String executeShellCommand(String commandLine) {
		String command;
		String parStr = null;
		if (commandLine.indexOf(' ') > -1) {
			command = Text.bc(commandLine, ' ');
			parStr = Text.ac(commandLine, ' ');
		} else {
			command = commandLine;
		}
		String[] par = new String[0];
		if (parStr != null) {
			par = parStr.split(" ");
		}
		ShellCommand shellCommand = commands.get(command);
		if (shellCommand == null) {
			throw new CTDException(COMMAND_NOT_FOUND + commandLine);
		}
		if (par.length > 0 && par[0].equals(ShellCommand.HELP_PARAMETER)) {
			return shellCommand.help();
		}
		return shellCommand.execute(container, par);
	}

	private String executeScript(List<String> lines) {
		Script script = slang.build(lines);
		if (script.isExpression()) {
			return executeExpression(lines.get(0));
		} else {
			script.execute(container);
			return null;
		}
	}

	private String executeScriptLine(String line) {
		List<String> lines = new ArrayList<String>();
		lines.add(line);
		return executeScript(lines);
	}

	private String executeExpression(String exp) {
		Expression expression = lang.getCache().get(exp);
		Object obj;
		try {
			obj = expression.eval(container);
		} catch (CTDException ctde) {
			expression.getRoot().asNode("  ");
			throw ctde;
		}
		String result = "";
		if (debug) {
			result = expression.getRoot().asNode("   ") + "\n";
		}
		if (obj != null) {
			return result + obj.toString();
		}
		return result;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	public ShellCommand getCommand(String command) {
		return commands.get(command);
	}

	public List<ShellCommand> getCommands() {
		return new ArrayList<ShellCommand>(commands.values());
	}

	/**
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * @param debug
	 *            the debug to set
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isScript() {
		return script;
	}

	public void setScript(boolean script) {
		this.script = script;
	}

}
