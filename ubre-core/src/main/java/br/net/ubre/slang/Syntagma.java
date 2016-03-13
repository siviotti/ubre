package br.net.ubre.slang;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.net.ubre.behave.BehaveMap;
import br.net.ubre.internal.Const;
import br.net.ubre.slang.command.Command;
import br.net.ubre.slang.command.console.InputCommand;
import br.net.ubre.slang.command.console.PrintCommand;
import br.net.ubre.slang.command.etc.CallCommand;
import br.net.ubre.slang.command.etc.PutCommand;
import br.net.ubre.slang.command.field.CheckCommand;
import br.net.ubre.slang.command.field.EnableCommand;
import br.net.ubre.slang.command.field.RequireCommand;
import br.net.ubre.slang.command.field.SetvalCommand;
import br.net.ubre.slang.command.field.UnrequireCommand;
import br.net.ubre.slang.keyword.EndKeyword;
import br.net.ubre.slang.keyword.Keyword;
import br.net.ubre.slang.keyword.action.ExppressionKeyword;
import br.net.ubre.slang.keyword.ifelse.ElseIfKeyword;
import br.net.ubre.slang.keyword.ifelse.ElseKeyword;
import br.net.ubre.slang.keyword.ifelse.IfKeyword;
import br.net.ubre.slang.keyword.loop.WhileKeyword;
import br.net.ubre.slang.keyword.map.CaseKeyword;
import br.net.ubre.slang.keyword.map.DefaultKeyword;
import br.net.ubre.slang.keyword.map.SwitchKeyword;
import br.net.ubre.util.GenericFreezable;

/**
 * Conjunto de elementos usados para estruturar a SLang: Comandos, estruturas de
 * fluxo, looop etc.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 09/10/2015 23:22
 */
public class Syntagma extends GenericFreezable {

	protected Map<String, Command> commands = new HashMap<String, Command>();
	protected Map<String, Keyword> controls = new HashMap<String, Keyword>();
	protected Map<String, Keyword> keywords = new HashMap<String, Keyword>();
	private BehaveMap behaveMap;
	protected ExppressionKeyword expressionKeyword = new ExppressionKeyword();
	protected EndKeyword endKeyword = new EndKeyword();

	public Syntagma() {
		super();
		// CONTROL KEYWORDS
		putControl(new IfKeyword());
		putControl(new ElseIfKeyword());
		putControl(new ElseKeyword());
		putControl(new SwitchKeyword());
		putControl(new CaseKeyword());
		putControl(new EndKeyword());
		putControl(new DefaultKeyword());
		putControl(new WhileKeyword());
		// EXPRESSION
		putKeyword(expressionKeyword); // não é control nem command
		// COMMANDS
		// Fields - propriedades
		putCommand(new CheckCommand());
		putCommand(new EnableCommand());
		putCommand(new RequireCommand());
		putCommand(new UnrequireCommand());
		putCommand(new SetvalCommand());
		
		// Rule
		putCommand(new CallCommand(this));
		// Etc
		putCommand(new PrintCommand(System.out));
		putCommand(new InputCommand(System.in));
		putCommand(new PutCommand()); 
	}

	/**
	 * Retorna uma Keyword a partir do token.
	 * 
	 * @param token
	 *            O texto (palavra) presente na lkinha do script.
	 * @return A Keyword correspondente ou <code>null</code> se não houver.
	 */
	public Keyword getKeyword(String token) {
		return keywords.get(token);
	}

	public Keyword getExpressionKeyword() {
		return expressionKeyword;
	}

	public EndKeyword getEndKeyword() {
		return endKeyword;
	}

	/**
	 * Adiciona uma Keyword.
	 * 
	 * @param keyword
	 *            A Keyword.
	 */
	private void putKeyword(Keyword keyword) {
		keywords.put(keyword.getToken(), keyword);
	}

	// Commands
	/**
	 * Informa se um token é um comando ou não. Se o token for uma keyword
	 * válida e não for um comando, será uma keyword de controla.
	 * 
	 * @param token
	 *            O token do comando.
	 * @return <code>true</code> se existe ou <code>false</code> se não existe.
	 */
	public boolean isCommand(String token) {
		return commands.containsKey(token);
	}

	/**
	 * Retorna um comando da lista de comandos (subconjunto de keywords).
	 * 
	 * @param token
	 *            O token.
	 * @return A instância de Keyword que representa o comando.
	 */
	public Command getCommand(String token) {
		return commands.get(token);
	}

	/**
	 * Adiciona um comando usando o método <code>getName()</code> como chave.
	 * 
	 * @param commandO
	 *            comando a ser adicionado.
	 */
	public void putCommand(Command command) {
		putKeyword(command.getKeyword());
		commands.put(command.getKeyword().getToken(), command);
	}

	// Controls

	/**
	 * Informa se existe um controle igual ao token passado no parâmetro.
	 * 
	 * @param token
	 *            O token de inicício de uma linha de controle.
	 * @return <code>true</code> se existe ou <code>false</code> se não existe.
	 */
	public boolean isControl(String token) {
		return controls.containsKey(token);
	}

	/**
	 * Adiciona uma palavra de controle.
	 * 
	 * @param keyword
	 *            palavra de controle que caracteriza uma linha do script.
	 */
	protected void putControl(Keyword keyword) {
		controls.put(keyword.getToken(), keyword);
		putKeyword(keyword);
	}

	public Set<String> getKeywordSet() {
		return keywords.keySet();
	}

	public boolean isCommentLine(String textLine) {
		return textLine != null && textLine.startsWith(Const.COMMENT_TAG);
	}

	// GEt / SET

	public BehaveMap getBehaveMap() {
		return behaveMap;
	}

	public void setBehaveMap(BehaveMap behaveMap) {
		testFrozen();
		this.behaveMap = behaveMap;
	}

}
