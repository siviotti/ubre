package br.gov.serpro.ubre.slang.script;

import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.slang.code.CodeLine;

/**
 * Exceção usada exclusivamente para execução de um Script. Esta exceção usa a
 * linha que gerou o erro como parâmetro da mensagem de erro.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 12/10/2015
 */
public class ScriptException extends CTDException {

	private static final String SCRIPT_EXCEPTION_MSG = "Erro na excução da linha número %d [%s]. Mensagem de erro:'%s'";

	private static final long serialVersionUID = 1L;

	public ScriptException(CodeLine codeLine, CTDException e) {
		super(String.format(SCRIPT_EXCEPTION_MSG, codeLine.getSourceLine()
				.getNumber(), codeLine.getSourceLine().toSource(), e
				.getMessage()), e);
	}

}
