package br.gov.serpro.ubre.io.txt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 31/03/2015
 * 
 */
public class TXTFile {
	
	private String fileName;
	
	public TXTFile(String fileName) {
		super();
		this.fileName = fileName;
	}

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

	/**
	 * Retorna o conteúdo texto de um arquivo a partir de seu nome.
	 * 
	 * @param fileName
	 *            o nome do arquivo.
	 * @return O conteúdo do arquivo armazenado em uma String.
	 */
	public String asString() {
		return asString("UTF-8");
	}

	/**
	 * Retorna o conteúdo texto de um arquivo a partir de seu nome.
	 * 
	 * @param fileName
	 *            o nome do arquivo.
	 * @return O conteúdo do arquivo armazenado em uma String.
	 */
	public String asString(String encoding) {
		StringBuilder sb = new StringBuilder();
		InputStream is = asStream(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is, encoding));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (IOException e) {
			throw new TxtException("Erro ao ler arquivo " + fileName, e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
