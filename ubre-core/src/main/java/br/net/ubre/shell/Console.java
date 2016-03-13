package br.net.ubre.shell;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import br.net.ubre.api.Engine;
import br.net.ubre.data.container.DataContainer;
import br.net.ubre.data.var.Datex;
import br.net.ubre.data.var.ValueType;
import br.net.ubre.exception.CTDException;
import br.net.ubre.framework.Context;
import br.net.ubre.framework.Metadata;
import br.net.ubre.io.ContextLoader;
import br.net.ubre.io.txt.TXTLoader;
import br.net.ubre.lang.Lang;
import br.net.ubre.slang.Slang;

/**
 * Console de validação de liguagem.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/04/2015
 * 
 */
public class Console {

	public static void main(String[] args) {
		// Área global (escopo de aplicação)
		Context context = getContext();
		Lang lang = context.getLang();
		Slang slang = context.getSlang();
		// Área local (escopo de sessão)
		Engine engine = new Engine("console", context);
		DataContainer container = engine.createContainer("ConsoleContainer", null);
		Shell shell = new Shell(lang, slang, container);
		Console console = new Console();
		console.run(shell, System.in, System.out);
	}

	private static Context getTxtContext() {
		ContextLoader contextLoader = new TXTLoader();
		Context context = contextLoader.load("context.txt", "UTF-8");
		context.build().freeze();
		return context;
	}

	private static Context getContext() {
		Context context = new Context("CtxCnsole", "Context", new Datex(), new Datex());
		context.addVar("x", ValueType.INTEGER, 0);
		context.addVar("d", ValueType.DECIMAL, 0);
		Metadata meta = new Metadata(ValueType.DECIMAL);
		meta.setId("field1");
		context.getDataMap().add(meta, false);
		// Build and Freeze
		context.build().freeze();
		return context;
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
