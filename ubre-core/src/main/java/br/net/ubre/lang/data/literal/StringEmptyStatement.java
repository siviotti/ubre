package br.net.ubre.lang.data.literal;

import br.net.ubre.internal.Str;

/**
 * Statement que representa uma String vazia do tipo ''.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 28/10/2015
 */
public class StringEmptyStatement extends StringStatement{
	
	public static final StringEmptyStatement INSTANCE= new StringEmptyStatement();

	public StringEmptyStatement() {
		super(Str.STRING_EMPTY_LITERAL);
	}

	@Override
	public String asToken() {
		return "''";
	}
	
	

}
