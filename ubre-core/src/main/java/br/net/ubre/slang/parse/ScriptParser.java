package br.net.ubre.slang.parse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.net.ubre.lang.Lang;
import br.net.ubre.lang.LangError;
import br.net.ubre.lang.parse.StringExtractor;
import br.net.ubre.lang.parse.StringMap;
import br.net.ubre.slang.SLangException;
import br.net.ubre.slang.Syntagma;
import br.net.ubre.slang.code.CodeLine;

/**
 * Parser de uma linha de um script.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 12/10/2015
 */
public class ScriptParser {

	private Lang lang;
	private Syntagma syntagma;
	private ActionLineParser actionParser = new ActionLineParser();
	private ControlLineParser controlParser = new ControlLineParser();
	private StringExtractor extractor = new StringExtractor();
	private StringMap stringMap;

	public ScriptParser(Lang lang, Syntagma framework) {
		this.lang = lang;
		this.syntagma = framework;
		stringMap = lang.getCompiler().getParser().getStringMap();
	}

	public List<CodeLine> parse(List<String> textLines) {
		List<CodeLine> codeLines = new ArrayList<CodeLine>();
		int number = 0;
		for (String textLine : textLines) {
			number++; // Comentários e linhas vazias contam
			if (syntagma.isCommentLine(textLine)) {
				continue;
			}
			if (textLine == null || textLine.trim().isEmpty()) {
				continue;
			}
			codeLines.add(parseLine(number, textLine));
		}
		return codeLines;
	}

	private CodeLine parseLine(int number, String textLine) {
		String noStringLine;
		try {
			noStringLine = extractor.extract(textLine, stringMap);
		} catch (ParseException e) {
			throw new SLangException(LangError.E10, textLine, e);
		}
		SourceLine sourceLine = new SourceLine(syntagma, number, noStringLine);
		if (sourceLine.getKeyword().isAction()) {
			return actionParser.parse(lang, syntagma, sourceLine);
		} else if (sourceLine.getKeyword().isControl()) {
			return controlParser.parse(lang, syntagma, sourceLine);
		}
		throw new SLangException(LangError.E10, sourceLine.toSource());
	}

	// GET / SET
	public Syntagma getSyntagma() {
		return syntagma;
	}

}
