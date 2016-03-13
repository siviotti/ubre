package br.net.ubre.framework;

import java.util.Date;

import br.net.ubre.data.var.ValueType;
import br.net.ubre.util.Debuggable;

/**
 * Representa uma memsagem do contexto. Uma mensagem é uma constante String que
 * pode fazer referências a constantes domínios e metadados através do literal $
 * seguido do ID do elemento que se deseja inserir na mensagem.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 09/04/2015
 * 
 */
public class Message extends DataElement implements Debuggable {

	public Message(String id, String label, Object value, Date begin,
			Date end, String tags) {
		super(id, label, ValueType.TEXT, value, begin, end, tags);
	}

	public String toDebug() {
		return "  MESSAGE " + getId() + "(" + getType() + ")="
				+ getType().getConverter().asString(getValue());
	}

}
