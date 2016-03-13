package br.net.ubre.io.txt;

import java.util.ArrayList;
import java.util.List;

/**
 * Conjunto de linhas lidas de um arquivo ou prestes a serem gravadas para este
 * arquivo.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @since 18/09/2015
 */
public class Lines {

	public static final char COMMENT = '#';

	private List<Line> lines = new ArrayList<Line>();
	private int lineNumber;

	/**
	 * Adiciona uma linha a partir de uma String.
	 * 
	 * @param lineStr
	 *            A String que representa a linha.
	 */
	public void add(String lineStr) {
		lineNumber++;
		if (lineStr.isEmpty() || lineStr.charAt(0) == COMMENT) {
			return; // Linha vazia ou de comentário
		}
		Line line = new Line(lineNumber, lineStr);
		if (line.isChild()) {
			getLast(lines).addChild(line);
		} else {
			lines.add(line);
		}
	}

	/**
	 * Retorna a linha referente ao índice passado.
	 * 
	 * @param index
	 *            O índice.
	 * @return A linha referente ao índice ou um erro de ArrayOutOfBounds.
	 */
	public Line get(int index) {
		return lines.get(index);
	}
	
	public void clear(){
		lines.clear();
	}

	private Line getLast(List<Line> lines) {
		return lines.get(lines.size() - 1);
	}

	// GET / SET
	public List<Line> getLines() {
		return lines;
	}

}
