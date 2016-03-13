package br.gov.serpro.ubre.io.txt.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.gov.serpro.ubre.framework.Context;
import br.gov.serpro.ubre.framework.ContextElement;
import br.gov.serpro.ubre.io.txt.Line;
import br.gov.serpro.ubre.io.txt.Text;
import br.gov.serpro.ubre.io.txt.TxtException;

/**
 * Classe que trata a parte direita de uma linha do arquivo de contexto. Nesta
 * parte estão os atributos no padrão chave=valor separados por.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 27/03/2015
 * 
 */
public abstract class LineConverter {

	public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(
			"dd/MM/yyyy");

	public static final String FIELD_LABEL = "label";
	public static final String FIELD_BEGIN = "begin";
	public static final String FIELD_PARENT = "parent";
	public static final String FIELD_END = "end";
	public static final String FIELD_TAGS = "tags";
	public static final String SEP_VALUE = "|";
	public static final String SEP_REGEX = "\\" + SEP_VALUE;
	public static final char EQ = '=';

	public abstract Object asObject(final Context context, Line line);

	public abstract Line asLine(ContextElement parent, Object object);

	/**
	 * Transforma uma String com chave/valor em um map.
	 * 
	 * @param line
	 *            A string com chave/valor.
	 * @return O mapa com os valores.
	 */
	public Map<String, String> getPipeMap(String line) {
		Map<String, String> map = new HashMap<String, String>();
		String[] values = line.split(SEP_REGEX);
		String key;
		String value;
		for (String item : values) {
			key = Text.bc(item, EQ);
			value = Text.ac(item, EQ);
			if (value != null && !value.isEmpty()) {
				map.put(key, value);
			}
		}
		return map;
	}

	protected String[] getPipeArray(String values) {
		return (values != null) ? values.split(SEP_REGEX) : null;
	}

	public void setValue(Map<String, String> map, String key, StringBuilder sb) {
		sb.append(key);
		sb.append(EQ);
		String s = map.get(key);
		if (s != null) {
			s = s.replaceAll(SEP_REGEX, "/");
			sb.append(s);
		}
		sb.append(SEP_VALUE);
	}

	public void setLastValue(Map<String, String> map, String key,
			StringBuilder sb) {
		setValue(map, key, sb);
		sb.deleteCharAt(sb.length() - 1);
	}

	public Date parseDate(String s) {
		if (s == null) {
			return null;
		}
		try {
			return DATE_FORMATTER.parse(s);
		} catch (ParseException e) {
			throw new TxtException("Data inválida:" + s, e);
		}
	}

	public void assigin(Map<String, String> map, ContextElement element) {
		// String label = map.get(FIELD_LABEL);
		// String tags = map.get(FIELD_TAGS);
	}

	public Boolean parseBoolean(String s) {
		if (s == null) {
			return null;
		}
		return Boolean.parseBoolean(s);
	}

	public int parseInt(String s) {
		if (s == null) {
			return 0;
		}
		return Integer.parseInt(s);
	}

	public String asString(Integer i) {
		if (i == null) {
			return null;
		}
		return Integer.toString(i);
	}

	public String asString(Date date) {
		if (date == null) {
			return null;
		}
		return DATE_FORMATTER.format(date);
	}

	public String asString(Boolean b) {
		if (b == null) {
			return null;
		}
		return Boolean.toString(b);
	}

}
