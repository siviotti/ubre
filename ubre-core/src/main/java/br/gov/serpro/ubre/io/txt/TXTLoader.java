package br.gov.serpro.ubre.io.txt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import br.gov.serpro.ubre.framework.Constant;
import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.framework.Message;
import br.gov.serpro.ubre.framework.Metadata;
import br.gov.serpro.ubre.framework.Scope;
import br.gov.serpro.ubre.framework.Variable;
import br.gov.serpro.ubre.framework.domain.Domain;
import br.gov.serpro.ubre.framework.process.Process;
import br.gov.serpro.ubre.framework.rule.Rule;
import br.gov.serpro.ubre.io.ContextLoader;
import br.gov.serpro.ubre.io.txt.converter.ContextConverter;
import br.gov.serpro.ubre.slang.command.Command;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/03/2015
 * 
 */
public class TXTLoader implements ContextLoader {

	private static final String PRIMEIRA_LINHA_DEVE_SER_CONTEXT = "A primeira linha (não comentada) do arquivo deve ser do tipo 'context' ";

	/**
	 * retorna um Stream que aponta para o conteúdo do arquivo.
	 * 
	 * @param fileName
	 *            O nome do arquivo.
	 * @return O Stream que aponta para o conteúdo do arquivo.
	 */
	public InputStream asStream(String fileName) {
		return this.getClass().getClassLoader().getResourceAsStream(fileName);
	}

	public Context load(String fileName, String encoding) {
		if (encoding == null) {
			encoding = "UTF-8";
		}
		InputStream is = asStream(fileName);
		BufferedReader reader = null;
		Lines lines = new Lines();
		try {
			reader = new BufferedReader(new InputStreamReader(is, encoding));
			String lineStr;
			Line line;
			int lineNumber = 0;
			// A primeira linha válida tem que ser CONTEXT
			while ((lineStr = reader.readLine()) != null) {
				lines.add(lineStr);
			}
			return createContext(lines);
		} catch (IOException e) {
			throw new TxtException("Erro ao ler arquivo " + fileName, e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Line getLast(List<Line> lines) {
		return lines.get(lines.size() - 1);
	}

	private void addConst(Context context, Line line) {
		Constant constant = (Constant) line.asElement(context);
		context.addConst(constant);
	}

	private void addVar(Context context, Line line) {
		Variable variable = (Variable) line.asElement(context);
		context.addVar(variable);
	}

	private void addDomain(Context context, Line line) {
		Domain domain = (Domain) line.asElement(context);
		context.addDomain(domain);
	}

	private void addMetadata(Context context, Line line) {
		Metadata metadata = (Metadata) line.asElement(context);
		context.addMetadata(metadata);
	}

	private void addMessage(Context context, Line line) {
		Message message = (Message) line.asElement(context);
		context.addMessage(message);
	}

	private void addScope(Context context, Line line) {
		Scope scope = (Scope) line.asElement(context);
		context.addScope(scope);
	}

	private void addRule(Context context, Line line) {
		Rule rule = (Rule) line.asElement(context);
		context.addRule(rule);
	}

	private void addProcess(Context context, Line line) {
		Process process = (Process) line.asElement(context);
		context.addProcess(process);
	}

	private Context createContext(Lines lines) {
		// Primeira linha tem que ser context
		Line firstLine = lines.get(0);
		if (!firstLine.getLineType().equals(LineType.CONTEXT)) {
			throw new TxtException(PRIMEIRA_LINHA_DEVE_SER_CONTEXT);
		}
		ContextConverter converter = new ContextConverter();
		Context context = converter.asObject(null, firstLine);

		// Demais linhas
		for (Line line : lines.getLines()) {
			if (line.getLineType().equals(LineType.CONSTANT)) {
				addConst(context, line);
			} else if (line.getLineType().equals(LineType.VARIABLE)) {
				addVar(context, line);
			} else if (line.getLineType().equals(LineType.DOMAIN)) {
				addDomain(context, line);
			} else if (line.getLineType().equals(LineType.METADATA)) {
				addMetadata(context, line);
			} else if (line.getLineType().equals(LineType.SCOPE)) {
				addScope(context, line);
			} else if (line.getLineType().equals(LineType.MESSAGE)) {
				addMessage(context, line);
			} else if (line.getLineType().equals(LineType.RULE)) {
				addRule(context, line);
			} else if (line.getLineType().equals(LineType.PROCESS)) {
				addProcess(context, line);
			}
		}
		// context.freeze(); // a partir de agora o Context é readonly!!
		return context;
	}

}
