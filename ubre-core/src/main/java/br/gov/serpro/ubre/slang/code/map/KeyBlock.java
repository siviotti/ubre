package br.gov.serpro.ubre.slang.code.map;

import br.gov.serpro.ubre.lang.LangError;
import br.gov.serpro.ubre.lang.expression.Expression;
import br.gov.serpro.ubre.slang.SLangException;
import br.gov.serpro.ubre.slang.code.CodeBlock;
import br.gov.serpro.ubre.slang.parse.SourceLine;

/**
 * Bloco de código que fica diretamente abaixo de um Map (Switch etc). A
 * direfença deste bloco para o bloco comum é que ao encontrar um igual
 * (LabelBlock) ele não o incorpora. Ele interrompe a indentação e volta para
 * que o Map faça a incorporaçao.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 */
public abstract class KeyBlock extends CodeBlock {

	private static final String KEYBLOCK_ACCEPT_ONLY_LITERALS = "Blocos identificados só aceita valores ou expressões literais. Existem variáveis ou valores indefinidos na expressão:";

	protected static Object eval(Expression expression) {
		if (expression== null){
			return null;// default
		}
		if (!expression.isLiteral()) {
			throw new SLangException(LangError.E20, KEYBLOCK_ACCEPT_ONLY_LITERALS + expression.getSource());
		}
		return expression.eval(null);// não precisa de container
	}

	private Object key;
	MapBlock mapBlock;

	public KeyBlock(SourceLine sourceLine, Object key) {
		super(sourceLine);
		this.key = key;
	}

	public Object getKey() {
		return key;
	}

	public abstract boolean isDefault();

	@Override
	public String toSource(int level) {
		return super.toSource(level - 1);
	}

	@Override
	public void close() {
		super.close();
	}

}
