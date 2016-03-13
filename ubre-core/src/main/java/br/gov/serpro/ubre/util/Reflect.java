package br.gov.serpro.ubre.util;

import java.util.List;

import br.gov.serpro.ubre.behave.Executable;
import br.gov.serpro.ubre.exception.CTDException;
import br.gov.serpro.ubre.framework.rule.RuleBody;

/**
 * Classe utilitária paraReflection.
 * 
 * @author Douglas Siviotti (073.116.317-69).
 * @since 08/10/2015
 */
public final class Reflect {

	private static final String REFLECT_CLASS_NOT_FOUND = "Classe não encontrada no Classpath:";
	private static final String REFLECT_RULE_BODY_NOT_FOUND = "Não foi encontrada nenhuma implementação (regra de código) para a regra ";
	private static final String REFLECT_CONV_THE_CLASS = "A classe ";
	private static final String REFLECT_DOESNT_EXTENDS = " não estende ";

	private Reflect() {

	}

	/**
	 * Procura uma classe anotada com <code>RuleBody</code> e tenta instanciá-la
	 * como <code>Executable</code>. Se não for encontrada nenhuma classe será
	 * disparado um erro.
	 * 
	 * @param ruleId
	 *            O ID da regra.
	 * @return A instância de <code>Executable</code> da classe anotada com
	 *         <code>RuleBody</code> se houver.
	 */
	public static Executable findRuleBody(String ruleId) {
		ResourceList resourceList = new ResourceList();
		List<Class<?>> list = resourceList.listTypes(true, false, false, false);
		for (Class<?> clazz : list) {
			if (clazz.isAnnotationPresent(RuleBody.class)) {
				RuleBody ruleBody = clazz.getAnnotation(RuleBody.class);
				if (ruleBody.ruleId().equals(ruleId)) {
					return createExecutable(clazz);
				}
			}
		}
		throw new CTDException(REFLECT_RULE_BODY_NOT_FOUND + ruleId);
	}

	/**
	 * Cria uma instância de <code>Executable</code> a partir de uma classe.
	 * 
	 * @param clazz
	 *            A classe que supostamente implementa <code>Execitable</code>.
	 * @return A instância de Executable.
	 */
	public static Executable createExecutable(Class<?> clazz) {
		if (!Executable.class.isAssignableFrom(clazz)) {
			throw new CTDException(REFLECT_CONV_THE_CLASS + clazz
					+ REFLECT_DOESNT_EXTENDS + Executable.class);
		}
		try {
			return (Executable) clazz.newInstance();
		} catch (InstantiationException e) {
			throw new CTDException(e);
		} catch (IllegalAccessException e) {
			throw new CTDException(e);
		}
	}

	/**
	 * Cria um <code>Executable</code> a pertir do nome completo da classe.
	 * 
	 * @param className
	 *            O nome completo da classe.
	 * @return A instância de <code>Executable.</code>
	 */
	public static Executable createExecutable(String className) {
		try {
			Class<?> clazz = Class.forName(className);
			return createExecutable(clazz);
		} catch (ClassNotFoundException e) {
			throw new CTDException(REFLECT_CLASS_NOT_FOUND + className, e);
		}
	}

	/**
	 * Teste se uma subclasse é herdeira de uma superclasse.
	 * 
	 * @param superclass
	 *            A Superclasse (Mãe).
	 * @param subclass
	 *            A subclasse (Filha).
	 */
	public static void testAssignableFrom(Class<?> superclass, Class<?> subclass) {
		if (!superclass.isAssignableFrom(subclass)) {
			throw new CTDException(REFLECT_CONV_THE_CLASS
					+ subclass.getCanonicalName() + REFLECT_DOESNT_EXTENDS
					+ superclass.getCanonicalName());
		}
	}

	/**
	 * Instancia um objeto a partir de uma classe.
	 * 
	 * @param clazz
	 *            A classe.
	 * @return A instância do objeto.
	 */
	public static Object newInstance(Class<?> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new CTDException(e);
		} catch (IllegalAccessException e) {
			throw new CTDException(e);
		}
	}

	/**
	 * Obtém uma classe a partir de seu classname (nome completo).
	 * 
	 * @param className
	 *            O nome completo.
	 * @return A instância de CLass.
	 */
	public static Class<?> classForName(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new CTDException(REFLECT_CLASS_NOT_FOUND + className, e);
		}
	}

}
