package br.net.ubre.shell.command;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.data.field.Field;
import br.net.ubre.data.var.ValueType;

/**
 * Comando para dumps de mem[ória de uma sessão.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 24/04/2015
 * 
 */
public class DumpCmd implements ShellCommand {

	public String execute(DataContainer container, String... par) {
		if (par.length == 0) {
			return dumpSession(container);
		}
		if ("*".equals(par[0])) {
			if (par.length == 2) {
				return dumpSession(container, par[1]);
			}
			return dumpSession(container);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < par.length; i++) {
			String token = par[i];
			if (container.isField(token)) {
				sb.append(dumpField(container, token));
			} else {
				sb.append(dumpVar(container, token));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private String dumpVar(DataContainer container, String token) {
		ValueType type = container.getType(token);
		Object data = container.getValue(token);
		return token + ":" + type.name() + "=" + data;
	}

	private String dumpSession(DataContainer container) {
		return dumpSession(container, "cfv");
	}

	private String dumpSession(DataContainer container, String par) {
		StringBuilder sb = new StringBuilder();
		if (par.contains("c")) {
			sb.append("[constants]\n");
			for (String constName: container.getDataMap().getConstantTokens()){
				sb.append(dumpVar(container, constName));
				sb.append("\n");				
			}
		}
		if (par.contains("f")) {
			sb.append("[fields]\n");
			for (String fieldName : container.getDataMap().getFieldTokens()) {
				sb.append(dumpField(container, fieldName));
				sb.append("\n");
			}
		}
		if (par.contains("v")) {
			sb.append("[variables]\n");
			for (String varName : container.getDataMap().getVarTokens()) {
				sb.append(dumpVar(container, varName));
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	private String dumpField(DataContainer container, String fieldName) {
		Field field = container.getField(fieldName);
		StringBuilder sb = new StringBuilder();
		sb.append(fieldName);
		sb.append(":");
		sb.append(field.getType());
		sb.append("=");
		sb.append(field.getString());
		sb.append(" {");
		sb.append("required=");
		sb.append(field.isRequired());
		sb.append(", visible=");
		sb.append(field.isVisible());
		sb.append(", enabled=");
		sb.append(field.isEnabled());
		sb.append(", valid=");
		sb.append(field.isValid());
		sb.append("}");
		return sb.toString();
	}

	public String token() {
		return "dump";
	}

	public String help() {
		return "dump [{fieldName}|{varName}|*] [cfv]";
	}

}
