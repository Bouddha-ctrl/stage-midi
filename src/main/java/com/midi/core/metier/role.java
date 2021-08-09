package com.midi.core.metier;

import java.util.ArrayList;
import java.util.List;

public class role {
	private String nom;
	private evaluation eval;
	private List<critere> criteres;
	
	
	public role() {
	}
	public role(String nom) {
		this.nom = nom;
		criteres = new ArrayList<critere>();
	}
	
	public void addCritere(critere crit) {
		if (criteres == null) criteres = new ArrayList<critere>();
		criteres.add(crit);
		crit.setRole(this);
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<critere> getCriteres() {
		return criteres;
	}
	public void setCriteres(List<critere> criteres) {
		this.criteres = criteres;
	}
	public evaluation getEval() {
		return eval;
	}
	public void setEval(evaluation eval) {
		this.eval = eval;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		role other = (role) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "objectif [nom=" + nom + ", criteres=" + criteres + "]";
	}
	
	
	
	public int scritCount() {
		int count = 0;
		for (critere critere : criteres) {
			count+=critere.getScriteres().size();
		}
		return count;
	}
	
	
}
