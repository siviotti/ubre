package br.net.ubre.framework.rule;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Interface que serve para marcar as classes que devem implementar a interface
 * <code>Executable</code> e ser o corpo (Body) de uma Regra de Código.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RuleBody {

	String ruleId();
}
