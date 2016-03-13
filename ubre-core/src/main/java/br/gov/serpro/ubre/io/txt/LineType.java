package br.gov.serpro.ubre.io.txt;

import java.util.regex.Pattern;

import br.gov.serpro.ubre.io.txt.converter.ConstConverter;
import br.gov.serpro.ubre.io.txt.converter.ContextConverter;
import br.gov.serpro.ubre.io.txt.converter.DomainConverter;
import br.gov.serpro.ubre.io.txt.converter.DomainItemConverter;
import br.gov.serpro.ubre.io.txt.converter.DomainMetaConverter;
import br.gov.serpro.ubre.io.txt.converter.EventConverter;
import br.gov.serpro.ubre.io.txt.converter.LineConverter;
import br.gov.serpro.ubre.io.txt.converter.MessageConverter;
import br.gov.serpro.ubre.io.txt.converter.MetadataConverter;
import br.gov.serpro.ubre.io.txt.converter.ProcessConverter;
import br.gov.serpro.ubre.io.txt.converter.ScopeConverter;
import br.gov.serpro.ubre.io.txt.converter.VarConverter;
import br.gov.serpro.ubre.io.txt.converter.rule.RuleConverter;

/**
 * Tipos de linha em um arquivo TXT.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 31/03/2015
 * 
 */
public enum LineType {

	/** Contexto */
	CONTEXT("context", null, null, null, new ContextConverter()),
	/** Contexto */
	CONTEXT_PROP(CONTEXT.getType(), "", ".*", CONTEXT, null),
	/** */
	CONSTANT("const", "(TEXT|INTEGER|DECIMAL|BOOLEAN|DATE)", null, null,
			new ConstConverter()),
	/** */
	VARIABLE("var", "(TEXT|INTEGER|DECIMAL|BOOLEAN|DATE)", null, null,
			new VarConverter()),
	/** */
	DOMAIN("domain", null, null, null, new DomainConverter()),
	/** */
	DOMAIN_META(DOMAIN.getType(), "meta", null, DOMAIN,
			new DomainMetaConverter()),
	/** */
	DOMAIN_ITEM(DOMAIN.getType(), "", ".*", DOMAIN, new DomainItemConverter()),
	/** */
	METADATA("meta", null, null, null, new MetadataConverter()),
	/** */
	SCOPE("scope", null, null, null, new ScopeConverter()),
	/** */
	SCOPE_FIELDS(SCOPE.getType(), "fields", null, SCOPE, new ScopeConverter()),
	/** */
	SCOPE_VARIABLES(SCOPE.getType(), "variables", null, SCOPE,
			new ScopeConverter()),
	/** */
	SCOPE_LISTS(SCOPE.getType(), "lists", null, SCOPE, new ScopeConverter()),
	/** */
	MESSAGE("message", null, null, null, new MessageConverter()),
	/** */
	RULE("rule", null, null, null, new RuleConverter()),
	/** */
	CODE(RULE.getType(), "code", null, RULE, null),
	/** */
	PROCESS("process", null, null, null, new ProcessConverter()),
	/** */
	EVENT(PROCESS.getType(), "", ".*", PROCESS, new EventConverter()), ;

	private String type;
	private String item;
	private Pattern itemPattern;
	private LineType parent;
	private LineConverter converter;

	private LineType(String tag, String item, String itemRegex,
			LineType parent, LineConverter converter) {
		this.type = tag;
		this.item = item;
		this.converter = converter;
		this.parent = parent;
		if (item != null) {
			if (itemRegex != null) {
				itemPattern = Pattern.compile(item + itemRegex);
			} else {
				itemPattern = Pattern.compile(item);
			}
		}
	}

	/**
	 * @return the tag
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the converter
	 */
	public LineConverter getConverter() {
		return converter;
	}

	public boolean useItem() {
		return item != null;
	}

	/**
	 * retorna a instância a partir da tag type e da tag item (quando for o
	 * caso).
	 * 
	 * @param typeStr
	 *            O tipo
	 * @param itemStr
	 *            O item opcional
	 * @return
	 */
	public static LineType get(String typeStr, String itemStr) {
		for (LineType lineType : values()) {
			if (lineType.type.equals(typeStr)) {
				if (itemStr == null) {
					return lineType;
				}
				if (lineType.itemMatches(itemStr)) {
					return lineType;
				}
			}
		}
		return null;
	}

	public boolean itemMatches(String itemStr) {
		if (item == null) {
			return false;
		}
		return itemPattern.matcher(itemStr).matches();
	}

	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}

	public boolean isChild() {
		return parent != null;
	}

	/**
	 * @return the parent
	 */
	public LineType getParent() {
		return parent;
	}

}
