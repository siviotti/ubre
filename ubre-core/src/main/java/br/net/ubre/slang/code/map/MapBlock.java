package br.net.ubre.slang.code.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.LangError;
import br.net.ubre.slang.SLangException;
import br.net.ubre.slang.code.CodeBlock;
import br.net.ubre.slang.code.CodeLine;
import br.net.ubre.slang.parse.SourceLine;

/**
 * Representa uma linha que faz link com outras linhas (CodeNode) que estão
 * diretamente ligadas à árvore (Switch).
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 09/10/2015
 * 
 */
public class MapBlock extends CodeBlock {

	private static final String MAPBLOCK_DEFAULT_ALREADY_EXISTS = "Já existe uma opção 'default' definida para ";
	private String keyToken;
	private Map<Object, CodeLine> map = new HashMap<Object, CodeLine>();
	private CodeLine defaultLine;

	public MapBlock(SourceLine sourceLine, String keyToken) {
		super(sourceLine);
		this.keyToken = keyToken;
	}

	@Override
	public void execute(DataContainer container) {
		Object result = container.getValue(keyToken);
		CodeLine codeLine = map.get(result);
		if (codeLine != null) {
			codeLine.execute(container);
		} else if (defaultLine != null) {
			defaultLine.execute(container);
		}
	}

	@Override
	public void link(CodeLine codeLine) {
		KeyBlock keyBlock = (KeyBlock) codeLine;
		if (keyBlock.isDefault()) {
			addDefault(keyBlock);
			close();
		} else {
			Object key = keyBlock.getKey();
			if (key instanceof List) {
				for (Object obj : (List) key) {
					map.put(obj, keyBlock);
				}
			} else {
				map.put(key, keyBlock);
			}
			super.link(keyBlock);
		}
		System.out.println(map);
	}

	private void addDefault(KeyBlock keyBlock) {
		if (defaultLine != null) {
			throw new SLangException(LangError.E20,
					MAPBLOCK_DEFAULT_ALREADY_EXISTS + toString());
		}
		defaultLine = keyBlock;
	}

	@Override
	public String toSource(int level) {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toSource(level));
		if (defaultLine != null) {
			sb.append(defaultLine.toSource(level + 1));
		}
		return sb.toString();
	}

}
