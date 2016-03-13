package br.net.ubre.io.txt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.net.ubre.framework.Context;

/**
 * type.name[:item]=values
 * <P>
 * type=context,domain,metadata,rule,process<BR>
 * name=nome até 20 caracteres <BR>
 * item=código do item de domínio, ação de regra ou script de processo
 * (opcional) <BR>
 * values=valores no padrão MAP
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 26/03/2015
 * 
 */
public class Line {

	public static final char SEP_NAME = '.';
	public static final char SEP_ITEM = '.';
	public static final char SEP_VALUES = '=';

	private int index = -1;
	private String type;
	private String id;
	private String item;
	private String values;
	private List<Line> children = new ArrayList<Line>();
	private LineType lineType;

	public Line(String type, String id, String item, String values) {
		this.type = type;
		this.id = id;
		this.item = item;
		this.values = values;
		defineType(type + SEP_ITEM + id + SEP_ITEM + item + SEP_VALUES + values);
	}

	public Line(int index, String line) {
		this.index = index;
		String key = Text.bc(line, SEP_VALUES);
		if (key == null) {
			invalidLine(index, "A chave não foi encontrada.", line);
		}
		type = Text.bc(key, SEP_NAME);
		if (type == null) {
			invalidLine(index, "O tipo do elemento não foi encontrado.", line);
		}
		id = Text.ac(key, SEP_NAME);
		if (id == null) {
			invalidLine(index, "O id do elemento não foi encontrado.", line);
		}
		if (id.indexOf(SEP_ITEM) >= 0) {
			String temp = id;
			id = Text.bc(temp, SEP_ITEM);
			item = Text.ac(temp, SEP_ITEM);
		}
		values = Text.ac(line, SEP_VALUES);
		defineType(line);
	}

	private void defineType(String line) {
		lineType = LineType.get(type, item);
		if (lineType == null) {
			invalidLine(index,
					"Inpossível definir o tipo da linha a partir de '"
							+ getKey() + "'", line);
		}
	}

	private void invalidLine(int index, String msg, String line) {
		StringBuilder sb = new StringBuilder();
		sb.append("Linha inválida: [");
		sb.append(index);
		sb.append("]. ");
		sb.append(msg);
		sb.append("\n");
		sb.append(line);
		throw new TxtException(sb.toString());
	}

	public String validate() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(type);
		sb.append(SEP_NAME);
		sb.append(id);
		if (item != null) {
			sb.append(SEP_ITEM);
			sb.append(item);
		}
		sb.append(SEP_VALUES);
		sb.append(values);
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof Line) {
			return toString().equals(obj.toString());
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	public void addChild(Line line) {
		if (!line.lineType.getParent().equals(lineType)) {
			invalidLine(line.getIndex(), "Linha inválida após '" + getKey()
					+ "' na linha anterior.", line.toString());
		}
		children.add(line);
	}

	public boolean isChild() {
		return lineType.isChild();
	}

	public boolean hasChild() {
		return !children.isEmpty();
	}

	public Object asElement(Context context) {
		return lineType.getConverter().asObject(context, this);
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		String key = type + SEP_ITEM + id;
		if (item == null) {
			return key;
		}
		return key + SEP_ITEM + item;
	}

	/**
	 * @return the value
	 */
	public String getValues() {
		return values;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValues(String values) {
		this.values = values;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the lineType
	 */
	public LineType getLineType() {
		return lineType;
	}

	/**
	 * @param lineType
	 *            the lineType to set
	 */
	public void setLineType(LineType lineType) {
		this.lineType = lineType;
	}

	/**
	 * @return the child
	 */
	public List<Line> getChildren() {
		return children;
	}

	/**
	 * Informa se a linha apesar de ter chave, não tem valor.
	 * 
	 * @return
	 */
	public boolean isValuesEmpty() {
		return values == null || values.isEmpty();
	}

	/**
	 * @param child
	 *            the child to set
	 */
	public void setChildren(List<Line> child) {
		this.children = child;
	}

	public Line getFirstChildByType(LineType childLineType) {
		for (Line line : children) {
			if (line.lineType.equals(childLineType)) {
				return line;
			}
		}
		return null;
	}

	/**
	 * Retorna uma lista de linhas filhas com base em um filtro de tipo.
	 * 
	 * @param childrenType
	 *            O tipo que deve filtrar a lista.
	 * @return
	 */
	public List<Line> getChildrenByType(LineType childrenType) {
		List<Line> subChildren = new ArrayList<Line>();
		for (Line child : children) {
			if (child.lineType.equals(childrenType)) {
				subChildren.add(child);
			}
		}
		return subChildren;
	}

	/**
	 * Retorna o último valor cuja linha tem o "item" igual ao item passado como
	 * filtro. Este método á usado especialmente para obter um valor de
	 * propertie. Nestes casos vale a última linha como se fosse um Map.
	 * 
	 * @param itemFilter
	 *            O item desejado.
	 * @return O valor da última linha cujo "item" é igual ao filtro ou
	 *         <code>null</code> se nehuma linha tiver este item.
	 */
	public String getValuesByLastItem(String itemFilter) {
		String result = null;
		for (Line child : children) {
			if (itemFilter.equals(child.getItem())) {
				result = child.values;
			}
		}
		return result;
	}

	/**
	 * retorna um mapa onde a chave é o "item" de uma linha filha e o value á a
	 * própria linha.
	 * 
	 * @return Um Map com as filhas etiquetadas pelo seu "item".
	 */
	public Map<String, Line> getChildMapByItem() {
		Map<String, Line> childMap = new HashMap<String, Line>();
		for (Line child : children) {
			childMap.put(child.getItem(), child);
		}
		return childMap;
	}

	/**
	 * Determina se a linha é do tipo passado como parâmetro.
	 * 
	 * @param lineType
	 * @return
	 */
	public boolean checkType(LineType lineType) {
		return this.lineType.equals(lineType);
	}

	public String asNumber() {
		return "[line " + index + "]";
	}

}
