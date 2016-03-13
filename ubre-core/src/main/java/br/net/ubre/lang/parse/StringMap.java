package br.net.ubre.lang.parse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tabela de Strings.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 25/03/2015
 * 
 */
public class StringMap {

	private List<String> strings = new ArrayList<String>();
	public final String idNull;
	public final String idEmpty;
	private String stringTag;

	public StringMap(String stringTag) {
		clear();
		this.stringTag = stringTag;
		idNull = stringTag + "0";
		idEmpty = stringTag + "1";

	}

	/**
	 * Adiciona uma String à tabela e retorna seu ID iniciado com $. Se uma
	 * String idêntica já estiver armazenada, será retornado o ID desta outra
	 * String e não criado um novo.
	 * 
	 * @param valor
	 *            A String a ser adicionada à tabela.
	 * @return O ID com o qual a String foi adicionada e será futuramente
	 *         recuperada.
	 */
	public String add(String string) {

		if (string == null) {
			return idNull;
		} else if (string.isEmpty()) {
			return idEmpty;
		} else {
			for (int i = 2; i < strings.size(); i++) {
				if (strings.get(i).equals(string)) {
					return stringTag + i;
				}
			}
		}
		// A String não está na tabela ainda
		String id = stringTag + strings.size();
		strings.add(string);
		return id;
	}

	/**
	 * Retorna uma String armazenada a partir de seu ID iniciado com $.
	 * 
	 * @param id
	 *            O ID iniciado com $.
	 * @return A String correspondente ao ID. Se o ID não existir será gerado um
	 *         erro.
	 * @throws ParseException
	 */
	public String get(String id) throws ParseException {
		if (id == null || id.trim().isEmpty()) {
			throw new ParseException("Id de String nulo ou vazio", 0);
		}
		if (!id.startsWith(stringTag) || id.length() < 2) {
			throw new ParseException("Id de String inválido:" + id, 0);
		}
		String indexStr = id.substring(1);
		int index = 0;
		try {
			index = Integer.parseInt(indexStr);
		} catch (NumberFormatException nfe) {
			throw new ParseException("Id de String inválido:" + id, 0);
		}
		if (index > strings.size() - 1) {
			throw new ParseException("Id de String não encontrado:" + id, 0);
		}
		return strings.get(index);
	}

	/**
	 * Retorna uma String a partir de seu ID sem $ no início, Ou seja. O ID é
	 * composto apenas pelo número (sem $).
	 * 
	 * @param idNumerico
	 *            A parte numérica do ID composto por $XXX.
	 * @return A String correspondente ao ID passado ou um erro se o ID não for
	 *         encontrado.
	 */
	public String get(int index) {
		if (index > strings.size() - 1 || index < 0) {
			throw new ArrayIndexOutOfBoundsException(
					"Id de String fora da faixa:" + index + " Faixa:[0-"
							+ (strings.size() - 1) + "]");
		}
		return strings.get(index);
	}

	/**
	 * Retorna a última String inserida.
	 * 
	 * @return A última String inserida.
	 */
	public String last() {
		return strings.get(strings.size() - 1);
	}

	/**
	 * Retorna o ID string (com $) da última String adicionada à tabela.
	 * 
	 * @return O ID da última String.
	 */
	public String lastId() {
		return stringTag + (strings.size() - 1);
	}

	/**
	 * @return O tamanho da lista interna com Strings. Contando com a String
	 *         null e a String vazia que são inseridas automaticamente.
	 */
	public int size() {
		return strings.size();
	}

	/**
	 * @return A lista interna de Strings. Util para o shell.
	 */
	public List<String> getAll() {
		return new ArrayList<String>(strings);
	}

	/**
	 * Limpa a lista de Strings;
	 */
	public void clear() {
		strings.clear();
		strings.add(null);// a primeira é null
		strings.add("");// a segunda é uma
	}

}
