package br.net.ubre.framework;

/**
 * Interface que representa um objeto que contém um ID e um Label.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 23/06/2015
 * 
 */
public interface IdLabel {

	String getId();

	void setId(String id);

	String getLabel();

	void setLabel(String label);

	/**
	 * retorna uma representação do Objeto através da concatenação de seu ID com
	 * seu Label. Descrição longa.
	 * 
	 * @return ID + Label.
	 */
	String asIdLabel();
}
