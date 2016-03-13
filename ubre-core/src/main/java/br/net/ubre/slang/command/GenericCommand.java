package br.net.ubre.slang.command;

import br.net.ubre.lang.expression.Expression;
import br.net.ubre.slang.keyword.Keyword;

/**
 * Classe genérica de Comando que fornece um tipo de parâmetro padrão
 * (Expression) além de método para extrair o alvo.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @since 18/09/2015
 */
public abstract class GenericCommand implements Command {

	private Keyword keyword;

	public GenericCommand(Keyword keyword) {
		super();
		this.keyword = keyword;
	}

	/**
	 * Retora <code>Expression.class</code> que pode resolver inclusive
	 * constantes.
	 * 
	 * @see br.net.ubre.slang.command.Command#getParameterType()
	 */
	public Class<?> getParameterType() {
		return Expression.class;
	}

	public Keyword getKeyword() {
		return keyword;
	}
}
