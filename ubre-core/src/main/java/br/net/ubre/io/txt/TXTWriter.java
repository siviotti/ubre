package br.net.ubre.io.txt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import br.net.ubre.framework.Constant;
import br.net.ubre.framework.Context;
import br.net.ubre.framework.Message;
import br.net.ubre.framework.Metadata;
import br.net.ubre.framework.Scope;
import br.net.ubre.framework.Variable;
import br.net.ubre.framework.domain.Domain;
import br.net.ubre.framework.process.Process;
import br.net.ubre.framework.rule.Rule;
import br.net.ubre.io.ContextWriter;

/**
 * Implementação de Writer para gravaçao em arquivos TXT.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 26/03/2015
 * 
 */
public class TXTWriter implements ContextWriter {

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

	public void write(Context context, String file, String encoding) {
		try {
			FileWriter writer = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(writer);
			write(out, "#Context", true);
			write(out, asContextLine(context));
			write(out, "#Constants", true);
			for (Constant constant : context.getConstants()) {
				write(out, asConstantLine(constant));
			}
			write(out, "#Variables", true);
			for (Variable variable : context.getVariables()) {
				write(out, asVariableLine(variable));
			}
			write(out, "#Domains", true);
			for (Domain domain : context.getDomains()) {
				write(out, asDomainLine(domain));
			}
			write(out, "#Metadatas", true);
			for (Metadata metadata : context.getMetadatas()) {
				write(out, asMetadataLine(metadata));
			}
			write(out, "#Scopes", true);
			for (Scope scope : context.getScopes()) {
				write(out, asScopeLine(scope));
			}
			write(out, "#Messages", true);
			for (Message message : context.getMessages()) {
				write(out, asMessageLine(message));
			}
			write(out, "#Rules", true);
			for (Rule rule : context.getRules()) {
				write(out, asRuleLine(rule));
			}
			write(out, "#Processes", true);
			for (Process process : context.getProcesses()) {
				write(out, asProcessLine(process));
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private Line asConstantLine(Constant constant) {
		return LineType.CONSTANT.getConverter().asLine(null, constant);

	}

	private Object asVariableLine(Variable variable) {
		return LineType.VARIABLE.getConverter().asLine(null, variable);
	}

	private Line asProcessLine(Process process) {
		return LineType.PROCESS.getConverter().asLine(null, process);
	}

	private Line asRuleLine(Rule rule) {
		return LineType.RULE.getConverter().asLine(null, rule);
	}

	private Line asMessageLine(Message message) {
		return LineType.MESSAGE.getConverter().asLine(null, message);
	}

	private Line asMetadataLine(Metadata metadata) {
		return LineType.METADATA.getConverter().asLine(null, metadata);
	}

	private Object asScopeLine(Scope scope) {
		return LineType.SCOPE.getConverter().asLine(null, scope);
	}

	private Line asDomainLine(Domain domain) {
		return LineType.DOMAIN.getConverter().asLine(null, domain);
	}

	private void write(BufferedWriter out, Object object, boolean space)
			throws IOException {
		if (space) {
			out.newLine();
		}
		out.write(object.toString());
		out.newLine();
		if (object instanceof Line) {
			Line line = (Line) object;
			if (line.hasChild()){
				for (Line childLIne: line.getChildren()){
					write(out, childLIne, space);
				}
			}
		}
	}

	private void write(BufferedWriter out, Object object) throws IOException {
		write(out, object, false);
	}


	private Line asContextLine(Context context) {
		return LineType.CONTEXT.getConverter().asLine(null, context);
	}

}
