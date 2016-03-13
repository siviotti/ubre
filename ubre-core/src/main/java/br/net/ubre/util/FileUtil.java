package br.net.ubre.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.net.ubre.exception.CTDException;

/**
 * Utilitário para manipulação de arquivos.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 26/03/2015
 * 
 */
public class FileUtil {
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
	public String asString(String fileName) {
		return asString(fileName, "UTF-8");
	}

	/**
	 * Retorna o conteúdo texto de um arquivo a partir de seu nome.
	 * 
	 * @param fileName
	 *            o nome do arquivo.
	 * @return O conteúdo do arquivo armazenado em uma String.
	 */
	public String asString(String fileName, String encoding) {
		StringBuilder sb = new StringBuilder();
		for (String line: asList(fileName, encoding)){
			sb.append(line).append("\n");
		}
		return sb.toString();
	}
	
	public List<String> asList(String fileName, String encoding){
		List<String> list = new ArrayList<String>();
		InputStream is = asStream(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is, encoding));
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			throw new CTDException("Erro ao ler arquivo " + fileName, e);
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
		return list;		
	}

}
