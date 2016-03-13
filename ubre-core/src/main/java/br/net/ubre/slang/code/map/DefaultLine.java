package br.net.ubre.slang.code.map;

import br.net.ubre.slang.parse.SourceLine;

/**
 * Linha default de um Switch.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 *
 */
public class DefaultLine extends KeyBlock {

	public DefaultLine(SourceLine sourceLine) {
		super(sourceLine, null);
	}

	@Override
	public boolean isDefault() {
		return true;
	}
	

}