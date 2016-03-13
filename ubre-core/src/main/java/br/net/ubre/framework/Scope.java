package br.net.ubre.framework;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.net.ubre.util.Debuggable;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 26/05/2015
 * 
 */
public class Scope extends ContextElement implements Debuggable{

	private List<String> varTokens;
	private List<Metadata> metadatas;
	private List<Scope> scopes = new ArrayList<Scope>();

	public Scope(String id, String label, List<Metadata> metadatas, List<String> varTokens,
			List<Scope> scopes, Date begin, Date end, String tags) {
		super(id, label, begin, end, tags);
		this.metadatas = new ArrayList<Metadata>(metadatas);
		this.varTokens = new ArrayList<String>(varTokens);
		if (scopes != null) {
			this.scopes = new ArrayList<Scope>(scopes);
		}
	}

	public String toDebug() {
		StringBuilder sb = new StringBuilder();
		sb.append("  SCOPE " + getId() + " " + getLabel());
		for (Metadata metadata : metadatas) {
			sb.append("\n  " + metadata.toDebug());
		}
		for (Scope scope : scopes) {
			sb.append("\n  " + scope.toDebug());
		}
		return sb.toString();
	}

	/**
	 * @return the metadatas
	 */
	public List<Metadata> getMetadatas() {
		return new ArrayList<Metadata>(metadatas);
	}

	/**
	 * @return the scopes
	 */
	public List<Scope> getScopes() {
		return new ArrayList<Scope>(scopes);
	}

	public List<String> getVarTokens() {
		return new ArrayList<String>(varTokens);
	}

}
