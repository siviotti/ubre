package br.net.ubre.slang.condition;

import br.net.ubre.data.container.DataContainer;

/**
 * Classe que faz a comparação entre uma variável ($abc) e um valor constante.
 * Exemplo $nj=='206-2'. Esta classe executa este tipo de comparação bem mais
 * rápido que uma expressão comum.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 */
public class FastEqual {

	private String token;
	private Object value;

	public FastEqual(String token, Object value) {
		super();
		this.token = token;
		this.value = value;
	}

	public boolean isEqual(DataContainer container) {
		Object tokenValue = container.getValue(token);
		return (tokenValue != null) ? tokenValue.equals(value) : value == null;
	}

}
