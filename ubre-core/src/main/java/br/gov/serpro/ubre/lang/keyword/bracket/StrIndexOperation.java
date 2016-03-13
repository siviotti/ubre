package br.gov.serpro.ubre.lang.keyword.bracket;

import static br.gov.serpro.ubre.lang.statement.StatementType.NUMERIC;
import static br.gov.serpro.ubre.lang.statement.StatementType.STRING;

import br.gov.serpro.ubre.data.container.DataContainer;
import br.gov.serpro.ubre.lang.LangError;
import br.gov.serpro.ubre.lang.LangException;
import br.gov.serpro.ubre.lang.data.literal.StringStatement;
import br.gov.serpro.ubre.lang.operation.Operation;
import br.gov.serpro.ubre.lang.statement.Statement;
import br.gov.serpro.ubre.lang.statement.StatementType;

public class StrIndexOperation implements Operation{

	public Statement perform(DataContainer container, Statement left, Statement right) {
		
		String str = (String) left.result(container);
		Integer index = (Integer) right.result(container); 
		Integer b1Size = str.length();
		
		int p1 = index.intValue();
		int tmpP1 = p1;
		
		if  (p1<0){
			p1 = b1Size+p1;
		}
		
		if (p1<0 || p1>= b1Size){
			throw new LangException(LangError.E41, "Valores inválidos: ["+tmpP1+"]");
		}
		
		return new StringStatement(str.substring(p1,p1+1));
	}		
		

	public StatementType leftType() {
		return STRING;
	}
	
	public StatementType rightType() {
		return NUMERIC;
	}

	public StatementType resultType() {
		return STRING;
	}
	public int precedence() {
		return 1;
	}

}
