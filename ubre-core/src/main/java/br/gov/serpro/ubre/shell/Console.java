package br.gov.serpro.ubre.shell;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import br.gov.serpro.ubre.api.Engine;
import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.data.field.Field;
import br.gov.serpro.ubre.data.field.FieldFactory;
import br.gov.serpro.ubre.data.var.Datex;
import br.gov.serpro.ubre.data.var.ValueType;
import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.framework.Metadata;
import br.gov.serpro.ubre.lang.Lang;
import br.gov.serpro.ubre.slang.Slang;
import br.gov.serpro.ubre.slang.command.console.PrintCommand;
import br.gov.serpro.ubre.slang.command.etc.PutCommand;

/**
 * Console de validação de liguagem.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/04/2015
 * 
 */
public class Console {

	public static void main(String[] args) {
		//ContextLoader contextLoader = new TXTLoader();
		Context context = new Context("CtxCnsole", "Context", new Datex(), new Datex());
		//Context context = contextLoader.load("context.txt", "UTF-8");
		context.addVar("x", ValueType.INTEGER, 0);
		context.addVar("d", ValueType.DECIMAL, 0);
		Metadata meta = new Metadata(ValueType.DECIMAL);
		meta.setId("field1");
		context.getDataMap().add(meta, false);
		Lang lang = context.getLang();
		Slang slang = context.getSlang();
		slang.getSyntagma().putCommand(new PutCommand());
		slang.getSyntagma().putCommand(new PrintCommand(System.out));
		// Freeze
		context.build();
		context.freeze();
		Engine engine = new Engine("console", context);
		DataContainer container = engine.createContainer("console", null);
		Shell shell = new Shell(lang, slang, container);
		Console console = new Console();
		console.run(shell, System.in, System.out);
	}

	public String run(Shell shell, InputStream in, PrintStream out) {
		Scanner scanner = new Scanner(in);
		String commandLine;
		String result;
		while (shell.isActive()) {
			try {
				out.print(">");
				commandLine = scanner.nextLine();
				result = shell.execute(commandLine);
				if (result != null) {
					out.println(result);
				}
			} catch (CTDException ctde) {
				out.println(ctde.getMessage());
				if (shell.isDebug()) {
					ctde.printStackTrace();
				}
			} catch (Exception e) {
				out.println("[ERRO FATAL]" + e.getMessage());
				e.printStackTrace();
				break;
			}
		}
		scanner.close();
		return "0";
	}
}
