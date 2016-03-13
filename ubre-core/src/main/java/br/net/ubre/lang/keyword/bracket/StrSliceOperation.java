package br.net.ubre.lang.keyword.bracket;

import br.net.ubre.data.container.DataContainer;
import br.net.ubre.lang.LangError;
import br.net.ubre.lang.LangException;
import br.net.ubre.lang.data.PairStatement;
import br.net.ubre.lang.data.literal.StringStatement;
import br.net.ubre.lang.operation.Operation;
import br.net.ubre.lang.statement.Statement;
import br.net.ubre.lang.statement.StatementType;

public class StrSliceOperation implements Operation{

	public Statement perform(DataContainer container, Statement left, Statement right) {
		
		String str = (String) left.result(container);
		PairStatement pair = (PairStatement) right.perform(container); 
		Integer b1Size = str.length();
		
		int p1 = ((Integer)pair.getLeft().result(container)).intValue();
		int p2 = ((Integer)pair.getRight().result(container)).intValue();
		int tmpP1 = p1;
		int tmpP2 = p2;
		
		
		if  (p1<0){ 
			p1 = b1Size+p1;
		}
		
		/*O zero no fim será interpretado como size ** p2<1 ** (p2<0)
		 * [0:0] -> tudo */
		if  (p2<1){ 
			p2 = b1Size+p2;
		}
		
		if (p1>p2 || p2> b1Size || p1<0){
			throw new LangException(LangError.E41, "Valores inválidos: ["+tmpP1+":"+tmpP2+"]");
		}  
		
		return new StringStatement(str.substring(p1, p2));
	}		
		

	public StatementType leftType() {
		return StatementType.STRING;
	}
	
	public StatementType rightType() {
		return StatementType.PAIR;
	}

	public StatementType resultType() {
		return StatementType.STRING;
	}

	public int precedence() {
		return 1;
	}

}
